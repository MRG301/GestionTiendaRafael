package dao.implementacion;

import dao.interfaces.DireccionDAO;
import java.sql.*;
import dao.interfaces.EmpleadoDAO;
import java.util.ArrayList;
import java.util.List;
import modelo.entidades.Direccion;
import modelo.entidades.Empleado;
import modelo.enums.RolEmpleado;
import util.ConexionBD;

public class EmpleadoDAOImplementacion implements EmpleadoDAO {

    private Connection conexion;
    private DireccionDAO direccionDAO;

    public EmpleadoDAOImplementacion() {
        this.conexion = ConexionBD.getInstance().getConexion();
        this.direccionDAO = (DireccionDAO) new DireccionDAOImplementacion();
    }

    @Override
    public boolean agregarEmpleado(Empleado empleado) {
        // Primero, agregar la dirección si no existe
        Direccion direccion = empleado.getDireccion();
        if (direccion.getIdDireccion() == 0) { // Asumiendo que 0 indica que no ha sido agregado
            boolean direccionAgregada = direccionDAO.agregarDireccion(direccion);
            if (!direccionAgregada) {
                return false;
            }
        }

        String sql = "INSERT INTO empleado (nombre, apellido, email, telefono, id_direccion, rol, salario) VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id_empleado";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getApellido());
            ps.setString(3, empleado.getEmail());
            ps.setString(4, empleado.getTelefono());
            ps.setInt(5, empleado.getDireccion().getIdDireccion());
            ps.setString(6, empleado.getRol().name());
            ps.setDouble(7, empleado.getSalario());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int idEmpleado = rs.getInt(1);
                empleado.setId(idEmpleado);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Considera usar un sistema de logging
        }
        return false;
    }

    @Override
    public boolean actualizarEmpleado(Empleado empleado) {
        // Primero, actualizar la dirección
        Direccion direccion = empleado.getDireccion();
        boolean direccionActualizada = direccionDAO.actualizarDireccion(direccion);
        if (!direccionActualizada) {
            return false;
        }

        String sql = "UPDATE empleado SET nombre = ?, apellido = ?, email = ?, telefono = ?, id_direccion = ?, rol = ?, salario = ? WHERE id_empleado = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getApellido());
            ps.setString(3, empleado.getEmail());
            ps.setString(4, empleado.getTelefono());
            ps.setInt(5, empleado.getDireccion().getIdDireccion());
            ps.setString(6, empleado.getRol().name());
            ps.setDouble(7, empleado.getSalario());
            ps.setInt(8, empleado.getId());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Considera usar un sistema de logging
        }
        return false;
    }

    @Override
    public boolean eliminarEmpleado(int idEmpleado) {
        String sql = "DELETE FROM empleado WHERE id_empleado = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idEmpleado);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Considera usar un sistema de logging
        }
        return false;
    }

    @Override
    public Empleado obtenerEmpleadoPorId(int idEmpleado) {
        String sql = "SELECT * FROM empleado WHERE id_empleado = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idEmpleado);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Direccion direccion = direccionDAO.obtenerDireccionPorId(rs.getInt("id_direccion"));
                    Empleado empleado = new Empleado(
                            rs.getInt("id_empleado"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("email"),
                            rs.getString("telefono"),
                            direccion,
                            RolEmpleado.valueOf(rs.getString("rol")),
                            rs.getDouble("salario")
                    );
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
        try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Direccion direccion = direccionDAO.obtenerDireccionPorId(rs.getInt("id_direccion"));
                Empleado empleado = new Empleado(
                        rs.getInt("id_empleado"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("email"),
                        rs.getString("telefono"),
                        direccion,
                        RolEmpleado.valueOf(rs.getString("rol")),
                        rs.getDouble("salario")
                );
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
                    Direccion direccion = direccionDAO.obtenerDireccionPorId(rs.getInt("id_direccion"));
                    Empleado empleado = new Empleado(
                            rs.getInt("id_empleado"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("email"),
                            rs.getString("telefono"),
                            direccion,
                            RolEmpleado.valueOf(rs.getString("rol")),
                            rs.getDouble("salario")
                    );
                    empleados.add(empleado);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Considera usar un sistema de logging
        }
        return empleados;
    }
}
