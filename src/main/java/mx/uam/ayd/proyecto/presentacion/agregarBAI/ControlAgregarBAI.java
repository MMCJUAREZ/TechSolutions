package mx.uam.ayd.proyecto.presentacion.agregarBAI;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.annotation.PostConstruct;

import mx.uam.ayd.proyecto.negocio.ServicioBateriaClinica;

/**
 * Controlador para capturar el inventario de ansiedad de Beck (BAI).
 * Mantiene el mismo patrón que ControlAgregarPaciente.
 */
@Component
public class ControlAgregarBAI {

    // Dependencias inyectadas
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

    public void inicia() {
        ventanaAgregarBAI.muestra();
    }

    public void guardarBAI(/* parámetros necesarios, e.g., pacienteId, respuestas */) {
        // /* 
        // try {
        //     servicioBateriaClinica.registrarBAI(pacienteId, respuestas);
        //     ventanaAgregarBAI.muestraDialogoConMensaje("BAI guardado correctamente");
        //     termina();
        // } catch (Exception ex) {
        //     ventanaAgregarBAI.muestraDialogoConMensaje("Error al guardar BAI: " + ex.getMessage());
        // }
        // */
        System.out.println("Servicio pendiente");
    }

    private void termina() {
        ventanaAgregarBAI.setVisible(false);
    }
}
