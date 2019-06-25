package condesaGUI;

import DbController.CrudOperations;
import DbController.WebApiClient;
import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainGUI extends Application {

    public static void main(String args[]){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
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
        CrudOperations hibernateCrud = new WebApiClient();
        nuevoHorarioGUI.setInitialValues(
            FXCollections.observableArrayList(hibernateCrud.GetAllCondesos()),
            FXCollections.observableList(hibernateCrud.GetAllTiendas()));
    }

    @Override
    public void stop(){
        //HibernateUtil.shutdown();
        try {
            super.stop();
        }catch (Exception e){
           System.out.println(e.getMessage());
        }
    }

}
