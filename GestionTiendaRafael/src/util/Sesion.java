package util;

import modelo.entidades.Usuario;

public class Sesion {

    private static Usuario usuarioActual;

    // Método para iniciar la sesión
    public static void iniciarSesion(Usuario usuario) {
        usuarioActual = usuario;
    }

    // Método para obtener el usuario actual
    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }

    // Método para cerrar la sesión
    public static void cerrarSesion() {
        usuarioActual = null;
    }

}
