package modelo.entidades;

import modelo.enums.Rol;

public class Empleado {

    private int id;
    private Persona datosPersonales;
    private String puesto;
    private double salario;
    private Rol rol;
    private Usuario usuario;

    public Empleado() {
    }

    // Constructor completo
    public Empleado(int id, Persona datosPersonales, String puesto, double salario, Rol rol, Usuario usuario) {
        this.id = id;
        this.datosPersonales = datosPersonales;
        this.puesto = puesto;
        this.salario = salario;
        this.rol = rol;
        this.usuario = usuario;
    }

    // Constructor sin ID (para inserciones)
    public Empleado(Persona datosPersonales, String puesto, double salario, Rol rol, Usuario usuario) {
        this.datosPersonales = datosPersonales;
        this.puesto = puesto;
        this.salario = salario;
        this.rol = rol;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Persona getDatosPersonales() {
        return datosPersonales;
    }

    public void setDatosPersonales(Persona datosPersonales) {
        this.datosPersonales = datosPersonales;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
