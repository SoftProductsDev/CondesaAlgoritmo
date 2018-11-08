package condesaGUI;


import DbController.HibernateCrud;
import condeso.Condeso;
import horario.Plantillas;
import horario.Turnos;
import javafx.application.Application;
import javafx.application.Platform;
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
import javafx.stage.Screen;
import javafx.stage.Stage;
import lalo.Disponibilidad;
import lalo.Parser;
import lalo.lalo;
import tiendas.Tiendas;
import condesaGUI.FrontGUI;

import javax.swing.*;
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


    private ObservableList<LocalDate> diasDeCierre = null;
    private List<LocalDate> dias = new ArrayList<>();
    private List<Condeso> allCondesos = DbController.HibernateCrud.GetAllCondesos();
    private Set<Condeso> foundCondesos = new HashSet<>();
    private List<Tiendas> allTiendas = DbController.HibernateCrud.GetAllTiendas();
    private Set<Disponibilidad> horario;
    private Set<Condeso> gms;
    private LocalDate date;
    private String filename2;
    private List<Condeso> allGMs = new LinkedList<>();
    private ArrayList<Turnos> turnosEncargado = new ArrayList<>();
    HashMap<Long, Integer[][]> turnosExtras = new HashMap<>();



    private HashMap<Integer, Integer[][]> disponibilidad;
    private LocalDate fecha;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    public HashMap<Integer, Integer[][]> changeSetToHashMap(Set<Disponibilidad> disponibilidad){
        HashMap<Integer, Integer[][]> hasmapDisponibilidad = new HashMap<>();
        for(Disponibilidad disponibilidadUnCondeso:disponibilidad){
            hasmapDisponibilidad.put(disponibilidadUnCondeso.getId(),disponibilidadUnCondeso.getDisponibilidad());
        }
        return hasmapDisponibilidad;
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
            HibernateCrud.UpdateTienda(tienda);
        }
    }

    public void iniciarClicked(ActionEvent actionEvent) throws Exception {
        disponibilidad = changeSetToHashMap(horario);
        if(fecha !=  null){
            Set<Tiendas> tiendasALL2 = new HashSet<>();
            tiendasALL2.addAll(allTiendas);
            lalo lalo = new lalo(gms, turnosEncargado, foundCondesos, tiendasALL2, disponibilidad, fecha,turnosExtras);
            lalo.start();
            //CloseOpenWindow("/frontGUI.fxml");
        }
    }

    private void CloseOpenWindow(String filename) throws Exception{
        ((Stage)iniciarButton.getScene().getWindow()).close();
        String sceneFile = filename;
        Parent root = null;
        URL url  = null;
        try {
            url  = getClass().getResource( sceneFile );
            root = FXMLLoader.load( url );
            Stage stage = new Stage();
            stage.setScene(new Scene(root, Screen.getPrimary().getVisualBounds().getWidth(),
                    Screen.getPrimary().getVisualBounds().getMaxY()));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
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
            HibernateCrud.UpdateTienda(tienda);
        }
    }

    public void mesDeInicioClicked(ActionEvent actionEvent){
        date = mesDeInicioPicker.getValue();
        fecha = date;
        gms = Parser.parseGMs(filename2, turnosEncargado,date, turnosExtras);
        for(Condeso gm: gms){
            long id = gm.getId();
            for(Condeso condeso : allCondesos){
                if(condeso.getId() == id){
                    allGMs.add(condeso);
                }
            }
        }
        gmName.setCellValueFactory(new PropertyValueFactory<Condeso, String>("nombre"));
        gmID.setCellValueFactory(new PropertyValueFactory<Condeso, Long>("Id"));
        gmAbreviación.setCellValueFactory(new PropertyValueFactory<Condeso, String>("abreviacion"));
        gmsTable.getItems().setAll(allGMs);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        final JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(fc);
        String filePath = null;
        if(returnVal == JFileChooser.APPROVE_OPTION){
            filePath = fc.getSelectedFile().getAbsolutePath();
            String filename = filePath;
            horario = Parser.parse2(filename);
            for(Disponibilidad e: horario){
                e.Print();
                System.out.println();
            }
            for(Disponibilidad condeso:horario){
                int id = condeso.getId();
                for(Condeso condeso1:allCondesos){
                    if(condeso1.getId() == id){
                        condeso1.setMaxHours(condeso.getMax());
                        condeso1.setMinHours(condeso.getMin());
                        //condeso1.checkMaxMin();
                        foundCondesos.add(condeso1) ;
                    }
                }
            }
        }else{
            String message = "No se selecciono ninguna\n direccion a un documento\n de disponibilidad condesos, \n las tablas estaran vacias! ";
            JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                    JOptionPane.ERROR_MESSAGE);
        }
        final JFileChooser fc2 = new JFileChooser();
        int returnVal2 = fc2.showOpenDialog(fc2);
        String filePath2 = null;
        if(returnVal2 == JFileChooser.APPROVE_OPTION) {
            filePath2 = fc2.getSelectedFile().getAbsolutePath();
            filename2 = filePath2;
        }else{
            String message = "No se selecciono ninguna\n direccion a un documento\n de disponibilidad GMs, \n las tablas estaran vacias! ";
            JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                    JOptionPane.ERROR_MESSAGE);
        }
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

    private void loadFechasDeCierreUpdate() {
        Tiendas tienda = tiendasTable.getSelectionModel().getSelectedItem();
        dias = tienda.getDiasDeCierre();
        diasDeCierre = FXCollections.observableArrayList(dias);
        fechaDeCierreList.setItems(diasDeCierre);
    }
}
