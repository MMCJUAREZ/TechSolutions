package mx.uam.ayd.proyecto.negocio.modelo;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class HistorialCitas {

    @Id
    private int id; // Usamos el mismo ID que la Cita para la relación 1 a 1

    private String padecimiento;
    private String estadoPadecimiento;

    // Relación uno a uno con Cita
    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Cita cita;
}