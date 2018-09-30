package condesaGUI;

import DbController.HibernateCrud;
import DbModel.Condeso;
import DbModel.Tiendas;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class NuevoHorarioGUI extends Application implements Initializable {
    @FXML private TableView<Condeso> condesosTable;
    @FXML private TableView<Tiendas> tiendasTable;
    @FXML private TableColumn<Condeso, String> condesoName;
    @FXML private  TableColumn<Tiendas, String> tiendasName;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        condesoName.setCellValueFactory(new PropertyValueFactory<Condeso, String>("nombre"));
        tiendasName.setCellValueFactory(new PropertyValueFactory<Tiendas, String>("nombre"));
        condesosTable.getItems().setAll( HibernateCrud.GetAllCondesos());
        tiendasTable.getItems().setAll( HibernateCrud.GetAllTiendas());
    }
}
