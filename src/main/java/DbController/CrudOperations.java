package DbController;

import condeso.Condeso;
import horario.Plantillas;
import tiendas.Tiendas;
import java.util.List;

public interface CrudOperations {
    String SaveCondeso(Condeso condeso);
    String UpdateCondeso(Condeso condeso);
    String DeleteCondeso(Condeso condeso);
    List<Condeso> GetAllCondesos();
    void UpdateMultipleCondesos(List<Condeso> condesos);
    String SaveTienda(Tiendas tienda);
    String UpdateTienda(Tiendas tienda);
    String DeleteTienda(Tiendas tienda);
    List<Tiendas> GetAllTiendas();
    void UpdateMultipleTiendas(List<Tiendas> tiendas);
    String UpdatePlantilla(Plantillas plantilla);
}
