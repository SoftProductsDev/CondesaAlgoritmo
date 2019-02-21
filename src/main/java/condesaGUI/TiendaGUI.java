package condesaGUI;

import DbController.HibernateCrud;
import horario.Plantillas;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import tiendas.Tiendas;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class TiendaGUI extends Application implements Initializable {
    @FXML private TableView<Tiendas> tableView;
    @FXML private TableColumn<Tiendas, String> tiendaNombre;
    @FXML private TableColumn<Tiendas, String> tiendaManager;
    @FXML private TableColumn<Tiendas, Date> fechaApertura;
    @FXML private TableColumn<Tiendas, Plantillas> plantillaActual;
    @FXML private TableColumn<Tiendas, Long> id;
    @FXML private TableColumn<Tiendas, String> tiendaColor;
    @FXML private TextField nombreTextField;
    @FXML private TextField managerTextField;
    @FXML private TextField idTextField;
    @FXML private DatePicker aperturaCalendario;
    @FXML private ColorPicker colorPicker;
    private List<Tiendas> tiendas;

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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/plantillasGUI.fxml"));  //TODO Example
        Parent root = null;
        String sceneFile = "/plantillasGUI.fxml";
        URL url  = null;
        try {
            //url  = getClass().getResource( sceneFile );
            //root = fxmlLoader.load( url );
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(root, Screen.getPrimary().getVisualBounds().getWidth(),
                Screen.getPrimary().getVisualBounds().getMaxY()));
        stage.show();
        PlantillaGUI plantillas = (PlantillaGUI) fxmlLoader.getController();
        plantillas.setInitialValues(tiendas);
        //OpenNewWindow("/plantillasGUI.fxml");
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
            colorPicker.setValue(Color.web(tienda.getColor()));
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
            String colorHex = colorPicker.getValue().toString();
            colorHex = "#" + colorHex.substring(2, 8);
            tienda.setColor(colorHex);
            HibernateCrud.SaveTienda(tienda);
            tiendas.add(tienda);
            tableView.getItems().setAll(tiendas);
        }
    }

    public void deleteButtonClicked(ActionEvent actionEventent){
        Tiendas tienda = tableView.getSelectionModel().getSelectedItem();

        var wd = new WorkIndicatorDialog(this.tableView.getScene().getWindow(), "Actualizando...");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("CUIDADO!!");
        alert.setHeaderText("Al eliminar una tienda\n esta se borrará permanentemente\n de la base de datos y su información se perderá.");
        alert.setContentText("Seguro que deseas eliminar a \n" + tienda.getNombre() + " permanentemente ?");

        ButtonType buttonTypeOne = new ButtonType("Si");
        ButtonType buttonTypeTwo = new ButtonType("No");

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){


            wd.addTaskEndNotification(result2 -> {
                System.out.println(result2);
                //wd=null; // don't keep the object, cleanup
            });

            wd.exec("123", inputParam -> {
                try {
                    HibernateCrud.DeleteTienda(tienda);
                    tiendas.remove(tienda);
                    tableView.getItems().setAll(tiendas);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return (1);
            });
        }
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
            String colorHex = colorPicker.getValue().toString();
            colorHex = "#" + colorHex.substring(2, 8);
            tienda.setColor(colorHex);
            HibernateCrud.UpdateTienda(tienda);
            tableView.getItems().setAll(tiendas);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public static void main(String[] args) {
        launch(args);
    }

    public void setInitialValues(ObservableList<Tiendas> tiendas) {
        this.tiendas = tiendas;
        id.setCellValueFactory(new PropertyValueFactory<Tiendas, Long>("id"));
        tiendaNombre.setCellValueFactory(new PropertyValueFactory<Tiendas, String>("nombre"));
        tiendaManager.setCellValueFactory(new PropertyValueFactory<Tiendas, String>("manager"));
        fechaApertura.setCellValueFactory(new PropertyValueFactory<Tiendas, Date>("fechaApertura"));
        plantillaActual.setCellValueFactory(new PropertyValueFactory<Tiendas, Plantillas>("plantilla"));
        tableView.getItems().setAll(this.tiendas);
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, newSelection, oldSelection) -> {
            loadTiendadUpdate();
        });
        tiendaColor.setCellValueFactory(new PropertyValueFactory<Tiendas, String>("color"));
        tiendaColor.setCellFactory( column -> {
            return new TableCell<Tiendas, String>(){
                @Override
                protected void updateItem(String item, boolean empty){
                    if (item == null || empty) {
                        setText(null);
                        setStyle("");
                    } else {

                        // Fill with a different color.
                        setStyle("-fx-background-color: " + item);
                    }
                }
            };
        });
    }
}
