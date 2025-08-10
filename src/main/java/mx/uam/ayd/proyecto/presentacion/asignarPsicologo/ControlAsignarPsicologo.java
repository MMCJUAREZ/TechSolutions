package mx.uam.ayd.proyecto.presentacion.asignarPsicologo;

import mx.uam.ayd.proyecto.negocio.ServicioPaciente;
import mx.uam.ayd.proyecto.negocio.ServicioPsicologo;
import mx.uam.ayd.proyecto.negocio.modelo.Paciente;
import mx.uam.ayd.proyecto.negocio.modelo.Psicologo;
import mx.uam.ayd.proyecto.presentacion.asignarPsicologo.VentanaAsignarPsicologo;

import org.springframework.stereotype.Component;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import jakarta.annotation.PostConstruct;

@Component
public class ControlAsignarPsicologo {
    private final VentanaAsignarPsicologo ventanaAsignarPsicologo;
    private final ServicioPsicologo servicioPsicologo;
    private final ServicioPaciente servicioPaciente;

    @Autowired
    public ControlAsignarPsicologo(VentanaAsignarPsicologo ventanaAsignarPsicologo,
                                    ServicioPaciente servicioPaciente,
                                    ServicioPsicologo servicioPsicologo){
                                        this.ventanaAsignarPsicologo = ventanaAsignarPsicologo;
                                        this.servicioPaciente = servicioPaciente;
                                        this.servicioPsicologo = servicioPsicologo;
    }
    /**
     * Método que se ejecuta después de la construcción del bean
     * y realiza la conexión bidireccional entre el control y la ventana
     */
    @PostConstruct
    public void init() {
        ventanaAsignarPsicologo.setControlAsignarPsicologo(this);
    }
    private Paciente pacienteActual;
    /**
     * Inicia el proceso de asignación de psicólogo para el paciente dado.
     * 
     * @param paciente El paciente al que se le asignará un psicólogo
     */
    public void inicia(Paciente paciente) {
        this.pacienteActual = paciente;
        List<Psicologo> psicologos = servicioPsicologo.obtenerPsicologosPorEdadPaciente(paciente);
        ventanaAsignarPsicologo.muestra(paciente, psicologos);
    }

    /**
     * Asigna un psicólogo al paciente actual.
     * 
     * @param paciente El paciente al que se le asignará un psicólogo
     * @param psicologo El psicólogo que se asignará al paciente
     */
    public void asignarPsicologo(Paciente paciente, Psicologo psicologo) {

        try {
			servicioPaciente.asignarPsicologo(paciente, psicologo);
			ventanaAsignarPsicologo.muestraDialogoConMensaje("Psicólogo asignado exitosamente");	
            termina(); //Solo termina la ventana si se ha guardado el HC
        } catch(Exception ex) {
			ventanaAsignarPsicologo.muestraDialogoConMensaje("Error al asignar el psicólogo: "+ex.getMessage());
		}
        
    }
    private void termina(){
        ventanaAsignarPsicologo.setVisible(false);
    }
}
