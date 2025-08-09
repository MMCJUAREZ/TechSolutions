package mx.uam.ayd.proyecto.presentacion.agregarCEPER;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.annotation.PostConstruct;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.util.Arrays;
import java.util.List;

import mx.uam.ayd.proyecto.negocio.ServicioBateriaClinica;
import mx.uam.ayd.proyecto.negocio.modelo.TipoBateria;
import mx.uam.ayd.proyecto.presentacion.agregarCEPER.VentanaAgregarCEPER;



@Component
public class ControlAgregarCEPER {
    
    private final VentanaAgregarCEPER ventanaAgregarCEPER;
    private final ServicioBateriaClinica servicioBateriaClinica;

    private Long pacienteID;

    @Autowired
    public ControlAgregarCEPER(
        VentanaAgregarCEPER ventanaAgregarCEPER,
        ServicioBateriaClinica servicioBateriaClinica) {
            this.ventanaAgregarCEPER=ventanaAgregarCEPER;
            this.servicioBateriaClinica=servicioBateriaClinica;
        }
    
    @PostConstruct
    public void inicializa(){
        ventanaAgregarCEPER.setControlAgregarCEPER(this);
    }

    public void inicia(Long pacienteID){
        this.pacienteID=pacienteID;
        ventanaAgregarCEPER.setControlAgregarCEPER(this);
        ventanaAgregarCEPER.setPacienteID(pacienteID);
        ventanaAgregarCEPER.muestra();
    }

    public void guardarCEPER(Long pacienteID, List<Integer> respuestas, String comentarios) {
        servicioBateriaClinica.registrarBateria(
            pacienteID,
            TipoBateria.CEPER,
            respuestas,
            comentarios
        );
    }    
}
