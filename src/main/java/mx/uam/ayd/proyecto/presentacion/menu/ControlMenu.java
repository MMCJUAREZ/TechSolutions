package mx.uam.ayd.proyecto.presentacion.menu;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ControlMenu {

    private final VentanaMenu ventana;
    
    @Autowired
    public ControlMenu(VentanaMenu ventana) {
        this.ventana = ventana;
    }
    
    @PostConstruct
    public void init() {
        ventana.setControlMenu(this);
    }
    
    public void inicia() {
        ventana.muestra();
    }
    
    // Métodos para los botones FXML
    public void agregarPaciente() {
        System.out.println("Agregar paciente");
        // Falta aun
    }
    
    public void listarPacientes() {
        System.out.println("Listar pacientes");
        // Falta aun
    }
    
    public void agregarPsicologo() {
        System.out.println("Agregar psicólogo");
        // Falta aun
    }
    
    public void listarPsicologo() {
        System.out.println("Listar psicólogos");
        // Falta aun
    }
    
    public void salir() {
        System.exit(0);
    }
}