package condesaGUI;

import condeso.Condeso;
import condeso.HorasMes;
import horario.Dias;
import horario.Turnos;
import horario.ShiftType;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import org.controlsfx.control.PopOver;
import tiendas.Tiendas;

public class AddPopOverGUI implements Initializable {
  @FXML private ChoiceBox<ShiftType> tipoChoice;
  @FXML private TextField inicioField;
  @FXML private TextField finField;
  @FXML private ChoiceBox<Condeso> condesoChoice;
  @FXML private ChoiceBox<Condeso> condesoTodosChoice;
  private GridPane gridPane;
  private Dias dia;
  private ObservableList<Condeso> condesos;
  private ObservableList<Tiendas> tiendas;
  private HashMap<LocalDate, Dias> diasEditados;

  public boolean addTurno(ActionEvent actionEvent) {
    Turnos turno = new Turnos();
    int inicio = Integer.parseInt(inicioField.getText());
    int fin = Integer.parseInt(finField.getText());
    if(inicio > 7 && inicio < 24 && fin > 8 && fin < 25){
      turno.setInicio(inicio);
      turno.setFin(fin);
    }else{
      Alert alert = new Alert(AlertType.ERROR);
      alert.setContentText("No se ingreso un horario valido");
      alert.show();
      return true;
    }
    Condeso selectedCondeso = null;
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
        selectedCondeso = condesoTodosChoice.getSelectionModel().getSelectedItem();
        turno.setCondeso(selectedCondeso);
      } else if (result.get() == buttonTypeTwo) {
        selectedCondeso = condesoChoice.getSelectionModel().getSelectedItem();
        turno.setCondeso(selectedCondeso);
      } else {
        Alert alertFinal = new Alert(Alert.AlertType.CONFIRMATION);
        alertFinal.setTitle("No se selecciono ninguno");
        alertFinal.setHeaderText("No se ha seleccionado ningun condeso\n por lo tanto no habra cambio en el turno.");
      }
    }else if(condesoTodosChoice.getSelectionModel().getSelectedItem() != null){
      selectedCondeso = condesoTodosChoice.getSelectionModel().getSelectedItem();
      turno.setCondeso(selectedCondeso);
    }else{
      selectedCondeso = condesoChoice.getSelectionModel().getSelectedItem();
      turno.setCondeso(selectedCondeso);
    }
    LocalDate fecha = dia.getDate();
    if(selectedCondeso != null){
      long id = selectedCondeso.getId();
      for(Condeso condeso : condesos){
        if(id == condeso.getId()){
          selectedCondeso = condeso;
          break;
        }
      }
//      HorasMes horas = selectedCondeso.getHorasMes().get(LocalDate.of(fecha.getYear(), fecha.getMonth(), 1));
//      if(horas != null){
//        int lasHoras = horas.getHoras();
//        horas.setHoras(lasHoras + turno.getDuracion());
//      }
      selectedCondeso.horasMesCalculadas += turno.getDuracion();
    }

    if (tipoChoice.getValue() == null){

    }else if (tipoChoice.getValue() == ShiftType.GM){
      turno.setShiftType(tipoChoice.getValue());
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Elegir columna");
      alert.setHeaderText("Hay 2 columnas de gm");
      alert.setContentText("Elija una:");

      ButtonType buttonTypeOne = new ButtonType("GM-1");
      ButtonType buttonTypeTwo = new ButtonType("GM-2");

      alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

      Optional<ButtonType> result = alert.showAndWait();
      if (result.get() == buttonTypeOne){
        gridPane.add(createLabel(turno),turno.getShiftType().ordinal(), turno.getInicio() - 7,
            1, turno.getDuracion());
        dia.getTurnos().add(turno);
        return true;
      } else if (result.get() == buttonTypeTwo) {
        gridPane.add(createLabel(turno),turno.getShiftType().ordinal() + 1, turno.getInicio() - 7,
            1, turno.getDuracion());
        dia.getTurnos().add(turno);
      } else {
        Alert alertFinal = new Alert(Alert.AlertType.CONFIRMATION);
        alertFinal.setTitle("No se selecciono ninguno");
        alertFinal.setHeaderText("No se ha seleccionado ninguna columna\n por lo tanto se agregara el turno.");
        return true;
      }

    }else{
      turno.setShiftType(tipoChoice.getValue());
    }
    gridPane.add(createLabel(turno),turno.getShiftType().ordinal() + 1, turno.getInicio() - 7,
        1, turno.getDuracion());
    dia.getTurnos().add(turno);
    diasEditados.put(dia.getDate(), dia);
    return true;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }

  public void setInitialValues(GridPane grid, Dias dia, ObservableList<Condeso> condesos,
                               ObservableList<Tiendas> tiendas, HashMap<LocalDate, Dias> diasEditados){
    this.gridPane = grid;
    this.dia = dia;
    this.tiendas = tiendas;
    this.condesos = condesos;
    this.diasEditados = diasEditados;
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
    ObservableList<ShiftType> turnos = FXCollections.observableArrayList(ShiftType.values());
    condesoTodosChoice.setItems(condesos);
    condesosCopy.removeAll(aBorrar);
    condesoChoice.setItems(condesosCopy);
    tipoChoice.setItems(turnos);
  }

  private Label createLabel(Turnos turno) {
    Label label = null;
    if(turno != null && turno.getCondeso() != null){
      label = new Label(turno.getCondeso().getNombre());
      label.setStyle("-fx-background-color: " + turno.getCondeso().getColor());
      //label.setStyle();
      label.setMaxHeight(125462739);
      label.setMaxWidth(1234567890);
      Label finalLabel = label;
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
              pop.show(finalLabel);
              EditPopOverGUI edit = (EditPopOverGUI) fxmlLoader.getController();
              edit.setInitialValues(turno, dia, gridPane, finalLabel, condesos, tiendas, diasEditados);
              event.consume();
            };
          });
    }
    return label;
  }
}
