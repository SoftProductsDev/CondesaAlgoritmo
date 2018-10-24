package condesaGUI;

import DbController.HibernateCrud;
import condeso.Condeso;
import horario.Turnos;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lalo.Disponibilidad;
import lalo.Parser;
import tiendas.Tiendas;
import lalo.GM;

import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class NuevoHorarioGUI extends Application implements Initializable {
    @FXML private TableView<Condeso> condesosTable;
    @FXML private TableView<Tiendas> tiendasTable;
    @FXML private TableColumn<Condeso, String> condesoName;
    @FXML private  TableColumn<Tiendas, String> tiendasName;
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


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
           // lalo lalo = new lalo();
           // lalo.start();
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

    public void iniciarClicked(ActionEvent actionEvent) {

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

    public void mesDeInicioClicked(ActionEvent actionEvent){
        LocalDate date = LocalDate.of(2018, 11, 1); // momentaneamente
        ArrayList<Turnos> turnos = new ArrayList<>();
        Set<GM> gms = Parser.parseGMs("GMs.txt", turnos,date);
        List<Condeso> allGMs = new LinkedList<>();
        for(GM gm: gms){
            int id = gm.getId();
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
        String filename = "disponibilidad2.txt";
        Set<Disponibilidad> horario = Parser.parse2(filename);
        List<Condeso> foundCondesos = new LinkedList<>();
        for(Disponibilidad e: horario){
            e.Print();
            System.out.println();
        }
        for(Disponibilidad condeso:horario){
            int id = condeso.getId();
            for(Condeso condeso1:allCondesos){
                if(condeso1.getId() == id){
                    foundCondesos.add(condeso1) ;
                }
            }
        }
        condesoName.setCellValueFactory(new PropertyValueFactory<Condeso, String>("nombre"));
        condesoID.setCellValueFactory(new PropertyValueFactory<Condeso, Long>("Id"));
        condesoAbreviación.setCellValueFactory(new PropertyValueFactory<Condeso, String>("abreviacion"));
        tiendasName.setCellValueFactory(new PropertyValueFactory<Tiendas, String>("nombre"));
        condesosTable.getItems().setAll(foundCondesos);
        tiendasTable.getItems().setAll( HibernateCrud.GetAllDTOTiendas());
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
