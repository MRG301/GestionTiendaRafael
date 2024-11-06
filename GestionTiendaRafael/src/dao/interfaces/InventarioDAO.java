
package dao.interfaces;

import java.util.List;
import modelo.entidades.Inventario;

public interface InventarioDAO {
    boolean agregarInventario(Inventario inventario);
    boolean actualizarInventario(Inventario inventario);
    boolean eliminarInventario(int idInventario);
    Inventario obtenerInventarioPorId(int idInventario);
    List<Inventario> obtenerTodosLosInventarios();
    Inventario obtenerInventarioPorProducto(long idProducto);
}
