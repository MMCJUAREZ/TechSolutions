package mx.uam.ayd.proyecto.presentacion.agregarPaciente;

//Notaciones
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

import mx.uam.ayd.proyecto.presentacion.agregarBAI.VentanaAgregarBAI;
import mx.uam.ayd.proyecto.presentacion.agregarBDI.VentanaAgregarBDI;
import mx.uam.ayd.proyecto.presentacion.agregarCEPER.VentanaAgregarCEPER;
import mx.uam.ayd.proyecto.presentacion.agregarPaciente.VentanaAgregarPaciente;
import mx.uam.ayd.proyecto.negocio.ServicioPaciente;
import mx.uam.ayd.proyecto.negocio.modelo.Paciente;
import mx.uam.ayd.proyecto.presentacion.contestarHistorialClinico.ControlContestarHistorialClinico;


/**
 * Controlador para agregar pacientes.
 * 
 * @author TechSolutions
 */

@Component
public class ControlAgregarPaciente {
    private Long pacienteID;

    //Dependencias inyectadas
    private final VentanaAgregarPaciente ventanaAgregarPaciente;
    private final ServicioPaciente servicioPaciente;
    private final VentanaAgregarBAI ventanaAgregarBAI;
    private final VentanaAgregarBDI ventanaAgregarBDI;
    private final VentanaAgregarCEPER ventanaAgregarCEPER;
    private final ControlContestarHistorialClinico controlContestarHistorialClinico;

    @Autowired
    public ControlAgregarPaciente(
    VentanaAgregarPaciente ventanaAgregarPaciente, 
    ServicioPaciente servicioPaciente, 
    VentanaAgregarBAI ventanaAgregarBAI,
    VentanaAgregarBDI ventanaAgregarBDI,
    VentanaAgregarCEPER ventanaAgregarCEPER,
    ControlContestarHistorialClinico controlContestarHistorialClinico) {
        this.ventanaAgregarPaciente = ventanaAgregarPaciente;
        this.servicioPaciente = servicioPaciente;
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
    
    /**
     * Agrega un paciente utilizando el servicio de pacientes.
     * 
     * @param nombre Nombre del paciente
     * @param correo Correo del paciente
     * @param telefono Teléfono del paciente
     * @param edad Edad del paciente
     */
    public void agregarPaciente(String nombre, String correo, String telefono, int edad) {
        try {
			Paciente paciente = servicioPaciente.agregarPaciente(nombre, correo, telefono, edad);
            pacienteID = paciente.getId();

			ventanaAgregarPaciente.muestraDialogoConMensaje("Paciente agregado exitosamente");
            this.contestarHistorialClinico(paciente);
		} catch(Exception ex) {
			ventanaAgregarPaciente.muestraDialogoConMensaje("Error al agregar usuario:\n \n"+ex.getMessage());
		}
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

    public void contestarHistorialClinico(Paciente paciente) {
        controlContestarHistorialClinico.inicia(paciente);
    }
}