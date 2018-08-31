package condesaGUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class TiendaGUI   extends Application implements Initializable {
    @FXML private TableView<DbModel.Tiendas> tableView;
    @FXML private TextField nombreTextField;
    @FXML private TextField managerTextField;

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

    public void addButtonClicked(ActionEvent actionEvent) {
//TODO
    }

    public void deleteButtonClicked(ActionEvent actionEventent){
//TODO
    }

    public void updateButtonClicked(ActionEvent actionEvent){
//TODO
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public static void main(String[] args) {
        launch(args);
    }
}
