package dao.implementacion;

import dao.interfaces.DireccionDAO;
import dao.interfaces.PersonaDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.entidades.Direccion;
import modelo.entidades.Persona;
import util.ConexionBD;

public class PersonaDAOImplementacion implements PersonaDAO {

    private Connection conexion;
    private DireccionDAO direccionDAO;

    public PersonaDAOImplementacion() {
        this.conexion = ConexionBD.getInstance().getConexion();
        this.direccionDAO = new DireccionDAOImplementacion();
    }

    @Override
    public void agregarPersona(Persona persona) throws Exception {
        String sqlPersona = "INSERT INTO persona (nombre, apellido, email, telefono, direccion_id) VALUES (?, ?, ?, ?, ?) RETURNING id";
        try (PreparedStatement stmtPersona = conexion.prepareStatement(sqlPersona)) {
            // Agregar la dirección primero
            direccionDAO.agregarDireccion(persona.getDireccion()); // Asegúrate de que este método asigna el ID a persona.getDireccion()
            int direccionId = persona.getDireccion().getIdDireccion();

            stmtPersona.setString(1, persona.getNombre());
            stmtPersona.setString(2, persona.getApellido());
            stmtPersona.setString(3, persona.getEmail());
            stmtPersona.setString(4, persona.getTelefono());
            stmtPersona.setInt(5, direccionId);

            ResultSet rs = stmtPersona.executeQuery();
            if (rs.next()) {
                int personaId = rs.getInt("id");
                persona.setId(personaId); // Asignar el ID al objeto Persona
            } else {
                throw new Exception("Error al obtener el ID de la persona recién insertada.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al agregar la persona: " + e.getMessage());
        }
    }

    @Override
    public boolean actualizarPersona(Persona persona) throws Exception {
        String sqlPersona = "UPDATE persona SET nombre = ?, apellido = ?, email = ?, telefono = ?, direccion_id = ? WHERE id = ?";
        try (PreparedStatement stmtPersona = conexion.prepareStatement(sqlPersona)) {
            // Actualizar la dirección primero
            boolean direccionActualizada = direccionDAO.actualizarDireccion(persona.getDireccion());
            if (!direccionActualizada) {
                throw new Exception("Error al actualizar la dirección de la persona.");
            }

            stmtPersona.setString(1, persona.getNombre());
            stmtPersona.setString(2, persona.getApellido());
            stmtPersona.setString(3, persona.getEmail());
            stmtPersona.setString(4, persona.getTelefono());
            stmtPersona.setInt(5, persona.getDireccion().getIdDireccion());
            stmtPersona.setInt(6, persona.getId());

            int filasAfectadas = stmtPersona.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al actualizar la persona: " + e.getMessage());
        }
    }

    @Override
    public boolean eliminarPersona(int idPersona) throws Exception {
        String sqlPersona = "DELETE FROM persona WHERE id = ?";
        try (PreparedStatement stmtPersona = conexion.prepareStatement(sqlPersona)) {
            // Obtener la persona para eliminar la dirección asociada
            Persona persona = obtenerPersonaPorId(idPersona);
            if (persona == null) {
                throw new Exception("Persona no encontrada con ID: " + idPersona);
            }

            // Eliminar la persona
            stmtPersona.setInt(1, idPersona);
            int filasAfectadas = stmtPersona.executeUpdate();

            if (filasAfectadas > 0) {
                // Eliminar la dirección asociada
                boolean direccionEliminada = direccionDAO.eliminarDireccion(persona.getDireccion().getIdDireccion());
                if (!direccionEliminada) {
                    throw new Exception("Error al eliminar la dirección asociada a la persona.");
                }
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al eliminar la persona: " + e.getMessage());
        }
    }

    @Override
    public Persona obtenerPersonaPorId(int idPersona) throws Exception {
        String sqlPersona = "SELECT * FROM persona WHERE id = ?";
        try (PreparedStatement stmtPersona = conexion.prepareStatement(sqlPersona)) {
            stmtPersona.setInt(1, idPersona);
            ResultSet rsPersona = stmtPersona.executeQuery();

            if (rsPersona.next()) {
                int id = rsPersona.getInt("id");
                String nombre = rsPersona.getString("nombre");
                String apellido = rsPersona.getString("apellido");
                String email = rsPersona.getString("email");
                String telefono = rsPersona.getString("telefono");
                int direccionId = rsPersona.getInt("direccion_id");

                // Obtener la dirección asociada
                Direccion direccion = direccionDAO.obtenerDireccionPorId(direccionId);
                if (direccion == null) {
                    throw new Exception("Dirección no encontrada con ID: " + direccionId);
                }

                return new Persona(id, nombre, apellido, email, telefono, direccion);
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener la persona por ID: " + e.getMessage());
        }
    }

    @Override
    public List<Persona> obtenerTodasLasPersonas() throws Exception {
        List<Persona> personas = new ArrayList<>();
        String sql = "SELECT * FROM persona";
        try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                int direccionId = rs.getInt("direccion_id");

                // Obtener la dirección asociada
                Direccion direccion = direccionDAO.obtenerDireccionPorId(direccionId);
                if (direccion == null) {
                    throw new Exception("Dirección no encontrada con ID: " + direccionId);
                }

                Persona persona = new Persona(id, nombre, apellido, email, telefono, direccion);
                personas.add(persona);
            }
            return personas;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener todas las personas: " + e.getMessage());
        }
    }
}
