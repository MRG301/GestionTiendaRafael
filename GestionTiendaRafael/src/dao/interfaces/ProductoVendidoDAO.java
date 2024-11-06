
package dao.interfaces;

import java.util.List;
import modelo.entidades.ProductoVendido;

public interface ProductoVendidoDAO {
    boolean agregarProductoVendido(ProductoVendido productoVendido);
    boolean eliminarProductoVendido(long idProductoVendido);
    ProductoVendido obtenerProductoVendidoPorId(long idProductoVendido);
    List<ProductoVendido> obtenerProductosVendidosPorVenta(long idVenta);
}
