package condesaGUI;

import DbController.HibernateCrud;
import horario.Plantillas;
import java.io.IOException;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
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
    @FXML private TableColumn<Tiendas, Long> id;
    @FXML private TextField nombreTextField;
    @FXML private TextField managerTextField;
    @FXML private TextField idTextField;
    @FXML private DatePicker aperturaCalendario;

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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/plantillasGUI.fxml"));
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
            Stage stage = new Stage();
            stage.setScene(new Scene(root, Screen.getPrimary().getVisualBounds().getWidth(),
                Screen.getPrimary().getVisualBounds().getHeight()));
            stage.show();
    }

    private void loadTiendadUpdate(){
        try{
            Tiendas tienda = tableView.getSelectionModel().getSelectedItem();
            idTextField.setText(Long.toString(tienda.getId()));
            nombreTextField.setText(tienda.getNombre());
            managerTextField.setText(tienda.getManager());
            plantillaActual.setText(tienda.getPlantilla().getNombre());
            aperturaCalendario.setValue(tienda.getFechaApertura());
        }catch(Exception e){

        }
    }

    public void addButtonClicked(ActionEvent actionEvent) {
        Tiendas tienda = new Tiendas();
        try{
            tienda.setId(Long.parseLong(idTextField.getText()));
        }catch (Exception e){
            e.getMessage();
        }

        if(!nombreTextField.getText().isEmpty() && !managerTextField.getText().isEmpty()) {
            tienda.setNombre(nombreTextField.getText());
            tienda.setManager(managerTextField.getText());
            tienda.setFechaApertura(aperturaCalendario.getValue());
            HibernateCrud.SaveTienda(tienda);
            tableView.getItems().setAll(HibernateCrud.GetAllTiendas()); //TODO
        }
    }

    public void deleteButtonClicked(ActionEvent actionEventent){
        Tiendas tienda = tableView.getSelectionModel().getSelectedItem();
        HibernateCrud.DeleteTienda(tienda);
        tableView.getItems().setAll(HibernateCrud.GetAllTiendas()); //TODO
    }

    public void updateButtonClicked(ActionEvent actionEvent){
        Tiendas tienda =  tableView.getSelectionModel().getSelectedItem();
        try{
            tienda.setId(Long.parseLong(idTextField.getText()));
        }catch (Exception e){
            e.getMessage();
        }

        if(!nombreTextField.getText().isEmpty() && !managerTextField.getText().isEmpty()) {
            tienda.setNombre(nombreTextField.getText());
            tienda.setManager(managerTextField.getText());
            tienda.setFechaApertura(aperturaCalendario.getValue());
            HibernateCrud.UpdateTienda(tienda);
            tableView.getItems().setAll(HibernateCrud.GetAllTiendas());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id.setCellValueFactory(new PropertyValueFactory<Tiendas, Long>("id"));
        tiendaNombre.setCellValueFactory(new PropertyValueFactory<Tiendas, String>("nombre"));
        tiendaManager.setCellValueFactory(new PropertyValueFactory<Tiendas, String>("manager"));
        fechaApertura.setCellValueFactory(new PropertyValueFactory<Tiendas, Date>("fechaApertura"));
        plantillaActual.setCellValueFactory(new PropertyValueFactory<Tiendas, Plantillas>("plantilla"));
        tableView.getItems().setAll(HibernateCrud.GetAllTiendas());
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, newSelection, oldSelection) -> {
            loadTiendadUpdate();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
