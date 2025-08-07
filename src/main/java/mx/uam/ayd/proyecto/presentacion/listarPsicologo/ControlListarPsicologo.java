package mx.uam.ayd.proyecto.presentacion.listarPsicologo;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
 * Controlador para la ventana ListarPsicologo
 * 
 * @author TechSolutions
 */

@Component
public class ControlListarPsicologo {

    private final VentanaListarPsicologo ventanaListarPsicologo;

    @Autowired
    public ControlListarPsicologo(VentanaListarPsicologo ventanaListarPsicologo){
        this.ventanaListarPsicologo = ventanaListarPsicologo;
    }

    @PostConstruct
    public void init(){
        ventanaListarPsicologo.setControlListarPsicologo(this);
    }

    public void inicia(){
        ventanaListarPsicologo.muestra();
    }

    public void termina() {
        ventanaListarPsicologo.setVisible(false);
    }
}
