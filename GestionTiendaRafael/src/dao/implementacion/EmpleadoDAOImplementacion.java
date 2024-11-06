
package dao.implementacion;

import java.sql.*;
import dao.interfaces.EmpleadoDAO;
import java.util.ArrayList;
import java.util.List;
import modelo.entidades.Empleado;
import modelo.enums.RolEmpleado;
import util.ConexionBD;


public class EmpleadoDAOImplementacion implements EmpleadoDAO {
    private Connection conexion;

    public EmpleadoDAOImplementacion() {
        this.conexion = ConexionBD.getInstance().getConexion();
    }

    @Override
    public boolean agregarEmpleado(Empleado empleado) {
        String sql = "INSERT INTO empleado (nombre, apellido, email, telefono, id_direccion, rol, salario) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getApellido());
            ps.setString(3, empleado.getEmail());
            ps.setString(4, empleado.getTelefono());
            ps.setInt(5, empleado.getDireccion().getIdDireccion());
            ps.setString(6, empleado.getRol().name());
            ps.setDouble(7, empleado.getSalario());
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas == 0) {
                throw new SQLException("Agregar empleado falló, ninguna fila afectada.");
            }
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    empleado.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Agregar empleado falló, no se obtuvo el ID.");
                }
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace(); // Considera usar un sistema de logging
        }
        return false;
    }

    @Override
    public boolean actualizarEmpleado(Empleado empleado) {
        String sql = "UPDATE empleado SET nombre = ?, apellido = ?, email = ?, telefono = ?, id_direccion = ?, rol = ?, salario = ? WHERE id_empleado = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getApellido());
            ps.setString(3, empleado.getEmail());
            ps.setString(4, empleado.getTelefono());
            ps.setInt(5, empleado.getDireccion().getIdDireccion());
            ps.setString(6, empleado.getRol().name());
            ps.setDouble(7, empleado.getSalario());
            ps.setLong(8, empleado.getId());
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Considera usar un sistema de logging
        }
        return false;
    }

    @Override
    public boolean eliminarEmpleado(long idEmpleado) {
        String sql = "DELETE FROM empleado WHERE id_empleado = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setLong(1, idEmpleado);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Considera usar un sistema de logging
        }
        return false;
    }

    @Override
    public Empleado obtenerEmpleadoPorId(long idEmpleado) {
        String sql = "SELECT * FROM empleado WHERE id_empleado = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setLong(1, idEmpleado);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Empleado empleado = new Empleado(
                            rs.getInt("id_empleado"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("email"),
                            rs.getString("telefono"),
                            null, // Deberás obtener la dirección por separado si es necesario
                            RolEmpleado.valueOf(rs.getString("rol")),
                            rs.getDouble("salario")
                    );
                    // Aquí podrías agregar lógica para obtener y asignar la dirección si es necesario
                    return empleado;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Considera usar un sistema de logging
        }
        return null;
    }

    @Override
    public List<Empleado> obtenerTodosLosEmpleados() {
        List<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT * FROM empleado";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Empleado empleado = new Empleado(
                        rs.getInt("id_empleado"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("email"),
                        rs.getString("telefono"),
                        null, // Obtener y asignar la dirección si es necesario
                        RolEmpleado.valueOf(rs.getString("rol")),
                        rs.getDouble("salario")
                );
                // Lógica para obtener y asignar la dirección
                empleados.add(empleado);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Considera usar un sistema de logging
        }
        return empleados;
    }

    @Override
    public List<Empleado> obtenerEmpleadosPorRol(RolEmpleado rol) {
        List<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT * FROM empleado WHERE rol = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, rol.name());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Empleado empleado = new Empleado(
                            rs.getInt("id_empleado"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("email"),
                            rs.getString("telefono"),
                            null, // Obtener y asignar la dirección si es necesario
                            RolEmpleado.valueOf(rs.getString("rol")),
                            rs.getDouble("salario")
                    );
                    // Lógica para obtener y asignar la dirección
                    empleados.add(empleado);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Considera usar un sistema de logging
        }
        return empleados;
    }
}
