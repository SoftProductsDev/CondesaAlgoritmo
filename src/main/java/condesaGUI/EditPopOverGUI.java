package condesaGUI;

import DbController.HibernateCrud;
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
import javafx.scene.layout.GridPane;
import org.controlsfx.control.PopOver;

public class EditPopOverGUI  implements Initializable {
  @FXML private TextField inicioField;
  @FXML private TextField finField;
  @FXML private ChoiceBox<Condeso> condesoChoice;
  private Turnos turno;
  private Dias dia;
  private GridPane grid;
  private Label label;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    ObservableList<Condeso> list = FXCollections.observableArrayList(HibernateCrud.GetAllCondesos());
    condesoChoice.setItems(list);
    ;
  }

  public void setInitialValues(Turnos turno, Dias dia, GridPane grid, Label label){
    this.turno = turno;
    this.dia = dia;
    this.grid = grid;
    this.label = label;
    inicioField.setText(Integer.toString(turno.getInicio()));
    finField.setText(Integer.toString(turno.getFin()));
    condesoChoice.getSelectionModel().select(turno.getCondeso() );
  }

  public void applyChange(ActionEvent actionEvent) {
    turno.setInicio(Integer.parseInt(inicioField.getText()));
    turno.setFin(Integer.parseInt(finField.getText()));
    turno.setCondeso(condesoChoice.getSelectionModel().getSelectedItem());
    grid.getChildren().remove(label);
    //considering the first hour is 8 am
    int hourIndex = turno.getInicio() - 7;
    int columna = turno.getTipoTurno().ordinal() + 1;
    grid.add(createLabel(), hourIndex, columna,1,turno.getDuracion());
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
            edit.setInitialValues(turno, dia, grid, label);
            event.consume();
          };
        });
    return label;
  }
}
