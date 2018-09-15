package condesaGUI;

import DbController.HibernateCrud;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
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
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class FrontGUI extends Application implements Initializable {

  @FXML private ComboBox<String>  tiendasComboBox;
  @FXML private  ListView<String> horaList0;
  @FXML private ListView<String>  horaList1;
  @FXML private ListView<String>  horaList2;
  @FXML private ListView<String>  horaList3;
  @FXML private ListView<String>  horaList4;
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
    horaList0.setItems(horario);
    horaList1.setItems(horario);
    horaList2.setItems(horario);
    horaList3.setItems(horario);
    horaList4.setItems(horario);
    tiendasComboBox.getItems().setAll(HibernateCrud.tiendasToList());
    Locale spanishLocale=new Locale("es", "ES");
    calendar = LocalDate.now();
    monthLabel.setText(calendar.format(DateTimeFormatter.ofPattern("MMMM, YYYY",spanishLocale)));
    calendarNodes = monthGrid.getChildren();
    setCalendarDays();
  }

  private void setCalendarDays() {
    DayOfWeek i = calendar.withDayOfMonth(1).getDayOfWeek();
    int lengthMonth = calendar.getMonth().length(calendar.isLeapYear());
    int x = 0;
    for (int j = 1; j <= 35; j++) {
      Label label = (Label) calendarNodes.get(x + 5);
      int dayNum = (j - i.getValue() + 1);
      int f = Math.floorMod(dayNum, lengthMonth + 1);
      if(f==0)f += 1;
      label.setText(Integer.toString(f));
      x++;
    }
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
}
