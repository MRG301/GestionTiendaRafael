package dao.interfaces;

import java.util.List;
import modelo.entidades.Cliente;

public interface ClienteDAO {

    void agregarCliente(Cliente cliente) throws Exception;
    Cliente obtenerClientePorId(int id) throws Exception;
    List<Cliente> obtenerTodosLosClientes() throws Exception;
    void actualizarCliente(Cliente cliente) throws Exception;
    void eliminarCliente(int id) throws Exception;
    List<Cliente> buscarClientesPorId(int id) throws Exception;
    List<Cliente> buscarClientesPorNombre(String nombre) throws Exception;
}
