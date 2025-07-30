package mx.uam.ayd.proyecto.negocio;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import mx.uam.ayd.proyecto.datos.PacienteRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Paciente;


@Service
/**
 * Servicio relacionado con los pacientes
 * 
 * @author TechSolutions
 *
 */
public class ServicioPaciente {

    private static final Logger log = LoggerFactory.getLogger(ServicioPaciente.class);
	
	private final PacienteRepository pacienteRepository;
	
	@Autowired
	public ServicioPaciente(PacienteRepository pacienteRepository) {
		this.pacienteRepository = pacienteRepository;
	}
	
	/**
	 * 
	 * Recupera todos los pacientes
	 * 
	 * @return una lista de pacientes vacía o con los pacientes existentes
	 */
	public List <Paciente> recuperaGrupos() {
		List <Paciente> pacientes = new ArrayList<>();
		
		for(Paciente paciente:pacienteRepository.findAll()) {
			pacientes.add(paciente);
		}
				
		return pacientes;
	}

    /**
     * 
     * @param nombre nombre del paciente
     * @param apellido apellido del paciente
     * @param correo correo del paciente
     * @return el paciente que se agrego
     * @throws IllegalArgumentException si el usuario ya existe, no existe el grupo,
	 *         o si alguno de los parámetros es nulo o vacío
     */

	public Paciente agregaPaciente(String nombre, String apellido, String correo) {
		
		// Validar que ningún parámetro sea nulo o vacío
		if(nombre == null || nombre.trim().isEmpty()) {
			throw new IllegalArgumentException("El nombre no puede ser nulo o vacío");
		}
		
		if(apellido == null || apellido.trim().isEmpty()) {
			throw new IllegalArgumentException("El apellido no puede ser nulo o vacío");
		}
		
		if(correo == null || correo.trim().isEmpty()) {
			throw new IllegalArgumentException("El nombre del grupo no puede ser nulo o vacío");
		}
		
		// Regla de negocio: No se permite agregar dos usuarios con el mismo nombre y apellido
		
		Paciente paciente = pacienteRepository.findByCorreo(correo);
		
		if(paciente != null) {
			throw new IllegalArgumentException("Ese paciente ya existe");
		}
		
		
		// Se validaron correctamente las reglas de negocio
		log.info("Agregando usuario nombre: "+nombre+" apellido:"+apellido);

		// Crea el usuario
		
		paciente = new Paciente();
		paciente.setNombre(nombre);
		paciente.setApellido(apellido);
		
		// Conecta al grupo con el usuari
		
		pacienteRepository.save(paciente); // Esto es el update
		
		return paciente;
		
	}

}