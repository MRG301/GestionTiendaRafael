package dao.interfaces;

import java.util.List;
import modelo.entidades.Persona;

public interface PersonaDAO {

    void agregarPersona(Persona persona) throws Exception;
    boolean actualizarPersona(Persona persona) throws Exception;
    boolean eliminarPersona(int idPersona) throws Exception;
    Persona obtenerPersonaPorId(int idPersona) throws Exception;
    List<Persona> obtenerTodasLasPersonas() throws Exception;
}
