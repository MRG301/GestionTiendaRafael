package dao.implementacion;

import dao.interfaces.AdministradorDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.entidades.Administrador;
import modelo.entidades.Direccion;
import modelo.enums.RolEmpleado;
import modelo.enums.TipoAdministrador;
import util.ConexionBD;

public class AdministradorDAOImplementacion implements AdministradorDAO {

    private Connection conexion;

    public AdministradorDAOImplementacion() {
        this.conexion = ConexionBD.getInstance().getConexion();
    }

    @Override
    public boolean agregarAdministrador(Administrador administrador) {
        String sqlEmpleado = "INSERT INTO empleado (nombre, apellido, email, telefono, id_direccion, rol, salario) VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id_empleado";
        String sqlAdministrador = "INSERT INTO administrador (id_empleado, tipo_administrador) VALUES (?, ?)";
        try {
            conexion.setAutoCommit(false);

            // Insertar en empleado
            PreparedStatement psEmpleado = conexion.prepareStatement(sqlEmpleado, Statement.RETURN_GENERATED_KEYS);
            psEmpleado.setString(1, administrador.getNombre());
            psEmpleado.setString(2, administrador.getApellido());
            psEmpleado.setString(3, administrador.getEmail());
            psEmpleado.setString(4, administrador.getTelefono());
            psEmpleado.setInt(5, administrador.getDireccion().getIdDireccion());
            psEmpleado.setString(6, administrador.getRol().name());
            psEmpleado.setDouble(7, administrador.getSalario());
            int filasAfectadasEmpleado = psEmpleado.executeUpdate();
            if (filasAfectadasEmpleado == 0) {
                throw new SQLException("Agregar empleado falló, ninguna fila afectada.");
            }
            long idEmpleado = 0;
            try (ResultSet generatedKeys = psEmpleado.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    idEmpleado = generatedKeys.getLong(1);
                } else {
                    throw new SQLException("Agregar empleado falló, no se obtuvo el ID.");
                }
            }

            // Insertar en administrador
            PreparedStatement psAdministrador = conexion.prepareStatement(sqlAdministrador);
            psAdministrador.setLong(1, idEmpleado);
            psAdministrador.setString(2, administrador.getTipoAdministrador().name());
            int filasAfectadasAdministrador = psAdministrador.executeUpdate();
            if (filasAfectadasAdministrador == 0) {
                throw new SQLException("Agregar administrador falló, ninguna fila afectada.");
            }

            administrador.setId((int) idEmpleado); // Asignar el ID al administrador
            conexion.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conexion.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                conexion.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean actualizarAdministrador(Administrador administrador) {
        String sqlActualizarEmpleado = "UPDATE empleado SET nombre = ?, apellido = ?, email = ?, telefono = ?, id_direccion = ?, rol = ?, salario = ? WHERE id_empleado = ?";
        String sqlActualizarAdministrador = "UPDATE administrador SET tipo_administrador = ? WHERE id_administrador = ?";
        try {
            conexion.setAutoCommit(false);

            // Actualizar empleado
            PreparedStatement psEmpleado = conexion.prepareStatement(sqlActualizarEmpleado);
            psEmpleado.setString(1, administrador.getNombre());
            psEmpleado.setString(2, administrador.getApellido());
            psEmpleado.setString(3, administrador.getEmail());
            psEmpleado.setString(4, administrador.getTelefono());
            psEmpleado.setInt(5, administrador.getDireccion().getIdDireccion());
            psEmpleado.setString(6, administrador.getRol().name());
            psEmpleado.setDouble(7, administrador.getSalario());
            psEmpleado.setLong(8, administrador.getId());
            int filasAfectadasEmpleado = psEmpleado.executeUpdate();
            if (filasAfectadasEmpleado == 0) {
                throw new SQLException("Actualizar empleado falló, ninguna fila afectada.");
            }

            // Actualizar administrador
            PreparedStatement psAdministrador = conexion.prepareStatement(sqlActualizarAdministrador);
            psAdministrador.setString(1, administrador.getTipoAdministrador().name());
            psAdministrador.setLong(2, administrador.getId()); // Asumimos que idAdministrador es igual a idEmpleado
            int filasAfectadasAdministrador = psAdministrador.executeUpdate();
            if (filasAfectadasAdministrador == 0) {
                throw new SQLException("Actualizar administrador falló, ninguna fila afectada.");
            }

            conexion.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conexion.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                conexion.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean eliminarAdministrador(int idAdministrador) {
        String sqlEliminarAdministrador = "DELETE FROM administrador WHERE id_administrador = ?";
        String sqlEliminarEmpleado = "DELETE FROM empleado WHERE id_empleado = ?";
        try {
            conexion.setAutoCommit(false);

            // Eliminar administrador
            PreparedStatement psEliminarAdministrador = conexion.prepareStatement(sqlEliminarAdministrador);
            psEliminarAdministrador.setLong(1, idAdministrador);
            int filasAfectadasAdministrador = psEliminarAdministrador.executeUpdate();
            if (filasAfectadasAdministrador == 0) {
                throw new SQLException("Eliminar administrador falló, ninguna fila afectada.");
            }

            // Eliminar empleado (se asume que idAdministrador == idEmpleado)
            PreparedStatement psEliminarEmpleado = conexion.prepareStatement(sqlEliminarEmpleado);
            psEliminarEmpleado.setLong(1, idAdministrador);
            int filasAfectadasEmpleado = psEliminarEmpleado.executeUpdate();
            if (filasAfectadasEmpleado == 0) {
                throw new SQLException("Eliminar empleado falló, ninguna fila afectada.");
            }

            conexion.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conexion.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                conexion.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public Administrador obtenerAdministradorPorId(int idAdministrador) {
        String sql = "SELECT e.*, a.tipo_administrador FROM empleado e JOIN administrador a ON e.id_empleado = a.id_empleado WHERE a.id_administrador = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setLong(1, idAdministrador);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Direccion direccion = new Direccion(
                            rs.getInt("id_direccion"),
                            "", "", "", "", "" // Obtener dirección completa si es necesario
                    );
                    return new Administrador(
                            (int) rs.getInt("id_empleado"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("email"),
                            rs.getString("telefono"),
                            direccion,
                            RolEmpleado.valueOf(rs.getString("rol")),
                            rs.getDouble("salario"),
                            TipoAdministrador.valueOf(rs.getString("tipo_administrador"))
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Administrador> obtenerTodosLosAdministradores() {
        List<Administrador> administradores = new ArrayList<>();
        String sql = "SELECT e.*, a.tipo_administrador FROM empleado e JOIN administrador a ON e.id_empleado = a.id_empleado";
        try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Direccion direccion = new Direccion(
                        rs.getInt("id_direccion"),
                        "", "", "", "", "" // Obtener dirección completa si es necesario
                );
                Administrador administrador = new Administrador(
                        (int) rs.getInt("id_empleado"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("email"),
                        rs.getString("telefono"),
                        direccion,
                        RolEmpleado.valueOf(rs.getString("rol")),
                        rs.getDouble("salario"),
                        TipoAdministrador.valueOf(rs.getString("tipo_administrador"))
                );
                administradores.add(administrador);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return administradores;
    }
}
