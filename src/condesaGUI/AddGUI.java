package condesaGUI;

import condeso.Condeso;
import condeso.Contrato;
import condeso.TipoEmpleado;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import tiendas.Tiendas;
import java.util.Set;
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
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;


public class AddGUI extends Application{
  private ToggleButton fijoCh;
  private TextField nameTF;
  private TextField levelTF;
  private ChoiceBox<TipoEmpleado> empleoCh;
  private RadioButton matutinoCh;
  private DatePicker fechaContrataciónDP;
  private ChoiceBox<Contrato> contratoCh;
  private RadioButton cajaTS;
  private RadioButton vespertinoCh;


  @Override
  public void start(Stage primaryStage) {
    Button btn = new Button();
    btn.setText("Añadir");
    btn.setOnAction(new EventHandler<ActionEvent>() {

      @Override
      public void handle(ActionEvent event) {
        int level = 0;
        try {level = Integer.parseInt(levelTF.getText());}
        catch(Exception e){String message = "Ingrese un número";}

        Condeso condeso = new Condeso(
            empleoCh.getValue(),
            nameTF.getText(),
            fijoCh.isSelected(),
            level,
            matutinoCh.isSelected(),
            cajaTS.isSelected(),
            fechaContrataciónDP.getValue(),
            new ArrayList<Tiendas>(),
            Contrato.Tipo1
        );

        System.out.println(condeso);
      }
    });

    ToggleGroup group1 = new ToggleGroup();
    fijoCh = new RadioButton("Fijo");
    fijoCh.setToggleGroup(group1);
    RadioButton temporal = new RadioButton("Temporal");
    temporal.setToggleGroup(group1);
    nameTF = new TextField("Nombre");
    empleoCh = new ChoiceBox<TipoEmpleado>
        (FXCollections.observableArrayList(TipoEmpleado.Encargado,
            TipoEmpleado.GM, TipoEmpleado.Equipo, TipoEmpleado.Nuevo));
    ToggleGroup group2 = new ToggleGroup();
    matutinoCh = new RadioButton("Matutino");
    matutinoCh.setToggleGroup(group2);
    vespertinoCh = new RadioButton("Vespertino");
    vespertinoCh.setToggleGroup(group2);
    fechaContrataciónDP = new DatePicker();
    contratoCh = new ChoiceBox<Contrato>
        (FXCollections.observableArrayList(Contrato.Tipo1));
    levelTF = new TextField("level");
    cajaTS = new RadioButton("Caja");

    TilePane tile = new TilePane(Orientation.VERTICAL);
    tile.setPadding(new Insets(5, 0, 5, 0));
    tile.setVgap(4);
    tile.setHgap(4);
    tile.setPrefColumns(2);
    tile.setStyle("-fx-background-color: DAE6F3;");
    tile.setAlignment(Pos.BASELINE_CENTER);

    tile.getChildren().add(nameTF);
    tile.getChildren().add(empleoCh);
    tile.getChildren().add(levelTF);
    tile.getChildren().add(fijoCh);
    tile.getChildren().add(temporal);
    tile.getChildren().add(cajaTS);
    tile.getChildren().add(matutinoCh);
    tile.getChildren().add(vespertinoCh);
    tile.getChildren().add(fechaContrataciónDP);
    tile.getChildren().add(contratoCh);
    tile.getChildren().add(btn);

    Scene scene = new Scene(tile, 300, 500);

    primaryStage.setTitle("Añadir Condesos");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  public static void main(String[] args) {
    launch(args);
  }
}
