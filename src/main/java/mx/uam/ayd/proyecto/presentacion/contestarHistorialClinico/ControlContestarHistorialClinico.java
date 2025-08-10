package mx.uam.ayd.proyecto.presentacion.contestarHistorialClinico;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.annotation.PostConstruct;

import mx.uam.ayd.proyecto.negocio.ServicioHistorialClinico;
import mx.uam.ayd.proyecto.negocio.modelo.Paciente;
import mx.uam.ayd.proyecto.presentacion.asignarPsicologo.ControlAsignarPsicologo;

@Component
public class ControlContestarHistorialClinico {
    
    // Dependencias 
    private final VentanaContestarHistorialClinico ventanaContestarHistorialClinico;
    private final ServicioHistorialClinico servicioHistorialClinico;
    private final ControlAsignarPsicologo controlAsignarPsicologo;

    @Autowired
    public ControlContestarHistorialClinico(
            VentanaContestarHistorialClinico ventanaContestarHistorialClinico,
            ServicioHistorialClinico servicioHistorialClinico, 
            ControlAsignarPsicologo controlAsignarPsicologo) {
        this.ventanaContestarHistorialClinico = ventanaContestarHistorialClinico;
        this.servicioHistorialClinico = servicioHistorialClinico;
        this.controlAsignarPsicologo = controlAsignarPsicologo;
    }

    /**
     * Método que se ejecuta después de la construcción del bean
     * y realiza la conexión bidireccional entre el control y la ventana
     */
    @PostConstruct
    public void inicializa(){
        ventanaContestarHistorialClinico.setControlContestarHistorialClinico(this);
    }

    private Paciente pacienteActual;
    /**
     * Inicia el controlador con el paciente actual.
     * 
     * @param paciente El paciente para el cual se contestará el historial clínico
     */
    public void inicia(Paciente paciente) {
        this.pacienteActual = paciente;
        ventanaContestarHistorialClinico.muestra(paciente.getNombre(),paciente.getCorreo());
    }

    /**
     * Guarda el historial clínico del paciente.
     * 
     * @param nombre El nombre del paciente
     * @param correo El correo del paciente
     * @param motivo El motivo de la consulta
     * @param consumoDrogas Información sobre el consumo de drogas
     * @param descripcion Descripción del consumo de drogas
     * @param consentimientoAceptado Indica si se aceptó el consentimiento
     */
    public void guardarHistorialClinico(String nombre, String correo, String motivo, String consumoDrogas, String descripcion, boolean consentimientoAceptado){
        try {
			servicioHistorialClinico.guardarHistorialClinico(nombre, correo, motivo, consumoDrogas, descripcion, consentimientoAceptado);
			ventanaContestarHistorialClinico.muestraDialogoConMensaje("Historial clinico agregado exitosamente");	
            termina(); //Solo termina la ventana si se ha guardado el HC
            this.controlAsignarPsicologo(this.pacienteActual); //Inicia asignar psicologo
        } catch(Exception ex) {
			ventanaContestarHistorialClinico.muestraDialogoConMensaje("Error: \n \n"+ex.getMessage());
		}
    }
    /**
     * Inicia el proceso de asignación de psicólogo para el paciente dado.
     * 
     * @param paciente El paciente al que se le asignará un psicólogo
     */
    public void controlAsignarPsicologo(Paciente paciente) {
        controlAsignarPsicologo.inicia(paciente);
    }
    
    private void termina(){
        ventanaContestarHistorialClinico.setVisible(false);
    }
}
