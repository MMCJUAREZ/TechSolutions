package mx.uam.ayd.proyecto.presentacion.agregarBDI;

import org.springframework.stereotype.Component;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import jakarta.annotation.PostConstruct;
import mx.uam.ayd.proyecto.negocio.ServicioBateriaClinica;
import mx.uam.ayd.proyecto.negocio.modelo.TipoBateria;

@Component
public class ControlAgregarBDI {
    
    // Dependencias inyectadas
    private final VentanaAgregarBDI ventanaAgregarBDI;
    private final ServicioBateriaClinica servicioBateriaClinica;

    @Autowired
    public ControlAgregarBDI(
            VentanaAgregarBDI ventanaAgregarBDI,
            ServicioBateriaClinica servicioBateriaClinica) {
        this.ventanaAgregarBDI = ventanaAgregarBDI;
        this.servicioBateriaClinica = servicioBateriaClinica;
    }

    @PostConstruct
    public void inicializa() {
        ventanaAgregarBDI.setControlAgregarBDI(this);
    }

    public void inicia(Long pacienteID) {
        ventanaAgregarBDI.setControlAgregarBDI(this);
        ventanaAgregarBDI.setPacienteID(pacienteID);
        ventanaAgregarBDI.muestra();
    }

    public void guardarBDI(Long pacienteID, List<Integer> respuestas, String comentarios) {
        servicioBateriaClinica.registrarBateria(
            pacienteID,
            TipoBateria.BDI_II,
            respuestas,
            comentarios
        );
    } 

}
