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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
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
    @FXML private Button btnAbrirDetalles;
    @FXML private Button btnVerHistorial;

    private BateriaClinica bateriaSeleccionada;
    private List<BateriaClinica> bateriasDelPaciente;
    private Paciente pacienteSeleccionado;

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
                (observable, oldValue, newValue) -> {
                    this.pacienteSeleccionado = newValue; // Guardamos referencia al paciente actual
                    control.seleccionarPaciente(newValue);
                }
            );

            listaBaterias.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    btnAbrirDetalles.setDisable(newValue == null);
                    if (newValue != null && bateriasDelPaciente != null) {
                        bateriasDelPaciente.stream()
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
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void muestraDialogoDeError(String mensaje) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void mostrarBaterias(List<BateriaClinica> baterias) {
        this.bateriasDelPaciente = baterias;
        List<String> nombresBaterias = baterias.stream()
            .map(bateria -> bateria.getTipoDeBateria().toString())
            .collect(Collectors.toList());
        listaBaterias.setItems(FXCollections.observableArrayList(nombresBaterias));
    }

    public void mostrarDetallesBateria(BateriaClinica bateria) {
        this.bateriaSeleccionada = bateria;
        puntajeObtenidoLabel.setText(String.valueOf(bateria.getCalificacion()));
        comentariosTextArea.setText(bateria.getComentarios());
    }

    public void limpiarDetallesDeBateria() {
        this.bateriaSeleccionada = null;
        this.bateriasDelPaciente = null;
        listaBaterias.getItems().clear();
        puntajeObtenidoLabel.setText("-");
        comentariosTextArea.clear();
        btnAbrirDetalles.setDisable(true);
        btnVerHistorial.setDisable(true);
    }

    public void habilitarBotonHistorial(boolean habilitar) {
        btnVerHistorial.setDisable(!habilitar);
    }

    public void mostrarHistorialEnDialogo(String textoHistorial) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Historial Clínico");
        if (pacienteSeleccionado != null) {
            alert.setHeaderText("Detalles del historial de: " + pacienteSeleccionado.getNombre());
        }

        TextArea textArea = new TextArea(textoHistorial);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);

        alert.getDialogPane().setContent(textArea);
        alert.setResizable(true);
        alert.getDialogPane().setPrefSize(480, 320); // Tamaño inicial del diálogo
        alert.showAndWait();
    }

    @FXML
    private void handleGuardarComentarios() {
        control.guardarComentarios(bateriaSeleccionada, comentariosTextArea.getText());
    }

    @FXML
    private void handleCerrar() {
        control.cerrar();
    }

    @FXML
    private void handleAbrirDetalles() {
        if (bateriaSeleccionada != null) {
            control.abrirDetallesBateria(bateriaSeleccionada);
        }
    }

    @FXML
    private void handleVerHistorial() {
        if (pacienteSeleccionado != null) {
            control.solicitarHistorial(pacienteSeleccionado);
        }
    }
}