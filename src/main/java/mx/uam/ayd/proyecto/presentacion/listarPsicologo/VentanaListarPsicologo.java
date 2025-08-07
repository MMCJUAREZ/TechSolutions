package mx.uam.ayd.proyecto.presentacion.listarPsicologo;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import mx.uam.ayd.proyecto.negocio.modelo.Psicologo;
import java.util.List;

/*
 * Ventana para listar psicólogos
 * 
 * @author TechSolutions
 */

@Component
public class VentanaListarPsicologo {

    private ControlListarPsicologo controlListarPsicologo;
    private Stage stage;

    @FXML
    private TableView<Psicologo> tableViewPsicologos;
    @FXML
    private TableColumn<Psicologo, Integer> tableColumnID;
    @FXML
    private TableColumn<Psicologo, String> tableColumnNombre;
    @FXML
    private TableColumn<Psicologo, String> tableColumnCorreo;
    @FXML
    private TableColumn<Psicologo, String> tableColumnTelefono;
    @FXML
    private TableColumn<Psicologo, String> tableColumnEspecialidad;

    public void setControlListarPsicologo(ControlListarPsicologo controlListarPsicologo) {
        this.controlListarPsicologo = controlListarPsicologo;
    }

    public void muestra(List<Psicologo> psicologos) {
        try {
            if (stage == null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ventanaListarPsicologos.fxml"));
                loader.setController(this);
                Parent root = loader.load();

                stage = new Stage();
                stage.setTitle("Listar Psicólogos");
                stage.setScene(new Scene(root));
            }
            stage.show();

            ObservableList<Psicologo> datos = FXCollections.observableArrayList(psicologos);
            tableViewPsicologos.setItems(datos);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al cargar la ventana de listar psicólogos: " + e.getMessage());
        }
    }

    @FXML
    public void initialize() {
        tableColumnID.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        tableColumnNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        tableColumnCorreo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCorreo()));
        tableColumnTelefono.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefono()));
        tableColumnEspecialidad.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEspecialidad().toString()));
    }

    public void setVisible(boolean visible) {
        if (stage != null) {
            if (visible) {
                stage.show();
            } else {
                stage.hide();
            }
        }
    }

    public void handleRegresar() {
        if (controlListarPsicologo != null) {
            controlListarPsicologo.termina();
        }
    }
}
