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
    private String especialidad;
    private String telefono;

    // Muchos Psicologos reportan a un Director
    @ManyToOne
    @JoinColumn(name = "director_id")
    private Director director;

    // Un Psicologo atiende a muchos Pacientes
    @OneToMany(mappedBy = "psicologo", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Paciente> pacientes;
}
