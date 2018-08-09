package condesaGUI;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class AddGUI extends Application{
  @Override
  public void start(Stage primaryStage) {
    Button btn = new Button();
    btn.setText("Añadir");
    btn.setOnAction(new EventHandler<ActionEvent>() {

      @Override
      public void handle(ActionEvent event) {
        System.out.println("Hello World!");
      }
    });

    ChoiceBox<String> pick = new ChoiceBox<>
        (FXCollections.observableArrayList("Fijo", "Temporal"));
    TextField name = new TextField("Nombre");
    ChoiceBox<String> empleo = new ChoiceBox<>
        (FXCollections.observableArrayList("GM", "Encargados", "Equipo", "Nuevo"));
    ChoiceBox<String> matutino = new ChoiceBox<>
        (FXCollections.observableArrayList("Matutino", "Vespertino"));
    DatePicker fechaContratación = new DatePicker();
    ChoiceBox<String> contrato = new ChoiceBox<>
        (FXCollections.observableArrayList("Tipo1", "Tipo2","Tipo3"));

    TilePane tile = new TilePane(Orientation.VERTICAL);
    tile.setPadding(new Insets(5, 0, 5, 0));
    tile.setVgap(4);
    tile.setHgap(4);
    tile.setPrefColumns(2);
    tile.setStyle("-fx-background-color: DAE6F3;");
    tile.setAlignment(Pos.BASELINE_CENTER);

    tile.getChildren().add(name);
    tile.getChildren().add(empleo);
    tile. getChildren().add(pick);
    tile.getChildren().add(matutino);
    tile.getChildren().add(fechaContratación);
    tile.getChildren().add(contrato);
    tile.getChildren().add(btn);

    Scene scene = new Scene(tile, 300, 250);

    primaryStage.setTitle("Añadir Condesos");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  public static void main(String[] args) {
    launch(args);
  }
}
