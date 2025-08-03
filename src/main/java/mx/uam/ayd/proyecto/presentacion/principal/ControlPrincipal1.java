package mx.uam.ayd.proyecto.presentacion.principal;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import mx.uam.ayd.proyecto.negocio.ServicioPsicologo;
import mx.uam.ayd.proyecto.negocio.modelo.Psicologo;
import mx.uam.ayd.proyecto.presentacion.agregarPaciente.ControlAgregarPaciente;


/**
 * Controlador para la ventana principal del sistema de psicología
 * 
 * @author TechSolutions
 */
@Component
public class ControlPrincipal1 {

    private final VentanaPrincipal1 ventana;
    private final ServicioPsicologo servicioPsicologo;
    private final ControlAgregarPaciente controlAgregarPaciente;
    @Autowired
    public ControlPrincipal1(VentanaPrincipal1 ventana, ServicioPsicologo servicioPsicologo
        , ControlAgregarPaciente controlAgregarPaciente) {
        this.ventana = ventana;
        this.servicioPsicologo = servicioPsicologo;
        this.controlAgregarPaciente = controlAgregarPaciente;
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
    //public void agregarPaciente() {
    //    System.out.println("Agregar paciente - Funcionalidad pendiente");
    //}
    
    /**
	 * Método que arranca la historia de usuario "agregar usuario"
	 * 
	 */
	public void agregarPaciente() {
		controlAgregarPaciente.inicia();
	}

    public void pacientesRegistrados() {
        System.out.println("Pacientes registrados - Funcionalidad pendiente");
    }

    public void listarPsicologos() {
        try {
            // Cargar el FXML de la ventana de psicólogos
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ventana-3-psicologos.fxml"));
            
            // Crear el controlador y pasarle el servicio
            VentanaPsicologos controllerPsicologos = new VentanaPsicologos(servicioPsicologo);
            loader.setController(controllerPsicologos);
            
            // Crear la escena y la ventana
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle("Psicólogos Registrados");
            stage.setScene(scene);
            stage.setResizable(false); // Igual que en el ejemplo del profesor
            stage.show();
            
            System.out.println("Ventana de psicólogos abierta correctamente");
            
        } catch (IOException e) {
            System.err.println("Error al abrir ventana de psicólogos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void agregarCita() {
        System.out.println("Agregar cita - Funcionalidad pendiente");
    }

    public void editarCitas() {
        System.out.println("Editar citas - Funcionalidad pendiente");
    }
}