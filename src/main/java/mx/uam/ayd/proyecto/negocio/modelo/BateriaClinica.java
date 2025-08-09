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

    private Integer respuesta1;
    private Integer respuesta2;
    private Integer respuesta3;
    private Integer respuesta4;
    private Integer respuesta5;

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

    public BateriaClinica(TipoBateria tipoDeBateria, Integer respuesta1, Integer respuesta2, 
                    Integer respuesta3, Integer respuesta4, Integer respuesta5){

                        this.tipoDeBateria = tipoDeBateria;
                        this.respuesta1 = respuesta1;
                        this.respuesta2 = respuesta2;
                        this.respuesta3 = respuesta3;
                        this.respuesta4 = respuesta4;
                        this.respuesta5 = respuesta5;
    }

}