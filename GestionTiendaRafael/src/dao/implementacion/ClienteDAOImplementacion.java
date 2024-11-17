package dao.implementacion;

import dao.interfaces.ClienteDAO;
import dao.interfaces.DireccionDAO;
import dao.interfaces.PersonaDAO;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import modelo.entidades.Cliente;
import modelo.entidades.Persona;
import modelo.enums.TipoCliente;
import util.ConexionBD;

public class ClienteDAOImplementacion implements ClienteDAO {

    private Connection conexion;
    private PersonaDAO personaDAO;
    private DireccionDAO direccionDAO;

    public ClienteDAOImplementacion() {
        this.conexion = ConexionBD.getInstance().getConexion();
        this.personaDAO = new PersonaDAOImplementacion();
        this.direccionDAO = new DireccionDAOImplementacion();
    }

    @Override
    public void agregarCliente(Cliente cliente) throws Exception {
        try {
            conexion.setAutoCommit(false);

            // Agregar la Persona
            personaDAO.agregarPersona(cliente.getDatosPersonales());
            int personaId = cliente.getDatosPersonales().getId(); // Obtener el ID del objeto Persona

            // Agregar el Cliente
            String sqlCliente = "INSERT INTO cliente (persona_id, tipo_cliente, fecha_ultima_actualizacion_tipo) VALUES (?, ?, ?)";
            try (PreparedStatement stmtCliente = conexion.prepareStatement(sqlCliente)) {
                stmtCliente.setInt(1, personaId);
                stmtCliente.setString(2, cliente.getTipoCliente().name());
                stmtCliente.setDate(3, Date.valueOf(cliente.getFechaUltimaActualizacionTipo()));
                stmtCliente.executeUpdate();
            }

            conexion.commit();

        } catch (Exception e) {
            conexion.rollback();
            e.printStackTrace();
            throw new Exception("Error al agregar el cliente: " + e.getMessage());
        } finally {
            conexion.setAutoCommit(true);
        }
    }

    @Override
    public Cliente obtenerClientePorId(int id) throws Exception {
        String sqlCliente = "SELECT * FROM cliente WHERE id = ?";
        try (PreparedStatement stmtCliente = conexion.prepareStatement(sqlCliente)) {
            stmtCliente.setInt(1, id);
            ResultSet rsCliente = stmtCliente.executeQuery();

            if (rsCliente.next()) {
                int clienteId = rsCliente.getInt("id");
                String tipoClienteStr = rsCliente.getString("tipo_cliente");
                TipoCliente tipoCliente = TipoCliente.valueOf(tipoClienteStr);
                Date fechaActualizacion = rsCliente.getDate("fecha_ultima_actualizacion_tipo");
                LocalDate fechaUltimaActualizacionTipo = fechaActualizacion.toLocalDate();
                int personaId = rsCliente.getInt("persona_id");

                // Obtener la Persona usando personaDAO
                Persona datosPersonales = personaDAO.obtenerPersonaPorId(personaId);

                return new Cliente(clienteId, datosPersonales, tipoCliente, fechaUltimaActualizacionTipo);
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener el cliente por ID: " + e.getMessage());
        }
    }

    @Override
    public List<Cliente> obtenerTodosLosClientes() throws Exception {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT id FROM cliente";

        try (PreparedStatement stmt = conexion.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                Cliente cliente = obtenerClientePorId(id);
                if (cliente != null) {
                    clientes.add(cliente);
                }
            }

            return clientes;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener todos los clientes: " + e.getMessage());
        }
    }

    @Override
    public void actualizarCliente(Cliente cliente) throws Exception {
        // Iniciar una transacción
        try {
            conexion.setAutoCommit(false);

            // Actualizar Persona usando personaDAO
            personaDAO.actualizarPersona(cliente.getDatosPersonales());

            // Actualizar Cliente
            String sqlCliente = "UPDATE cliente SET tipo_cliente = ?, fecha_ultima_actualizacion_tipo = ? WHERE id = ?";
            try (PreparedStatement stmtCliente = conexion.prepareStatement(sqlCliente)) {
                stmtCliente.setString(1, cliente.getTipoCliente().name());
                stmtCliente.setDate(2, Date.valueOf(cliente.getFechaUltimaActualizacionTipo()));
                stmtCliente.setInt(3, cliente.getId());
                stmtCliente.executeUpdate();
            }

            // Confirmar la transacción
            conexion.commit();

        } catch (Exception e) {
            conexion.rollback();
            e.printStackTrace();
            throw new Exception("Error al actualizar el cliente: " + e.getMessage());
        } finally {
            conexion.setAutoCommit(true);
        }
    }

    @Override
    public void eliminarCliente(int id) throws Exception {
        // Iniciar una transacción
        try {
            conexion.setAutoCommit(false);

            // Obtener el Cliente
            Cliente cliente = obtenerClientePorId(id);
            if (cliente == null) {
                throw new Exception("Cliente no encontrado con ID: " + id);
            }

            int personaId = cliente.getDatosPersonales().getId();

            // Eliminar Cliente
            String sqlCliente = "DELETE FROM cliente WHERE id = ?";
            try (PreparedStatement stmtCliente = conexion.prepareStatement(sqlCliente)) {
                stmtCliente.setInt(1, id);
                stmtCliente.executeUpdate();
            }

            // Eliminar Persona usando personaDAO
            personaDAO.eliminarPersona(personaId);

            // Confirmar la transacción
            conexion.commit();

        } catch (Exception e) {
            conexion.rollback();
            throw new Exception("Error al eliminar el cliente: " + e.getMessage());
        } finally {
            conexion.setAutoCommit(true);
        }
    }

    @Override
    public List<Cliente> buscarClientesPorId(int id) throws Exception {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Cliente cliente = mapearCliente(rs);
                    clientes.add(cliente);
                }
            }
        }
        return clientes;
    }

    @Override
    public List<Cliente> buscarClientesPorNombre(String nombre) throws Exception {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT c.* FROM cliente c "
                + "JOIN persona p ON c.persona_id = p.id "
                + "WHERE p.nombre ILIKE ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, "%" + nombre + "%"); // Búsqueda parcial e insensible a mayúsculas
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Cliente cliente = mapearCliente(rs);
                    clientes.add(cliente);
                }
            }
        }
        return clientes;
    }

    // Método auxiliar para mapear el ResultSet a un objeto Cliente
    private Cliente mapearCliente(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        int personaId = rs.getInt("persona_id");
        String tipoClienteStr = rs.getString("tipo_cliente");
        TipoCliente tipoCliente = TipoCliente.valueOf(tipoClienteStr.toUpperCase());
        LocalDate fechaUltimaActualizacionTipo = rs.getDate("fecha_ultima_actualizacion_tipo").toLocalDate();

        // Obtener la información de la persona asociada
        Persona persona = personaDAO.obtenerPersonaPorId(personaId);
        if (persona == null) {
            throw new Exception("No se encontró la persona con ID: " + personaId);
        }

        return new Cliente(id, persona, tipoCliente, fechaUltimaActualizacionTipo);
    }

    // Métodos auxiliares para gestionar Persona y Dirección (similares a los proporcionados en EmpleadoDAOImplementacion)
    // Aquí debes incluir los métodos auxiliares para gestionar Persona y Dirección, reutilizando el código del EmpleadoDAOImplementacion.
}
