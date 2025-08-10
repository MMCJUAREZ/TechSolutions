package mx.uam.ayd.proyecto.negocio;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.ayd.proyecto.datos.PacienteRepository;
import mx.uam.ayd.proyecto.datos.PsicologoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Paciente;
import mx.uam.ayd.proyecto.negocio.modelo.Psicologo;
import mx.uam.ayd.proyecto.negocio.modelo.TipoEspecialidad;

@Service
public class ServicioPsicologo {

    private static final Logger log = LoggerFactory.getLogger(ServicioPsicologo.class);
    
    @Autowired
    private PacienteRepository pacienteRepository;
    
    @Autowired
    private PsicologoRepository psicologoRepository;

    /**
     * Recupera todos los pacientes y los filtra, devolviendo
     * únicamente aquellos que tienen al menos una batería clínica asociada.
     * @return Lista de pacientes con cuestionarios.
     */
    public List<Paciente> obtenerPacientesConCuestionarios() {
        List<Paciente> todosLosPacientes = new ArrayList<>();
        pacienteRepository.findAll().forEach(todosLosPacientes::add);

        return todosLosPacientes.stream()
                .filter(paciente -> paciente.getBateriasClinicas() != null && !paciente.getBateriasClinicas().isEmpty())
                .collect(Collectors.toList());
    }

    public Psicologo agregarPsicologo(String nombre, String correo, String telefono, TipoEspecialidad especialidad) {
        if(nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacio");
        }

        if(correo == null || correo.trim().isEmpty()) {
            throw new IllegalArgumentException("El correo no puede ser nulo o vacio");
        }

        if(telefono == null || telefono.trim().isEmpty()) {
            throw new IllegalArgumentException("El telefono no puede ser nulo o vacio");
        }

        // Regla de negocio, no podemos tener dos psicologos con el mismo correo
        Psicologo psicologo = psicologoRepository.findByCorreo(correo);
        if (psicologo != null){
            throw new IllegalArgumentException("Ese psicologo ya existe: Correo no disponible");
        }

        // Regla de negocio, no podemos tener dos psicologos con el mismo telefono
        psicologo = psicologoRepository.findByTelefono(telefono);
        if (psicologo != null){
            throw new IllegalArgumentException("Ese psicologo ya existe: Telefono no disponible");
        }


        // Si las reglas fueron validadas correctamente
        log.info("Agregando psicologo nombre: "+nombre+" correo: "+correo);

        //Creamos el psicologo
        psicologo = new Psicologo();
        psicologo.setNombre(nombre);
        psicologo.setCorreo(correo);
        psicologo.setTelefono(telefono);
        psicologo.setEspecialidad(especialidad);

        psicologoRepository.save(psicologo);

        return psicologo;
    }

    public List<Psicologo> listarPsicologos() {
        List<Psicologo> psicologos = new ArrayList<>();
        psicologoRepository.findAll().forEach(psicologos::add);
        return psicologos;
    }

    //Obtiene los psicologos por especialidad acorde a la edad del paciente
    public List<Psicologo> obtenerPsicologosPorEdadPaciente(Paciente paciente) {
    if (paciente.getEdad() < 18) {
        // Solo psicólogos infantiles
        return psicologoRepository.findByEspecialidad(TipoEspecialidad.INFANTIL);
    } else {
        // Todos menos infantiles
        return psicologoRepository.findByEspecialidadNot(TipoEspecialidad.INFANTIL);
    }
}

}