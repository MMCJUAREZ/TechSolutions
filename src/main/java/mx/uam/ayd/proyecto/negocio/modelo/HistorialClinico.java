package mx.uam.ayd.proyecto.negocio.modelo;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Data
public class HistorialClinico {

    @Id
    private Long id; // Usamos el mismo ID que el Paciente para la relación 1 a 1

    private String observaciones;

    @Temporal(TemporalType.DATE)
    private Date fechaElaboracion;

    private boolean consentimientoAceptado;

    // Relación uno a uno con Paciente
    @OneToOne
    @MapsId // Mapea esta clave primaria con la del Paciente
    @JoinColumn(name = "id")
    private Paciente paciente;
}