package dao.implementacion;

import dao.interfaces.ClienteDAO;
import dao.interfaces.DireccionDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.entidades.Cliente;
import modelo.entidades.Direccion;
import util.ConexionBD;

public class ClienteDAOImplementacion implements ClienteDAO {

    private Connection conexion;
    private DireccionDAO direccionDAO;

    
    public ClienteDAOImplementacion() {
        this.conexion = ConexionBD.getInstance().getConexion();
        this.direccionDAO = (DireccionDAO) new DireccionDAOImplementacion(); // Asegúrate de tener esta implementación
    }

    @Override
    public boolean agregarCliente(Cliente cliente) {
        // Primero, agregar la dirección si no existe
        Direccion direccion = cliente.getDireccion();
        if (direccion.getIdDireccion() == 0) { // Asumiendo que 0 indica que no ha sido agregado
            boolean direccionAgregada = direccionDAO.agregarDireccion(direccion);
            if (!direccionAgregada) {
                System.err.println("Error al agregar dirección para el cliente: " + cliente.getNombre());
                return false;
            }
        }

        String sql = "INSERT INTO cliente (nombre, apellido, email, telefono, id_direccion, tipo_cliente) VALUES (?, ?, ?, ?, ?, ?) RETURNING id_cliente";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getEmail());
            ps.setString(4, cliente.getTelefono());
            ps.setInt(5, cliente.getDireccion().getIdDireccion());
            ps.setString(6, cliente.getTipoCliente());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int idCliente = rs.getInt(1);
                cliente.setId(idCliente);
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error al agregar cliente: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean actualizarCliente(Cliente cliente) {
        // Primero, actualizar la dirección
        Direccion direccion = cliente.getDireccion();
        boolean direccionActualizada = direccionDAO.actualizarDireccion(direccion);
        if (!direccionActualizada) {
            System.err.println("Error al actualizar dirección para el cliente: " + cliente.getNombre());
            return false;
        }

        String sql = "UPDATE cliente SET nombre = ?, apellido = ?, email = ?, telefono = ?, id_direccion = ?, tipo_cliente = ? WHERE id_cliente = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getEmail());
            ps.setString(4, cliente.getTelefono());
            ps.setInt(5, cliente.getDireccion().getIdDireccion());
            ps.setString(6, cliente.getTipoCliente());
            ps.setInt(7, cliente.getId());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar cliente: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean eliminarCliente(int idCliente) {
        String sql = "DELETE FROM cliente WHERE id_cliente = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar cliente: " + e.getMessage());
        }
        return false;
    }

    @Override
    public Cliente obtenerClientePorId(int idCliente) {
        String sql = "SELECT * FROM cliente WHERE id_cliente = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Direccion direccion = direccionDAO.obtenerDireccionPorId(rs.getInt("id_direccion"));
                    return new Cliente(
                            rs.getInt("id_cliente"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("email"),
                            rs.getString("telefono"),
                            direccion,
                            rs.getString("tipo_cliente")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener cliente por ID: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Cliente> obtenerTodosLosClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Direccion direccion = direccionDAO.obtenerDireccionPorId(rs.getInt("id_direccion"));
                Cliente cliente = new Cliente(
                        rs.getInt("id_cliente"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("email"),
                        rs.getString("telefono"),
                        direccion,
                        rs.getString("tipo_cliente")
                );
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener todos los clientes: " + e.getMessage());
        }
        return clientes;
    }

    @Override
    public List<Cliente> obtenerClientesPorTipo(String tipoCliente) {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente WHERE tipo_cliente = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, tipoCliente);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Direccion direccion = direccionDAO.obtenerDireccionPorId(rs.getInt("id_direccion"));
                    Cliente cliente = new Cliente(
                            rs.getInt("id_cliente"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("email"),
                            rs.getString("telefono"),
                            direccion,
                            rs.getString("tipo_cliente")
                    );
                    clientes.add(cliente);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener clientes por tipo: " + e.getMessage());
        }
        return clientes;
    }
}
