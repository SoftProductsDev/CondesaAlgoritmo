package condesaGUI;

import DbController.HibernateCrud;
import DbModel.Condeso;
import condeso.Contrato;
import condeso.TipoEmpleado;
import java.util.List;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import DbModel.Tiendas;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import org.hibernate.Hibernate;

public class CondesoGUI  extends Application implements Initializable {
    @FXML private TableView<DbModel.Condeso> tableView;
    @FXML private TableColumn<Condeso, Long> condesoId;
    @FXML private TableColumn<DbModel.Condeso, String> condesoName;
    @FXML private TableColumn<Condeso, String> condesoAbreviacion;
    @FXML private TableColumn<DbModel.Condeso, Contrato> condesoContrato;
    @FXML private TableColumn<Condeso, Boolean> condesoVespertino;
    @FXML private TableColumn<Condeso, Boolean> condesoMatutino;
    @FXML private TableColumn<Condeso, Date> condesoAntiguedad;
    @FXML private TableColumn<Condeso, Integer> condesoNivel;
    @FXML private TableColumn<Condeso, Boolean> condesoCaja;
    @FXML private TableColumn<Condeso, TipoEmpleado> condesoCargo;
    @FXML private TableColumn<Condeso, String> condesoColor;
    @FXML private TableColumn<Condeso, Boolean> condesoSexo;
    @FXML private ListView<Tiendas> listTiendas;
    @FXML private ComboBox<TipoEmpleado> cargoComboBox;
    @FXML private ComboBox<String> nivelComboBox;
    @FXML private ChoiceBox<Contrato> contratoChoiceBox;
    @FXML private TableView<Tiendas> tiendasTableView;
    @FXML private TableColumn<Tiendas, String> tiendasNombre;
    @FXML private TableColumn<Tiendas, Boolean> tiendasCheckBox;
    @FXML private TextField nombreTextField;
    @FXML private RadioButton masculinoRadio;
    @FXML private RadioButton femeninoRadio;
    @FXML private RadioButton matutinoRadio;
    @FXML private RadioButton vespertinoRadio;
    @FXML private DatePicker calendario;
    @FXML private RadioButton cajaRadio;
    @FXML private ColorPicker color;
    @FXML private  TextField idTextField;
    @FXML private TextField abrevTextField;
    @FXML private Label errorLabel;
    private List<Tiendas> tiendas;




    @Override
    public void start(Stage primaryStage) throws Exception{
        String sceneFile = "/condesosGUI.fxml";
        Parent root = null;
        URL url  = null;
        try
        {
            url  = getClass().getResource( sceneFile );
            root = FXMLLoader.load( url );
            System.out.println( "  fxmlResource = " + sceneFile );
        }
        catch ( Exception ex )
        {
            System.out.println( "Exception on FXMLLoader.load()" );
            System.out.println( "  * url: " + url );
            System.out.println( "  * " + ex );
            System.out.println( "    ----------------------------------------\n" );
            throw ex;
        }
        primaryStage.setTitle("Administrador de Condesos");
        primaryStage.setScene(new Scene(root, 700, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargoComboBox.getItems().setAll(TipoEmpleado.values());
        contratoChoiceBox.getItems().setAll(Contrato.values());
        ArrayList<String> lvlList = new ArrayList<>();
        lvlList.add("Nuevo - 1");
        lvlList.add("Equipo - 2");
        lvlList.add("Bueno - 3");
        nivelComboBox.setItems( FXCollections.observableArrayList(lvlList));
        condesoName.setCellValueFactory(new PropertyValueFactory<Condeso, String>("nombre"));
        condesoAbreviacion.setCellValueFactory(new PropertyValueFactory<Condeso, String>("abreviacion"));
        condesoMatutino.setCellValueFactory(new Callback<CellDataFeatures<Condeso,Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(CellDataFeatures<Condeso, Boolean> param) {
                return param.getValue().Manana();
            }
        });
        condesoMatutino.setCellFactory(CheckBoxTableCell.forTableColumn(condesoMatutino));
        condesoMatutino.setEditable(false);
        condesoVespertino.setCellValueFactory(new Callback<CellDataFeatures<Condeso,Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(CellDataFeatures<Condeso, Boolean> param) {
                return param.getValue().Tarde();
            }
        });
        condesoVespertino.setCellFactory(CheckBoxTableCell.forTableColumn(condesoVespertino));
        condesoVespertino.setEditable(false);
        condesoCaja.setCellValueFactory(new Callback<CellDataFeatures<Condeso,Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(CellDataFeatures<Condeso, Boolean> param) {
                return param.getValue().Nivel();
            }
        });
        condesoCaja.setCellFactory(CheckBoxTableCell.forTableColumn(condesoCaja));
        condesoCaja.setEditable(false);
        condesoContrato.setCellValueFactory(new PropertyValueFactory<Condeso, Contrato>("contrato"));
        condesoAntiguedad.setCellValueFactory(new PropertyValueFactory<Condeso, Date>("antiguedad"));
        condesoCargo.setCellValueFactory(new PropertyValueFactory<Condeso, TipoEmpleado>("tipo"));
        condesoNivel.setCellValueFactory(new PropertyValueFactory<Condeso, Integer>("level"));
        condesoId.setCellValueFactory(new PropertyValueFactory<Condeso, Long>("Id"));
        condesoSexo.setCellValueFactory(new PropertyValueFactory<Condeso, Boolean>("femenino"));
        condesoSexo.setCellFactory( column -> {
            return new TableCell<Condeso, Boolean>(){
                @Override
                protected void updateItem(Boolean item, boolean empty){
                    if (item == null || empty) {
                        setText(null);
                        setStyle("");
                    } else {
                        if(item){setText("Femenino");}
                        else {setText("Masculino");}
                    }
                }
            };
        });
        condesoColor.setCellValueFactory(new PropertyValueFactory<Condeso, String>("color"));
        condesoColor.setCellFactory( column -> {
            return new TableCell<Condeso, String>(){
                @Override
                protected void updateItem(String item, boolean empty){
                    if (item == null || empty) {
                        setText(null);
                        setStyle("");
                    } else {

                        // Fill with a different color.
                        setStyle("-fx-background-color: " + item);
                    }
                }
            };
        });


        tiendasNombre.setCellValueFactory(new PropertyValueFactory<Tiendas, String>("nombre"));
       /*tiendasCheckBox.setCellValueFactory(
                new Callback<CellDataFeatures<Tiendas, Boolean>, ObservableValue<Boolean>>() {
                    @Override
                    public ObservableValue<Boolean> call(CellDataFeatures<Tiendas, Boolean> param) {
                        return param.getValue().selected();
                    }
                });
                */
        tiendasCheckBox.setCellFactory(CheckBoxTableCell.forTableColumn(tiendasCheckBox));
        tiendasCheckBox.setEditable(true);
        tiendas = HibernateCrud.GetAllTiendas();
        tiendasTableView.getItems().setAll(tiendas);

        tableView.getItems().setAll( HibernateCrud.GetAllCondesos());

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, newSelection,
            oldSelection) -> {
            loadCondesoUpdate();
            });
    }

    private void loadCondesoUpdate() {
        try {
            Condeso condeso = tableView.getSelectionModel().getSelectedItem();
            idTextField.setText(Long.toString(condeso.getId()));
            nombreTextField.setText(condeso.getNombre());
            abrevTextField.setText(condeso.getAbreviacion());
            contratoChoiceBox.setValue(condeso.getContrato());
            matutinoRadio.setSelected(condeso.isManana());
            vespertinoRadio.setSelected(condeso.isTarde());
            calendario.setValue(condeso.getAntiguedad());
            cargoComboBox.setValue(condeso.getTipo());
            cajaRadio.setSelected(condeso.isCaja());
            masculinoRadio.setSelected(condeso.isMasculino());
            femeninoRadio.setSelected(condeso.isFemenino());
            nivelComboBox.setValue(nivelComboBox.getItems().get(condeso.getLevel() - 1));
            color.setValue(Color.web(condeso.getColor()));
            Hibernate.initialize(condeso.getDondePuedeTrabajar());
            ObservableList<Tiendas> tiendas = (ObservableList<Tiendas>)condeso.getDondePuedeTrabajar() ;
            listTiendas.setItems(tiendas);
        }
        catch (Exception e)
        {

        }
    }

    public void addButtonClicked(ActionEvent actionEvent) {
        DbModel.Condeso condeso = new Condeso();
        try{condeso.setId(Long.parseLong(idTextField.getText()));}
        catch (Exception e){
            errorLabel.setText("Error: El Id solo puede contener números!");
        }
        condeso.setNombre(nombreTextField.getText());
        condeso.setAbreviacion(abrevTextField.getText());
        condeso.setContrato(contratoChoiceBox.getValue());
        condeso.setManana(matutinoRadio.isSelected());
        condeso.setTarde(vespertinoRadio.isSelected());
        condeso.setAntiguedad(calendario.getValue());
        condeso.setTipo(cargoComboBox.getValue());
        condeso.setLevel(Integer.parseInt(String.valueOf(nivelComboBox.getValue().charAt
                (nivelComboBox.getValue().length() - 1))));
        condeso.setCaja(cajaRadio.isSelected());
        condeso.setMasculino(masculinoRadio.isSelected());
        condeso.setFemenino(femeninoRadio.isSelected());
        String colorHex = color.getValue().toString();
        colorHex = "#" + colorHex.substring(2, 8);
        condeso.setColor(colorHex);

        try {
            HibernateCrud.SaveCondeso(condeso);
        }catch (Exception InvocationTargetException){
            errorLabel.setText("Error: Ese Id ya existe; elija otro!");
        }

        tableView.getItems().setAll( HibernateCrud.GetAllCondesos());
    }

    public void deleteButtonClicked(ActionEvent actionEvent) {
        Condeso condeso = tableView.getSelectionModel().getSelectedItem();
        HibernateCrud.DeleteCondeso(condeso);
        tableView.getItems().setAll( HibernateCrud.GetAllCondesos());
    }

    public void updateButtonClicked(ActionEvent actionEvent) {
        Condeso condeso = tableView.getSelectionModel().getSelectedItem();
        try{condeso.setId(Long.parseLong(idTextField.getText()));}
        catch (Exception e){
            errorLabel.setText("Error: El Id solo puede contener números!");
        }
        condeso.setNombre(nombreTextField.getText());
        condeso.setAbreviacion(abrevTextField.getText());
        condeso.setContrato(contratoChoiceBox.getValue());
        condeso.setManana(matutinoRadio.isSelected());
        condeso.setTarde(vespertinoRadio.isSelected());
        condeso.setAntiguedad(calendario.getValue());
        condeso.setTipo(cargoComboBox.getValue());
        condeso.setLevel(Integer.parseInt(String.valueOf(nivelComboBox.getValue().charAt
            (nivelComboBox.getValue().length() - 1))));
        condeso.setCaja(cajaRadio.isSelected());
        String colorHex = color.getValue().toString();
        colorHex = "#" + colorHex.substring(2, 8);
        condeso.setColor(colorHex);
        condeso.setFemenino(femeninoRadio.isSelected());
        condeso.setMasculino(masculinoRadio.isSelected());
        HibernateCrud.UpdateCondeso(condeso);
        tableView.getItems().setAll( HibernateCrud.GetAllCondesos());
    }
}
