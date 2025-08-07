package mx.uam.ayd.proyecto.presentacion.agregarPsicologo;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
 * Controlador para la ventana AgregarPsicologo
 * 
 * @author TechSolutions
 */

@Component
public class ControlAgregarPsicologo {

    private final VentanaAgregarPsicologo ventanaAgregarPsicologo;

    @Autowired
    public ControlAgregarPsicologo(VentanaAgregarPsicologo ventanaAgregarPsicologo){
        this.ventanaAgregarPsicologo = ventanaAgregarPsicologo;
    }

    @PostConstruct
    public void init(){
        ventanaAgregarPsicologo.setControlAgregarPsicologo(this);
    }

    public void inicia(){
        ventanaAgregarPsicologo.muestra();
    }


}