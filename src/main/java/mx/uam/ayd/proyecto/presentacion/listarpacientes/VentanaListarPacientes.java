package mx.uam.ayd.proyecto.presentacion.listarpacientes;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import mx.uam.ayd.proyecto.negocio.modelo.BateriaClinica;
import mx.uam.ayd.proyecto.negocio.modelo.Paciente;

@Component
public class VentanaListarPacientes {

    private Stage stage;
    private ControlListarPacientes control;
    
    @FXML private TableView<Paciente> tablaPacientes;
    @FXML private TableColumn<Paciente, String> columnaNombre;
    @FXML private TableColumn<Paciente, String> columnaCorreo;
    @FXML private TableColumn<Paciente, String> columnaTelefono;
    @FXML private ListView<String> listaBaterias;
    @FXML private Label puntajeObtenidoLabel;
    @FXML private TextArea comentariosTextArea;
    
    private BateriaClinica bateriaSeleccionada;
    private List<BateriaClinica> bateriasDelPaciente;

    public void muestra(ControlListarPacientes control, List<Paciente> pacientes) {
        this.control = control;
        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(() -> muestra(control, pacientes));
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ventana-listar-pacientes.fxml"));
            loader.setController(this);

            Parent root = loader.load();
            columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            columnaCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
            columnaTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
            
            tablaPacientes.setItems(FXCollections.observableArrayList(pacientes));
    
            tablaPacientes.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> control.seleccionarPaciente(newValue)
            );

            listaBaterias.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if(newValue != null && bateriasDelPaciente != null) {
                        bateriasDelPaciente.stream()
                            // CORRECCIÓN 1: Usamos getTipoDeBateria() para encontrar la batería correcta
                            .filter(b -> b.getTipoDeBateria().toString().equals(newValue))
                            .findFirst()
                            .ifPresent(b -> control.seleccionarBateria(b));
                    }
                }
            );

            stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Listado de Pacientes");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cierra() {
        if (stage != null) {
            stage.close();
        }
    }
    
    public void muestraDialogoDeInformacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    
    public void muestraDialogoDeError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void mostrarBaterias(List<BateriaClinica> baterias) {
        this.bateriasDelPaciente = baterias;
        // CORRECCIÓN 2: Mapeamos usando el tipo de batería para mostrar en la lista
        List<String> nombresBaterias = baterias.stream()
            .map(bateria -> bateria.getTipoDeBateria().toString())
            .collect(Collectors.toList());
        listaBaterias.setItems(FXCollections.observableArrayList(nombresBaterias));
    }
    
    public void mostrarDetallesBateria(BateriaClinica bateria) {
        this.bateriaSeleccionada = bateria;
        // CORRECCIÓN 3: Usamos getCalificacion() en lugar de getPuntuacionTotal()
        puntajeObtenidoLabel.setText(String.valueOf(bateria.getCalificacion()));
        comentariosTextArea.setText(bateria.getComentarios());
    }
    
    public void limpiarDetallesDeBateria() {
        this.bateriaSeleccionada = null;
        this.bateriasDelPaciente = null;
        listaBaterias.getItems().clear();
        puntajeObtenidoLabel.setText("-");
        comentariosTextArea.clear();
    }

    @FXML
    private void handleGuardarComentarios() {
        control.guardarComentarios(bateriaSeleccionada, comentariosTextArea.getText());
    }

    @FXML
    private void handleCerrar() {
        control.cerrar();
    }
}