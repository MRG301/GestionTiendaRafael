package dao.implementacion;

import dao.interfaces.UsuarioDAO;
import modelo.entidades.Usuario;
import util.ConexionBD;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;
import modelo.enums.Rol;

public class UsuarioDAOImplementacion implements UsuarioDAO {

    private final Connection conexion;

    public UsuarioDAOImplementacion() {
        this.conexion = ConexionBD.getInstance().getConexion();
    }

    @Override
    public void agregarUsuario(Usuario usuario) throws Exception {
        String sqlUsuario = "INSERT INTO usuario (username, password) VALUES (?, crypt(?, gen_salt('bf'))) RETURNING id";
        try (PreparedStatement stmtUsuario = conexion.prepareStatement(sqlUsuario)) {
            stmtUsuario.setString(1, usuario.getUsername());
            stmtUsuario.setString(2, usuario.getPassword()); // Contraseña en texto plano, será encriptada en la BD

            ResultSet rs = stmtUsuario.executeQuery();
            if (rs.next()) {
                int usuarioId = rs.getInt("id");
                usuario.setId(usuarioId);

                // Insertar roles
                insertarRolesUsuario(usuario);
            } else {
                throw new Exception("Error al agregar el usuario.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al agregar el usuario: " + e.getMessage());
        }
    }

    @Override
    public Usuario obtenerUsuarioPorId(int id) throws Exception {
        String sqlUsuario = "SELECT * FROM usuario WHERE id = ?";
        try (PreparedStatement stmtUsuario = conexion.prepareStatement(sqlUsuario)) {
            stmtUsuario.setInt(1, id);
            ResultSet rsUsuario = stmtUsuario.executeQuery();

            if (rsUsuario.next()) {
                String username = rsUsuario.getString("username");
                String password = rsUsuario.getString("password");

                // Obtener roles
                Set<Rol> roles = obtenerRolesPorUsuarioId(id);

                return new Usuario(id, username, password, roles);
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener el usuario por ID: " + e.getMessage());
        }
    }

    @Override
    public Usuario obtenerUsuarioPorUsername(String username) throws Exception {
        String sqlUsuario = "SELECT * FROM usuario WHERE username = ?";
        try (PreparedStatement stmtUsuario = conexion.prepareStatement(sqlUsuario)) {
            stmtUsuario.setString(1, username);

            ResultSet rsUsuario = stmtUsuario.executeQuery();

            if (rsUsuario.next()) {
                int id = rsUsuario.getInt("id");
                String password = rsUsuario.getString("password");

                // Obtener roles
                Set<Rol> roles = obtenerRolesPorUsuarioId(id);

                return new Usuario(id, username, password, roles);
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener el usuario por username: " + e.getMessage());
        }
    }

    @Override
    public void actualizarUsuario(Usuario usuario) throws Exception {
        String sqlUpdateUsuario = "UPDATE usuario SET username = ?, password = ? WHERE id = ?";
        try (PreparedStatement stmtUpdate = conexion.prepareStatement(sqlUpdateUsuario)) {
            stmtUpdate.setString(1, usuario.getUsername());
            stmtUpdate.setString(2, usuario.getPassword()); // Actualizar contraseña en texto plano (NO recomendado)
            stmtUpdate.setInt(3, usuario.getId());

            int filasAfectadas = stmtUpdate.executeUpdate();
            if (filasAfectadas > 0) {
                // Actualizar roles
                eliminarRolesUsuario(usuario.getId());
                insertarRolesUsuario(usuario);
            } else {
                throw new Exception("No se pudo actualizar el usuario.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al actualizar el usuario: " + e.getMessage());
        }
    }

    @Override
    public void eliminarUsuario(int usuarioId) throws Exception {
        String sqlDeleteUsuarioRol = "DELETE FROM usuario_rol WHERE usuario_id = ?";
        String sqlDeleteUsuario = "DELETE FROM usuario WHERE id = ?";
        try (PreparedStatement stmtDeleteUsuarioRol = conexion.prepareStatement(sqlDeleteUsuarioRol); PreparedStatement stmtDeleteUsuario = conexion.prepareStatement(sqlDeleteUsuario)) {

            conexion.setAutoCommit(false);

            // Eliminar de usuario_rol
            stmtDeleteUsuarioRol.setInt(1, usuarioId);
            stmtDeleteUsuarioRol.executeUpdate();

            // Eliminar de usuario
            stmtDeleteUsuario.setInt(1, usuarioId);
            stmtDeleteUsuario.executeUpdate();

            conexion.commit();

        } catch (SQLException e) {
            throw new Exception("Error al eliminar el usuario: " + e.getMessage());
        }
    }

    // Métodos auxiliares para gestionar roles
    private void insertarRolesUsuario(Usuario usuario) throws Exception {
        String sqlInsertRol = "INSERT INTO usuario_rol (usuario_id, rol_id) VALUES (?, ?)";
        try (PreparedStatement stmtInsertRol = conexion.prepareStatement(sqlInsertRol)) {
            for (Rol rol : usuario.getRoles()) {
                int rolId = obtenerRolIdPorNombre(rol.name());
                stmtInsertRol.setInt(1, usuario.getId());
                stmtInsertRol.setInt(2, rolId);
                stmtInsertRol.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al insertar roles para el usuario: " + e.getMessage());
        }
    }

    private void eliminarRolesUsuario(int usuarioId) throws Exception {
        String sqlDeleteRoles = "DELETE FROM usuario_rol WHERE usuario_id = ?";
        try (PreparedStatement stmtDeleteRoles = conexion.prepareStatement(sqlDeleteRoles)) {
            stmtDeleteRoles.setInt(1, usuarioId);
            stmtDeleteRoles.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al eliminar roles del usuario: " + e.getMessage());
        }
    }

    private Set<Rol> obtenerRolesPorUsuarioId(int usuarioId) throws Exception {
        Set<Rol> roles = new HashSet<>();
        String sqlRoles = "SELECT r.nombre FROM rol r INNER JOIN usuario_rol ur ON r.id = ur.rol_id WHERE ur.usuario_id = ?";
        try (PreparedStatement stmtRoles = conexion.prepareStatement(sqlRoles)) {
            stmtRoles.setInt(1, usuarioId);
            ResultSet rsRoles = stmtRoles.executeQuery();
            while (rsRoles.next()) {
                String rolNombre = rsRoles.getString("nombre");
                roles.add(Rol.valueOf(rolNombre));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener roles del usuario: " + e.getMessage());
        }
        return roles;
    }

    private int obtenerRolIdPorNombre(String nombreRol) throws Exception {
        String sql = "SELECT id FROM rol WHERE nombre = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, nombreRol);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            } else {
                throw new Exception("Rol no encontrado: " + nombreRol);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener ID del rol: " + e.getMessage());
        }
    }

    @Override
    public Usuario obtenerUsuarioPorUsernameYPassword(String username, String password) throws Exception {
        String sqlUsuario = "SELECT u.id, u.username, u.password, r.nombre AS rol_nombre "
                + "FROM usuario u "
                + "JOIN usuario_rol ur ON u.id = ur.usuario_id "
                + "JOIN rol r ON ur.rol_id = r.id "
                + "WHERE u.username = ? AND u.password = crypt(?, u.password)";
        try (PreparedStatement stmtUsuario = conexion.prepareStatement(sqlUsuario)) {
            stmtUsuario.setString(1, username);
            stmtUsuario.setString(2, password); // Contraseña ingresada por el usuario

            ResultSet rs = stmtUsuario.executeQuery();

            Usuario usuario = null;
            Set<Rol> roles = new HashSet<>();

            while (rs.next()) {
                if (usuario == null) {
                    int id = rs.getInt("id");
                    String usernameDB = rs.getString("username");
                    String passwordDB = rs.getString("password"); // Contraseña encriptada

                    usuario = new Usuario(id, usernameDB, passwordDB, roles);
                }
                String rolNombre = rs.getString("rol_nombre");
                roles.add(Rol.valueOf(rolNombre));
            }

            return usuario;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener el usuario por username y password: " + e.getMessage());
        }
    }

}
