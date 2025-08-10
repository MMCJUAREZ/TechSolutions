package mx.uam.ayd.proyecto.presentacion.principal;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import java.io.IOException;
import javafx.scene.control.PasswordField;

/**
 * Ventana de Login para el Centro Psicológico
 * 
 * @author TechSolutions
 */
@Component
public class VentanaPrincipalCentro {

    @FXML
    private TextField textFieldUsuario;
    
    @FXML
    private PasswordField textFieldContrasena;

    private Stage stage;
    private ControlPrincipalCentro control;
    private boolean initialized = false;

    public VentanaPrincipalCentro() {
        // Constructor vacío
    }

    /**
     * Inicializa la interfaz de usuario en el hilo de JavaFX
     */
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
            stage.setTitle("Login - Centro Psicológico TechSolutions");
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ventanaLogin.fxml"));
            loader.setController(this);
            Scene scene = new Scene(loader.load(), 640, 400);
            stage.setScene(scene);
            
            // Cerrar aplicación al cerrar ventana de login
            stage.setOnCloseRequest(e -> Platform.exit());
            
            initialized = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Establece el controlador para esta ventana
     * 
     * @param control el controlador principal del centro
     */
    public void setControlPrincipalCentro(ControlPrincipalCentro control) {
        this.control = control;
    }

    /**
     * Muestra la ventana de login
     */
    public void muestra() {
        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(() -> this.muestra());
            return;
        }
        
        initializeUI();
        stage.show();
    }

    /**
     * Maneja el evento del botón "Ingresar"
     */
    @FXML
    private void handleIngresar() {
        String usuario = textFieldUsuario.getText();
        String contrasena = textFieldContrasena.getText();
        
        if (control != null) {
            control.autenticar(usuario, contrasena);
        }
    }

    /**
     * Muestra un mensaje de error en una ventana de diálogo
     * 
     * @param mensaje el mensaje de error a mostrar
     */
    public void mostrarError(String mensaje) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de autenticación");
            alert.setHeaderText(null);
            alert.setContentText(mensaje);
            alert.showAndWait();
        });
    }

    /**
     * Cierra la ventana de login
     */
    public void cerrarLogin() {
        if (stage != null) {
            stage.close();
        }
    }
}