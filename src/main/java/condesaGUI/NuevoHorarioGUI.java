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
import lalo.Disponibilidad;
import lalo.Parser;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

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
        String filename = "disponibilidad2.txt";
        Set<Disponibilidad> horario = Parser.parse2(filename);
        List<Condeso> allCondesos = lalo.Parser.getFoundCondesos();
        for(Disponibilidad e: horario){
            e.Print();
            System.out.println();
        }
        condesoName.setCellValueFactory(new PropertyValueFactory<Condeso, String>("nombre"));
        tiendasName.setCellValueFactory(new PropertyValueFactory<Tiendas, String>("nombre"));
        //condesosTable.getItems().setAll( allCondesos);
        tiendasTable.getItems().setAll( HibernateCrud.GetAllTiendas());

    }
}
