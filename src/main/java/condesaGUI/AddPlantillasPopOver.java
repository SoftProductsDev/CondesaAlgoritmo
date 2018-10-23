package condesaGUI;

import DbModel.Condeso;
import DbModel.Dias;
import DbModel.Turnos;
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

public class AddPlantillasPopOver implements Initializable {
  @FXML private ChoiceBox<TipoTurno> tipoChoice;
  @FXML private TextField inicioField;
  @FXML private TextField finField;
  @FXML private AnchorPane anchorPane;
  private GridPane gridPane;
  private Dias dia;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    ObservableList<TipoTurno> turnos = FXCollections.observableArrayList(TipoTurno.values());
    tipoChoice.setItems(turnos);
  }

  public void addTurno(ActionEvent actionEvent) {
    Turnos turno = new Turnos();
    turno.setInicio(Integer.parseInt(inicioField.getText()));
    turno.setFin(Integer.parseInt(finField.getText()));
    turno.setTipoTurno(tipoChoice.getValue());
    gridPane.add(createLabel(turno),turno.getTipoTurno().ordinal() + 1, turno.getInicio() - 7,
        1, turno.getDuracion());
    dia.getTurnos().add(turno);
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
            edit.setInitialValues(turno, dia, gridPane, label);
            event.consume();
          };
        });
    return label;
  }

  public void setInitialValues(GridPane gridPane, Dias dia){
    this.gridPane = gridPane;
    this.dia = dia;
  }
}
