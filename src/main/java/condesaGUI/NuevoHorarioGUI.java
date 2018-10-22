package condesaGUI;

import DbController.HibernateCrud;
import DbModel.Condeso;
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
import lalo.lalo;
import tiendas.Tiendas;

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
    @FXML private TableColumn<Condeso, Integer> gmID;
    @FXML private TableColumn<Condeso, String> gmAbreviación;


    private ObservableList<LocalDate> diasDeCierre = null;
    private List<LocalDate> dias = new ArrayList<>();


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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String filename = "disponibilidad2.txt";
        Set<Disponibilidad> horario = Parser.parse2(filename);
        //Set<GM> gms = Parser.parse
        List<Condeso> allCondesos = DbController.HibernateCrud.GetAllCondesos();
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
        condesosTable.getItems().setAll( allCondesos);
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
