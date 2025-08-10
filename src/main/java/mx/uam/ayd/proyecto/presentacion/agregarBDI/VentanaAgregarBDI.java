package mx.uam.ayd.proyecto.presentacion.agregarBDI;

import java.io.IOException;

import org.springframework.stereotype.Component;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;



@Component
public class VentanaAgregarBDI {

    private Stage stage;
    private boolean initialized = false; 
    private ControlAgregarBDI controlAgregarBDI;

    private Long pacienteID;

    public void setControlAgregarBDI(ControlAgregarBDI controlAgregarBDI) {
        this.controlAgregarBDI = controlAgregarBDI;
    }
    
    private void initializeUI() {
        if (initialized) return;
        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(this::initializeUI);
            return;
        }
        try {
            stage = new Stage();
            stage.setTitle("Inventario de Depresion de Beck (BDI-II)");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ventana-BDI.fxml"));
            loader.setController(this); // Solo si NO hay fx:controller en el FXML
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            initialized = true;
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "No se pudo cargar la ventana: " + e.getMessage()).showAndWait();
        }
    }

    public VentanaAgregarBDI() {
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

    public void setPacienteID(Long pacienteID) {
        this.pacienteID=pacienteID;
    }

    @FXML private javafx.scene.control.ToggleGroup q1;
    @FXML private javafx.scene.control.ToggleGroup q2;
    @FXML private javafx.scene.control.ToggleGroup q3;
    @FXML private javafx.scene.control.ToggleGroup q4;
    @FXML private javafx.scene.control.ToggleGroup q5;

    @FXML    
    private void onGuard() {
        try {
            java.util.List<Integer> respuestas = java.util.Arrays.asList(
                getSelectedValue(q1),
                getSelectedValue(q2),
                getSelectedValue(q3),
                getSelectedValue(q4),
                getSelectedValue(q5)
            );

            if (respuestas.stream().anyMatch(r -> r == null)) {
                muestraDialogoConMensaje("Responde todas las preguntas antes de guardar.");
                return;
            }

            String comentarios = " ";
            controlAgregarBDI.guardarBDI(pacienteID, respuestas, comentarios);

            muestraDialogoConMensaje("¡Batería BDI guardada!");
            stage.close();

        } catch (Exception ex) {
            new Alert(Alert.AlertType.ERROR, "Error al guardar: " + ex.getMessage()).showAndWait();
        }
    
    }

    private Integer getSelectedValue(ToggleGroup group) {
        if (group != null && group.getSelectedToggle() != null &&
            group.getSelectedToggle().getUserData() != null) {
            return Integer.parseInt(group.getSelectedToggle().getUserData().toString());
        }
        return 0;
    }
}
