package mx.uam.ayd.proyecto.presentacion.principal;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.springframework.stereotype.Component;

import java.io.IOException;
import javafx.scene.control.Alert;


@Component
public class VentanaPrincipal {

	private Stage stage;
	private ControlPrincipal control;
	private boolean initialized = false;

	public VentanaPrincipal() {
		// Constructor
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
			stage.setTitle("Mi Aplicación");
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ventana-principal.fxml"));
			loader.setController(this);
			Scene scene = new Scene(loader.load(), 450, 300);
			stage.setScene(scene);
			
			initialized = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setControlPrincipal(ControlPrincipal control) {
		this.control = control;
	}

	public void muestra() {
		if (!Platform.isFxApplicationThread()) {
			Platform.runLater(this::muestra);
			return;
		}
		
		initializeUI();
		stage.show();
	}
	
	@FXML
	private void handleAgregarUsuario() {
		if (control != null) {
			control.agregarUsuario();
		}
	}
	
	@FXML
	private void handleListarUsuarios() {
		if (control != null) {
			control.listarUsuarios();
		}
	}
	
	@FXML
	private void handleListarGrupos() {
		if (control != null) {
			control.listarGrupos();
		}
	}
	
	@FXML
    private void handleListarPacientes() {
        if (control != null) {
            control.listarPacientes();
        }
    }
	
	public void muestraDialogoDeError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}