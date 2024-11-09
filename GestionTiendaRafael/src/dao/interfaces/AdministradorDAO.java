
package dao.interfaces;

import java.util.List;
import modelo.entidades.Administrador;

public interface AdministradorDAO {
    boolean agregarAdministrador(Administrador administrador);
    boolean actualizarAdministrador(Administrador administrador);
    boolean eliminarAdministrador(int idAdministrador);
    Administrador obtenerAdministradorPorId(int idAdministrador);
    List<Administrador> obtenerTodosLosAdministradores();
}
