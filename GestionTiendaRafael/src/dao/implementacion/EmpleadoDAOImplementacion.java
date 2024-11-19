package dao.implementacion;

import dao.interfaces.DireccionDAO;
import java.sql.*;
import dao.interfaces.EmpleadoDAO;
import dao.interfaces.PersonaDAO;
import dao.interfaces.UsuarioDAO;
import java.util.ArrayList;
import java.util.List;
import modelo.entidades.Direccion;
import modelo.entidades.Empleado;
import modelo.entidades.Persona;
import modelo.entidades.Usuario;
import modelo.enums.Rol;
import util.ConexionBD;

public class EmpleadoDAOImplementacion implements EmpleadoDAO {

    private final Connection conexion;
    private final UsuarioDAO usuarioDAO;
    private final PersonaDAO personaDAO;
    private final DireccionDAO direccionDAO;

    public EmpleadoDAOImplementacion() {
        this.conexion = ConexionBD.getInstance().getConexion();
        this.usuarioDAO = new UsuarioDAOImplementacion();
        this.personaDAO = new PersonaDAOImplementacion();
        this.direccionDAO = new DireccionDAOImplementacion();
    }

    @Override
    public void agregarEmpleado(Empleado empleado) throws Exception {
        try {
            conexion.setAutoCommit(false);

            // Agregar el Usuario
            usuarioDAO.agregarUsuario(empleado.getUsuario());
            int usuarioId = empleado.getUsuario().getId();

            // Agregar la Persona
            personaDAO.agregarPersona(empleado.getDatosPersonales());
            int personaId = empleado.getDatosPersonales().getId();

            // Insertar el Empleado
            String sqlEmpleado = "INSERT INTO empleado (persona_id, puesto, salario, rol, usuario_id) VALUES (?, ?, ?, ?::rol_enum, ?)";
            try (PreparedStatement stmtEmpleado = conexion.prepareStatement(sqlEmpleado)) {
                stmtEmpleado.setInt(1, personaId);
                stmtEmpleado.setString(2, empleado.getPuesto());
                stmtEmpleado.setDouble(3, empleado.getSalario());
                stmtEmpleado.setString(4, empleado.getRol().name());
                stmtEmpleado.setInt(5, usuarioId);
                stmtEmpleado.executeUpdate();
            }

            conexion.commit();

        } catch (Exception e) {
            conexion.rollback();
            e.printStackTrace();
            throw new Exception("Error al agregar el empleado: " + e.getMessage());
        } finally {
            conexion.setAutoCommit(true);
        }
    }

    @Override
    public Empleado obtenerEmpleadoPorId(int id) throws Exception {
        String sqlEmpleado = "SELECT * FROM empleado WHERE id = ?";
        try (PreparedStatement stmtEmpleado = conexion.prepareStatement(sqlEmpleado)) {
            stmtEmpleado.setInt(1, id);
            ResultSet rsEmpleado = stmtEmpleado.executeQuery();

            if (rsEmpleado.next()) {
                int empleadoId = rsEmpleado.getInt("id");
                String puesto = rsEmpleado.getString("puesto");
                double salario = rsEmpleado.getDouble("salario");
                String rolStr = rsEmpleado.getString("rol");
                Rol rol = Rol.valueOf(rolStr);
                int personaId = rsEmpleado.getInt("persona_id");
                int usuarioId = rsEmpleado.getInt("usuario_id");

                // Obtener la Persona usando personaDAO
                Persona datosPersonales = personaDAO.obtenerPersonaPorId(personaId);

                // Obtener el Usuario
                Usuario usuario = usuarioDAO.obtenerUsuarioPorId(usuarioId);

                return new Empleado(empleadoId, datosPersonales, puesto, salario, rol, usuario);
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener el empleado por ID: " + e.getMessage());
        }
    }

    @Override
    public List<Empleado> obtenerTodosLosEmpleados() throws Exception {
        List<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT e.id AS empleado_id, e.puesto, e.salario, e.rol AS empleado_rol, "
                + "p.id AS persona_id, p.nombre, p.apellido, p.email, p.telefono, "
                + "d.id_direccion, d.calle, d.numero, d.ciudad, d.codigo_postal, d.estado, "
                + "u.id AS usuario_id, u.username, u.password "
                + "FROM empleado e "
                + "JOIN persona p ON e.persona_id = p.id "
                + "JOIN direccion d ON p.direccion_id = d.id_direccion "
                + "JOIN usuario u ON e.usuario_id = u.id";

        try (PreparedStatement stmt = conexion.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                // Crear Dirección
                Direccion direccion = new Direccion();
                direccion.setIdDireccion(rs.getInt("id_direccion"));
                direccion.setCalle(rs.getString("calle"));
                direccion.setNumero(rs.getString("numero"));
                direccion.setCiudad(rs.getString("ciudad"));
                direccion.setCodigoPostal(rs.getString("codigo_postal"));
                direccion.setEstado(rs.getString("estado"));

                // Crear Persona
                Persona persona = new Persona();
                persona.setId(rs.getInt("persona_id"));
                persona.setNombre(rs.getString("nombre"));
                persona.setApellido(rs.getString("apellido"));
                persona.setEmail(rs.getString("email"));
                persona.setTelefono(rs.getString("telefono"));
                persona.setDireccion(direccion);

                // Crear Usuario
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("usuario_id"));
                usuario.setUsername(rs.getString("username"));
                usuario.setPassword(rs.getString("password"));
                // También podrías necesitar recuperar los roles

                // Crear y poblar Empleado
                Empleado empleado = new Empleado();
                empleado.setId(rs.getInt("empleado_id"));
                empleado.setPuesto(rs.getString("puesto"));
                empleado.setSalario(rs.getDouble("salario"));
                empleado.setRol(Rol.valueOf(rs.getString("empleado_rol")));
                empleado.setDatosPersonales(persona);
                empleado.setUsuario(usuario);

                empleados.add(empleado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener los empleados: " + e.getMessage());
        }

        return empleados;
    }

    @Override
    public void actualizarEmpleado(Empleado empleado) throws Exception {

        try {
            conexion.setAutoCommit(false);

            // Actualizar Usuario
            usuarioDAO.actualizarUsuario(empleado.getUsuario());

            // Actualizar Persona usando personaDAO
            personaDAO.actualizarPersona(empleado.getDatosPersonales());

            // Actualizar Empleado
            String sqlEmpleado = "UPDATE empleado SET puesto = ?, salario = ?, rol = ? WHERE id = ?";
            try (PreparedStatement stmtEmpleado = conexion.prepareStatement(sqlEmpleado)) {
                stmtEmpleado.setString(1, empleado.getPuesto());
                stmtEmpleado.setDouble(2, empleado.getSalario());
                stmtEmpleado.setString(3, empleado.getRol().name());
                stmtEmpleado.setInt(4, empleado.getId());
                stmtEmpleado.executeUpdate();
            }

            // Confirmar la transacción
            conexion.commit();

        } catch (Exception e) {
            conexion.rollback();
            throw new Exception("Error al actualizar el empleado: " + e.getMessage());
        } finally {
            conexion.setAutoCommit(true);
        }
    }

    @Override
    public void eliminarEmpleado(int empleadoId) throws Exception {
        try {
            conexion.setAutoCommit(false);

            // Obtener el Empleado
            Empleado empleado = obtenerEmpleadoPorId(empleadoId);
            if (empleado == null) {
                throw new Exception("Empleado no encontrado con ID: " + empleadoId);
            }

            int usuarioId = empleado.getUsuario().getId();
            int personaId = empleado.getDatosPersonales().getId();
            int direccionId = empleado.getDatosPersonales().getDireccion().getIdDireccion();

            // Eliminar de empleado
            String sqlDeleteEmpleado = "DELETE FROM empleado WHERE id = ?";
            try (PreparedStatement stmtDeleteEmpleado = conexion.prepareStatement(sqlDeleteEmpleado)) {
                stmtDeleteEmpleado.setInt(1, empleadoId);
                stmtDeleteEmpleado.executeUpdate();
            }

            // Eliminar de usuario
            usuarioDAO.eliminarUsuario(usuarioId);

            // Eliminar de persona
            String sqlDeletePersona = "DELETE FROM persona WHERE id = ?";
            try (PreparedStatement stmtDeletePersona = conexion.prepareStatement(sqlDeletePersona)) {
                stmtDeletePersona.setInt(1, personaId);
                stmtDeletePersona.executeUpdate();
            }

            // Eliminar de dirección
            String sqlDeleteDireccion = "DELETE FROM direccion WHERE id_direccion = ?";
            try (PreparedStatement stmtDeleteDireccion = conexion.prepareStatement(sqlDeleteDireccion)) {
                stmtDeleteDireccion.setInt(1, direccionId);
                stmtDeleteDireccion.executeUpdate();
            }

            conexion.commit();

        } catch (Exception e) {
            if (conexion != null && !conexion.getAutoCommit()) {
                conexion.rollback();
            }
            throw new Exception("Error al eliminar el empleado: " + e.getMessage());
        } finally {
            if (conexion != null) {
                conexion.setAutoCommit(true);
            }
        }
    }

    @Override
    public List<Empleado> buscarEmpleadosPorId(int id) throws Exception {
        List<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT * FROM empleado WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Empleado empleado = mapearEmpleado(rs);
                    empleados.add(empleado);
                }
            }
        }
        return empleados;
    }

    @Override
    public List<Empleado> buscarEmpleadosPorNombre(String nombre) throws Exception {
        List<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT * FROM empleado e JOIN persona p ON e.persona_id = p.id WHERE p.nombre ILIKE ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, "%" + nombre + "%"); // Búsqueda parcial
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Empleado empleado = mapearEmpleado(rs);
                    empleados.add(empleado);
                }
            }
        }
        return empleados;
    }

    private Empleado mapearEmpleado(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        int personaId = rs.getInt("persona_id");
        String puesto = rs.getString("puesto");
        double salario = rs.getDouble("salario");
        String rolStr = rs.getString("rol");
        Rol rol = Rol.valueOf(rolStr.toUpperCase());
        int usuarioId = rs.getInt("usuario_id");

        // Obtener la información de la persona asociada
        Persona persona = personaDAO.obtenerPersonaPorId(personaId);
        if (persona == null) {
            throw new Exception("No se encontró la persona con ID: " + personaId);
        }

        // Obtener la información de la dirección asociada a la persona
        int direccionId = persona.getDireccion().getIdDireccion();
        Direccion direccion = direccionDAO.obtenerDireccionPorId(direccionId);
        if (direccion == null) {
            throw new Exception("No se encontró la dirección con ID: " + direccionId);
        }
        persona.setDireccion(direccion);

        // Obtener la información del usuario asociado si es necesario
        Usuario usuario = usuarioDAO.obtenerUsuarioPorId(usuarioId);

        // Crear y retornar el objeto Empleado
        return new Empleado(id, persona, puesto, salario, rol, usuario);
    }

    // Métodos auxiliares para gestionar Persona y Dirección (similar a los proporcionados en la respuesta anterior)
    // Implementa agregarPersona, actualizarPersona, eliminarPersona, obtenerPersonaPorId, agregarDireccion, actualizarDireccion, obtenerDireccionPorId
    // Aquí debes incluir los métodos auxiliares para gestionar Persona y Dirección, similares a los que te proporcioné anteriormente en el `EmpleadoDAOImplementacion`.
}
