package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static ConexionBD instance;
    private Connection conexion;

    // Configuración de la conexión
    private static final String URL = "jdbc:postgresql://localhost:5432/gestionTienda";
    private static final String USER = "postgres";
    private static final String PASSWORD = "uacm123";

    private ConexionBD() {
        try {
            // Registrar el driver de PostgreSQL
            Class.forName("org.postgresql.Driver");
            // Establecer la conexión
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver no encontrado.");
        } catch (SQLException e) {
            System.err.println("Error al conectar con la BD.");
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
