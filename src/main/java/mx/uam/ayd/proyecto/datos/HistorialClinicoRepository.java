package mx.uam.ayd.proyecto.datos;

import mx.uam.ayd.proyecto.negocio.modelo.HistorialClinico;
import mx.uam.ayd.proyecto.negocio.modelo.Paciente;
import mx.uam.ayd.proyecto.negocio.modelo.Psicologo;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
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

    // Nuevos métodos basados en las relaciones establecidas:

    /**
     * Encuentra todos los historiales clínicos accedidos por un psicólogo específico.
     * @param psicologo El psicólogo del cual se buscan los historiales.
     * @return Una lista de historiales clínicos.
     */
    List<HistorialClinico> findByPsicologo(Psicologo psicologo);

    /**
     * Encuentra historiales clínicos por consentimiento aceptado y psicólogo.
     * @param consentimientoAceptado Estado del consentimiento.
     * @param psicologo El psicólogo asignado.
     * @return Una lista de historiales clínicos.
     */
    List<HistorialClinico> findByConsentimientoAceptadoAndPsicologo(boolean consentimientoAceptado, Psicologo psicologo);
}