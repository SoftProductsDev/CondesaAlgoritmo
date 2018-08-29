package condesaGUI;

import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class FrontGUI {

  public void CondesosClicked(ActionEvent actionEvent) throws Exception {
    OpenNewWindow("/condesosGUI.fxml");
  }

  public void TiendasClicked(ActionEvent actionEvent) throws Exception {
    OpenNewWindow("/tiendasGUI.fxml");
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
}
