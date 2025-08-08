package mx.uam.ayd.proyecto.presentacion.agregarBAI;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Controlador para la ventana de agregado del inventario BAI.
 */
@Component
public class ControlAgregarBAI {

    private final VentanaAgregarBAI ventana;

    @Autowired
    public ControlAgregarBAI(VentanaAgregarBAI ventana) {
        this.ventana = ventana;
    }

    @PostConstruct
    public void init() {
        ventana.setControlAgregarBAI(this);
    }

    /**
     * Inicia el flujo mostrando la ventana correspondiente.
     */
    public void inicia() {
        ventana.muestra();
    }

    /**
     * Termina el flujo ocultando la ventana.
     */
    public void termina() {
        ventana.setVisible(false);
    }
}

