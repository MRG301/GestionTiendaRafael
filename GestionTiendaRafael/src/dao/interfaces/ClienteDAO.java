
package dao.interfaces;

import java.util.List;
import modelo.entidades.Cliente;

public interface ClienteDAO {
    boolean agregarCliente(Cliente cliente);
    boolean actualizarCliente(Cliente cliente);
    boolean eliminarCliente(int idCliente);
    Cliente obtenerClientePorId(int idCliente);
    List<Cliente> obtenerTodosLosClientes();
    List<Cliente> obtenerClientesPorTipo(String tipoCliente);
}
