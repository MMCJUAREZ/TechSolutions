package mx.uam.ayd.proyecto.negocio.modelo;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class BateriaClinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String tipo;
    private String resultados;
    private String comentarios;

    // Muchas Baterías Clínicas pertenecen a un Paciente
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
}