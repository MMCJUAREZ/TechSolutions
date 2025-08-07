package mx.uam.ayd.proyecto.negocio;

import mx.uam.ayd.proyecto.datos.PacienteRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicioPsicologo {
    
    @Autowired
    private PacienteRepository pacienteRepository;

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
}