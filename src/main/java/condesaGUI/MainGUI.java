package condesaGUI;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;


public class MainGUI extends Application {

    private TilePane Tile;
    private Node[] controls;
    @Override
    public void start(Stage primaryStage) throws Exception {
        CreateLayout();
        CreateControls();
        AddControlsToLayout();
        Scene scene = new Scene(Tile, 800, 500);
        primaryStage.setTitle("Horarios CONDESA");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void AddControlsToLayout()
    {
        for (int i=0; i < 3; i++)
        {
            Tile.getChildren().add(controls[i]);
        }
    }

    private void CreateControls()
    {
        controls = new Node[3];
        Button algoritmo = new Button();
        algoritmo.setText("Nuevo Horario");
        algoritmo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//TODO
            }
        });
        Button tiendas = new Button();
        tiendas.setText("Tiendas");
        tiendas.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Parent root;
                try {
                    Stage stage = new Stage();
                    stage.setTitle("Add Condeso");
                    AddCondesoGUI condeso = new AddCondesoGUI();
                    condeso.start(stage);
                    stage.setScene(condeso.scene);
                    stage.show();
                    // Hide this current window (if this is what you want)
                    //((Node)(event.getSource())).getScene().getWindow().hide();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Button condesos = new Button();
        condesos.setText("Condesos");
        condesos.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Parent root;
                try {
                    Stage stage = new Stage();
                    stage.setTitle("Add Condeso");
                    AddTiendaGUI tienda = new AddTiendaGUI();
                    tienda.start(stage);
                    stage.setScene(tienda.scene);
                    stage.show();
                    // Hide this current window (if this is what you want)
                    //((Node)(event.getSource())).getScene().getWindow().hide();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        controls[0] = condesos;
        controls[1] = tiendas;
        controls[2] = algoritmo;
    }

    private void CreateLayout()
    {
        Tile = new TilePane(Orientation.HORIZONTAL);
        Tile.setPadding(new Insets(5, 5, 5, 5));
        Tile.setVgap(3);
        Tile.setHgap(5);
        Tile.setPrefColumns(2);
        Tile.setStyle("-fx-background-color: #ff63b4;");
        Tile.setAlignment(Pos.TOP_LEFT);
    }
}
