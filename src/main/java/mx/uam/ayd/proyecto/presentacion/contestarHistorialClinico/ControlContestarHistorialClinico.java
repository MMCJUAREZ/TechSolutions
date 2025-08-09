package mx.uam.ayd.proyecto.presentacion.contestarHistorialClinico;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.annotation.PostConstruct;

import mx.uam.ayd.proyecto.negocio.ServicioHistorialClinico;

@Component
public class ControlContestarHistorialClinico {
    
    // Dependencias inyectadas
    private final VentanaContestarHistorialClinico ventanaContestarHistorialClinico;
    private final ServicioHistorialClinico servicioHistorialClinico;

    @Autowired
    public ControlContestarHistorialClinico(
            VentanaContestarHistorialClinico ventanaContestarHistorialClinico,
            ServicioHistorialClinico servicioHistorialClinico){
        this.ventanaContestarHistorialClinico = ventanaContestarHistorialClinico;
        this.servicioHistorialClinico = servicioHistorialClinico;
    }

    @PostConstruct
    public void inicializa(){
        ventanaContestarHistorialClinico.setControlContestarHistorialClinico(this);
    }

    public void inicia(){
        ventanaContestarHistorialClinico.muestra();
    }
    //Agregue el booleano del consentimiento
    public void guardarHistorialClinico(String nombre, String correo, String motivo, String consumoDrogas, String descripcion, boolean consentimientoAceptado){
        try {
			servicioHistorialClinico.guardarHistorialClinico(nombre, correo, motivo, consumoDrogas, descripcion, consentimientoAceptado);
			ventanaContestarHistorialClinico.muestraDialogoConMensaje("Historial clinico agregado exitosamente");	
		} catch(Exception ex) {
			ventanaContestarHistorialClinico.muestraDialogoConMensaje("Error al agregar historial clinico: "+ex.getMessage());
		}
		
		termina(); 
    }

    private void termina(){
        ventanaContestarHistorialClinico.setVisible(false);
    }
}
