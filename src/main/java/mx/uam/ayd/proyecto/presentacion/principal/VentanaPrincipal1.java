package mx.uam.ayd.proyecto.presentacion.principal;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import java.io.IOException;

/**
 * Ventana principal del sistema de psicología usando JavaFX con FXML
 */
@Component
public class VentanaPrincipal1 {

    private Stage stage;
    private ControlPrincipal1 control;
    private boolean initialized = false;

    /**
     * Constructor without UI initialization
     */
    public VentanaPrincipal1() {
        // Don't initialize JavaFX components in constructor
    }
    
    /**
     * Initialize UI components on the JavaFX application thread
     */
    private void initializeUI() {
        if (initialized) {
            return;
        }
        
        // Create UI only if we're on JavaFX thread
        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(this::initializeUI);
            return;
        }
        
        try {
            stage = new Stage();
            stage.setTitle("Centro de Atención Psicológica");
            
            // Load FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ventana-1-principal.fxml"));
            loader.setController(this);
            Scene scene = new Scene(loader.load(), 500, 400);
            stage.setScene(scene);
            
            initialized = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void setControlPrincipal1(ControlPrincipal1 control) {
        this.control = control;
    }

    /**
     * Muestra la ventana
     */
    public void muestra() {
        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(() -> this.muestra());
            return;
        }
        
        initializeUI();
        stage.show();
    }

    // FXML Event Handlers
    
    @FXML
    private void handleAgregarPaciente() {
        if (control != null) {
            control.agregarPaciente();
        }
    }
    
    @FXML
    private void handlePacientesRegistrados() {
        if (control != null) {
            control.pacientesRegistrados();
        }
    }
    
    @FXML
    private void handleListarPsicologos() {
        if (control != null) {
            control.listarPsicologos();
        }
    }
    
    @FXML
    private void handleAgregarCita() {
        if (control != null) {
            control.agregarCita();
        }
    }
    
    @FXML
    private void handleEditarCitas() {
        if (control != null) {
            control.editarCitas();
        }
    }
}