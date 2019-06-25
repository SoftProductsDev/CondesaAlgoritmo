package condesaGUI;

import DbController.CrudOperations;
import DbController.WebApiClient;
import condeso.Condeso;
import condeso.Contrato;
import condeso.TipoEmpleado;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.hibernate.Hibernate;
import tiendas.Tiendas;

import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class CondesoGUI  extends Application implements Initializable {
    @FXML private TableView<Condeso> tableView;
    @FXML private TableColumn<Condeso, Long> condesoId;
    @FXML private TableColumn<Condeso, String> condesoName;
    @FXML private TableColumn<Condeso, String> condesoAbreviacion;
    @FXML private TableColumn<Condeso, Contrato> condesoContrato;
    @FXML private TableColumn<Condeso, Boolean> condesoVespertino;
    @FXML private TableColumn<Condeso, Boolean> condesoLunch;
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
    @FXML private TextField nombreTextField;
    @FXML private RadioButton masculinoRadio;
    @FXML private RadioButton femeninoRadio;
    @FXML private RadioButton matutinoRadio;
    @FXML private RadioButton lunchRadio;
    @FXML private RadioButton vespertinoRadio;
    @FXML private DatePicker calendario;
    @FXML private RadioButton cajaRadio;
    @FXML private ColorPicker color;
    @FXML private  TextField idTextField;
    @FXML private TextField abrevTextField;
    @FXML private Label errorLabel;
    @FXML private ScrollPane scrollPane;
    private List<Tiendas> tiendas;
    private List<Tiendas> tiendasAddCondeso;
    private ObservableList<Condeso> condesos;
    private CrudOperations hibernateCrud;





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
        primaryStage.setScene(new Scene(root, 7300, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setInitialValues(ObservableList<Condeso> condesos, List<Tiendas> tiendas){
        tiendasAddCondeso = new ArrayList<>();
        hibernateCrud = new WebApiClient();
        this.tiendas = tiendas;//HibernateCrud.GetAllTiendas(); //TODO elliminar
        this.condesos = condesos;
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
        condesoLunch.setCellValueFactory(new Callback<CellDataFeatures<Condeso,Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(CellDataFeatures<Condeso, Boolean> param) {
                return param.getValue().Lunch();
            }
        });
        condesoLunch.setCellFactory(CheckBoxTableCell.forTableColumn(condesoLunch));
        condesoLunch.setEditable(false);
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

        tableView.getItems().setAll(this.condesos);
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, newSelection,
                                                                          oldSelection) -> {
            loadCondesoUpdate();
        });
        initializeListasTiendas();
    }

  private void initializeListasTiendas() {
    GridPane grid = new GridPane();
    ColumnConstraints column = new ColumnConstraints();
    column.prefWidthProperty().set(150);
    column.setMaxWidth(1234567890);
    grid.getColumnConstraints().add(column);
    grid.getColumnConstraints().add(column);
    for (int i = 0; i < tiendas.size(); i++){
      RowConstraints row = new RowConstraints();
      row.setPrefHeight(50);
      grid.getRowConstraints().add(row);
    }
    for (int i = 0; i < tiendas.size(); i++){
      Label label = new Label(tiendas.get(i).getNombre());
      label.setAlignment(Pos.CENTER);
      label.setStyle("-fx-font-size: 14pt;");
      grid.add(label, 0, i );
      CheckBox checkBox = new CheckBox();
      int finalI = i;
      checkBox.addEventHandler(MouseEvent.MOUSE_CLICKED,
          new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
              Tiendas tienda = tiendas.get(finalI);
              if(checkBox.isSelected()){
              tiendasAddCondeso.add(tienda);}
              else{
                tiendasAddCondeso.remove(tienda);
              }
            };
      });
      grid.add(checkBox, 1, i);
    }
    scrollPane.setContent(grid);
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
            lunchRadio.setSelected(condeso.isLunch());
            calendario.setValue(condeso.getAntiguedad());
            cargoComboBox.setValue(condeso.getTipo());
            cajaRadio.setSelected(condeso.isCaja());
            masculinoRadio.setSelected(condeso.isMasculino());
            femeninoRadio.setSelected(condeso.isFemenino());
            nivelComboBox.setValue(nivelComboBox.getItems().get(condeso.getLevel() - 1));
            color.setValue(Color.web(condeso.getColor()));
            Hibernate.initialize(condeso.getDondePuedeTrabajar());
            ObservableList<Tiendas> tiendas =
                FXCollections.observableArrayList(condeso.getDondePuedeTrabajar());
            listTiendas.setItems(tiendas);
        }
        catch (Exception e)
        {

        }
    }

    public void addButtonClicked(ActionEvent actionEvent) {
        Condeso condeso = new Condeso();
        try{condeso.setId(Long.parseLong(idTextField.getText()));}
        catch (Exception e){
            errorLabel.setText("Error: El Id solo puede contener números!");
        }
        try{
        condeso.setNombre(nombreTextField.getText());
        condeso.setAbreviacion(abrevTextField.getText());
        condeso.setContrato(contratoChoiceBox.getValue());
        condeso.setManana(matutinoRadio.isSelected());
        condeso.setLunch(lunchRadio.isSelected());
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
        errorLabel.setText("");}
        catch (NullPointerException e){
          errorLabel.setText("Error: Complete todos los campos");
        }

        if (tiendasAddCondeso.isEmpty()){
          errorLabel.setText("No se seleccionaron tiendas");
        }
        else{
            var wd = new WorkIndicatorDialog(this.tableView.getScene().getWindow(), "Actualizando...");

            wd.addTaskEndNotification(result -> {
                System.out.println(result);
                //wd=null; // don't keep the object, cleanup
            });

            wd.exec("123", inputParam -> {
                try {
                    condeso.setDondePuedeTrabajar(tiendasAddCondeso);
                    hibernateCrud.SaveCondeso(condeso);
                    condesos.add(condeso);
                    tableView.getItems().setAll(condesos);
                } catch (Exception e) {
                    errorLabel.setText("Ya existe un condeso con ese id");
                }
                return (1);
            });

        }
    }

    public void deleteButtonClicked(ActionEvent actionEvent) {

            var wd = new WorkIndicatorDialog(this.tableView.getScene().getWindow(), "Actualizando...");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("CUIDADO!!");
            alert.setHeaderText("Al eliminar un condeso\n este se borrara permanentemente\n de la base de datos y su informacion se perdera.");
            alert.setContentText("Seguro que deseas eliminar a \n" + tableView.getSelectionModel().getSelectedItem().getNombre() + " permanentemente ?");

            ButtonType buttonTypeOne = new ButtonType("Si");
            ButtonType buttonTypeTwo = new ButtonType("No");

            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeOne){


                wd.addTaskEndNotification(result2 -> {
                    System.out.println(result2);
                    //wd=null; // don't keep the object, cleanup
                });

                wd.exec("123", inputParam -> {
                    try {
                        Condeso condeso = tableView.getSelectionModel().getSelectedItem();
                        TimeUnit.SECONDS.sleep(3);
                        hibernateCrud.DeleteCondeso(condeso);
                        condesos.remove(condeso);
                        tableView.getItems().setAll(condesos);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return (1);
                });
                //TODO
            } else if (result.get() == buttonTypeTwo) {
            } else {
            }
    }

    public String updateButtonClicked(ActionEvent actionEvent) {
        Condeso condeso = tableView.getSelectionModel().getSelectedItem();
        errorLabel.setText("");
        long id = -1;
        try{id = Long.parseLong(idTextField.getText());}
        catch (Exception e){
            errorLabel.setText("Error: El Id solo puede contener números!");
        }
        for (Condeso c:condesos) {
            if(c.getId() == id && c != condeso)
            {
                errorLabel.setText("Ya existe un condeso con ese id!!!!");
                return "error";
            }
        }
        if (tiendasAddCondeso.isEmpty()){
            errorLabel.setText("No se seleccionaron tiendas");
            return "error";

        }

        try {
            condeso.setId(id);
          condeso.setNombre(nombreTextField.getText());
          condeso.setAbreviacion(abrevTextField.getText());
          condeso.setContrato(contratoChoiceBox.getValue());
          condeso.setManana(matutinoRadio.isSelected());
          condeso.setLunch(lunchRadio.isSelected());
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
          condeso.setMasculino(masculinoRadio.isSelected());}

        catch (NullPointerException e){
          errorLabel.setText("Error: Complete todos los campos");
          return "error";
        }

        var wd = new WorkIndicatorDialog(this.tableView.getScene().getWindow(), "Actualizando...");

        wd.addTaskEndNotification(result -> {
            System.out.println(result);
            //wd=null; // don't keep the object, cleanup
        });

        wd.exec("123", inputParam -> {
            try {
                condeso.setDondePuedeTrabajar(tiendasAddCondeso);
                hibernateCrud.UpdateCondeso(condeso);
                errorLabel.setText("");
                tableView.getItems().setAll(condesos);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return (1);
        });
        return "success";
    }
}
