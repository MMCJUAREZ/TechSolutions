package mx.uam.ayd.proyecto.negocio;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import mx.uam.ayd.proyecto.datos.PacienteRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Paciente;
import mx.uam.ayd.proyecto.negocio.modelo.Psicologo;

/**
 * Servicio relacionado con los pacientes
 * @author TechSolutions
 */
@Service
public class ServicioPaciente {

    private static final Logger log = LoggerFactory.getLogger(ServicioPaciente.class);

    @Autowired
    private PacienteRepository pacienteRepository;
    
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

        Paciente paciente = pacienteRepository.findByCorreo(correo);
        if (paciente != null){
            throw new IllegalArgumentException("Este correo ya ha sido registrado en el centro");
        }

        log.info("Agregando paciente nombre: "+nombre+" correo: "+correo);

        paciente = new Paciente();
        paciente.setNombre(nombre);
        paciente.setCorreo(correo);
        paciente.setTelefono(telefono);
        paciente.setEdad(edad);

        pacienteRepository.save(paciente);

        return paciente;
    }

    // Lista para recuperar los pacientes
    public List<Paciente> recuperarTodosLosPacientes() {
        List<Paciente> pacientes = new ArrayList<>();
        pacienteRepository.findAll().forEach(pacientes::add);
        return pacientes;
    }

    @Transactional
    public void asignarPsicologo(Paciente paciente, Psicologo psicologo) {
        paciente.setPsicologo(psicologo);
        pacienteRepository.save(paciente);
    }

}