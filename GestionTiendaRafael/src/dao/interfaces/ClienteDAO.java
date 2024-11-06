
package dao.interfaces;

import java.util.List;
import modelo.entidades.Cliente;

public interface ClienteDAO {
    boolean agregarCliente(Cliente cliente);
    boolean actualizarCliente(Cliente cliente);
    boolean eliminarCliente(long idCliente);
    Cliente obtenerClientePorId(long idCliente);
    List<Cliente> obtenerTodosLosClientes();
    List<Cliente> obtenerClientesPorTipo(String tipoCliente);
}
