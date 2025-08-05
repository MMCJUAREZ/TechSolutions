package mx.uam.ayd.proyecto.negocio.modelo;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Data
public class HistorialClinico {

    @Id
    private Long id; // Mismo ID que el Paciente

    // Relación: Paciente llena 1 <--> 1 HistorialClinico
    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Paciente paciente;

    // Relación: Psicologo accede 1 <--> 1...* HistorialClinico
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "psicologo_id")
    private Psicologo psicologo;
    
    //Observaciones realizadas durante el llenado del historial
    private String observaciones;

    @Temporal(TemporalType.DATE)
    private Date fechaElaboracion;

    //Obligatorio para seguir
    private boolean consentimientoAceptado;

    //Respuestas
    private String motivo;
    private String consumoDrogas;
    private String descripcionDrogas;
}