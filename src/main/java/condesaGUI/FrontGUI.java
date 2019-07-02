package condesaGUI;

import DbController.CrudOperations;
import DbController.WebApiClient;
import ExcelController.ExcelWriter;
import condeso.Condeso;
import horario.Dias;
import horario.HorarioMaster;
import horario.ShiftType;
import horario.Turnos;
import javafx.application.Application;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.TextAlignment;
import javafx.stage.DirectoryChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.controlsfx.control.PopOver;
import tiendas.Tiendas;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FrontGUI extends Application implements Initializable {

  @FXML private ComboBox<Tiendas>  tiendasComboBox;
  @FXML private Label monthLabel;
  @FXML private GridPane monthGrid;
  private ObservableList<Tiendas> tiendas;
  private ObservableList<Condeso> condesos;
  private CrudOperations webApi;
  private HashMap<LocalDate, Dias> mes;


  private ObservableList<Node> calendarNodes;
  private LocalDate calendar;
  private static final ObservableList
      horario = FXCollections.observableArrayList(getStaticList());
  private List<Dias> dias;

  private static ArrayList<String> getStaticList() {
    ArrayList<String> list = new ArrayList<>();
    list.add("  ");
    for (int i = 8; i < 24; i++){
      list.add(i + "-" + (i+1));
    }
    return list;
  }

  public void start(Stage primaryStage) throws Exception{

  }

  public void initialize(URL location, ResourceBundle resources) {
  }

  public void setInitialValues(ObservableList<Condeso> condesos, ObservableList<Tiendas> tiendas){
    //Populate javafx Nodes with data.
    addHourGrids();
    this.tiendas = tiendas;
    this.condesos = condesos;
    tiendasComboBox.setItems(tiendas);
    Locale spanishLocale=new Locale("es", "ES");
    calendar = LocalDate.now();
    for(Condeso elCondeso : condesos){
      elCondeso.setFecha(calendar);
    }
    monthLabel.setText(calendar.format(DateTimeFormatter.ofPattern("MMMM, YYYY",spanishLocale)));
    calendarNodes = monthGrid.getChildren();
    setCalendarDays();
    addLabelGrids();
    this.webApi = new WebApiClient();
  }

  private void addHourGrids()
  {
    for(int i = 0; i <= 8; i += 8)
    {
      for(int j = 1; j <= 11; j += 2)
      {
        monthGrid.add(createHourGrid(), i, j);
      }
    }
  }

  private GridPane createHourGrid() {
    GridPane result = new GridPane();
    ColumnConstraints column = new ColumnConstraints();
    column.prefWidthProperty().set(50);
    column.setMaxWidth(1234567890);
    result.getColumnConstraints().add(column);
    int hourRow = 0;
    for (String h: getStaticList())
    {
      RowConstraints row = new RowConstraints();
      row.setPrefHeight(40);
      result.getRowConstraints().add(row);
      result.add(new Label(h), 0, hourRow);
      hourRow += 1;
    }
    return result;
  }

  private void addLabelGrids() {

    for (int j = 1; j < 12; j+=2){
      for (int i = 1; i < 8; i++){
        GridPane grid = new GridPane();
        for (int k = 0; k < 8; k++) {
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
        grid.setId(i + "-" + j);
        grid.setStyle("-fx-border-color: black;");
        addLetrasArriba(grid);
        monthGrid.add(grid,i,j);
      }
    }
  }

  private void setCalendarDays() {
    DayOfWeek day = calendar.withDayOfMonth(1).getDayOfWeek();
    int lengthMonth = calendar.getMonth().length(calendar.isLeapYear());
    int lengthLastMonth = calendar.plusMonths(- 1).lengthOfMonth();
    int labelIndex = 0;
    //There are 42 Labels for days
    for (int j = 1; j <= 42; j++) {
      Label label = (Label) calendarNodes.get(labelIndex);
      int dayNum = (j - day.getValue() + 1);
      int f = 0;
      if(dayNum <=0 ) {
        f = Math.floorMod(dayNum, lengthLastMonth);
        if(f == 0){f = lengthLastMonth;}
      }
      else {
        f = Math.floorMod(dayNum, lengthMonth);
        if(f == 0){f = lengthMonth;}
      }
      label.setText(Integer.toString(f));
      labelIndex++;
    }
  }

  private void setHorarioMaster(){
    Tiendas tienda = tiendasComboBox.getValue();
    HorarioMaster master = null;
     mes = null;
    if(tienda != null){
      //TODO poner webapi
      mes = webApi.GetDaysForShop(tienda.getId(), calendar.withDayOfMonth(1));
      //master = tienda.getMaster();
    }
    if (mes != null){
      for (int i = 1; i <= calendar.getMonth().length(calendar.isLeapYear()); i++)
      {
          //Map<LocalDate, Dias> mes = master.getMes();
          Dias dia = mes.get(calendar.withDayOfMonth(i));
          if(dia!=null){
          setDias(dia);
          }
      }
    }
  }

  private void setDias(Dias dia){
    //42 date Labels + 12 listViews of time
    int dateIndex = dia.getDate().getDayOfMonth() + 52 +
        calendar.withDayOfMonth(1).getDayOfWeek().getValue();
    GridPane pane = (GridPane) monthGrid.getChildren().get(dateIndex);
    addGridEventHandler(pane, dia);
    boolean isGM = false;
    for (Turnos turno:dia.getTurnos()
    ) {
      if(turno.getShiftType() == ShiftType.GM){
        setTurnos(dia, turno, pane, isGM);
        isGM = true;
      }else{
        setTurnos(dia, turno, pane, isGM);
      }
    }
  }

  private void setTurnos(Dias dia, Turnos turno, GridPane pane,boolean isGM){
    //considering the first hour is 8 am
    int hourIndex = turno.getInicio() - 7;
    if(hourIndex < 0) {
      System.out.println("hour index must greater than 0");
    }

    Label label = createLabel(dia,turno, pane);
    if(isGM && turno.getShiftType() == ShiftType.GM){
      pane.add(label, turno.getShiftType().ordinal(), hourIndex, 1, turno.getDuracion());
    }else {
      pane.add(label, turno.getShiftType().ordinal()+1, hourIndex, 1, turno.getDuracion());
    }
  }

  private Label createLabel(Dias dia, Turnos turno, GridPane grid) {
    Label label = new Label();
    if(turno.getCondeso() == null){
      label.setStyle("-fx-background-color: black");
    }
    else{
        label.setText(turno.getCondeso().getAbreviacion());
      label.setStyle("-fx-background-color: " + turno.getCondeso().getColor());
    }
    label.setMaxHeight(125462739);
    label.setMaxWidth(1234567890);
    label.addEventHandler(MouseEvent.MOUSE_CLICKED,
        new EventHandler<>() {
          @Override
          public void handle(MouseEvent event) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/editPopOver.fxml"));
            Parent root = null;
            try {
              root = fxmlLoader.load();
            } catch (IOException e) {
              e.printStackTrace();
            }
            PopOver pop = new PopOver(root);
            pop.setAutoFix(false);
            pop.show(label);
            EditPopOverGUI edit = fxmlLoader.getController();
            edit.setInitialValues(turno, dia, grid, label, condesos, tiendas);
            event.consume();
          }
        });
    return label;
  }

  public static void main(String[] args) {
    launch(args);
  }

  public void CondesosClicked(ActionEvent actionEvent) throws Exception {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/condesosGUI.fxml"));
    Parent root = null;
    try {
      root = fxmlLoader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }
    Stage stage = new Stage();
    stage.setScene(new Scene(Objects.requireNonNull(root), Screen.getPrimary().getVisualBounds().getWidth(),
              Screen.getPrimary().getVisualBounds().getMaxY()));
    stage.show();
    CondesoGUI condesos = fxmlLoader.getController();
    condesos.setInitialValues(this.condesos, tiendas);
  }

  public void TiendasClicked(ActionEvent actionEvent) throws Exception {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/tiendasGUI.fxml"));
    Parent root = null;
    try {
      root =  fxmlLoader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }
    Stage stage = new Stage();
    stage.setScene(new Scene(Objects.requireNonNull(root), Screen.getPrimary().getVisualBounds().getWidth(),
            Screen.getPrimary().getVisualBounds().getMaxY()));
    stage.show();
    TiendaGUI tiendasGUI = fxmlLoader.getController();
    tiendasGUI.setInitialValues(tiendas, condesos);
  }

  public void NuevoHorarioClicked(ActionEvent actionEvent) throws Exception{
    CloseOpenWindow("/nuevoHorarioGUI.fxml");
  }

  private void CloseOpenWindow(String filename) throws Exception{
    ((Stage)tiendasComboBox.getScene().getWindow()).close();
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(filename));
    Parent root = null;
    try {
      root = fxmlLoader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }
    Stage stage = new Stage();
    stage.setScene(new Scene(Objects.requireNonNull(root), Screen.getPrimary().getVisualBounds().getWidth(),
        Screen.getPrimary().getVisualBounds().getMaxY()));
    stage.show();
    NuevoHorarioGUI nuevoHorarioGUI = fxmlLoader.getController();
    nuevoHorarioGUI.setInitialValues(condesos, tiendas);
  }

  public void monthBackButton(ActionEvent actionEvent) {
    Locale spanishLocale=new Locale("es", "ES");
    calendar = calendar.plusMonths(-1);
    for(Condeso elCondeso : condesos){
      elCondeso.setFecha(calendar);
    }
    setCalendarDays();
    monthLabel.setText(calendar.format(DateTimeFormatter.ofPattern(
        "MMMM, YYYY",spanishLocale)));
    deleteTurnosLabels();
    setHorarioMaster();
  }

  public void monthNextButton(ActionEvent actionEvent) {
    Locale spanishLocale=new Locale("es", "ES");
    calendar = calendar.plusMonths(1);
    for(Condeso elCondeso : condesos){
      elCondeso.setFecha(calendar);
    }
    setCalendarDays();
    monthLabel.setText(calendar.format(DateTimeFormatter.ofPattern(
        "MMMM, YYYY",spanishLocale)));
    deleteTurnosLabels();
    setHorarioMaster();
  }

  public void getHorario(ActionEvent actionEvent) {
    deleteTurnosLabels();
    setHorarioMaster();
  }

  public void deleteTurnosLabels() {
    int ignoreHourGrids = 0;
    for (Node node: monthGrid.getChildren()) {
      if(node.getClass() == GridPane.class && ignoreHourGrids > 53){
        GridPane grid = (GridPane) node;
        grid.getChildren().clear();
        addLetrasArriba(grid);
      }
      ignoreHourGrids ++;
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
        new EventHandler<>() {
          @Override
          public void handle(MouseEvent event) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/addPopOver.fxml"));
            Parent root = null;
            try {
              root = fxmlLoader.load();
            } catch (IOException e) {
              e.printStackTrace();
            }
            AddPopOverGUI add = fxmlLoader.getController();
            add.setInitialValues(pane, dia, condesos, tiendas);
            PopOver pop = new PopOver(root);
            pop.setAutoFix(false);
            pop.setAnimated(false);
            pop.show(pane);
          }
        });
  }

  public void guardarCambios(ActionEvent actionEvent) {
    //TODO
      var dias = mes.values().toArray(new Dias[100]);
      webApi.UpdateMultipleDays(Arrays.asList(dias));
      webApi.UpdateMultipleCondesos(condesos);
  }

  public void writeExcel(ActionEvent actionEvent) {
    DirectoryChooser directoryChooser = new DirectoryChooser();
    directoryChooser.setTitle("Open Resource File");
    File f = directoryChooser.showDialog(monthGrid.getScene().getWindow());
    if (f != null){
      WorkIndicatorDialog<String> wd = new WorkIndicatorDialog<>(this.monthGrid.getScene().getWindow(), "Creando Excel...");

      wd.addTaskEndNotification(result -> {
        System.out.println(result);
        //wd=null; // don't keep the object, cleanup
      });

      wd.exec("123", inputParam -> {
        ExcelWriter excelWriter = new ExcelWriter(tiendas, condesos, calendar, f.getAbsolutePath());
        try {
          excelWriter.createHorarioMasterExcel();
        } catch (IOException e) {
          e.printStackTrace();
        } catch (Exception e) {
          e.printStackTrace();
        }
        return (1);
      });
    }
  }
}
