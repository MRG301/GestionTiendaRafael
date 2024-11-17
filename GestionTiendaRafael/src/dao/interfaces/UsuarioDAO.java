package dao.interfaces;

import modelo.entidades.Usuario;

public interface UsuarioDAO {

    void agregarUsuario(Usuario usuario) throws Exception;
    Usuario obtenerUsuarioPorId(int id) throws Exception;
    Usuario obtenerUsuarioPorUsername(String username) throws Exception;
    Usuario obtenerUsuarioPorUsernameYPassword(String username, String password) throws Exception;
    void actualizarUsuario(Usuario usuario) throws Exception;
    void eliminarUsuario(int id) throws Exception;
}
