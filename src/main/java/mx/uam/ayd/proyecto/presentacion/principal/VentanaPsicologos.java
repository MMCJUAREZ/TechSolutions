package mx.uam.ayd.proyecto.presentacion.principal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import mx.uam.ayd.proyecto.negocio.ServicioPsicologo;
import mx.uam.ayd.proyecto.negocio.modelo.Psicologo;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class VentanaPsicologos implements Initializable {

    @FXML private TableView<Psicologo> tablaPsicologos;
    @FXML private TableColumn<Psicologo, String> idNombre;
    @FXML private TableColumn<Psicologo, String> idEspecialidad;
    @FXML private TableColumn<Psicologo, String> idTelefono;
    
    private final ServicioPsicologo servicioPsicologo;
    
    public VentanaPsicologos(ServicioPsicologo servicioPsicologo) {
        this.servicioPsicologo = servicioPsicologo;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Configurar las columnas para que tomen los datos del objeto Psicologo
        idNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        idEspecialidad.setCellValueFactory(new PropertyValueFactory<>("especialidad"));
        idTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        
        // Cargar los datos de psicólogos
        cargarPsicologos();
    }
    
    private void cargarPsicologos() {
        try {
            List<Psicologo> psicologos = servicioPsicologo.listarPsicologos();
            ObservableList<Psicologo> observableList = FXCollections.observableArrayList(psicologos);
            tablaPsicologos.setItems(observableList);
        } catch (Exception e) {
            System.err.println("Error al cargar psicólogos en la tabla: " + e.getMessage());
        }
    }
    
    @FXML
    private void handlerRegresar() {
        // Cerrar la ventana actual
        Stage stage = (Stage) tablaPsicologos.getScene().getWindow();
        stage.close();
    }
}