package mx.uam.ayd.proyecto.presentacion.listarPsicologo;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import mx.uam.ayd.proyecto.negocio.ServicioPsicologo;
import mx.uam.ayd.proyecto.negocio.modelo.Psicologo;

/*
 * Controlador para la ventana ListarPsicologo
 * 
 * @author TechSolutions
 */

@Component
public class ControlListarPsicologo {

    private final VentanaListarPsicologo ventanaListarPsicologo;

    @Autowired
    private ServicioPsicologo servicioPsicologo;

    @Autowired
    public ControlListarPsicologo(VentanaListarPsicologo ventanaListarPsicologo){
        this.ventanaListarPsicologo = ventanaListarPsicologo;
    }

    @PostConstruct
    public void init(){
        ventanaListarPsicologo.setControlListarPsicologo(this);
    }

    public void inicia(){
        List<Psicologo> psicologos = servicioPsicologo.listarPsicologos();
        ventanaListarPsicologo.muestra(psicologos);
    }

    public void termina() {
        ventanaListarPsicologo.setVisible(false);
    }
}
