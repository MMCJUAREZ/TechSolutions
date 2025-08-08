package mx.uam.ayd.proyecto.presentacion.agregarCEPER;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.annotation.PostConstruct;

import mx.uam.ayd.proyecto.negocio.ServicioBateriaClinica;
import mx.uam.ayd.proyecto.presentacion.agregarCEPER.VentanaAgregarCEPER;

@Component
public class ControlAgregarCEPER {
    
    private final VentanaAgregarCEPER ventanaAgregarCEPER;
    private final ServicioBateriaClinica servicioBateriaClinica;

    @Autowired
    public ControlAgregarCEPER(
        VentanaAgregarCEPER ventanaAgregarCEPER,
        ServicioBateriaClinica servicioBateriaClinica) {
            this.ventanaAgregarCEPER=ventanaAgregarCEPER;
            this.servicioBateriaClinica=servicioBateriaClinica;
        }
    
        @PostConstruct
        public void inicializa(){
            ventanaAgregarCEPER.setControlAgregarCEPER(this);
        }

        public void inicia(){
            ventanaAgregarCEPER.muestra();
        }
}
