package condesaGUI;

import DbController.HibernateCrud;
import DbModel.Condeso;
import condeso.Contrato;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

public class PruebaGUI  extends Application implements Initializable {
  @FXML private TableView<DbModel.Condeso> tableView;
  @FXML private TableColumn<DbModel.Condeso, String> condesoName;
  @FXML private  TableColumn<DbModel.Condeso, Contrato> condesoContrato;
  @FXML private  TableColumn<Condeso, Boolean> condesoVespertino;
  @FXML private  TableColumn<Condeso, Boolean> condesoMatutino;
  @FXML private  TableColumn<Condeso, Date> condesoAntiguedad;



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
    condesoName.setCellValueFactory(new PropertyValueFactory<Condeso, String>("nombre"));
    condesoMatutino.setCellValueFactory(new Callback<CellDataFeatures<Condeso,Boolean>, ObservableValue<Boolean>>() {
                                          @Override
                                          public ObservableValue<Boolean> call(CellDataFeatures<Condeso, Boolean> param) {
                                            return param.getValue().Manana();
                                          }
                                        });
    condesoMatutino.setCellFactory(CheckBoxTableCell.forTableColumn(condesoMatutino));
    condesoVespertino.setCellValueFactory(new Callback<CellDataFeatures<Condeso,Boolean>, ObservableValue<Boolean>>() {
      @Override
      public ObservableValue<Boolean> call(CellDataFeatures<Condeso, Boolean> param) {
        return param.getValue().Tarde();
      }
    });
    condesoVespertino.setCellFactory(CheckBoxTableCell.forTableColumn(condesoVespertino));
    condesoContrato.setCellValueFactory(new PropertyValueFactory<Condeso, Contrato>("contrato"));
    condesoAntiguedad.setCellValueFactory(new PropertyValueFactory<Condeso, Date>("antiguedad"));

    tableView.getItems().setAll( HibernateCrud.GetAllCondesos());
  }
}
