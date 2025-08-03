package mx.uam.ayd.proyecto.presentacion.principal1;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Controlador para la ventana principal del sistema de psicología
 * 
 * @author TechSolutions
 */
@Component
public class ControlPrincipal1 {

    private final VentanaPrincipal1 ventana;
    
    @Autowired
    public ControlPrincipal1(VentanaPrincipal1 ventana) {
        this.ventana = ventana;
    }
    
    /**
     * Método que se ejecuta después de la construcción del bean
     * y realiza la conexión bidireccional entre el control y la ventana
     */
    @PostConstruct
    public void init() {
        ventana.setControlPrincipal1(this);
    }
    
    /**
     * Inicia el flujo de control de la ventana principal de psicología
     */
    public void inicia() {
        ventana.muestra();
    }

    // Métodos para manejar los eventos de los botones solo que estan vacios 
    public void agregarPaciente() {
        System.out.println("Agregar paciente - Funcionalidad pendiente");
    }
    
    public void pacientesRegistrados() {
        System.out.println("Pacientes registrados - Funcionalidad pendiente");
    }

    public void listarPsicologos() {
        System.out.println("Listar psicólogos - Funcionalidad pendiente");
    }

    public void agregarCita() {
        System.out.println("Agregar cita - Funcionalidad pendiente");
    }

    public void editarCitas() {
        System.out.println("Editar citas - Funcionalidad pendiente");
    }
}