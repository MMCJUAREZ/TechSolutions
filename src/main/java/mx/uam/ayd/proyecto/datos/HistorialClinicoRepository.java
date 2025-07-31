package mx.uam.ayd.proyecto.datos;

import mx.uam.ayd.proyecto.negocio.modelo.HistorialClinico;
import mx.uam.ayd.proyecto.negocio.modelo.Paciente;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

/**
 * Repositorio para la entidad HistorialClinico
 */
public interface HistorialClinicoRepository extends CrudRepository<HistorialClinico, Long> {

    /**
     * Encuentra el historial clínico asociado a un paciente específico.
     * @param paciente El paciente del cual se busca el historial.
     * @return Un Optional que contiene el historial clínico si existe.
     */
    Optional<HistorialClinico> findByPaciente(Paciente paciente);
}