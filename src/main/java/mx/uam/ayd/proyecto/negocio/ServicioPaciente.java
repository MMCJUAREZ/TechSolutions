package mx.uam.ayd.proyecto.negocio;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.ayd.proyecto.datos.PacienteRepository;
import mx.uam.ayd.proyecto.datos.CitaRepository;
import mx.uam.ayd.proyecto.datos.HistorialClinicoRepository;
import mx.uam.ayd.proyecto.datos.BateriaClinicaRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Paciente;
import mx.uam.ayd.proyecto.negocio.modelo.Cita;
import mx.uam.ayd.proyecto.negocio.modelo.HistorialClinico;
import mx.uam.ayd.proyecto.negocio.modelo.BateriaClinica;


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
	private final CitaRepository citaRepository;
	private final HistorialClinicoRepository historialClinicoRepository;
	private final BateriaClinicaRepository bateriaClinicaRepository;

	@Autowired
	public ServicioPaciente(
	        PacienteRepository pacienteRepository,
	        CitaRepository citaRepository, 
	        HistorialClinicoRepository historialClinicoRepository,
	        BateriaClinicaRepository bateriaClinicaRepository) {
	    this.pacienteRepository = pacienteRepository;
	    this.citaRepository = citaRepository;
	    this.historialClinicoRepository = historialClinicoRepository;
	    this.bateriaClinicaRepository = bateriaClinicaRepository;
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

	public Paciente agregaPaciente(String nombre, String correo, String telefono) {
		
		// Validar que ningún parámetro sea nulo o vacío
		if(nombre == null || nombre.trim().isEmpty()) {
			throw new IllegalArgumentException("El nombre no puede ser nulo o vacío");
		}

		if(telefono == null || telefono.trim().isEmpty()) {
			throw new IllegalArgumentException("El teléfono no puede ser nulo o vacío");
		}
		
		if(correo == null || correo.trim().isEmpty()) {
			throw new IllegalArgumentException("El correo no puede ser nulo o vacío");
		}
		
		// Regla de negocio: No se permite agregar dos pacientes con el mismo correo
		Paciente paciente = pacienteRepository.findByCorreo(correo);
		
		if(paciente != null) {
			throw new IllegalArgumentException("Ya existe un paciente con ese correo");
		}
		
		
		// Se validaron correctamente las reglas de negocio
		log.info("Agregando paciente nombre: " + nombre + ", correo: " + correo + ", telefono: " + telefono);

		// Crea el paciente
		paciente = new Paciente();
		paciente.setNombre(nombre);
		paciente.setCorreo(correo);
		paciente.setTelefono(telefono);

		pacienteRepository.save(paciente);
		
		return paciente;
		
	}
	
	public void registrarPaciente(Paciente p) {
	    /*
			Se busca paciente mediante su corre en su respectivo repositorio
		*/
		pacienteRepository.save(p);
	}

	public Paciente obtenerPacientePorCorreo(String correo) {
		return pacienteRepository.findByCorreo(correo);
	}

	/**
	 * Acepta el consentimiento de un paciente en su historial clínico
	 * 
	 * @param p el paciente que acepta el consentimiento
	 * @throws IllegalArgumentException si el paciente es nulo o no tiene historial clínico
	 */
	public void aceptarConsentimiento(Paciente p) {
	    // Validar que el paciente no sea nulo
	    if(p == null) {
	        throw new IllegalArgumentException("El paciente no puede ser nulo");
	    }
	    
	    // Validar que el paciente tenga historial clínico
		// Se declara variable del tipo HistorialClinico
		// Se almcena el historial asociado del paciente
	    HistorialClinico historial = p.getHistorialClinico();
	    if(historial == null) {
	        throw new IllegalArgumentException("El paciente no tiene historial clínico para aceptar consentimiento");
	    }
	    
	    log.info("Aceptando consentimiento para paciente: " + p.getNombre());
	    
	    // Marcar consentimiento como aceptado
	    historial.setConsentimientoAceptado(true);
	    
	    // Guardar cambios
	    historialClinicoRepository.save(historial);
	}

	/**
	 * Obtiene todas las citas de un paciente
	 * 
	 * @param p el paciente del cual obtener las citas
	 * @return lista de citas del paciente (puede estar vacía)
	 * @throws IllegalArgumentException si el paciente es nulo
	 */
	public List<Cita> obtenerCitas(Paciente p) {
	    // Validar que el paciente no sea nulo
	    if(p == null) {
	        throw new IllegalArgumentException("El paciente no puede ser nulo");
	    }
	    
	    log.info("Obteniendo citas para paciente: " + p.getNombre());
	    
	    // Usar el repository para buscar citas por paciente
	    return citaRepository.findByPaciente(p);
	}
	
	/**
	 * Llena/guarda un historial clínico
	 * 
	 * @param h el historial clínico a guardar
	 * @throws IllegalArgumentException si el historial es nulo o no tiene paciente asociado
	 */
	public void llenarHistorialClinico(HistorialClinico h) {
	    // Validar que el historial no sea nulo
	    if(h == null) {
	        throw new IllegalArgumentException("El historial clínico no puede ser nulo");
	    }
	    
	    // Validar que tenga paciente asociado
	    if(h.getPaciente() == null) {
	        throw new IllegalArgumentException("El historial clínico debe tener un paciente asociado");
	    }
	    
	    log.info("Llenando historial clínico para paciente: " + h.getPaciente().getNombre());
	    
	    // Guardar el historial clínico
	    historialClinicoRepository.save(h);
	}

	/**
	 * Procesa la respuesta de una batería clínica
	 * 
	 * @param b la batería clínica contestada
	 * @throws IllegalArgumentException si la batería es nula o no tiene paciente asociado
	 */
	public void contestarBateria(BateriaClinica b) {
	    // Validar que la batería no sea nula
	    if(b == null) {
	        throw new IllegalArgumentException("La batería clínica no puede ser nula");
	    }
	    
	    // Validar que tenga paciente asociado
	    if(b.getPaciente() == null) {
	        throw new IllegalArgumentException("La batería clínica debe tener un paciente asociado");
	    }
	    
	    log.info("Procesando batería clínica para paciente: " + b.getPaciente().getNombre());
	    
	    // Guardar la batería clínica contestada
	    bateriaClinicaRepository.save(b);
	}
}