package mx.uam.ayd.proyecto.negocio.modelo;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Data
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.DATE)
    private Date fechaCita;

    @Temporal(TemporalType.TIME)
    private Date horaCita;

    @Enumerated(EnumType.STRING)
    private TipoConfirmacionCita estadoCita;
    
    private String detallesAdicionalesPsicologo;
    private String detallesAdicionalesPaciente;
    private String notaPostSesion;
    private String motivoCancelacion;

    // Relación con Paciente
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
}