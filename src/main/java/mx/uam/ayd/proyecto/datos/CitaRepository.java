package mx.uam.ayd.proyecto.datos;

import mx.uam.ayd.proyecto.negocio.modelo.Cita;
import mx.uam.ayd.proyecto.negocio.modelo.Paciente;
import mx.uam.ayd.proyecto.negocio.modelo.TipoConfirmacionCita;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Date;

/**
 * Repositorio para la entidad Cita
 */
public interface CitaRepository extends CrudRepository<Cita, Integer> {

    /**
     * Recupera todas las citas asociadas a un paciente
     * @param paciente El paciente del cual se quieren obtener las citas
     * @return Una lista de citas del paciente
     */
    List<Cita> findByPaciente(Paciente paciente);

    /**
     * Busca citas por estado
     * @param estadoCita El estado de la cita
     * @return Una lista de citas con el estado especificado
     */
    List<Cita> findByEstadoCita(TipoConfirmacionCita estadoCita);

    /**
     * Busca citas por fecha
     * @param fechaCita La fecha de la cita
     * @return Una lista de citas en la fecha especificada
     */
    List<Cita> findByFechaCita(Date fechaCita);

    /**
     * Busca citas por paciente y estado
     * @param paciente El paciente
     * @param estadoCita El estado de la cita
     * @return Una lista de citas del paciente con el estado especificado
     */
    List<Cita> findByPacienteAndEstadoCita(Paciente paciente, TipoConfirmacionCita estadoCita);
}