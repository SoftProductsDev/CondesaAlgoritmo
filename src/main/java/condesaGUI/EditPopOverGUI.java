package condesaGUI;

import DbController.HibernateCrud;
import condeso.Condeso;
import horario.Dias;
import horario.HorarioMaster;
import horario.Turnos;
import horario.TipoTurno;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

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
import tiendas.Tiendas;

public class EditPopOverGUI  implements Initializable {
  @FXML private TextField inicioField;
  @FXML private TextField finField;
  @FXML private ChoiceBox<Condeso> condesoChoice;
  private Turnos turno;
  private Dias dia;
  private GridPane grid;
  private Label label;
  private ObservableList<Condeso> condesos;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
  }

  public void setInitialValues(Turnos turno, Dias dia, GridPane grid, Label label, ObservableList<Condeso> condesos){
    this.turno = turno;
    this.dia = dia;
    this.grid = grid;
    this.label = label;
    inicioField.setText(Integer.toString(turno.getInicio()));
    finField.setText(Integer.toString(turno.getFin()));
    this.condesos = condesos;
    condesoChoice.setItems(condesos);
    condesoChoice.getSelectionModel().select(turno.getCondeso() );
    /*HorarioMaster master;
    List<Condeso> foundCondesos = new ArrayList<>();
    for(Condeso condesoTemp:condesos) {
      boolean encontrado = false;
      for (Tiendas tienda : allTiendas) {
        master = tienda.getMaster();
        Dias diaD = master.getMes().get(dia.getDate());
        if(diaD != null) {
          Set<Turnos> losTurnos = diaD.getTurnos();
          for (Turnos turnoEnDia : losTurnos) {
            //if (turnoEnDia.getCondeso().getId() == turno.getCondeso().getId()) encontrado = true; TODO arreglar que el turno tenga condeso o que le entre el condeso directamente al poup
          }
        }
      }
      if (!encontrado) {
        foundCondesos.add(condesoTemp);
      }
    }
    ObservableList<Condeso> list = FXCollections.observableArrayList(foundCondesos);
    condesoChoice.setItems(list);*/
  }

  public void applyChange(ActionEvent actionEvent) {
    turno.setInicio(Integer.parseInt(inicioField.getText()));
    turno.setFin(Integer.parseInt(finField.getText()));
    turno.setCondeso(condesoChoice.getSelectionModel().getSelectedItem());
    grid.getChildren().remove(label);
    //considering the first hour is 8 am
    int hourIndex = turno.getInicio() - 7;
    int columna = turno.getTipoTurno().ordinal() + 1;
    grid.add(createLabel(), columna,  hourIndex,1,turno.getDuracion());
  }

  public void Delete(ActionEvent actionEvent) {
    dia.getTurnos().remove(turno);
    grid.getChildren().remove(label);
  }

  private Label createLabel() {
    Label label = new Label(turno.getCondeso().getAbreviacion());
    label.setStyle("-fx-background-color: " + turno.getCondeso().getColor());
    //label.setStyle();
    label.setMaxHeight(125462739);
    label.setMaxWidth(1234567890);
    label.addEventHandler(MouseEvent.MOUSE_CLICKED,
        new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent event) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/editPopOver.fxml"));
            String sceneFile = "/editPopOver.fxml";
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
            pop.setAnimated(false);
            EditPopOverGUI edit = (EditPopOverGUI) fxmlLoader.getController();
            edit.setInitialValues(turno, dia, grid, label, condesos);
            event.consume();
          };
        });
    return label;
  }
}
