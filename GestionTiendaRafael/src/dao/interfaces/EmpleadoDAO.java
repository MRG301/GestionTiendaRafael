package dao.interfaces;

import java.util.List;
import modelo.entidades.Empleado;

public interface EmpleadoDAO {

    void agregarEmpleado(Empleado empleado) throws Exception;
    Empleado obtenerEmpleadoPorId(int id) throws Exception;  //revisar
    List<Empleado> obtenerTodosLosEmpleados() throws Exception;
    void actualizarEmpleado(Empleado empleado) throws Exception;
    void eliminarEmpleado(int id) throws Exception;
    List<Empleado> buscarEmpleadosPorId(int id) throws Exception;
    List<Empleado> buscarEmpleadosPorNombre(String nombre) throws Exception;
}
