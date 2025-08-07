package mx.uam.ayd.proyecto.negocio.modelo;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Psicologo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private String correo;
    private String telefono;
    private TipoEspecialidad especialidad;

    // Relación: Psicologo atiende 1 <--> 0...* Paciente
    @OneToMany(mappedBy = "psicologo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Paciente> pacientes;

    // Relación: Psicologo accede 1 <--> 1...* HistorialClinico
    @OneToMany(mappedBy = "psicologo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<HistorialClinico> historialesClinico;
}
