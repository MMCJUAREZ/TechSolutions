package mx.uam.ayd.proyecto.presentacion.agregarBAI;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.annotation.PostConstruct;

import java.util.List;

import mx.uam.ayd.proyecto.negocio.ServicioBateriaClinica;
import mx.uam.ayd.proyecto.negocio.modelo.TipoBateria;


@Component
public class ControlAgregarBAI {

    private final VentanaAgregarBAI ventanaAgregarBAI;
    private final ServicioBateriaClinica servicioBateriaClinica;

    @Autowired
    public ControlAgregarBAI(
            VentanaAgregarBAI ventanaAgregarBAI,
            ServicioBateriaClinica servicioBateriaClinica) {
        this.ventanaAgregarBAI = ventanaAgregarBAI;
        this.servicioBateriaClinica = servicioBateriaClinica;
    }

    @PostConstruct
    public void inicializa() {
        ventanaAgregarBAI.setControlAgregarBAI(this);
    }

    public void inicia(Long pacienteID) {
        ventanaAgregarBAI.setControlAgregarBAI(this);
        ventanaAgregarBAI.setPacienteID(pacienteID);
        ventanaAgregarBAI.muestra();
    }

    public void guardarBAI(Long pacienteID, List<Integer> respuestas, String comentarios) {
       servicioBateriaClinica.registrarBateria(
        pacienteID,
        TipoBateria.BAI,
        respuestas,
        comentarios
        );
    }
}
