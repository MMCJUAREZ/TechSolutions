package mx.uam.ayd.proyecto.datos;



import org.springframework.data.repository.CrudRepository;

import mx.uam.ayd.proyecto.negocio.modelo.HistorialClinico;

/**
 * 
 * Repositorio para usuarios
 * 
 * @author humbertocervantes
 *
 */
public interface HistorialClinicoRepository extends CrudRepository <HistorialClinico, Long> {
	
}