package condesaGUI;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PlantillaGUI   extends Application implements Initializable {
    @FXML private ComboBox<?> nombre;

    @Override
    public void start(Stage primaryStage) throws Exception {
        String sceneFile = "/plantillasGUI.fxml";
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
        primaryStage.setTitle("Administrador de Plantillas");
        primaryStage.setScene(new Scene(root, 700, 600));
        primaryStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public static void main(String[] args) {
        launch(args);
    }
}
