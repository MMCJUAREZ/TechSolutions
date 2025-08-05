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

    // Relación: Psicologo atiende 1 <--> 0...* Paciente
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "psicologo_id")
    private Psicologo psicologo;

    // Relación: Paciente llena 1 <--> 1 HistorialClinico
    @OneToOne(mappedBy = "paciente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private HistorialClinico historialClinico;

    // Relación: Paciente llena 1 <--> 1...3 BateriasClinica
    @OneToMany(mappedBy = "paciente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BateriaClinica> bateriasClinicas;

    // Relación: Paciente tiene 1 <--> 1...* citas
    @OneToMany(mappedBy = "paciente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Cita> citas;
}