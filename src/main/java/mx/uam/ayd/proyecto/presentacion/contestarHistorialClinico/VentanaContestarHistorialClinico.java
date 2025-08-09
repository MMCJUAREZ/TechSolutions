package mx.uam.ayd.proyecto.presentacion.contestarHistorialClinico;

import java.io.IOException;

import org.springframework.stereotype.Component;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

@Component
public class VentanaContestarHistorialClinico {
    private Stage stage;
    private boolean initialized = false;
    private ControlContestarHistorialClinico controlContestarHistorialClinico;

    @FXML
    private TextField textFieldNombre;
    
    @FXML
    private TextField textFieldCorreo;
    
    @FXML
    private TextField textFieldMotivo;
    
    @FXML
    private TextField textFieldConsumo;
    
    @FXML
    private TextField textFieldDescripcion;
    
    @FXML
    private TextField textFieldDia;
    
    @FXML
    private TextField textFieldMes;
    
    @FXML
    private TextField textFieldAnio;
    
    @FXML
    private CheckBox checkBoxConsentimiento;

    public void setControlContestarHistorialClinico(ControlContestarHistorialClinico controlContestarHistorialClinico){
        this.controlContestarHistorialClinico = controlContestarHistorialClinico;
    }

    private void initializeUI() {
        if (initialized) return;
        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(this::initializeUI);
            return;
        }
        try {
            stage = new Stage();
            stage.setTitle("Historial Clinico: Centro de Bienestar Psicologico");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ventanaHistorialClinico.fxml"));
            loader.setController(this); // Solo si NO hay fx:controller en el FXML
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            initialized = true;
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "No se pudo cargar la ventana: " + e.getMessage()).showAndWait();
        }
    }

    public VentanaContestarHistorialClinico() {
        // No inicializar componentes JavaFX en el constructor
    }

    public void muestra() {
        if (!initialized) {
            initializeUI();
        }
        stage.show();
    }

    public void setVisible(boolean visible) {
        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(() -> this.setVisible(visible));
            return;
        }

        if (!initialized) {
            if (visible) {
                initializeUI();
            } else {
                return;
            }
        }

        if (visible) {
            stage.show();
        } else {
            stage.hide();
        }
    }

    public void muestraDialogoConMensaje(String mensaje) {
        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(() -> this.muestraDialogoConMensaje(mensaje));
            return;
        }

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    private void handleGuardar(){

        if (textFieldNombre.getText().isEmpty() || textFieldMotivo.getText().isEmpty() || 
            textFieldCorreo.getText().isEmpty() || textFieldConsumo.getText().isEmpty() ||
            textFieldDescripcion.getText().isEmpty()) {
            muestraDialogoConMensaje("Los campos no deben estar vacíos");
        } else {
            controlContestarHistorialClinico.guardarHistorialClinico(textFieldNombre.getText(), textFieldCorreo.getText(),
                                                                     textFieldMotivo.getText(),  textFieldConsumo.getText(),
                                                                     textFieldDescripcion.getText());
        }
    }
}
