package mx.uam.ayd.proyecto.datos;

import mx.uam.ayd.proyecto.negocio.modelo.BateriaClinica;
import mx.uam.ayd.proyecto.negocio.modelo.Paciente;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

/**
 * Repositorio para la entidad BateriaClinica
 */
public interface BateriaClinicaRepository extends CrudRepository<BateriaClinica, Integer> {

    /**
     * Recupera todas las baterías clínicas asociadas a un paciente
     * * @param paciente El paciente del cual se quieren obtener las baterías
     * @return Una lista de baterías clínicas
     */
    public List<BateriaClinica> findByPaciente(Paciente paciente);
}