package condesaGUI;



import java.awt.List;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javax.naming.ldap.Control;

/**
 * Created by javier on 09/08/2018.
 */
public class AddTiendaGUI extends Application {

  private TilePane Tile;
  private Node[] controls;

  @Override
  public void start(Stage primaryStage) throws Exception {
    CreateLayout();
    CreateControls();
    AddControlsToLayout();
    Scene scene = new Scene(Tile, 300, 500);
    primaryStage.setTitle("Añadir Tiendas");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private void AddControlsToLayout()
  {
    for (int i=0; i < 8; i++)
    {
      Tile.getChildren().add(controls[i]);
    }
  }

  private void CreateControls()
  {
    controls = new Node[8];
    controls[0] = new Label("Ingrese el nombre de la tienda");
    controls[1] = new TextField();
    controls[2] = new Label("Seleccione inicio de turno");
    controls[3] = new ChoiceBox();
    controls[4]= new Label("Seleccione el fin de turno");
    controls[5] = new ChoiceBox();
    controls[6] = new Button("Añadir Turno");
    controls[7] = new Button("Añadir Tienda");
  }
  
  private void CreateLayout()
  {
    Tile = new TilePane(Orientation.VERTICAL);
    Tile.setPadding(new Insets(5, 0, 5, 0));
    Tile.setVgap(8);
    Tile.setHgap(4);
    Tile.setPrefColumns(2);
    Tile.setStyle("-fx-background-color: DAE6F3;");
    Tile.setAlignment(Pos.BASELINE_CENTER);
  }
}
