package mx.uam.ayd.proyecto.negocio.modelo;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class BateriaClinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombreCuestionario;
    //private List<Pregunta> preguntas;
    
    /*
        El decorador indica a JPA que debe mapear un campo de fecha/hora
        a un tipo que sea compatible con SQL
    */
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAplicacion;
    
    private int puntuacionTotal;

    private String tipo;
    private String resultados;
    private String comentarios;

    // Muchas Baterías Clínicas pertenecen a un Paciente
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
}