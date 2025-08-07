package mx.uam.ayd.proyecto.negocio;

import mx.uam.ayd.proyecto.datos.PacienteRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio relacionado con los psicólogos
 * * @author TechSolutions
 */
@Service
public class ServicioPsicologo {
    
    // 1. Inyectamos el repositorio para poder consultar la base de datos de pacientes
    @Autowired
    private PacienteRepository pacienteRepository;

    /**
     * 2. MÉTODO NUEVO QUE RESUELVE EL ERROR.
     * Recupera todos los pacientes y los filtra, devolviendo
     * únicamente aquellos que tienen al menos una batería clínica asociada.
     * @return Lista de pacientes con cuestionarios.
     */
    public List<Paciente> obtenerPacientesConCuestionarios() {
        // Obtenemos todos los pacientes desde la base de datos
        List<Paciente> todosLosPacientes = new ArrayList<>();
        pacienteRepository.findAll().forEach(todosLosPacientes::add);

        // Usamos un stream para filtrar la lista y devolver solo los que nos interesan
        return todosLosPacientes.stream()
                .filter(paciente -> paciente.getBateriasClinicas() != null && !paciente.getBateriasClinicas().isEmpty())
                .collect(Collectors.toList());
    }
}