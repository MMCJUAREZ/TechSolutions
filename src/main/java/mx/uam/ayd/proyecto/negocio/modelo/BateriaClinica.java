package mx.uam.ayd.proyecto.negocio.modelo;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Data
public class BateriaClinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    /*
        El decorador indica a JPA que debe mapear un campo de fecha/hora
        a un tipo que sea compatible con SQL
    */
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAplicacion;
    
    private int calificacion;
    private int respuesta1;
    private int respuesta2;
    private int respuesta3;
    private int respuesta4;
    private int respuesta5;
    
    @Enumerated(EnumType.STRING)
    private TipoBateria tipoDeBateria;
    
    private String comentarios;

    // Relación: Paciente llena 1 <--> 1...3 BateriasClinica
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;


}