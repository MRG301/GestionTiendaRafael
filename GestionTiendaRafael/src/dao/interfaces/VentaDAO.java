
package dao.interfaces;

import java.time.LocalDate;
import java.util.List;
import modelo.entidades.Venta;

public interface VentaDAO {
    boolean agregarVenta(Venta venta);
    boolean actualizarVenta(Venta venta);
    boolean eliminarVenta(long idVenta);
    Venta obtenerVentaPorId(long idVenta);
    List<Venta> obtenerTodasLasVentas();
    List<Venta> obtenerVentasPorFecha(LocalDate fecha);
}
