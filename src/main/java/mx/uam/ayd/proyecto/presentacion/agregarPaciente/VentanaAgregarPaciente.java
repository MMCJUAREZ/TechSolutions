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

    /**
     * Establece el controlador asociado a esta ventana
     * 
     * @param control El controlador asociado
     */
    public void setControlAgregarPaciente(ControlAgregarPaciente controlAgregarPaciente) {
        this.controlAgregarPaciente = controlAgregarPaciente;
    }

    /**
     * Inicializa la interfaz de usuario.
     */
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
            loader.setController(this); 
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            initialized = true;
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "No se pudo cargar la ventana: " + e.getMessage()).showAndWait();
        }
    }

    /**
     * Limpia los campos de texto de la ventana.
     */
    private void limpiarCampos() {
        textFieldNombre.clear();
        textFieldTelefono.clear();
        textFieldCorreo.clear();
        textFieldEdad.clear();
    }

    /**
     * Muestra la ventana.
     */
    public void muestra() {
        if (!initialized) {
            initializeUI();
        }
        limpiarCampos();
        stage.show();
    }

    /**
     * Constructor sin inicialización de UI
     */
    public VentanaAgregarPaciente() {
        // No inicializar componentes JavaFX en el constructor
    }

    /**
     * Muestra un diálogo con un mensaje.
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
     * Establece la visibilidad de la ventana.
     * 
     * @param visible true para mostrar la ventana, false para ocultarla
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


    /**
     * Maneja el evento del botón "Agregar paciente" en la ventana.
     */
    @FXML
    private void handleAgregarPaciente() {
        String nombre = textFieldNombre.getText().trim();
        String telefono = textFieldTelefono.getText().trim();
        String correo = textFieldCorreo.getText().trim();
        String edadStr = textFieldEdad.getText().trim();

        //Expresiones regulares en java para validar correo y telefono
        String regexCorreo = "^[\\w._%+-]+@[\\w.-]+\\.[A-Za-z]{2,}$";
        String regexTelefono = "^(\\+\\d{1,3}\\s?)?\\d{7,15}$";

        StringBuilder errores = new StringBuilder();

        // Validar campos vacíos
        if (nombre.isEmpty()) errores.append("- El nombre no debe estar vacío.\n");
        if (telefono.isEmpty()) errores.append("- El teléfono no debe estar vacío.\n");
        if (correo.isEmpty()) errores.append("- El correo no debe estar vacío.\n");
        if (edadStr.isEmpty()) errores.append("- La edad no debe estar vacía.\n");

        // Validar formato correo
        if (!correo.isEmpty() && !correo.matches(regexCorreo)) {
            errores.append("- El correo no tiene un formato válido.\n");
        }

        // Validar formato teléfono
        if (!telefono.isEmpty() && !telefono.matches(regexTelefono)) {
            errores.append("- El número de teléfono no es válido.\n");
        }

        // Validar edad
        if (!edadStr.isEmpty()) {
            try {
                int edad = Integer.parseInt(edadStr);
                if (edad <= 5) {
                    errores.append("- La edad debe ser mayor a 5 años.\n");
                }
            } catch (NumberFormatException e) {
                errores.append("- La edad debe ser un número válido.\n");
            }
        }

        // Mostrar todos los errores en un Alert
        if (errores.length() > 0) {
            muestraDialogoConMensaje("Por favor corrige los siguientes errores:\n\n" + errores.toString());
            return;
        }

        // Si pasa las validaciones, continuar
        int edad = Integer.parseInt(edadStr);
        controlAgregarPaciente.agregarPaciente(nombre, correo, telefono, edad);
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