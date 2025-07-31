package mx.uam.ayd.proyecto.negocio.modelo;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String telefono;
    private String correo;
    private int edad;

    // Muchos Pacientes son atendidos por un Psicologo
    @ManyToOne
    @JoinColumn(name = "psicologo_id")
    private Psicologo psicologo;

    // Un Paciente tiene un solo Historial Clínico
    @OneToOne(mappedBy = "paciente", cascade = CascadeType.ALL)
    private HistorialClinico historialClinico;

    // Un Paciente puede tener muchas Citas
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<Cita> citas;

    // Un Paciente puede tener de 1 a 3 Baterías Clínicas
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<BateriaClinica> bateriasClinicas;
}