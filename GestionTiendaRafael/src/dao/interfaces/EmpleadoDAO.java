
package dao.interfaces;

import java.util.List;
import modelo.entidades.Empleado;
import modelo.enums.RolEmpleado;

public interface EmpleadoDAO {
    boolean agregarEmpleado(Empleado empleado);
    boolean actualizarEmpleado(Empleado empleado);
    boolean eliminarEmpleado(int idEmpleado);
    Empleado obtenerEmpleadoPorId(int idEmpleado);
    List<Empleado> obtenerTodosLosEmpleados();
    List<Empleado> obtenerEmpleadosPorRol(RolEmpleado rol);
}
