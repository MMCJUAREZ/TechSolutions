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
import mx.uam.ayd.proyecto.datos.CitaRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Paciente;
import mx.uam.ayd.proyecto.negocio.modelo.Psicologo;
import mx.uam.ayd.proyecto.negocio.modelo.Cita;

@Service
/**
 * Servicio relacionado con los psicólogos
 * 
 * @author TechSolutions
 */
public class ServicioPsicologo {

    private static final Logger log = LoggerFactory.getLogger(ServicioPsicologo.class);
    
    private final PacienteRepository pacienteRepository;
    private final PsicologoRepository psicologoRepository;
    private final CitaRepository citaRepository;
    
    @Autowired
    public ServicioPsicologo(
            PacienteRepository pacienteRepository,
            PsicologoRepository psicologoRepository,
            CitaRepository citaRepository) {
        this.pacienteRepository = pacienteRepository;
        this.psicologoRepository = psicologoRepository;
        this.citaRepository = citaRepository;
    }

    /**
     * Obtiene todos los pacientes que tienen al menos una batería clínica asignada
     * 
     * @return lista de pacientes con cuestionarios (puede estar vacía)
     */
    public List<Paciente> obtenerPacientesConCuestionarios() {
        log.info("Obteniendo pacientes con cuestionarios");
        
        List<Paciente> todosPacientes = new ArrayList<>();
        for(Paciente paciente : pacienteRepository.findAll()) {
            todosPacientes.add(paciente);
        }
        
        // Filtrar pacientes que tienen al menos una batería clínica
        List<Paciente> pacientesConCuestionarios = new ArrayList<>();
        for(Paciente paciente : todosPacientes) {
            if(paciente.getBateriasClinicas() != null && !paciente.getBateriasClinicas().isEmpty()) {
                pacientesConCuestionarios.add(paciente);
            }
        }
        
        log.info("Se encontraron " + pacientesConCuestionarios.size() + " pacientes con cuestionarios");
        
        return pacientesConCuestionarios;
    }

    /**
     * Asigna un psicólogo a un paciente
     * 
     * @param p el paciente al que asignar el psicólogo
     * @param psico el psicólogo a asignar
     * @throws IllegalArgumentException si algún parámetro es nulo
     */
    public void asignarPsicologo(Paciente p, Psicologo psico) {
        // Validar que el paciente no sea nulo
        if(p == null) {
            throw new IllegalArgumentException("El paciente no puede ser nulo");
        }
        
        // Validar que el psicólogo no sea nulo
        if(psico == null) {
            throw new IllegalArgumentException("El psicólogo no puede ser nulo");
        }
        
        log.info("Asignando psicólogo " + psico.getNombre() + " al paciente " + p.getNombre());
        
        // Asignar el psicólogo al paciente
        p.setPsicologo(psico);
        
        // Guardar los cambios
        pacienteRepository.save(p);
        
        log.info("Psicólogo asignado exitosamente");
    }


    /**
     * Obtiene la agenda (citas) de un psicólogo específico
     * 
     * @param psicologoID el ID del psicólogo
     * @return lista de citas del psicólogo (puede estar vacía)
     * @throws IllegalArgumentException si el ID es nulo o vacío, o si no se encuentra el psicólogo
     */
    public List<Cita> obtenerAgenda(String psicologoID) {
        // Validar que el ID no sea nulo o vacío
        if(psicologoID == null || psicologoID.trim().isEmpty()) {
            throw new IllegalArgumentException("El ID del psicólogo no puede ser nulo o vacío");
        }
        
        log.info("Obteniendo agenda para psicólogo ID: " + psicologoID);
        
        try {
            // Convertir String a int (según el tipo de ID en Psicologo)
            int id = Integer.parseInt(psicologoID);
            
            // Buscar el psicólogo
            Psicologo psicologo = psicologoRepository.findById(id).orElse(null);
            if(psicologo == null) {
                throw new IllegalArgumentException("No se encontró psicólogo con ID: " + psicologoID);
            }
            
            // Hay que establecer las relaciones de citas, puente mediante pacientes
            //Pero no creo sea lo mas recomendable, no se que opinen?
            List<Cita> citasDelPsicologo = new ArrayList<>();
            
            if(psicologo.getPacientes() != null) {
                for(Paciente paciente : psicologo.getPacientes()) {
                    List<Cita> citasPaciente = citaRepository.findByPaciente(paciente);
                    citasDelPsicologo.addAll(citasPaciente);
                }
            }
            
            log.info("Se encontraron " + citasDelPsicologo.size() + " citas para el psicólogo");
            
            return citasDelPsicologo;
            
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El ID del psicólogo debe ser un número válido: " + psicologoID);
        }
    }

    /**
     * Lista todos los psicólogos registrados en el sistema
     * 
     * @return lista de psicólogos (puede estar vacía)
     */
    public List<Psicologo> listarPsicologos() {
        log.info("Obteniendo lista de psicólogos");
        
        try {
            // Debemos convertir Iterable a List
            List<Psicologo> psicologos = new ArrayList<>();
            for (Psicologo psicologo : psicologoRepository.findAll()) {
                psicologos.add(psicologo);
            }
            
            log.info("Se encontraron {} psicólogos", psicologos.size());
            return psicologos;
        } catch (Exception e) {
            log.error("Error al obtener lista de psicólogos", e);
            return new ArrayList<>();
        }
    }
}