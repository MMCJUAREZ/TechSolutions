package mx.uam.ayd.proyecto.presentacion.agregarPsicologo;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import mx.uam.ayd.proyecto.negocio.modelo.TipoEspecialidad;
import java.io.IOException;

@Component
public class VentanaAgregarPsicologo {

    @FXML
    private TextField textFieldNombre;
    
    @FXML
    private TextField textFieldCorreo;
    
    @FXML
    private TextField textFieldTelefono;
    
    @FXML
    private ComboBox<TipoEspecialidad> comboBoxEspecialidad;

    private Stage stage;
    private ControlAgregarPsicologo controlAgregarPsicologo;
    private boolean initialized = false;

    public VentanaAgregarPsicologo() {
        //constructor vacio
    }

    private void initializeUI(){
        if (initialized) {
            return;
        }
        
        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(this::initializeUI);
            return;
        }
        
        try {
            stage = new Stage();
            stage.setTitle("Centro Psicológico - Agregar Psicologo");
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ventanaAgregarPsicologo.fxml"));
            loader.setController(this);
            Scene scene = new Scene(loader.load(), 640, 400);
            stage.setScene(scene);

            inicializarComboBox();
            
            initialized = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        
    public void setControlAgregarPsicologo(ControlAgregarPsicologo control){
        this.controlAgregarPsicologo = control;
    }

    public void muestra(){
        if (!Platform.isFxApplicationThread()){
            Platform.runLater(() -> this.muestra());
            return;
        }

        initializeUI();
        stage.show();
    }

    private void inicializarComboBox() {
        if (comboBoxEspecialidad != null) {
            comboBoxEspecialidad.setItems(FXCollections.observableArrayList(TipoEspecialidad.values()));
            comboBoxEspecialidad.setPromptText("Seleccione una espacialidad");
        }
    }

    @FXML
    private void handleAgregarPsicologo(){
        String nombre = textFieldNombre.getText();
        String correo = textFieldCorreo.getText();
        String telefono = textFieldTelefono.getText();
        TipoEspecialidad especialidad = comboBoxEspecialidad.getValue();

        // Validaciones 
        if (nombre == null || nombre.trim().isEmpty()){
            mostrarError("El nombre es obligatorio");
            return;
        }

        if (correo == null || correo.trim().isEmpty()) {
            mostrarError("El correo es obligatorio");
            return;
        }

        if (!correo.contains("@") || !correo.contains(".")) {
            mostrarError("El formato del correo no es válido");
            return;
        }

        if (telefono == null || telefono.trim().isEmpty()) {
            mostrarError("El teléfono es obligatorio");
            return;
        }

        if (especialidad == null) {
            mostrarError("Debe seleccionar una especialidad");
            return;
        }

        if (controlAgregarPsicologo != null){
            controlAgregarPsicologo.agregarPsicologo(nombre, correo, telefono, especialidad);
        }
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error de validacion");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void mostrarExito(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Éxito");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void limpiarCampos() {
        textFieldNombre.clear();
        textFieldCorreo.clear();
        textFieldTelefono.clear();
        comboBoxEspecialidad.setValue(null);
    }

    @FXML
    private void handleCancelar() {
        if (stage != null) {
            stage.close();
        }
    }
}

