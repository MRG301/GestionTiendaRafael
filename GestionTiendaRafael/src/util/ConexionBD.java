package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static ConexionBD instance;
    private Connection conexion;

    // Configuración de la conexión
    private static final String URL = "jdbc:postgresql://localhost:5432/gestionTienda"; // Cambia 'localhost' y '5432' si es necesario
    private static final String USER = "postgres"; // Reemplaza con tu usuario de PostgreSQL
    private static final String PASSWORD = "uacm123"; // Reemplaza con tu contraseña de PostgreSQL

    // Constructor privado para implementar el patrón Singleton
    private ConexionBD() {
        try {
            // Registrar el driver de PostgreSQL
            Class.forName("org.postgresql.Driver");
            // Establecer la conexión
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver de PostgreSQL no encontrado.");
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos.");
        }
    }

    // Método público para obtener la instancia de ConexionDB
    public static ConexionBD getInstance() {
        if (instance == null) {
            synchronized (ConexionBD.class) {
                if (instance == null) {
                    instance = new ConexionBD();
                }
            }
        }
        return instance;
    }

    // Método para obtener la conexión
    public Connection getConexion() {
        return conexion;
    }

    // Método para cerrar la conexión (opcional)
    public void cerrarConexion() {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("Conexión cerrada.");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión.");
            }
        }
    }
}
