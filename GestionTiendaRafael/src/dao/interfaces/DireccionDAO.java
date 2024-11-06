
package dao.interfaces;

import java.util.List;
import modelo.entidades.Direccion;

public interface DireccionDAO {
    boolean agregarDireccion(Direccion direccion);
    boolean actualizarDireccion(Direccion direccion);
    boolean eliminarDireccion(int idDireccion);
    Direccion obtenerDireccionPorId(int idDireccion);
    List<Direccion> obtenerTodasLasDirecciones();
}
