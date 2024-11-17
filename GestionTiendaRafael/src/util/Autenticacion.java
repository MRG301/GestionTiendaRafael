package util;

import dao.implementacion.UsuarioDAOImplementacion;
import dao.interfaces.UsuarioDAO;
import modelo.entidades.Usuario;

public class Autenticacion {

    private UsuarioDAO usuarioDAO;

    public Autenticacion() {
        this.usuarioDAO = new UsuarioDAOImplementacion();
    }

    public Usuario iniciarSesion(String username, String password) throws Exception {
        Usuario usuario = usuarioDAO.obtenerUsuarioPorUsernameYPassword(username, password);
        if (usuario != null) {
            return usuario;
        } else {
            throw new Exception("Credenciales inválidas.");
        }
    }
}
