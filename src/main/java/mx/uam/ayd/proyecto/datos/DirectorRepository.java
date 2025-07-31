package mx.uam.ayd.proyecto.datos;

import org.springframework.data.repository.CrudRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Director;

/**
 * Repositorio para la entidad Director
 */
public interface DirectorRepository extends CrudRepository<Director, Integer> {

    /**
     * Encuentra a un director a partir de su correo electrónico
     * * @param correo El correo del director a buscar
     * @return El director encontrado, o null si no existe
     */
    public Director findByCorreo(String correo);
}