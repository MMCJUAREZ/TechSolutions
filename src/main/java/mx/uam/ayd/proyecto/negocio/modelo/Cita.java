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

    @Temporal(TemporalType.DATE)
    private Date fechaConfirmacion;

    private String estado;
    private String detallesAdicionales;
    private String motivoCancelacion;

    // Muchas Citas pertenecen a un Paciente
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    // Una Cita tiene un HistorialDeCitas
    @OneToOne(mappedBy = "cita", cascade = CascadeType.ALL)
    private HistorialCitas historialDeCitas;
}