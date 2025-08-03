package mx.uam.ayd.proyecto.presentacion.agregarPaciente;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioPaciente;

@Component
public class ControlAgregarPaciente {
    private final VentanaAgregarPaciente ventana;
    private final ServicioPaciente servicioPaciente;

    @Autowired
    public ControlAgregarPaciente(VentanaAgregarPaciente ventana, ServicioPaciente servicioPaciente) {
        this.ventana = ventana;
        this.servicioPaciente = servicioPaciente;
    }

    /**
	 * Método que se ejecuta después de la construcción del bean
	 * y realiza la conexión bidireccional entre el control y la ventana
	 */
	@PostConstruct
	public void init() {
		ventana.setControlAgregarPaciente(this);
	}
	
	/**
	 * Inicia la historia de usuario
	 * 
	 */
	public void inicia() {
		ventana.muestra();
	}

	public void agregaPaciente(String nombre, String correo, String telefono) {

		try {
			servicioPaciente.agregaPaciente(nombre, correo, telefono);
			ventana.muestraDialogoConMensaje("Paciente agregado exitosamente");
			
			
		} catch(Exception ex) {
			ventana.muestraDialogoConMensaje("Error al agregar paciente: "+ex.getMessage());
		}
		
		termina();
		
	}
	
	/**
	 * Termina la historia de usuario
	 * 
	 */
	public void termina() {
		ventana.setVisible(false);		
	}
    
}