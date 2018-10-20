package condesaGUI;

import DbController.HibernateCrud;
import DbModel.Dias;
import DbModel.Tiendas;
import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import org.controlsfx.control.PopOver;

public class PlantillaGUI   extends Application implements Initializable {
    @FXML private ChoiceBox<String> nombreChoice;
    @FXML private ChoiceBox<Tiendas> tiendasChoice;
    @FXML private ListView<String>  horaList0;
    @FXML private ListView<String>  horaList1;
    @FXML private ListView<String>  horaList3;
    @FXML private ListView<String>  horaList2;
    @FXML private GridPane weekGrid;
    @FXML private GridPane weekGrid1;

    private static final ObservableList<String>
            horario = FXCollections.observableArrayList(getStaticList());

    private static ArrayList getStaticList() {
        ArrayList list = new ArrayList<>();
        list.add("  ");
        for (int i = 8; i < 24; i++){
            list.add(i + "-" + (i+1));
        }
        return list;
    }

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
        horaList0.setItems(horario);
        horaList1.setItems(horario);
        horaList2.setItems(horario);
        horaList3.setItems(horario);
        ObservableList<Tiendas> tiendas = FXCollections.observableList(HibernateCrud.GetAllTiendas());
        tiendasChoice.setItems(tiendas);
        addLabelGrids(weekGrid);
        addLabelGrids(weekGrid1);
    }

    private void addLabelGrids(GridPane gridToAddLabels) {
            for (int i = 1; i < 8; i++){
                GridPane grid = new GridPane();
                for (int k = 0; k < 7; k++) {
                    ColumnConstraints column = new ColumnConstraints();
                    column.prefWidthProperty().set(200);
                    column.setMaxWidth(1234567890);
                    grid.getColumnConstraints().add(column);
                }
                for (int k = 0; k < 17; k++) {
                    RowConstraints column = new RowConstraints();
                    column.setPrefHeight(400);
                    grid.getRowConstraints().add(column);
                }
                grid.setId(i + "-" + 0);
                grid.gridLinesVisibleProperty().set(true);
                addLetrasArriba(grid);
                addGridEventHandler(grid, new Dias());
                gridToAddLabels.add(grid,i,0);
            }
    }

    public void addLetrasArriba(GridPane grid){
        Label label1 = new Label("GM");
        label1.setTextAlignment(TextAlignment.CENTER);
        label1.setAlignment(Pos.CENTER);
        label1.setMaxWidth(12345546);
        label1.setMaxHeight(12345546);
        label1.setStyle("-fx-border-color: black;");
        Label label2 = new Label("GM");
        label2.setTextAlignment(TextAlignment.CENTER);
        label2.setAlignment(Pos.CENTER);
        label2.setMaxWidth(12345546);
        label2.setMaxHeight(12345546);
        label2.setStyle("-fx-border-color: black;");
        Label label3 = new Label("G");
        label3.setTextAlignment(TextAlignment.CENTER);
        label3.setAlignment(Pos.CENTER);
        label3.setMaxWidth(12345546);
        label3.setMaxHeight(12345546);
        label3.setStyle("-fx-border-color: black;");
        Label label4 = new Label("F");
        label4.setTextAlignment(TextAlignment.CENTER);
        label4.setAlignment(Pos.CENTER);
        label4.setMaxWidth(12345546);
        label4.setMaxHeight(12345546);
        label4.setStyle("-fx-border-color: black;");
        Label label5 = new Label("H");
        label5.setTextAlignment(TextAlignment.CENTER);
        label5.setAlignment(Pos.CENTER);
        label5.setMaxWidth(12345546);
        label5.setMaxHeight(12345546);
        label5.setStyle("-fx-border-color: black;");
        Label label6 = new Label("B");
        label6.setTextAlignment(TextAlignment.CENTER);
        label6.setAlignment(Pos.CENTER);
        label6.setMaxWidth(12345546);
        label6.setMaxHeight(12345546);
        label6.setStyle("-fx-border-color: black;");
        Label label7 = new Label("R");
        label7.setTextAlignment(TextAlignment.CENTER);
        label7.setAlignment(Pos.CENTER);
        label7.setMaxWidth(12345546);
        label7.setMaxHeight(12345546);
        label7.setStyle("-fx-border-color: black;");
        grid.setStyle("-fx-border-color: black;");

        grid.add(label1, 0,0);
        grid.add(label2, 1,0);
        grid.add(label3, 2,0);
        grid.add(label4, 3,0);
        grid.add(label5, 4,0);
        grid.add(label6, 5,0);
        grid.add(label7, 6,0);
    }


    private void addGridEventHandler(GridPane pane, Dias dia) {
        pane.addEventHandler(MouseEvent.MOUSE_CLICKED,
            new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/addPlantillaPopOver.fxml"));
                    Parent root = null;
                    try {
                        root = (Parent) fxmlLoader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    PopOver pop = new PopOver(root);
                    pop.setAutoFix(false);
                    pop.show(pane);
                }
            });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
