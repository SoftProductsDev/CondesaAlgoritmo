package condesaGUI;

import DbController.HibernateCrud;
import horario.Plantillas;
import tiendas.Tiendas;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class TiendaGUI extends Application implements Initializable {
    @FXML private TableView<Tiendas> tableView;
    @FXML private TableColumn<Tiendas, String> tiendaNombre;
    @FXML private TableColumn<Tiendas, String> tiendaManager;
    @FXML private TableColumn<Tiendas, Date> fechaApertura;
    @FXML private TableColumn<Tiendas, Plantillas> plantillaActual;
    @FXML private TextField nombreTextField;
    @FXML private TextField managerTextField;
    @FXML private DatePicker apertura;

    @Override
    public void start(Stage primaryStage) throws Exception{
        String sceneFile = "/tiendasGUI.fxml";
        Parent root = null;
        URL url  = null;
        try
        {
            url  = getClass().getResource( sceneFile );
            root = FXMLLoader.load( url );
            System.out.println( "  fxmlResource = " + sceneFile );
        }
        catch ( Exception ex )
        {
            System.out.println( "Exception on FXMLLoader.load()" );
            System.out.println( "  * url: " + url );
            System.out.println( "  * " + ex );
            System.out.println( "    ----------------------------------------\n" );
            throw ex;
        }
        primaryStage.setTitle("Administrador de Tiendas");
        primaryStage.setScene(new Scene(root, 700, 600));
        primaryStage.show();
    }
    public void plantillasClicked(ActionEvent actionEvent) throws Exception {
        OpenNewWindow("/plantillasGUI.fxml");
    }
    private void OpenNewWindow(String filename) throws Exception{
        String sceneFile = filename;
        Parent root = null;
        URL url  = null;
        try {
            url  = getClass().getResource( sceneFile );
            root = FXMLLoader.load( url );
            Stage stage = new Stage();
            stage.setScene(new Scene(root, Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight()));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void loadTiendadUpdate(){
        try{
            Tiendas tienda = tableView.getSelectionModel().getSelectedItem();
            nombreTextField.setText(tienda.getNombre());
            managerTextField.setText(tienda.getManager());
            fechaApertura.setCellValueFactory(new PropertyValueFactory<Tiendas, Date>("fechaApertura"));
            plantillaActual.setText(tienda.getPlantilla().getNombre());
        }catch(Exception e){

        }
    }

    public void addButtonClicked(ActionEvent actionEvent) {
        Tiendas tienda = new Tiendas();
        tienda.setNombre(nombreTextField.getText());
        tienda.setManager(managerTextField.getText());
        tienda.setFechaApertura(apertura.getValue());
        HibernateCrud.SaveTienda(tienda);
        tableView.getItems().setAll(HibernateCrud.GetAllTiendas());
    }

    public void deleteButtonClicked(ActionEvent actionEventent){
        Tiendas tienda = tableView.getSelectionModel().getSelectedItem();
        HibernateCrud.DeleteTienda(tienda);
        tableView.getItems().setAll(HibernateCrud.GetAllTiendas());
    }

    public void updateButtonClicked(ActionEvent actionEvent){
        Tiendas tienda =  tableView.getSelectionModel().getSelectedItem();
        tienda.setNombre(nombreTextField.getText());
        tienda.setManager(managerTextField.getText());
        tienda.setFechaApertura(apertura.getValue());
        HibernateCrud.UpdateTienda(tienda);
        tableView.getItems().setAll(HibernateCrud.GetAllTiendas());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tiendaNombre.setCellValueFactory(new PropertyValueFactory<Tiendas, String>("nombre"));
        tiendaManager.setCellValueFactory(new PropertyValueFactory<Tiendas, String>("manager"));
        fechaApertura.setCellValueFactory(new PropertyValueFactory<Tiendas, Date>("fechaApertura"));
        tableView.getItems().setAll(HibernateCrud.GetAllTiendas());
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, newSelection, oldSelection) -> {
            loadTiendadUpdate();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
