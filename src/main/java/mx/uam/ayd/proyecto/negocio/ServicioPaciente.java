package mx.uam.ayd.proyecto.negocio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.ayd.proyecto.datos.PacienteRepository;
import mx.uam.ayd.proyecto.negocio.modelo.BateriaClinica;
import mx.uam.ayd.proyecto.negocio.modelo.Paciente;



/**
 * Servicio relacionado con los pacientes
 * 
 * @author TechSolutions
 */
@Service
public class ServicioPaciente {

     private static final Logger log = LoggerFactory.getLogger(ServicioPaciente.class);

    @Autowired
    private PacienteRepository pacienteRepository;
    
    // TODO: Implementar lógica de negocio para pacientes
    public Paciente agregarPaciente(String nombre, String correo, String telefono, int edad) {
        if(nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacio");
        }

        if(correo == null || correo.trim().isEmpty()) {
            throw new IllegalArgumentException("El correo no puede ser nulo o vacio");
        }

        if(telefono == null || telefono.trim().isEmpty()) {
            throw new IllegalArgumentException("El telefono no puede ser nulo o vacio");
        }

        // Regla de negocio, no podemos tener dos pacientes con el mismo correo
        Paciente paciente = pacienteRepository.findByCorreo(correo);
        if (paciente != null){
            throw new IllegalArgumentException("El paciente ya existe: Correo no disponible");
        }

        // Si las reglas fueron validadas correctamente
        log.info("Agregando psicologo nombre: "+nombre+" correo: "+correo);

        //Creamos el paciente
        paciente = new Paciente();
        paciente.setNombre(nombre);
        paciente.setCorreo(correo);
        paciente.setTelefono(telefono);
        paciente.setEdad(edad);

        pacienteRepository.save(paciente);

        return paciente;
    }

}