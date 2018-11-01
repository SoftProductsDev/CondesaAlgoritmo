package condesaGUI;

import DbController.HibernateCrud;
import horario.Dias;
import horario.Turnos;
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
import javafx.scene.layout.GridPane;
import org.controlsfx.control.PopOver;
import org.controlsfx.control.ToggleSwitch;

public class EditPlantillasPopOverGUI  implements Initializable {
    @FXML
    private TextField inicioField;
    @FXML private TextField finField;
    private Turnos turno;
    private Dias dia;
    private GridPane grid;
    private GridPane weekGrid;
    private Label label;
    private ToggleSwitch toggleEditar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setInitialValues(Turnos turno, Dias dia, GridPane grid, Label label,
        ToggleSwitch toggleEditar, GridPane weekGrid){
      this.turno = turno;
      this.dia = dia;
      this.grid = grid;
      this.label = label;
      this.toggleEditar = toggleEditar;
      inicioField.setText(Integer.toString(turno.getInicio()));
      finField.setText(Integer.toString(turno.getFin()));
      this.weekGrid = weekGrid;
    }

    public void applyChange(ActionEvent actionEvent) {
      int oldInicio = turno.getInicio();
      int oldFin = turno.getFin();
      try {
        turno.setInicio(Integer.parseInt(inicioField.getText()));
        turno.setFin(Integer.parseInt(finField.getText()));
        turno.setTipoTurno(turno.getTipoTurno());
      }catch (Exception e){ }
      if(!checkoverlap(turno)){
        grid.getChildren().remove(label);
        //considering the first hour is 8 am
        int hourIndex = turno.getInicio() - 7;
        int columna = turno.getTipoTurno().ordinal();
        grid.add(createLabel(), columna + 1, hourIndex,1, turno.getDuracion());
      }else {
        turno.setInicio(oldInicio);
        turno.setFin(oldFin);
      }
    }

  private boolean checkoverlap(Turnos turno) {
    for (Turnos t:dia.getTurnos()) {
      if(turno.overlapGUI(t)){
        return true;
      }
    }
    return false;
  }

  public void Delete(ActionEvent actionEvent) {
      dia.getTurnos().remove(turno);
      grid.getChildren().remove(label);
    }

    private Label createLabel() {
      Label label = new Label("Turno");
      label.setStyle("-fx-background-color: #4286f4; -fx-border-color: black");
      //label.setStyle();
      label.setMaxHeight(125462739);
      label.setMaxWidth(1234567890);
      label.addEventHandler(MouseEvent.MOUSE_CLICKED,
          new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
              if(toggleEditar.isSelected() || grid.getParent().equals(weekGrid)){
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
                condesaGUI.EditPlantillasPopOverGUI edit = fxmlLoader.getController();
                edit.setInitialValues(turno, dia, grid, label, toggleEditar, weekGrid);
                event.consume();
              };
            }
          });
      return label;
    }
}
