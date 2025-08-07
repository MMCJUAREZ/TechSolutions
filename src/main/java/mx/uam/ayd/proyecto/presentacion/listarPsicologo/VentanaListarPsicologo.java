package mx.uam.ayd.proyecto.presentacion.listarPsicologo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

/*
 * Ventana para listar psicólogos
 * 
 * @author TechSolutions
 */

@Component
public class VentanaListarPsicologo {
    
    private ControlListarPsicologo controlListarPsicologo;
    private Stage stage;

    public void setControlListarPsicologo(ControlListarPsicologo controlListarPsicologo) {
        this.controlListarPsicologo = controlListarPsicologo;
    }

    public void muestra() {
        try {
            if (stage == null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/venatanaListarPsicologos.fxml"));
                loader.setController(this);
                Parent root = loader.load();
                
                stage = new Stage();
                stage.setTitle("Listar Psicólogos");
                stage.setScene(new Scene(root));
            }
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al cargar la ventana de listar psicólogos: " + e.getMessage());
        }
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
