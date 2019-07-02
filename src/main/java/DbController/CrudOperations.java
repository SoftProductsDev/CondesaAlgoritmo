package DbController;

import condeso.Condeso;
import condeso.User;
import horario.Dias;
import horario.Plantillas;
import javafx.collections.ObservableList;
import lalo.Disponibilidad;
import tiendas.Tiendas;

import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public interface CrudOperations {
    int SaveCondeso(Condeso condeso) throws KeyStoreException, NoSuchAlgorithmException;
    int UpdateCondeso(Condeso condeso);
    int DeleteCondeso(Condeso condeso);
    List<Condeso> GetAllCondesos();
    int UpdateMultipleCondesos(List<Condeso> condesos);
    String SaveTienda(Tiendas tienda);
    int UpdateTienda(Tiendas tienda);
    int DeleteTienda(Tiendas tienda);
    List<Tiendas> GetAllTiendas();
    int UpdateMultipleTiendas(List<Tiendas> tiendas);
    int UpdatePlantilla(Plantillas plantilla);
    int UpdateMultipleDays(List<Dias> dias);
    int SaveMultipleDays(List<Dias> dias);
    HashMap<LocalDate, Dias> GetDaysForShop(long shopId, LocalDate time);
    Set<Disponibilidad> GetAvailabilities(Month month);
    List<User> GetAllUsers();
    int SaveUser(User user);
    int DeleteUser(User user);
}
