package mx.uam.ayd.proyecto.presentacion.agregarPaciente;

import java.io.IOException;

//Notaciones de Jakarta y Spring
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

import mx.uam.ayd.proyecto.presentacion.agregarBAI.VentanaAgregarBAI;
import mx.uam.ayd.proyecto.presentacion.agregarBDI.VentanaAgregarBDI;
import mx.uam.ayd.proyecto.presentacion.agregarCEPER.VentanaAgregarCEPER;

//Importaciones necesarias
import mx.uam.ayd.proyecto.presentacion.agregarPaciente.VentanaAgregarPaciente;
import mx.uam.ayd.proyecto.negocio.ServicioPaciente;
import mx.uam.ayd.proyecto.negocio.modelo.Paciente;
import mx.uam.ayd.proyecto.presentacion.contestarBaterias.ControlContestarBaterias;
import mx.uam.ayd.proyecto.presentacion.contestarHistorialClinico.ControlContestarHistorialClinico;

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
    private final VentanaAgregarBDI ventanaAgregarBDI;
    private final VentanaAgregarCEPER ventanaAgregarCEPER;
    private final ControlContestarHistorialClinico controlContestarHistorialClinico;

    private Long pacienteID;

    @Autowired
    public ControlAgregarPaciente(
    VentanaAgregarPaciente ventanaAgregarPaciente, 
    ServicioPaciente servicioPaciente, 
    ControlContestarBaterias controlContestarBaterias,
    VentanaContestarBaterias ventanaContestarBaterias,
    VentanaAgregarBAI ventanaAgregarBAI,
    VentanaAgregarBDI ventanaAgregarBDI,
    VentanaAgregarCEPER ventanaAgregarCEPER,
    ControlContestarHistorialClinico controlContestarHistorialClinico) {
        this.ventanaAgregarPaciente = ventanaAgregarPaciente;
        this.servicioPaciente = servicioPaciente;
        this.controlContestarBaterias = controlContestarBaterias;
        this.ventanaContestarBaterias = ventanaContestarBaterias;
        this.ventanaAgregarBAI = ventanaAgregarBAI;
        this.ventanaAgregarBDI = ventanaAgregarBDI;
        this.ventanaAgregarCEPER=ventanaAgregarCEPER;
        this.controlContestarHistorialClinico = controlContestarHistorialClinico;
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
        try {
			Paciente paciente = servicioPaciente.agregarPaciente(nombre, correo, telefono, edad);
            pacienteID = paciente.getId();
            

			ventanaAgregarPaciente.muestraDialogoConMensaje("Paciente agregado exitosamente");
            this.contestarHistorialClinico(correo);
		} catch(Exception ex) {
			ventanaAgregarPaciente.muestraDialogoConMensaje("Error al agregar usuario: "+ex.getMessage());
		}
		
		termina(); 
    }



    public void agregarBAI() {
        ventanaAgregarBAI.setPacienteID(pacienteID);
        ventanaAgregarBAI.muestra();
    }

    public void agregarBDI() {
        ventanaAgregarBDI.muestra();
    }
    
    public void agregarCEPER() {
        ventanaAgregarCEPER.setPacienteID(pacienteID);
        ventanaAgregarCEPER.muestra();
    }

    public void contestarHistorialClinico(String correo){
        controlContestarHistorialClinico.inicia();
    }

    /**
     * Termina el proceso de agregar paciente y muestra la ventana de contestar baterías
     */
    private void termina() {
        ventanaAgregarPaciente.setVisible(false);
    }
}