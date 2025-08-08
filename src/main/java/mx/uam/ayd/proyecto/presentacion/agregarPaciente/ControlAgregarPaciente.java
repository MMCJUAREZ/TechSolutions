package mx.uam.ayd.proyecto.presentacion.agregarPaciente;

import java.io.IOException;

//Notaciones de Jakarta y Spring
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

import mx.uam.ayd.proyecto.presentacion.agregarBAI.VentanaAgregarBAI;
import mx.uam.ayd.proyecto.presentacion.agregarCEPER.VentanaAgregarCEPER;
//Importaciones necesarias
import mx.uam.ayd.proyecto.presentacion.agregarPaciente.VentanaAgregarPaciente;
import mx.uam.ayd.proyecto.negocio.ServicioPaciente;
import mx.uam.ayd.proyecto.presentacion.contestarBaterias.ControlContestarBaterias;
import mx.uam.ayd.proyecto.presentacion.contestarBaterias.VentanaContestarBaterias;


/**
 * Controlador para agregar pacientes.
 * 
 * @author TechSolutions
 */

@Component
public class ControlAgregarPaciente {

    //Dependencias inyectadas
    private final VentanaAgregarPaciente ventanaAgregarPaciente;
    private final ServicioPaciente servicioPaciente;
    private final ControlContestarBaterias controlContestarBaterias;
    private final VentanaContestarBaterias ventanaContestarBaterias;
    private final VentanaAgregarBAI ventanaAgregarBAI;
    private final VentanaAgregarCEPER ventanaAgregarCEPER;

    @Autowired
    public ControlAgregarPaciente(
    VentanaAgregarPaciente ventanaAgregarPaciente, 
    ServicioPaciente servicioPaciente, 
    ControlContestarBaterias controlContestarBaterias,
    VentanaContestarBaterias ventanaContestarBaterias,
    VentanaAgregarBAI ventanaAgregarBAI,
    VentanaAgregarCEPER ventanaAgregarCEPER) {
        this.ventanaAgregarPaciente = ventanaAgregarPaciente;
        this.servicioPaciente = servicioPaciente;
        this.controlContestarBaterias = controlContestarBaterias;
        this.ventanaContestarBaterias = ventanaContestarBaterias;
        this.ventanaAgregarBAI = ventanaAgregarBAI;
        this.ventanaAgregarCEPER=ventanaAgregarCEPER;
    }

    /**
     * Método que se ejecuta después de la construcción del bean
     * y realiza la conexión bidireccional entre el control y la ventana
     */
    @PostConstruct
    public void inicializa() {
        ventanaAgregarPaciente.setControlAgregarPaciente(this);
    }

    /**
     * Inicia la historia de usuario
     * 
     */
    public void inicia() {
        ventanaAgregarPaciente.muestra();
    }

    public void agregarPaciente(String nombre, String correo, String telefono, int edad) {
        /*try {
			servicioPaciente.agregarPaciente(nombre, correo, telefono, edad);
			ventanaAgregarPaciente.muestraDialogoConMensaje("Paciente agregado exitosamente");
			
			
		} catch(Exception ex) {
			ventanaAgregarPaciente.muestraDialogoConMensaje("Error al agregar usuario: "+ex.getMessage());
		}
		
		termina(); */
        System.out.println("Servicio pendiente"); 
    }

    public void agregarBAI() {
        ventanaAgregarBAI.muestra();
    }

    public void agregarCEPER() {
        ventanaAgregarCEPER.muestra();
    }

    /**
     * Termina el proceso de agregar paciente y muestra la ventana de contestar baterías
     */
    private void termina() {
        ventanaAgregarPaciente.setVisible(false);
    }
}