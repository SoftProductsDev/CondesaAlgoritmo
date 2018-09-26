package condesaGUI;

import DbController.HibernateCrud;
import DbModel.Condeso;
import DbModel.Dias;
import DbModel.HorarioMaster;
import DbModel.Turnos;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import DbModel.Tiendas;
import org.hibernate.Hibernate;

public class FrontGUI extends Application implements Initializable {

  @FXML private ComboBox<Tiendas>  tiendasComboBox;
  @FXML private  ListView<String> horaList0;
  @FXML private ListView<String>  horaList1;
  @FXML private ListView<String>  horaList2;
  @FXML private ListView<String>  horaList3;
  @FXML private ListView<String>  horaList4;
  @FXML private ListView<String>  horaList5;
  @FXML private Label monthLabel;
  @FXML private GridPane monthGrid;
  private ObservableList<Node> calendarNodes;
  private LocalDate calendar;
  private static final ObservableList<String>
      horario = FXCollections.observableArrayList(getStaticList());

  private static ArrayList getStaticList() {
    ArrayList list = new ArrayList<>();
    for (int i = 8; i < 24; i++){
      list.add(i + "-" + (i+1));
    }
    return list;
  }


  public void start(Stage primaryStage) throws Exception{

  }

  public void initialize(URL location, ResourceBundle resources) {
    //Populate javafx Nodes with data.
    horaList0.setItems(horario);
    horaList1.setItems(horario);
    horaList2.setItems(horario);
    horaList3.setItems(horario);
    horaList4.setItems(horario);
    horaList5.setItems(horario);
    tiendasComboBox.setItems(FXCollections.observableList(HibernateCrud.GetAllTiendas()));
    Locale spanishLocale=new Locale("es", "ES");
    calendar = LocalDate.now();
    monthLabel.setText(calendar.format(DateTimeFormatter.ofPattern("MMMM, YYYY",spanishLocale)));
    calendarNodes = monthGrid.getChildren();
    setCalendarDays();
    addLabelGrids();
    //setTurnos(GetTestTurno());
  }

  private void addLabelGrids() {

    for (int j = 1; j < 12; j+=2){
      for (int i = 1; i < 8; i++){
        GridPane grid = new GridPane();
        for (int k = 0; k < 7; k++) {
          ColumnConstraints column = new ColumnConstraints();
          column.prefWidthProperty().set(200);
          column.setMaxWidth(1234567890);
          grid.getColumnConstraints().add(column);
        }
        for (int k = 0; k < 16; k++) {
          RowConstraints column = new RowConstraints();
          column.setPrefHeight(400);
          grid.getRowConstraints().add(column);
        }
        grid.setId(i + "-" + j);
        //grid.gridLinesVisibleProperty().set(true);
        grid.setStyle("-fx-padding: 0 0 0 0;");
        monthGrid.add(grid,i,j);
      }
    }
  }

  private void setCalendarDays() {
    DayOfWeek day = calendar.withDayOfMonth(1).getDayOfWeek();
    int lengthMonth = calendar.getMonth().length(calendar.isLeapYear());
    int lengthLastMonth = calendar.plusMonths(- 1).lengthOfMonth();
    int labelIndex = 0;
    for (int j = 1; j <= 42; j++) {
      Label label = (Label) calendarNodes.get(labelIndex + 6);
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
    //Tiendas tienda = tiendasComboBox.getValue();
    //HorarioMaster master = tiendasComboBox.getValue().getMaster();
    HorarioMaster master = createHorario();
    for (int i = 1; i < calendar.getMonth().length(calendar.isLeapYear()); i++)
    {
        Map<LocalDate, Dias> mes = master.getMes();
        Dias dia = mes.get(calendar.withDayOfMonth(i));
        if(dia!=null){
        setDias(dia);
        }
    }
  }

  private void setDias(Dias dia){
    int[] latestTurn = {0,0,0,0,0,0,0};
    for (DbModel.Turnos turno:dia.getTurnos()
    ) {
      latestTurn = setTurnos(turno, dia.getDate(), latestTurn);
    }
  }

  private int[] setTurnos(DbModel.Turnos turno, LocalDate date, int[] latestTurn){
    //considering the first hour is 8 am
    int hourIndex = turno.getInicio() - 8;
    if(hourIndex < 0){
      //Cry
    }

    //column inside "Day"
    int columnIndex = 0;
    for (int j = 0; j < 7; j++){
      if (latestTurn[j] <= turno.getInicio()){
        columnIndex = j;
        latestTurn[j] = turno.getFin();
        break;
      }
    }

    //42 date Labels
    int dateIndex = date.getDayOfMonth() + 46 +
        calendar.withDayOfMonth(1).getDayOfWeek().getValue();
    GridPane pane = (GridPane) monthGrid.getChildren().get(dateIndex);

    for (int i = 0; i < turno.getDuracion(); i++){
    Label label = new Label(turno.getCondeso().getNombre());
    label.setStyle("-fx-background-color: " + turno.getCondeso().getColor());
    label.setMaxHeight(125462739);
    label.setMaxWidth(1234567890);
    pane.add(label, columnIndex, hourIndex);
    hourIndex++;
    }

    return latestTurn;
  }

  public static void main(String[] args) {
    launch(args);
  }

  public void CondesosClicked(ActionEvent actionEvent) throws Exception {
    OpenNewWindow("/condesosGUI.fxml");
  }

  public void TiendasClicked(ActionEvent actionEvent) throws Exception {
    OpenNewWindow("/tiendasGUI.fxml");
  }

  public void NuevoHorarioClicked(ActionEvent actionEvent) throws Exception{
    OpenNewWindow("/nuevoHorarioGUI.fxml");
  }

  private void OpenNewWindow(String filename) throws Exception{
    String sceneFile = filename;
    Parent root = null;
    URL url  = null;
    try {
      url  = getClass().getResource( sceneFile );
      root = FXMLLoader.load( url );
      Stage stage = new Stage();
      stage.setScene(new Scene(root, Screen.getPrimary().getVisualBounds().getWidth(),
          Screen.getPrimary().getVisualBounds().getMaxY()));
      stage.show();
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  public void monthBackButton(ActionEvent actionEvent) {
    Locale spanishLocale=new Locale("es", "ES");
    calendar = calendar.plusMonths(-1);
    setCalendarDays();
    monthLabel.setText(calendar.format(DateTimeFormatter.ofPattern(
        "MMMM, YYYY",spanishLocale)));

  }

  public void monthNextButton(ActionEvent actionEvent) {
    Locale spanishLocale=new Locale("es", "ES");
    calendar = calendar.plusMonths(1);
    setCalendarDays();
    monthLabel.setText(calendar.format(DateTimeFormatter.ofPattern(
        "MMMM, YYYY",spanishLocale)));
  }

  public void getHorario(ActionEvent actionEvent) {
    setHorarioMaster();
  }

  public HorarioMaster createHorario() {
    HorarioMaster horarioMaster = new HorarioMaster();
    horarioMaster.setMes(createMes());
    return horarioMaster;
  }

  private Map<LocalDate, Dias> createMes() {
    Map<LocalDate, Dias> result = new HashMap<>();
    Dias dia1 = createDia(LocalDate.now().withDayOfMonth(1));
    Dias dia2 = createDia2(LocalDate.now().withDayOfMonth(2));
    result.put(dia1.getDate(), dia1);
    result.put(dia2.getDate(), dia2);
    return result;
  }

  private Dias createDia(LocalDate date) {
    Dias result = new Dias();
    result.setDate(date);
    result.setTurnos(createTurnos());
    return result;
  }

  private Dias createDia2(LocalDate date) {
    Dias result = new Dias();
    result.setDate(date);
    result.setTurnos(createTurnos2());
    return result;
  }

  private Set<Turnos> createTurnos2() {
    Set<Turnos> result = new TreeSet<>();
    List<Condeso> condesos = HibernateCrud.GetAllCondesos();

    Turnos turno1 = new Turnos();
    turno1.setInicio(9);
    turno1.setFin(16);
    turno1.setCondeso(condesos.get(9));

    Turnos turno2 = new Turnos();
    turno2.setInicio(16);
    turno2.setFin(21);
    turno2.setCondeso(condesos.get(1));

    Turnos turno3 = new Turnos();
    turno3.setInicio(9);
    turno3.setFin(15);
    turno3.setCondeso(condesos.get(10));

    Turnos turno4 = new Turnos();
    turno4.setInicio(17);
    turno4.setFin(23);
    turno4.setCondeso(condesos.get(12));

    Turnos turno5 = new Turnos();
    turno5.setInicio(17);
    turno5.setFin(23);
    turno5.setCondeso(condesos.get(11));

    Turnos turno6 = new Turnos();
    turno6.setInicio(9);
    turno6.setFin(14);
    turno6.setCondeso(condesos.get(13));

    Turnos turno7 = new Turnos();
    turno7.setInicio(15);
    turno7.setFin(21);
    turno7.setCondeso(condesos.get(14));

    Turnos turno8 = new Turnos();
    turno8.setInicio(16);
    turno8.setFin(22);
    turno8.setCondeso(condesos.get(4));



    result.add(turno1);
    result.add(turno2);
    result.add(turno3);
    result.add(turno4);
    result.add(turno5);
    result.add(turno6);
    result.add(turno7);
    result.add(turno8);

    return result;
  }

  private Set<Turnos> createTurnos() {
    Set<Turnos> result = new TreeSet<>();
    List<Condeso> condesos = HibernateCrud.GetAllCondesos();

    Turnos turno1 = new Turnos();
    turno1.setInicio(12);
    turno1.setFin(20);
    turno1.setCondeso(condesos.get(0));

    Turnos turno2 = new Turnos();
    turno2.setInicio(8);
    turno2.setFin(14);
    turno2.setCondeso(condesos.get(1));

    Turnos turno3 = new Turnos();
    turno3.setInicio(8);
    turno3.setFin(14);
    turno3.setCondeso(condesos.get(2));

    Turnos turno4 = new Turnos();
    turno4.setInicio(14);
    turno4.setFin(22);
    turno4.setCondeso(condesos.get(3));

    Turnos turno5 = new Turnos();
    turno5.setInicio(17);
    turno5.setFin(22);
    turno5.setCondeso(condesos.get(4));

    Turnos turno6 = new Turnos();
    turno6.setInicio(12);
    turno6.setFin(18);
    turno6.setCondeso(condesos.get(5));

    Turnos turno7 = new Turnos();
    turno7.setInicio(16);
    turno7.setFin(22);
    turno7.setCondeso(condesos.get(6));

    Turnos turno8 = new Turnos();
    turno8.setInicio(16);
    turno8.setFin(22);
    turno8.setCondeso(condesos.get(7));



    result.add(turno1);
    result.add(turno2);
    result.add(turno3);
    result.add(turno4);
    result.add(turno5);
    result.add(turno6);
    result.add(turno7);
    result.add(turno8);

    return result;
  }
}
