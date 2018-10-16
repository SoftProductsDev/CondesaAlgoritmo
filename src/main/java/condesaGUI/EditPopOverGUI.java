package condesaGUI;

import DbController.HibernateCrud;
import DbModel.Condeso;
import DbModel.Dias;
import DbModel.Turnos;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class EditPopOverGUI  implements Initializable {
  @FXML private TextField inicioField;
  @FXML private TextField finField;
  @FXML private ChoiceBox<Condeso> condesoChoice;
  private Turnos turno;
  private Dias dia;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    ObservableList<Condeso> list = FXCollections.observableArrayList(HibernateCrud.GetAllCondesos());
    condesoChoice.setItems(list);
  }

  public void SetInitialValues(Turnos turno, Dias dia){
    this.turno = turno;
    this.dia = dia;
    inicioField.setText(Integer.toString(turno.getInicio()));
    finField.setText(Integer.toString(turno.getFin()));
    condesoChoice.getSelectionModel().select(turno.getCondeso() );
  }

  public void applyChange(ActionEvent actionEvent) {
    turno.setInicio(Integer.parseInt(inicioField.getText()));
    turno.setFin(Integer.parseInt(finField.getText()));
    turno.setCondeso(condesoChoice.getSelectionModel().getSelectedItem());
  }

  public void Delete(ActionEvent actionEvent) {
    dia.getTurnos().remove(turno);
  }
}
