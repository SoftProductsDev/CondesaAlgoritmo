package condesaGUI;

import DbController.CrudOperations;
import DbController.HibernateCrud;
import horario.Dias;
import horario.Plantillas;
import horario.Turnos;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import org.controlsfx.control.PopOver;
import org.controlsfx.control.ToggleSwitch;
import org.hibernate.Hibernate;
import tiendas.Tiendas;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeSet;

public class PlantillaGUI   extends Application implements Initializable {
    @FXML private ChoiceBox<Plantillas> nombreChoice;
    @FXML private ChoiceBox<Tiendas> tiendasChoice;
    @FXML private GridPane hourGrid1;
    @FXML private GridPane hourGrid2;
    @FXML private GridPane hourGrid3;
    @FXML private GridPane hourGrid4;
    @FXML private GridPane weekGrid;
    @FXML private GridPane weekGrid1;
    @FXML private ChoiceBox<Tiendas> tiendasChoiceNueva;
    @FXML private TextField nombrePlantilla;
    @FXML private ToggleSwitch toggleEditar;
    private List<Dias> dias;
    private ObservableList<Tiendas> tiendas = FXCollections.observableArrayList();
    private static final ObservableList<String>
            horario = FXCollections.observableArrayList(getStaticList());
    private Plantillas nuevaPlantilla;
    private List<PopOver> popOvers = new ArrayList<>();
    private CrudOperations hibernateCrud = new HibernateCrud();

    public PlantillaGUI(){}

    private static ArrayList getStaticList() {
        ArrayList list = new ArrayList<>();
        list.add("  ");
        for (int i = 8; i < 24; i++){
            list.add(i + "-" + (i+1));
        }
        return list;
    }

    private void fillHourGridPane(GridPane pane){
      for (int i = 0; i < 17; i++){
        pane.add(new Label(horario.get(i)),0, i);
      }
    }

    private static List<Dias> createWeek(){
        List<Dias> dias = new ArrayList<>();
        //This day is a monday, and should always be monday
        LocalDate mondayDate = LocalDate.of(2018,10,22);
        for (int i = 0; i < 7; i++){
            Dias dia = new Dias();
            dia.setDate(mondayDate);
            dia.setTurnos(new TreeSet<>());
            dias.add(i, dia);
            //Adds a day
            mondayDate = mondayDate.plusDays(1);
        }
        return dias;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        String sceneFile = "/plantillasGUI.fxml";
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
        primaryStage.setTitle("Administrador de Plantillas");
        primaryStage.setScene(new Scene(root, 700, 600));
        primaryStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }




    private void addChoiceboxListeners(){
      tiendasChoice.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
        @Override
        public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
          try{
            Tiendas tienda = tiendasChoice.getItems().get(number2.intValue());
            ObservableList<Plantillas> plantillas = FXCollections.observableArrayList(
                tienda.getPlantillasAnteriores());
            nombreChoice.setItems(plantillas);
          }catch (Exception e){}
        }
      });
      nombreChoice.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
        @Override
        public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
          try {
            Plantillas plantilla = nombreChoice.getItems().get(newValue.intValue());
            deleteTurnosLabels(weekGrid1);
            setDias(plantilla.getDias());
          }catch (Exception e){}
        }
      });
    }

  private void setDias(List<Dias> dias) {
    for (Dias dia: dias
    ) {
      for (Turnos turno: dia.getTurnos()
      ) {
        setTurno(turno, dia.getDate().getDayOfWeek().getValue() + 1, dia);
      }

    }
  }

  private void setTurno(Turnos turno, int gridIndex, Dias dia) {
      GridPane grid =  (GridPane) weekGrid1.getChildren().get(gridIndex);
    int hourIndex = turno.getInicio() - 7;
    int columna = turno.getTipoTurno().ordinal();
    grid.add(createLabel(turno, dia, grid), columna + 1, hourIndex,1, turno.getDuracion());
    addGridEventHandler(grid, dia);
  }

  private Node createLabel(Turnos turno, Dias dia,GridPane grid) {
      Label label = new Label("Turno");
      label.setStyle("-fx-background-color: #4286f4; -fx-border-color: black");
      label.setMaxHeight(125462739);
      label.setMaxWidth(1234567890);
      label.addEventHandler(MouseEvent.MOUSE_CLICKED,
        new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent event) {
            if(toggleEditar.isSelected()){
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
              condesaGUI.EditPlantillasPopOverGUI edit = fxmlLoader.getController();
              edit.setInitialValues(turno, dia, grid, label, toggleEditar, weekGrid);
              event.consume();
            };
          }
        });
    return label;
  }

  private void addLabelGrids(GridPane gridToAddLabels) {
            for (int i = 1; i < 8; i++){
                GridPane grid = new GridPane();
                for (int k = 0; k < 7; k++) {
                    ColumnConstraints column = new ColumnConstraints();
                    column.prefWidthProperty().set(200);
                    column.setMaxWidth(1234567890);
                    grid.getColumnConstraints().add(column);
                }
                for (int k = 0; k < 17; k++) {
                    RowConstraints column = new RowConstraints();
                    column.setPrefHeight(400);
                    grid.getRowConstraints().add(column);
                }
                grid.setId(i + "-" + 0);
                //grid.gridLinesVisibleProperty().set(true);
                grid.setStyle("-fx-border-color: black;");
                addLetrasArriba(grid);
                addGridEventHandler(grid, dias.get(i-1));
                gridToAddLabels.add(grid,i,0);
            }
    }

    public void addLetrasArriba(GridPane grid){
        grid.add(createUpLettersLabel("GM") , 0,0);
        grid.add(createUpLettersLabel("GM"), 1,0);
        grid.add(createUpLettersLabel("G"), 2,0);
        grid.add(createUpLettersLabel("F"), 3,0);
        grid.add(createUpLettersLabel("H"), 4,0);
        grid.add(createUpLettersLabel("B"), 5,0);
        grid.add(createUpLettersLabel("R"), 6,0);
        grid.add(createUpLettersLabel("E"), 7,0);
    }

    private Label createUpLettersLabel(String name) {
        Label label = new Label(name);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setAlignment(Pos.CENTER);
        label.setMaxWidth(12345546);
        label.setMaxHeight(12345546);
        label.setStyle("-fx-border-color: black;");
        return label;
    }


    private void addGridEventHandler(GridPane pane, Dias dia) {
        pane.addEventHandler(MouseEvent.MOUSE_CLICKED,
            new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                  if(toggleEditar.isSelected() || pane.getParent().equals(weekGrid)){
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/addPlantillaPopOver.fxml"));
                    Parent root = null;
                    try {
                        root = (Parent) fxmlLoader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    PopOver pop = new PopOver(root);
                    pop.setAnimated(false);
                    pop.setAutoFix(false);
                    pop.show(pane);
                    popOvers.add(pop);
                    AddPlantillasPopOver add = fxmlLoader.getController();
                    add.setInitialValues(pane, dia, toggleEditar, weekGrid);
                }
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

  public void savePlantilla(ActionEvent actionEvent) {
      Tiendas tienda = tiendasChoiceNueva.getSelectionModel().getSelectedItem();
      Plantillas nueva = new Plantillas();
      try{
        nueva.setNombre(nombrePlantilla.getText());
      }catch (Exception e){}
      nueva.setDias(nuevaPlantilla.getDias());
      try {
        Hibernate.initialize(tienda.getPlantillasAnteriores());
        tienda.getPlantillasAnteriores().add(nueva);
      }catch(Exception e){
        tienda.setPlantillasAnteriores(new ArrayList<>());
        tienda.getPlantillasAnteriores().add(nueva);
      }
      hibernateCrud.UpdateTienda(tienda);
      nuevaPlantilla.setDias(createWeek());
      deleteTurnosLabels(weekGrid);
    //ObservableList<Tiendas> tiendas = FXCollections.observableList(HibernateCrud.GetAllTiendas());

    try {
    tiendasChoice.setItems(tiendas);
    nombreChoice.setItems(FXCollections.observableList(new ArrayList<>()));
    }catch(Exception e){}
  }

  public void deleteTurnosLabels(GridPane pane){
    for (Node node: pane.getChildren()
    ) {
      if(node.getClass() == GridPane.class){
        GridPane grid = (GridPane) node;
        if(grid !=hourGrid1 && grid!= hourGrid2 && grid != hourGrid3 && grid != hourGrid4){
          grid.getChildren().clear();
          addLetrasArriba(grid);
        }

      }
    }
  }

  public void setPlantilla(ActionEvent actionEvent) {
      Tiendas tienda = tiendasChoice.getSelectionModel().getSelectedItem();
      Plantillas plantillas = nombreChoice.getSelectionModel().getSelectedItem();
      tienda.setPlantilla(plantillas);
      hibernateCrud.UpdateTienda(tienda);
  }

  public void deletePlantilla(ActionEvent actionEvent) {
    Tiendas tienda = tiendasChoice.getSelectionModel().getSelectedItem();
    Plantillas plantillas = nombreChoice.getSelectionModel().getSelectedItem();
    tienda.getPlantillasAnteriores().remove(plantillas);
    try {
      if(tienda.getPlantilla().equals(plantillas)){
        tienda.setPlantilla(null);}
    }catch(NullPointerException e){}
    hibernateCrud.UpdateTienda(tienda);
    deleteTurnosLabels(weekGrid1);
    //ObservableList<Tiendas> tiendas = FXCollections.observableList(HibernateCrud.GetAllTiendas());
    try {
      tiendasChoice.setItems(tiendas);
      nombreChoice.setItems(FXCollections.observableList(new ArrayList<>()));
    }catch(Exception e){}
  }

  public void updatePlantilla(ActionEvent actionEvent) {
      Plantillas plantilla = nombreChoice.getSelectionModel().getSelectedItem();
      if(plantilla != null) {
        hibernateCrud.UpdatePlantilla(plantilla);
      }
    //ObservableList<Tiendas> tiendas = FXCollections.observableList(HibernateCrud.GetAllTiendas());
    try {
      tiendasChoice.setItems(tiendas);//Todo chcar que sirva
      nombreChoice.setItems(FXCollections.observableList(new ArrayList<>()));
    }catch(Exception e){}
  }

  public void handleClick(MouseEvent mouseEvent) {
      if(nombreChoice.getSelectionModel().getSelectedItem() == null){
        toggleEditar.setSelected(false);
      }
  }

  @FXML
  public void exitApplication(ActionEvent event) {
    for (PopOver pop:popOvers
    ) {
      pop.hide();
    }
    Platform.exit();
  }

  public void deletePopOvers() {
    for (PopOver pop:popOvers
    ) {
      pop.hide();
    }
  }

    public void setInitialValues(List<Tiendas> tiendas) {
        dias = createWeek();
        nuevaPlantilla = new Plantillas();
        nuevaPlantilla.setDias(dias);
        fillHourGridPane(hourGrid1);
        fillHourGridPane(hourGrid2);
        fillHourGridPane(hourGrid3);
        fillHourGridPane(hourGrid4);
        this.tiendas.setAll(tiendas);
        tiendasChoice.setItems(this.tiendas);
        addChoiceboxListeners();
        tiendasChoiceNueva.setItems(this.tiendas);
        addLabelGrids(weekGrid);
        addLabelGrids(weekGrid1);
    }
}
