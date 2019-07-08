package condesaGUI;


import DbController.CrudOperations;
import DbController.WebApiClient;
import condeso.Condeso;
import horario.Plantillas;
import horario.Turnos;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import lalo.Disponibilidad;
import lalo.Parser;
import lalo.lalo;
import tiendas.Tiendas;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class NuevoHorarioGUI extends Application implements Initializable {
    @FXML private TableView<Condeso> condesosTable;
    @FXML private TableView<Tiendas> tiendasTable;
    @FXML private TableColumn<Condeso, String> condesoName;
    @FXML private TableColumn<Tiendas, String> tiendasName;
    @FXML private TableColumn<Tiendas, Plantillas> tiendasPlantilla;
    @FXML private TableColumn<Condeso, Long> condesoID;
    @FXML private TableColumn<Condeso, String> condesoAbreviación;
    @FXML private Button iniciarButton;
    @FXML private Button eliminarButton;
    @FXML private DatePicker fechaDeCierre;
    @FXML private ListView<LocalDate> fechaDeCierreList;
    @FXML private TableView<Condeso> gmsTable;
    @FXML private TableColumn<Condeso, String> gmName;
    @FXML private TableColumn<Condeso, Long> gmID;
    @FXML private TableColumn<Condeso, String> gmAbreviación;
    @FXML private DatePicker mesDeInicioPicker;
    @FXML private Button importarButton;
    @FXML private Button regresarButton;
    @FXML private RadioButton sinChecarNivel;


    private ObservableList<LocalDate> diasDeCierre = null;
    private List<LocalDate> dias = new ArrayList<>();
    private List<Condeso> allCondesos ;
    private Set<Condeso> foundCondesos = new HashSet<>();
    private List<Tiendas> allTiendas;
    private Set<Disponibilidad> horario;
    private Set<Condeso> gms;
    private LocalDate date;
    private String filename;
    private String filename2;
    private List<Condeso> allGMs = new LinkedList<>();
    private ArrayList<Turnos> turnosEncargado = new ArrayList<>();
    HashMap<Long, Integer[][]> turnosExtras = new HashMap<>();
    private Set<Disponibilidad> fijos;

    private HashMap<Integer, Integer[][]> disponibilidad;
    private LocalDate fecha;
    private CrudOperations webApi;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    }

    public void setInitialValues(List<Condeso> condesos, List<Tiendas> tiendas){
        webApi = new WebApiClient();
        allCondesos = condesos;
        allTiendas = tiendas;
        condesoName.setCellValueFactory(new PropertyValueFactory<Condeso, String>("nombre"));
        condesoID.setCellValueFactory(new PropertyValueFactory<Condeso, Long>("Id"));
        condesoAbreviación.setCellValueFactory(new PropertyValueFactory<Condeso, String>("abreviacion"));
        tiendasName.setCellValueFactory(new PropertyValueFactory<Tiendas, String>("nombre"));
        tiendasPlantilla.setCellValueFactory(new PropertyValueFactory<Tiendas, Plantillas>("plantilla"));
        condesosTable.getItems().setAll(foundCondesos);
        tiendasTable.getItems().setAll(allTiendas);
        tiendasTable.getSelectionModel().selectedItemProperty().addListener((obs, newSelection,
            oldSelection) -> {
            loadFechasDeCierreUpdate();
        });
    }

    public HashMap<Integer, Integer[][]> changeSetToHashMap(Set<Disponibilidad> disponibilidad){
        HashMap<Integer, Integer[][]> hasmapDisponibilidad = new HashMap<>();
        for(Disponibilidad disponibilidadUnCondeso:disponibilidad){
            hasmapDisponibilidad.put(disponibilidadUnCondeso.getId(),disponibilidadUnCondeso.getDisponibilidad());
        }
        return hasmapDisponibilidad;
    }

    public void regresarClicked(ActionEvent actionEvent) throws Exception {
        CloseOpenWindow("/frontGUI.fxml");
    }

    public void eliminarClicked(ActionEvent actionEvent) {
        LocalDate date = fechaDeCierreList.getSelectionModel().getSelectedItem();
        Tiendas tienda =  tiendasTable.getSelectionModel().getSelectedItem();
        if(date != null && tienda != null){
            dias = tienda.getDiasDeCierre();
            dias.remove(date);
            diasDeCierre = FXCollections.observableArrayList(dias);
            fechaDeCierreList.setItems(diasDeCierre);
            tienda.setDiasDeCierre(dias);

        }
    }

    public void iniciarClicked(ActionEvent actionEvent) throws Exception {
        if(fecha !=  null && horario != null){
            webApi.DeleteMultipleDays(fecha);
            disponibilidad = changeSetToHashMap(horario);
            Set<Tiendas> tiendasALL2 = new HashSet<>();
            tiendasALL2.addAll(allTiendas);
            lalo lalo = new lalo(gms, turnosEncargado, foundCondesos, tiendasALL2, disponibilidad, fecha,turnosExtras, sinChecarNivel.isSelected(),
                    fijos);

            var wd = new WorkIndicatorDialog(this.iniciarButton.getScene().getWindow(), "Creando horarios...");

            wd.addTaskEndNotification(result -> {
                System.out.println(result);
                //wd=null; // don't keep the object, cleanup
                try {
                    allTiendas = lalo.getTiendas();
                    CloseOpenWindow("/frontGUI.fxml");
                    float promedio = lalo.getPromedio();
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("PORCENTAJE");
                    alert.setHeaderText("El procentaje de turnos asignados es: \n" + promedio);
                    alert.setContentText(null);
                    alert.showAndWait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            wd.exec("123", inputParam -> {
                try {
                    lalo.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return (1);
            });
            

            //frame.setVisible(false);
        }

    }

    private void CloseOpenWindow(String filename) throws Exception{
        ((Stage)iniciarButton.getScene().getWindow()).close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/frontGUI.fxml"));
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(root, Screen.getPrimary().getVisualBounds().getWidth(),
            Screen.getPrimary().getVisualBounds().getMaxY()));
        stage.show();
        FrontGUI nuevoHorarioGUI = fxmlLoader.getController();
        nuevoHorarioGUI.setInitialValues(FXCollections.observableList(allCondesos),
            FXCollections.observableList(allTiendas));
    }


    public void fechasDeCierreClicked(ActionEvent actionEvent){
        LocalDate date = fechaDeCierre.getValue();
        Tiendas tienda = tiendasTable.getSelectionModel().getSelectedItem();
        if(date != null && tienda != null){
            dias = tienda.getDiasDeCierre();
            dias.add(date);
            diasDeCierre = FXCollections.observableArrayList(dias);
            fechaDeCierreList.setItems(diasDeCierre);
            tienda.setDiasDeCierre(dias);
        }
    }

    public void importarClicked(ActionEvent actionEvent){
        if(date != null) {
            final Stage stage = new Stage();
            stage.setTitle("Disponibilidad condesos");
            final FileChooser chooser = new FileChooser();
            chooser.setTitle("Disponibilidad Condesos");

            Alert info1 = new Alert(Alert.AlertType.INFORMATION);
            info1.setTitle("Archivo a seleccionar");
            info1.setHeaderText("Disponibilidad de condesos");
            info1.setContentText(null);
            info1.showAndWait();

            File file = chooser.showOpenDialog(stage);
            if(file != null){
                filename = file.getAbsolutePath();
                horario = Parser.parse2(filename);
                for (Disponibilidad condeso : horario) {
                    int id = condeso.getId();
                    for (Condeso condeso1 : allCondesos) {
                        if (condeso1.getId() == id) {
                            condeso1.setMaxHours(condeso.getMax());
                            condeso1.setMinHours(condeso.getMin());
                            //condeso1.checkMaxMin();
                            foundCondesos.add(condeso1);
                        }
                    }
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("AVISO!");
                alert.setHeaderText("No se selecciono documento de disponibilidad de condesos,\n las tablas estaran vacias! ");
                alert.setContentText(null);
                alert.showAndWait();
            }

            final Stage stage2 = new Stage();
            stage.setTitle("Condesos fijos");
            final FileChooser fc1 = new FileChooser();
            fc1.setTitle("Tabla de condesos fijos");

            Alert info2 = new Alert(Alert.AlertType.INFORMATION);
            info2.setTitle("Archivo a seleccionar");
            info2.setHeaderText("Tabla de condesos fijos");
            info2.setContentText(null);
            info2.showAndWait();

            File file3 = fc1.showOpenDialog(stage);
            if(file3 != null){
                fijos = Parser.parseFijos(file3.getAbsolutePath());
                for (Disponibilidad condeso : fijos) {
                    int id = condeso.getId();
                    for (Condeso condeso1 : allCondesos) {
                        if (condeso1.getId() == id) {
                            condeso1.setMaxHours(condeso.getMax());
                            condeso1.setMinHours(condeso.getMin());
                            //condeso1.checkMaxMin();
                            foundCondesos.add(condeso1);
                        }
                    }
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("AVISO!");
                alert.setHeaderText("No se selecciono documento de condesos fijos");
                alert.setContentText(null);
                alert.showAndWait();
            }


            final Stage stage1 = new Stage();
            stage1.setTitle("Rotación GMs");
            final FileChooser fc2 = new FileChooser();

            Alert info3 = new Alert(Alert.AlertType.INFORMATION);
            info3.setTitle("Archivo a seleccionar");
            info3.setHeaderText("Rotación de GM's");
            info3.setContentText(null);
            info3.showAndWait();

            File file2 = fc2.showOpenDialog(stage1);
            fc2.setTitle("Rotación GMs");
            if (file2 != null) {
                filename2 = file2.getAbsolutePath();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("AVISO!");
                alert.setHeaderText("No se selecciono documento de disponibilidad de GM's,\n las tablas estaran vacias! ");
                alert.setContentText(null);
                alert.showAndWait();
            }
            if (filename2 != null) {
                gms = Parser.parseGMs(filename2, turnosEncargado, date, turnosExtras);
                for (Condeso gm : gms) {
                    long id = gm.getId();
                    for (Condeso condeso : allCondesos) {
                        if (condeso.getId() == id) {
                            allGMs.add(condeso);
                        }
                    }
                }
                gmName.setCellValueFactory(new PropertyValueFactory<Condeso, String>("nombre"));
                gmID.setCellValueFactory(new PropertyValueFactory<Condeso, Long>("Id"));
                gmAbreviación.setCellValueFactory(new PropertyValueFactory<Condeso, String>("abreviacion"));
                gmsTable.getItems().setAll(allGMs);
            }

            condesosTable.getItems().addAll(foundCondesos);
            tiendasTable.getItems().setAll(allTiendas);
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("AVISO!");
            alert.setHeaderText("No se selecciono fecha de inicio del mes! ");
            alert.setContentText(null);
            alert.showAndWait();
        }
    }

    public void mesDeInicioClicked(ActionEvent actionEvent){
        date = mesDeInicioPicker.getValue();
        fecha = date;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    private void loadFechasDeCierreUpdate() {
        Tiendas tienda = tiendasTable.getSelectionModel().getSelectedItem();
        dias = tienda.getDiasDeCierre();
        diasDeCierre = FXCollections.observableArrayList(dias);
        fechaDeCierreList.setItems(diasDeCierre);
    }

    public void importarNube(ActionEvent actionEvent) {
        if(fecha == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("AVISO!");
            alert.setHeaderText("No se selecciono fecha de inicio del mes! ");
            alert.setContentText(null);
            alert.showAndWait();
        }
        else {
            horario = webApi.GetAvailabilities(fecha.getMonth());
            foundCondesos.clear();
            for (Disponibilidad disponibilidad : horario) {
                disponibilidad.getAvailableDaysAsArray();
                long id = disponibilidad.getCondesoId();
                for (Condeso condeso1 : allCondesos) {
                    if (condeso1.getId() == id) {
                        condeso1.setMaxHours(disponibilidad.getMax());
                        condeso1.setMinHours(disponibilidad.getMin());
                        //condeso1.checkMaxMin();
                        foundCondesos.add(condeso1);}
                }
            }
            condesosTable.getItems().clear();
            condesosTable.getItems().addAll(foundCondesos);
        }
    }
}
