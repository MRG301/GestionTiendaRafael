
package dao.interfaces;

import java.util.List;
import modelo.entidades.Producto;
import modelo.enums.CategoriaTienda;

public interface ProductoDAO {
    boolean agregarProducto(Producto producto);
    boolean actualizarProducto(Producto producto);
    boolean eliminarProducto(long idProducto); // Cambiado a long
    Producto obtenerProductoPorId(long idProducto); // Cambiado a long
    List<Producto> obtenerTodosLosProductos();
    List<Producto> obtenerProductosPorCategoria(CategoriaTienda categoria);
}
