package DbController;

import condeso.Condeso;
import horario.Plantillas;
import tiendas.Tiendas;

import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

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
}
