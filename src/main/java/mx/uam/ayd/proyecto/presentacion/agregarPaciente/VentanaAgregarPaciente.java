package mx.uam.ayd.proyecto.presentacion.agregarPaciente;

import java.io.IOException;

// Notaciones de Jakarta y Spring
import org.springframework.stereotype.Component;

// Importaciones necesarias de JavaFX
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

@Component
public class VentanaAgregarPaciente {
    private Stage stage;
    private boolean initialized = false;
    private ControlAgregarPaciente controlAgregarPaciente;

    @FXML
    private TextField textFieldNombre;
    
    @FXML
    private TextField textFieldTelefono;

    @FXML
    private TextField textFieldCorreo;

    @FXML
    private TextField textFieldEdad;

    public void setControlAgregarPaciente(ControlAgregarPaciente controlAgregarPaciente) {
        this.controlAgregarPaciente = controlAgregarPaciente;
    }

    private void initializeUI() {
        if (initialized) return;
        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(this::initializeUI);
            return;
        }
        try {
            stage = new Stage();
            stage.setTitle("Agregar Paciente");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ventanaAgregarPaciente.fxml"));
            loader.setController(this); // Solo si NO hay fx:controller en el FXML
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            initialized = true;
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "No se pudo cargar la ventana: " + e.getMessage()).showAndWait();
        }
    }

    public void muestra() {
        if (!initialized) {
            initializeUI();
        }

        //textFieldNombre.setText("");
		//textFieldCorreo.setText("");
		//textFieldTelefono.setText("");
		//textFieldEdad.setText("");
        stage.show();
    }

    /**
     * Establece el controlador asociado a esta ventana
     * 
     * @param control El controlador asociado
     */
    /**
     * Constructor sin inicialización de UI
     */
    public VentanaAgregarPaciente() {
        // No inicializar componentes JavaFX en el constructor
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


    /**
     * Maneja el evento del botón "Agregar paciente" en la ventana.
     */
    @FXML
    private void handleAgregarPaciente() {
        // Validación básica
        if (textFieldNombre.getText().isEmpty() || textFieldTelefono.getText().isEmpty() || 
            textFieldCorreo.getText().isEmpty() || textFieldEdad.getText().isEmpty()) {
            muestraDialogoConMensaje("Los campos no deben estar vacíos");
        } else {
            controlAgregarPaciente.agregarPaciente(textFieldNombre.getText(), textFieldCorreo.getText(), textFieldTelefono.getText(), Integer.parseInt(textFieldEdad.getText()));
        }
    }

    @FXML
    private void handleCeper(){
        if(controlAgregarPaciente != null) {
            controlAgregarPaciente.agregarCEPER();
        }
    }

    @FXML
    private void handleBAI(){
        if (controlAgregarPaciente != null) {
            controlAgregarPaciente.agregarBAI(); 
        }
    }

    @FXML
    private void handleBDI(){
        if (controlAgregarPaciente != null) {
            controlAgregarPaciente.agregarBDI(); 
        }
    }

}