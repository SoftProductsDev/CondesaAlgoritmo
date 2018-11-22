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
import javafx.scene.control.*;
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
    if(condesoTodosChoice.getSelectionModel().getSelectedItem() != null && condesoChoice.getSelectionModel().getSelectedItem() != null){
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("2 condesos con el mismo turno");
      alert.setHeaderText("Hay 2 condesos con el mismo turno y debes elegir uno,");
      alert.setContentText("los condesos son los siguientes:");

      ButtonType buttonTypeOne = new ButtonType(condesoTodosChoice.getSelectionModel().getSelectedItem().getNombre());
      ButtonType buttonTypeTwo = new ButtonType(condesoChoice.getSelectionModel().getSelectedItem().getNombre());

      alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

      Optional<ButtonType> result = alert.showAndWait();
      if (result.get() == buttonTypeOne){
        turno.setCondeso(condesoTodosChoice.getSelectionModel().getSelectedItem());
      } else if (result.get() == buttonTypeTwo) {
        turno.setCondeso(condesoChoice.getSelectionModel().getSelectedItem());
      } else {
        Alert alertFinal = new Alert(Alert.AlertType.CONFIRMATION);
        alertFinal.setTitle("No se selecciono ninguno");
        alertFinal.setHeaderText("No se ha seleccionado ningun condeso\n por lo tanto no habra cambio en el turno.");
      }
    }else if(condesoTodosChoice.getSelectionModel().getSelectedItem() != null){
      turno.setCondeso(condesoTodosChoice.getSelectionModel().getSelectedItem());
    }else{
      turno.setCondeso(condesoChoice.getSelectionModel().getSelectedItem());
    }
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
    Map<LocalDate, Dias> master = new HashMap<>();
    Dias diaD =  new Dias();
    Set<Turnos> turnosX = new HashSet<>();
    ObservableList<Condeso> condesosCopy = FXCollections.observableArrayList();
    condesosCopy.addAll(condesos);
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
    ObservableList<TipoTurno> turnos = FXCollections.observableArrayList(TipoTurno.values());
    condesoTodosChoice.setItems(condesos);
    condesosCopy.removeAll(aBorrar);
    condesoChoice.setItems(condesosCopy);
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
