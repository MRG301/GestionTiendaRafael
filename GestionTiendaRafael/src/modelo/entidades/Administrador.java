package modelo.entidades;

import modelo.enums.RolEmpleado;
import modelo.enums.TipoAdministrador;

public class Administrador extends Empleado {

    private TipoAdministrador tipoAdministrador;

    public Administrador(int id, String nombre, String apellido, String email, String telefono, Direccion direccion, RolEmpleado rol, double salario, TipoAdministrador tipoAdministrador) {
        super(id, nombre, apellido, email, telefono, direccion, rol, salario);
        this.tipoAdministrador = tipoAdministrador;
    }

    public TipoAdministrador getTipoAdministrador() {
        return tipoAdministrador;
    }

    public void setTipoAdministrador(TipoAdministrador tipoAdministrador) {
        this.tipoAdministrador = tipoAdministrador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

}
