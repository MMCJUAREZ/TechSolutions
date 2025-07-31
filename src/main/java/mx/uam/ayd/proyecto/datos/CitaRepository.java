package mx.uam.ayd.proyecto.datos;

import mx.uam.ayd.proyecto.negocio.modelo.Cita;
import mx.uam.ayd.proyecto.negocio.modelo.Paciente;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

/**
 * Repositorio para la entidad Cita
 */
public interface CitaRepository extends CrudRepository<Cita, Integer> {

    /**
     * Recupera todas las citas asociadas a un paciente
     * * @param paciente El paciente del cual se quieren obtener las citas
     * @return Una lista de citas del paciente
     */
    public List<Cita> findByPaciente(Paciente paciente);
}