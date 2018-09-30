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
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.TextAlignment;
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
    list.add("  ");
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
        for (int k = 0; k < 17; k++) {
          RowConstraints column = new RowConstraints();
          column.setPrefHeight(400);
          grid.getRowConstraints().add(column);
        }
        grid.setId(i + "-" + j);
        //grid.gridLinesVisibleProperty().set(true);
        grid.setStyle("-fx-padding: 0 0 0 0;");
        Label label1 = new Label("GM");
        label1.setTextAlignment(TextAlignment.CENTER);
        label1.setAlignment(Pos.CENTER);
        Label label2 = new Label("GM");
        label2.setTextAlignment(TextAlignment.CENTER);
        label2.setAlignment(Pos.CENTER);
        Label label3 = new Label("G");
        label3.setTextAlignment(TextAlignment.CENTER);
        label3.setAlignment(Pos.CENTER);
        Label label4 = new Label("F");
        label4.setTextAlignment(TextAlignment.CENTER);
        label4.setAlignment(Pos.CENTER);
        Label label5 = new Label("H");
        label5.setTextAlignment(TextAlignment.CENTER);
        label5.setAlignment(Pos.CENTER);
        Label label6 = new Label("B");
        label6.setTextAlignment(TextAlignment.CENTER);
        label6.setAlignment(Pos.CENTER);
        Label label7 = new Label("R");
        label7.setTextAlignment(TextAlignment.CENTER);
        label7.setAlignment(Pos.CENTER);
        grid.add(label1, 0,0);
        grid.add(label2, 1,0);
        grid.add(label3, 2,0);
        grid.add(label4, 3,0);
        grid.add(label5, 4,0);
        grid.add(label6, 5,0);
        grid.add(label7, 6,0);
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
    Tiendas tienda = tiendasComboBox.getValue();
     HorarioMaster master = tiendasComboBox.getValue().getMaster();
    //HorarioMaster master = createHorario();
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
    int hourIndex = turno.getInicio() - 7;
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


}
