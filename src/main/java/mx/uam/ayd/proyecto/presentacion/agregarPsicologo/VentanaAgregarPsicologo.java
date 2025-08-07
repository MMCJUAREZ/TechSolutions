package mx.uam.ayd.proyecto.presentacion.agregarPsicologo;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class VentanaAgregarPsicologo {

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

    @FXML
    private void handleAgregarPsicologo(){
        if (controlAgregarPsicologo != null) {
            //controlAgregarPsicologo.agregarPsicologo();
        }
    }
}