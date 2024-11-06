
package dao.interfaces;

import java.util.List;
import modelo.entidades.Administrador;

public interface AdministradorDAO {
    boolean agregarAdministrador(Administrador administrador);
    boolean actualizarAdministrador(Administrador administrador);
    boolean eliminarAdministrador(long idAdministrador);
    Administrador obtenerAdministradorPorId(long idAdministrador);
    List<Administrador> obtenerTodosLosAdministradores();
}
