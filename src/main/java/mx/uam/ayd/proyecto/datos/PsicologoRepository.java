package mx.uam.ayd.proyecto.datos;

import mx.uam.ayd.proyecto.negocio.modelo.Psicologo;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

/**
 * Repositorio para la entidad Psicologo
 */
public interface PsicologoRepository extends CrudRepository<Psicologo, Integer> {

    /**
     * Encuentra un psicólogo por su número de teléfono.
     * @param telefono El teléfono a buscar.
     * @return El psicólogo encontrado.
     */
    Psicologo findByTelefono(String telefono);

    /**
     * Encuentra todos los psicólogos con una especialidad específica.
     * @param especialidad La especialidad a buscar.
     * @return Una lista de psicólogos.
     */
    List<Psicologo> findByEspecialidad(String especialidad);
}