

package mx.uam.ayd.proyecto.presentacion.agregarPaciente;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class VentanaAgregarPaciente {
    
    private Stage stage;
	private ControlAgregarPaciente control;
	
	@FXML
	private TextField textFieldNombre;

	@FXML
	private TextField textFieldCorreo;

	@FXML
	private TextField textFieldTelefono;

	private boolean initialized = false;

	/**
	 * Constructor without UI initialization
	 */
	public VentanaAgregarPaciente() {
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
			stage.setTitle("Agregar Paciente");

			// Load FXML
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ventana-1-2-agregar-paciente.fxml"));
			loader.setController(this);
			Scene scene = new Scene(loader.load(), 300, 220);
			stage.setScene(scene);
			
			initialized = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Establece el controlador asociado a esta ventana
	 * 
	 * @param control El controlador asociado
	 */
	public void setControlAgregarPaciente(ControlAgregarPaciente control) {
		this.control = control;
	}
	
	/**
	 * Muestra la ventana y establece los datos
	 */
	public void muestra() {
		if (!Platform.isFxApplicationThread()) {
			Platform.runLater(this::muestra);
			return;
		}
		
		initializeUI();
		
		textFieldNombre.setText("");
		
		textFieldCorreo.setText("");
		textFieldTelefono.setText("");

		
		stage.show();
	}
	
	/**
	 * Muestra un diálogo con un mensaje
	 * 
	 * @param mensaje El mensaje a mostrar
	 */
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
	
	/**
	 * Oculta la ventana
	 */
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
	
	// FXML Event Handlers
	
	@FXML
	private void handleAgregarPaciente() {
		if(textFieldNombre.getText().isEmpty() || textFieldCorreo.getText().isEmpty() || textFieldTelefono.getText().isEmpty()) {
			muestraDialogoConMensaje("Todos los campos deben estar llenos");
		} else {
			control.agregaPaciente(textFieldNombre.getText(), textFieldCorreo.getText(), textFieldTelefono.getText());
		}
	}
	
	@FXML
	private void handleCancelar() {
		control.termina();
	}

}
