package mx.uam.ayd.proyecto.datos;

import mx.uam.ayd.proyecto.negocio.modelo.HistorialCitas;
import mx.uam.ayd.proyecto.negocio.modelo.Cita;
import org.springframework.data.repository.CrudRepository;

/**
 * Repositorio para la entidad HistorialDeCitas
 */
public interface HistorialCitasRepository extends CrudRepository<HistorialCitas, Integer> {

    /**
     * Encuentra el historial de una cita específica
     * * @param cita La cita de la cual se quiere obtener el historial
     * @return El historial de la cita
     */
    public HistorialCitas findByCita(Cita cita);
}