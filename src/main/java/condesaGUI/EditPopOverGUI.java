package condesaGUI;

import DbController.HibernateCrud;
import condeso.Condeso;
import condeso.HorasMes;
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
import javafx.scene.control.*;
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
  @FXML private ChoiceBox<Condeso> condesoTodosChoice;
  private ObservableList<Tiendas> tiendas;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
  }

  public void setInitialValues(Turnos turno, Dias dia, GridPane grid, Label label,
      ObservableList<Condeso> condesos, ObservableList<Tiendas> tiendas){
    this.turno = turno;
    this.dia = dia;
    this.grid = grid;
    this.label = label;
    this.tiendas = tiendas;
    this.condesos = condesos;
    condesoTodosChoice.setItems(condesos);
    condesoTodosChoice.getSelectionModel().select(turno.getCondeso() );
    Map<LocalDate, Dias> master;
    Dias diaD;
    Set<Turnos> turnos;
    ObservableList<Condeso> condesosCopy = FXCollections.observableArrayList();
    condesosCopy.addAll(condesos);
    List<Condeso> aBorrar = new ArrayList<>();
    for(Tiendas tienda:tiendas){
      master = tienda.getMaster().getMes();
        diaD = master.get(LocalDate.of(dia.getDate().getYear(),dia.getDate().getMonth(),  dia.getDate().getDayOfMonth()));
        if (diaD != null) {
          turnos = diaD.getTurnos();
          for(Turnos turnoD:turnos){
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

    condesosCopy.removeAll(aBorrar);
    inicioField.setText(Integer.toString(turno.getInicio()));
    finField.setText(Integer.toString(turno.getFin()));
    condesoChoice.setItems(condesosCopy);
  }

  public boolean applyChange(ActionEvent actionEvent) {
    int duracion1 = turno.getDuracion();
    int inicio = Integer.parseInt(inicioField.getText());
    int fin = Integer.parseInt(finField.getText());
    if(inicio > 7 && inicio < 24 && fin > 8 && fin < 25){
      turno.setInicio(inicio);
      turno.setFin(fin);
    }else{
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("No se ingreso un horario valido");
      alert.show();
      return true;
    }
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
          Condeso elCondeso = turno.getCondeso();
          LocalDate fecha = dia.getDate();
          if(elCondeso != null){
            long id = elCondeso.getId();
            for(Condeso condeso : condesos){
              if(id == condeso.getId()){
                elCondeso = condeso;
                break;
              }
            }
            HorasMes horas = elCondeso.getHorasMes().get(LocalDate.of(fecha.getYear(), fecha.getMonth(), 1));
            if(horas != null){
              int lasHoras = horas.getHoras();
              horas.setHoras(lasHoras - duracion1);
              //HibernateCrud.UpdateCondeso(elCondeso);
            }
          }
          elCondeso = condesoTodosChoice.getSelectionModel().getSelectedItem();
          turno.setCondeso(elCondeso);
          HorasMes horas = elCondeso.getHorasMes().get(LocalDate.of(fecha.getYear(), fecha.getMonth(), 1));
          if(horas != null){
            int lasHoras = horas.getHoras();
            horas.setHoras(lasHoras + turno.getDuracion());
            //HibernateCrud.UpdateCondeso(elCondeso);
          }else{
            horas = new HorasMes();
            horas.setHoras(turno.getDuracion());
            elCondeso.getHorasMes().put(LocalDate.of(fecha.getYear(), fecha.getMonth(), 1),horas);
            //HibernateCrud.UpdateCondeso(elCondeso);
          }



        } else if (result.get() == buttonTypeTwo) {
          Condeso elCondeso = turno.getCondeso();
          LocalDate fecha = dia.getDate();
          if(elCondeso != null){
            long id = elCondeso.getId();
            for(Condeso condeso : condesos){
              if(id == condeso.getId()){
                elCondeso = condeso;
                break;
              }
            }
            HorasMes horas = elCondeso.getHorasMes().get(LocalDate.of(fecha.getYear(), fecha.getMonth(), 1));
            if(horas != null){
              int lasHoras = horas.getHoras();
              horas.setHoras(lasHoras - duracion1);
              //HibernateCrud.UpdateCondeso(elCondeso);
            }
          }
          elCondeso = condesoChoice.getSelectionModel().getSelectedItem();
          turno.setCondeso(elCondeso);
          HorasMes horas = elCondeso.getHorasMes().get(LocalDate.of(fecha.getYear(), fecha.getMonth(), 1));
          if(horas != null){
            int lasHoras = horas.getHoras();
            horas.setHoras(lasHoras + turno.getDuracion());
            //HibernateCrud.UpdateCondeso(elCondeso);
          }else{
            horas = new HorasMes();
            horas.setHoras(turno.getDuracion());
            elCondeso.getHorasMes().put(LocalDate.of(fecha.getYear(), fecha.getMonth(), 1),horas);
            //HibernateCrud.UpdateCondeso(elCondeso);
          }
        } else {
          Alert alertFinal = new Alert(Alert.AlertType.CONFIRMATION);
          alertFinal.setTitle("No se selecciono ninguno");
          alertFinal.setHeaderText("No se ha seleccionado ningun condeso\n por lo tanto no habra cambio en el turno.");
        }
    }else if(condesoTodosChoice.getSelectionModel().getSelectedItem() != null){
      Condeso elCondeso = turno.getCondeso();
      LocalDate fecha = dia.getDate();
      if(elCondeso != null){
        long id = elCondeso.getId();
        for(Condeso condeso : condesos){
          if(id == condeso.getId()){
            elCondeso = condeso;
            break;
          }
        }
        HorasMes horas = elCondeso.getHorasMes().get(LocalDate.of(fecha.getYear(), fecha.getMonth(), 1));
        if(horas != null){
          int lasHoras = horas.getHoras();
          horas.setHoras(lasHoras - duracion1);
          //HibernateCrud.UpdateCondeso(elCondeso);
        }
      }
      elCondeso = condesoTodosChoice.getSelectionModel().getSelectedItem();
      turno.setCondeso(elCondeso);
      HorasMes horas = elCondeso.getHorasMes().get(LocalDate.of(fecha.getYear(), fecha.getMonth(), 1));
      if(horas != null){
        int lasHoras = horas.getHoras();
        horas.setHoras(lasHoras + turno.getDuracion());
        //HibernateCrud.UpdateCondeso(elCondeso);
      }else{
        horas = new HorasMes();
        horas.setHoras(turno.getDuracion());
        elCondeso.getHorasMes().put(LocalDate.of(fecha.getYear(), fecha.getMonth(), 1),horas);
        //HibernateCrud.UpdateCondeso(elCondeso);
      }
    }else{
      Condeso elCondeso = turno.getCondeso();
      LocalDate fecha = dia.getDate();
      if(elCondeso != null){
        long id = elCondeso.getId();
        for(Condeso condeso : condesos){
          if(id == condeso.getId()){
            elCondeso = condeso;
            break;
          }
        }
        HorasMes horas = elCondeso.getHorasMes().get(LocalDate.of(fecha.getYear(), fecha.getMonth(), 1));
        if(horas != null){
          int lasHoras = horas.getHoras();
          horas.setHoras(lasHoras - duracion1);
          //HibernateCrud.UpdateCondeso(elCondeso);
        }
      }
      elCondeso = condesoChoice.getSelectionModel().getSelectedItem();
      turno.setCondeso(elCondeso);
      HorasMes horas = elCondeso.getHorasMes().get(LocalDate.of(fecha.getYear(), fecha.getMonth(), 1));
      if(horas != null){
        int lasHoras = horas.getHoras();
        horas.setHoras(lasHoras + turno.getDuracion());
        //HibernateCrud.UpdateCondeso(elCondeso);
      }else{
        horas = new HorasMes();
        horas.setHoras(turno.getDuracion());
        elCondeso.getHorasMes().put(LocalDate.of(fecha.getYear(), fecha.getMonth(), 1),horas);
        //HibernateCrud.UpdateCondeso(elCondeso);
      }
    }
    grid.getChildren().remove(label);
    //considering the first hour is 8 am
    int hourIndex = turno.getInicio() - 7;
    int  columna;
    if(turno.getTipoTurno() == TipoTurno.GM){
       columna = GridPane.getColumnIndex(label);
    }else{
      columna = turno.getTipoTurno().ordinal() + 1;
    }
    grid.add(createLabel(), columna,  hourIndex,1,turno.getDuracion());
    return true;
  }

  public void Delete(ActionEvent actionEvent) {
    Condeso elCondeso = turno.getCondeso();
    LocalDate fecha = dia.getDate();
    if(elCondeso != null){
      long id = elCondeso.getId();
      for(Condeso condeso : condesos){
        if(id == condeso.getId()){
          elCondeso = condeso;
          break;
        }
      }
      HorasMes horas = elCondeso.getHorasMes().get(LocalDate.of(fecha.getYear(), fecha.getMonth(), 1));
      if(horas != null){
        int lasHoras = horas.getHoras();
        horas.setHoras(lasHoras - turno.getDuracion());
        //HibernateCrud.UpdateCondeso(elCondeso);
      }
    }
    dia.getTurnos().remove(turno);
    grid.getChildren().remove(label);
  }

  public void Empty(){
    Condeso elCondeso = turno.getCondeso();
    LocalDate fecha = dia.getDate();
    if(elCondeso != null){
      long id = elCondeso.getId();
      for(Condeso condeso : condesos){
        if(id == condeso.getId()){
          elCondeso = condeso;
          break;
        }
      }
      HorasMes horas = elCondeso.getHorasMes().get(LocalDate.of(fecha.getYear(), fecha.getMonth(), 1));
      if(horas != null){
        int lasHoras = horas.getHoras();
        horas.setHoras(lasHoras - turno.getDuracion());
      }
    }
    turno.setCondeso(null);
    grid.getChildren().remove(label);
    //considering the first hour is 8 am
    int hourIndex = turno.getInicio() - 7;
    int  columna;
    if(turno.getTipoTurno() == TipoTurno.GM){
      columna = GridPane.getColumnIndex(label);
    }else{
      columna = turno.getTipoTurno().ordinal() + 1;
    }
    grid.add(createLabel(), columna,  hourIndex,1,turno.getDuracion());
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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/editPopOver.fxml")); // TODO EXAMPLE
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
            pop.setAnimated(false);
            pop.show(label);
            EditPopOverGUI edit = (EditPopOverGUI) fxmlLoader.getController();
            edit.setInitialValues(turno, dia, grid, label, condesos, tiendas);
            event.consume();
          };
        });
    return label;
  }
}
