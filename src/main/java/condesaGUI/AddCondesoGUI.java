package condesaGUI;

import DbController.HibernateCrud;
import condeso.Condeso;
import condeso.Contrato;
import condeso.TipoEmpleado;
import horario.Dias;
import horario.HorarioEntrega;
import horario.Turnos;
import java.util.HashMap;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import tiendas.Tiendas;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;


public class AddCondesoGUI extends Application{
    private ToggleButton fijoCh;
    private TextField nameTF;
    private TextField levelTF;
    private ChoiceBox<TipoEmpleado> empleoCh;
    private RadioButton matutinoCh;
    private DatePicker fechaContratacionDP;
    private ChoiceBox<Contrato> contratoCh;
    private RadioButton cajaTS;
    private RadioButton vespertinoCh;
    private RadioButton temporal;
    private Label errorMssg;
    public Scene scene;

    //Builds elements
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Añadir");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if(CheckValidInput()){
                    int level = 0;
                    try {
                        level = Integer.parseInt(levelTF.getText());
                        Condeso condeso = CreateCondeso(level);
                        //Guarda en base de datos
                        HibernateCrud.SaveCondeso(condeso);
                        errorMssg.setText("Se ha añadido: " + condeso.getNombre());
                        System.out.println(condeso);
                    }
                    catch(Exception e)
                    {
                        errorMssg.setText("Ingrese un valor numérico en nivel" + e.getMessage());
                    }
                }
            }
        });

        CreateControls();

        TilePane tile = new TilePane(Orientation.VERTICAL);
        tile.setPadding(new Insets(5, 0, 5, 0));
        tile.setVgap(8);
        tile.setHgap(4);
        tile.setPrefColumns(2);
        tile.setStyle("-fx-background-color: DAE6F3;");
        tile.setAlignment(Pos.BASELINE_CENTER);

        tile.getChildren().add(new Label("Nombre del empleado"));
        tile.getChildren().add(nameTF);
        tile.getChildren().add(new Label("Seleccione el cargo"));
        tile.getChildren().add(empleoCh);
        tile.getChildren().add(new Label("Nivel - Escriba un número"));
        tile.getChildren().add(levelTF);
        tile.getChildren().add(new Label("Elija si es un trabajador fijo o temporal"));
        tile.getChildren().add(fijoCh);
        tile.getChildren().add(temporal);
        tile.getChildren().add(new Label("Seleccione si tiene permisos de usar la caja"));
        tile.getChildren().add(cajaTS);
        tile.getChildren().add(new Label("Seleccione el tipo de turno"));
        tile.getChildren().add(matutinoCh);
        tile.getChildren().add(vespertinoCh);
        tile.getChildren().add(new Label("Fecha de contratación (DD/MM/YYYY)"));
        tile.getChildren().add(fechaContratacionDP);
        tile.getChildren().add(new Label("Seleccione el tipo de contrato"));
        tile.getChildren().add(contratoCh);
        tile.getChildren().add(btn);
        tile.getChildren().add(errorMssg);

        scene = new Scene(tile, 300, 700);

        primaryStage.setTitle("Añadir Condesos");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private boolean CheckValidInput()
    {
        if(!nameTF.getText().isEmpty() &&
                empleoCh.getValue() != null &&
                !levelTF.getText().isEmpty()&&
                fijoCh.isSelected() || temporal.isSelected() &&
                matutinoCh.isSelected() || vespertinoCh.isSelected()
                && fechaContratacionDP.getValue() != null
                && contratoCh.getValue() != null){return true;}
        else
        {
            errorMssg.setText("Hay campos incompletos, inténtelo de nuevo");
            return false;
        }
    }

    private Condeso CreateCondeso(int level) {
        Condeso condeso = new Condeso(
                empleoCh.getValue(),
                nameTF.getText(),
                fijoCh.isSelected(),
                level,
                matutinoCh.isSelected(),
                cajaTS.isSelected(),
                asDate(fechaContratacionDP.getValue()),
                new ArrayList<Tiendas>(),
                Contrato.Tipo1
        );
        condeso.setEntrega(new HorarioEntrega(new HashMap()));
        return condeso;
    }

    private Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    private void CreateControls(){
        ToggleGroup group1 = new ToggleGroup();
        fijoCh = new RadioButton("Fijo");
        fijoCh.setToggleGroup(group1);
        temporal = new RadioButton("Temporal");
        temporal.setToggleGroup(group1);
        nameTF = new TextField();
        empleoCh = new ChoiceBox<TipoEmpleado>
                (FXCollections.observableArrayList(TipoEmpleado.Encargado,
                        TipoEmpleado.GM, TipoEmpleado.Equipo, TipoEmpleado.Nuevo));
        ToggleGroup group2 = new ToggleGroup();
        matutinoCh = new RadioButton("Matutino");
        matutinoCh.setToggleGroup(group2);
        vespertinoCh = new RadioButton("Vespertino");
        vespertinoCh.setToggleGroup(group2);
        fechaContratacionDP = new DatePicker();
        contratoCh = new ChoiceBox<Contrato>
                (FXCollections.observableArrayList(Contrato.Tipo1));
        levelTF = new TextField();
        cajaTS = new RadioButton("Caja");
        errorMssg = new Label();
    }

    public static void main(String[] args) {
        launch(args);
        AddCondesoGUI.main(args);
    }
}
