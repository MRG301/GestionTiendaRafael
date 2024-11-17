
package dao.interfaces;

import java.util.List;
import modelo.entidades.Direccion;

public interface DireccionDAO {
    void agregarDireccion(Direccion direccion) throws Exception;
    boolean actualizarDireccion(Direccion direccion) throws Exception;
    boolean eliminarDireccion(int idDireccion) throws Exception;
    Direccion obtenerDireccionPorId(int idDireccion) throws Exception;
    List<Direccion> obtenerTodasLasDirecciones() throws Exception;

}
