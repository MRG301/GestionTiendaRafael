package modelo.entidades;

import java.time.LocalDate;
import modelo.enums.TipoCliente;

public class Cliente {

    private int id;
    private Persona datosPersonales;
    private TipoCliente tipoCliente;
    private LocalDate fechaUltimaActualizacionTipo;

    // Constructor completo
    public Cliente(int id, Persona datosPersonales, TipoCliente tipoCliente, LocalDate fechaUltimaActualizacionTipo) {
        this.id = id;
        this.datosPersonales = datosPersonales;
        this.tipoCliente = tipoCliente;
        this.fechaUltimaActualizacionTipo = fechaUltimaActualizacionTipo;
    }

    // Constructor sin ID (para inserciones)
    public Cliente(Persona datosPersonales, TipoCliente tipoCliente, LocalDate fechaUltimaActualizacionTipo) {
        this.datosPersonales = datosPersonales;
        this.tipoCliente = tipoCliente;
        this.fechaUltimaActualizacionTipo = fechaUltimaActualizacionTipo;
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

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public LocalDate getFechaUltimaActualizacionTipo() {
        return fechaUltimaActualizacionTipo;
    }

    public void setFechaUltimaActualizacionTipo(LocalDate fechaUltimaActualizacionTipo) {
        this.fechaUltimaActualizacionTipo = fechaUltimaActualizacionTipo;
    }

}
