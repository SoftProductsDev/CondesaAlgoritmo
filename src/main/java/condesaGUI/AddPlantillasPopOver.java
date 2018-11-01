package condesaGUI;

import condeso.Condeso;
import horario.Dias;
import horario.Turnos;
import horario.TipoTurno;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.controlsfx.control.PopOver;
import org.controlsfx.control.ToggleSwitch;

public class  AddPlantillasPopOver implements Initializable {
  @FXML private ChoiceBox<TipoTurno> tipoChoice;
  @FXML private TextField inicioField;
  @FXML private TextField finField;
  @FXML private AnchorPane anchorPane;
  private GridPane weekGrid;
  private GridPane gridPane;
  private Dias dia;
  private ToggleSwitch toggleEditar;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    ObservableList<TipoTurno> turnos = FXCollections.observableArrayList(TipoTurno.values());
    turnos.remove(TipoTurno.GM);
    tipoChoice.setItems(turnos);
  }

  public String addTurno(ActionEvent actionEvent) {
    Turnos turno = new Turnos();
    try {
      turno.setInicio(Integer.parseInt(inicioField.getText()));
      turno.setFin(Integer.parseInt(finField.getText()));
    }catch (Exception e){
      return "Introduzca numeros entre el 8 y 24";
    }
    if(tipoChoice.getValue() != null){
      turno.setTipoTurno(tipoChoice.getValue());
    }
    else{
      return "Elija el tipo de turno";
    }
    gridPane.add(createLabel(turno),turno.getTipoTurno().ordinal() + 1, turno.getInicio() - 7,
        1, turno.getDuracion());
    dia.getTurnos().add(turno);
    return "";
  }

  private Label createLabel(Turnos turno) {
    Label label = new Label("Turno");
    label.setStyle("-fx-background-color: #4286f4; -fx-border-color: black");
    label.setMaxHeight(125462739);
    label.setMaxWidth(1234567890);
    label.addEventHandler(MouseEvent.MOUSE_CLICKED,
        new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent event) {
            if(toggleEditar.isSelected() || gridPane.getParent().equals(weekGrid)){
              FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/editPlantillasPopOver.fxml"));
              String sceneFile = "/editPlantillasPopOver.fxml";
              Parent root = null;
              URL url  = null;
              try {
                //url  = getClass().getResource( sceneFile );
                //root = fxmlLoader.load( url );
                root = (Parent) fxmlLoader.load();
              } catch (IOException e) {
                e.printStackTrace();
              }
              PopOver pop = new PopOver(root);
              pop.setAutoFix(false);
              pop.show(label);
              EditPlantillasPopOverGUI edit = fxmlLoader.getController();
              edit.setInitialValues(turno, dia, gridPane, label, toggleEditar, weekGrid);
              event.consume();
            };
          }
        });
    return label;
  }

  public void setInitialValues(GridPane gridPane, Dias dia, ToggleSwitch toggleEditar, GridPane weekGrid){
    this.gridPane = gridPane;
    this.dia = dia;
    this.toggleEditar = toggleEditar;
    this.weekGrid = weekGrid;
  }
}
