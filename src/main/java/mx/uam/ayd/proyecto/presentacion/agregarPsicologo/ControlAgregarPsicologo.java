package mx.uam.ayd.proyecto.presentacion.agregarPsicologo;

import jakarta.annotation.PostConstruct;
import mx.uam.ayd.proyecto.negocio.ServicioPsicologo;
import mx.uam.ayd.proyecto.negocio.modelo.TipoEspecialidad;

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
    private final ServicioPsicologo servicioPsicologo;

    @Autowired
    public ControlAgregarPsicologo(VentanaAgregarPsicologo ventanaAgregarPsicologo,
                                    ServicioPsicologo servicioPsicologo){
        this.ventanaAgregarPsicologo = ventanaAgregarPsicologo;
        this.servicioPsicologo = servicioPsicologo;
    }

    @PostConstruct
    public void init(){
        ventanaAgregarPsicologo.setControlAgregarPsicologo(this);
    }

    public void inicia(){
        ventanaAgregarPsicologo.muestra();
    }

    public void agregarPsicologo(String nombre, String correo, String telefono, TipoEspecialidad especialidad) {
        System.out.println("Aun no se implementa");
    }
}