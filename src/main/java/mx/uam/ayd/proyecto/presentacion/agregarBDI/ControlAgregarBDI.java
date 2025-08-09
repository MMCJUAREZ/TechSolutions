package mx.uam.ayd.proyecto.presentacion.agregarBDI;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.annotation.PostConstruct;
import javafx.scene.control.ToggleGroup;
import mx.uam.ayd.proyecto.negocio.ServicioBateriaClinica;

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

    public void inicia() {
        ventanaAgregarBDI.muestra();
    }

    public void guardarBDI(ToggleGroup q1, ToggleGroup q2, ToggleGroup q3, ToggleGroup q4, ToggleGroup q5) {
        try {
            servicioBateriaClinica.guardarBateriaBDI(q1, q2, q3, q4, q5);
            ventanaAgregarBDI.muestraDialogoConMensaje("BDI guardado correctamente");
            termina();
         } catch (Exception ex) {
            ventanaAgregarBDI.muestraDialogoConMensaje("Error al guardar BDI: " + ex.getMessage());
         }
        //System.out.println("Servicio pendiente");
    }

    private void termina() {
        ventanaAgregarBDI.setVisible(false);
    }
}
