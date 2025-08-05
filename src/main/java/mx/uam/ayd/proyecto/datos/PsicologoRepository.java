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

    /**
     * Encuentra un psicólogo por su correo electrónico.
     * @param correo El correo a buscar.
     * @return El psicólogo encontrado.
     */
    Psicologo findByCorreo(String correo);

    /**
     * Encuentra todos los psicólogos que tienen pacientes asignados.
     * @return Una lista de psicólogos con pacientes.
     */
    List<Psicologo> findByPacientesIsNotEmpty();

    /**
     * Encuentra todos los psicólogos que han accedido a historiales clínicos.
     * @return Una lista de psicólogos con historiales clínicos.
     */
    List<Psicologo> findByHistorialesClinicoIsNotEmpty();
}