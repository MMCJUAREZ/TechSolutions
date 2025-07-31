package mx.uam.ayd.proyecto.datos;

import mx.uam.ayd.proyecto.negocio.modelo.Paciente;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

/**
 * Repositorio para la entidad Paciente
 */
public interface PacienteRepository extends CrudRepository<Paciente, Long> {

    /**
     * Encuentra un paciente por su correo electrónico.
     * @param correo El correo a buscar.
     * @return El paciente encontrado.
     */
    Paciente findByCorreo(String correo);

    /**
     * Encuentra pacientes dentro de un rango de edad.
     * @param edad1 Edad mínima.
     * @param edad2 Edad máxima.
     * @return Una lista de pacientes en el rango de edad.
     */
    List<Paciente> findByEdadBetween(int edad1, int edad2);
}