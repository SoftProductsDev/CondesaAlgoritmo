package condesaGUI;

import DbController.HibernateCrud;
import condeso.Condeso;
import horario.Dias;
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

public class AddPopOverGUI implements Initializable {
  @FXML private ChoiceBox<TipoTurno> tipoChoice;
  @FXML private TextField inicioField;
  @FXML private TextField finField;
  @FXML private ChoiceBox<Condeso> condesoChoice;
  @FXML private ChoiceBox<Condeso> condesoTodosChoice;
  private GridPane gridPane;
  private Dias dia;
  private ObservableList<Condeso> condesos;
  private ObservableList<Tiendas> tiendas;

  public void addTurno(ActionEvent actionEvent) {
    Turnos turno = new Turnos();
    turno.setInicio(Integer.parseInt(inicioField.getText()));
    turno.setFin(Integer.parseInt(finField.getText()));
    turno.setCondeso(condesoChoice.getSelectionModel().getSelectedItem());
    turno.setTipoTurno(tipoChoice.getValue());
    gridPane.add(createLabel(turno),turno.getTipoTurno().ordinal() + 1, turno.getInicio() - 7,
        1, turno.getDuracion());
    dia.getTurnos().add(turno);
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }

  public void setInitialValues(GridPane grid, Dias dia, ObservableList<Condeso> condesos,
      ObservableList<Tiendas> tiendas){
    this.gridPane = grid;
    this.dia = dia;
    this.tiendas = tiendas;
    this.condesos = condesos;
    ObservableList<TipoTurno> turnos = FXCollections.observableArrayList(TipoTurno.values());
    condesoTodosChoice.setItems(condesos);
    Map<LocalDate, Dias> master = new HashMap<>();
    Dias diaD =  new Dias();
    Set<Turnos> turnosX = new HashSet<>();
    List<Condeso> aBorrar = new ArrayList<>();
    for(Tiendas tienda:tiendas){
      master = tienda.getMaster().getMes();
      diaD = master.get(LocalDate.of(dia.getDate().getYear(),dia.getDate().getMonth(),  dia.getDate().getDayOfMonth()));
      if (diaD != null) {
        turnosX = diaD.getTurnos();
        for(Turnos turnoD:turnosX){
          if(turnoD.getCondeso() != null){
            for(Condeso condeso:condesos){
              if(condeso.getId() == turnoD.getCondeso().getId()){
                aBorrar.add(condeso);
                break;
              }
            }
          }
        }
      }

    }
    this.condesos.removeAll(aBorrar);
    condesoChoice.setItems(condesos);
    tipoChoice.setItems(turnos);
  }

  private Label createLabel(Turnos turno) {
    Label label = new Label(turno.getCondeso().getNombre());
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
            pop.setAnimated(false);
            pop.setAutoFix(false);
            pop.show(label);
            EditPopOverGUI edit = (EditPopOverGUI) fxmlLoader.getController();
            edit.setInitialValues(turno, dia, gridPane, label, condesos, tiendas);
            event.consume();
          };
        });
    return label;
  }
}
