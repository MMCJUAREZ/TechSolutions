package mx.uam.ayd.proyecto.presentacion.agregarBAI;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Ventana para capturar el inventario de ansiedad de Beck (BAI).
 *
 * Esta clase sigue el mismo patrón que el resto de las ventanas del
 * proyecto: carga un archivo FXML y permite mostrar u ocultar la
 * interfaz gráfica a través de métodos públicos.
 */
@Component
public class VentanaAgregarBAI {

    private Stage stage;
    private ControlAgregarBAI control;
    private boolean initialized = false;

    /**
     * Establece el controlador asociado a la ventana.
     *
     * @param control controlador correspondiente
     */
    public void setControlAgregarBAI(ControlAgregarBAI control) {
        this.control = control;
    }

    /**
     * Inicializa los componentes de la interfaz si aún no se han creado.
     */
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
            stage.setTitle("Inventario de Ansiedad de Beck (BAI)");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ventana-BAI.fxml"));
            loader.setController(this);
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);

            initialized = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Muestra la ventana.
     */
    public void muestra() {
        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(this::muestra);
            return;
        }

        initializeUI();
        stage.show();
    }

    /**
     * Permite mostrar u ocultar la ventana.
     *
     * @param visible indica si la ventana debe mostrarse
     */
    public void setVisible(boolean visible) {
        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(() -> setVisible(visible));
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
}

