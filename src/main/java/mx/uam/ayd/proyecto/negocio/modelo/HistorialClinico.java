package mx.uam.ayd.proyecto.negocio.modelo;

import jakarta.persistence.*;
import lombok.Data;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Data
public class HistorialClinico {

    @Id
    private Long id; // Mismo ID que el Paciente

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "psicologo_id")
    private Psicologo psicologo;
    
    private String observaciones;

    @Temporal(TemporalType.DATE)
    private Date fechaElaboracion;

    private boolean consentimientoAceptado;

    private String motivo;
    private String consumoDrogas;
    private String descripcionDrogas;

    /**
     * MÉTODO QUE FALTA:
     * La anotación @Transient le dice a la base de datos que ignore este método.
     * Su función es convertir los datos del historial en un String legible.
     * @return Un String con toda la información del historial formateada.
     */
    @Transient
    public String toStringFormateado() {
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        sb.append("Fecha de Elaboración: ");
        if (fechaElaboracion != null) {
            sb.append(formatter.format(fechaElaboracion));
        } else {
            sb.append("No especificada");
        }
        sb.append("\n\n");

        sb.append("========================================\n");
        sb.append("MOTIVO DE LA CONSULTA\n");
        sb.append("========================================\n");
        sb.append(motivo != null ? motivo : "No especificado.").append("\n\n");

        sb.append("========================================\n");
        sb.append("CONSUMO DE SUSTANCIAS\n");
        sb.append("========================================\n");
        sb.append(consumoDrogas != null ? consumoDrogas : "No especificado.").append("\n");

        if (descripcionDrogas != null && !descripcionDrogas.trim().isEmpty()) {
            sb.append("\nDescripción: \n");
            sb.append(descripcionDrogas).append("\n");
        }
        sb.append("\n");

        sb.append("========================================\n");
        sb.append("OBSERVACIONES DEL PSICÓLOGO\n");
        sb.append("========================================\n");
        sb.append(observaciones != null ? observaciones : "Sin observaciones.").append("\n\n");
        
        sb.append("Consentimiento Informado Aceptado: ").append(consentimientoAceptado ? "Sí" : "No");

        return sb.toString();
    }
}