package condesaGUI;

import DbController.HibernateCrud;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class FrontGUI extends Application implements Initializable {

  @FXML private ComboBox<String>  tiendasComboBox;

  public void start(Stage primaryStage) throws Exception{

  }

  public void initialize(URL location, ResourceBundle resources) {
    tiendasComboBox.getItems().setAll(HibernateCrud.tiendasToList());
  }
  public static void main(String[] args) {
    launch(args);
  }

  public void CondesosClicked(ActionEvent actionEvent) throws Exception {
    OpenNewWindow("/condesosGUI.fxml");
  }

  public void TiendasClicked(ActionEvent actionEvent) throws Exception {
    OpenNewWindow("/tiendasGUI.fxml");
  }

  public void NuevoHorarioClicked(ActionEvent actionEvent) throws Exception{
    OpenNewWindow("/nuevoHorarioGUI.fxml");
  }

  private void OpenNewWindow(String filename) throws Exception{
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
}
