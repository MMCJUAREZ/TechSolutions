package mx.uam.ayd.proyecto.presentacion.menu;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class VentanaMenu {

    private Stage stage;
    private ControlMenu control;
    private boolean initialized = false;

    public VentanaMenu() {
        // Constructor vacio
    }
    
    private void initializeUI() {
        if (initialized) {
            return;
        }
        
        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(this::initializeUI);
            return;
        }
        
        try {
            stage = new Stage();
            stage.setTitle("Centro Psicológico - Menú Principal");
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ventanaPrincipal.fxml"));
            loader.setController(this);
            Scene scene = new Scene(loader.load(), 640, 400);
            stage.setScene(scene);
            
            initialized = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void setControlMenu(ControlMenu control) {
        this.control = control;
    }

    public void muestra() {
        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(() -> this.muestra());
            return;
        }
        
        initializeUI();
        stage.show();
    }
    
    // Handler para botones FXML
    @FXML
    private void handleAgregarPaciente() {
        if (control != null) {
            control.agregarPaciente();
        }
    }
    
    @FXML
    private void handleListarPacientes() {
        if (control != null) {
            control.listarPacientes();
        }
    }
    
    @FXML
    private void handleAgregarPsicologo() {
        if (control != null) {
            control.agregarPsicologo();
        }
    }
    
    @FXML
    private void handleListarPsicologo() {
        if (control != null) {
            control.listarPsicologo();
        }
    }
    
    @FXML
    private void handleSalir() {
        if (control != null) {
            control.salir();
        }
    }
}