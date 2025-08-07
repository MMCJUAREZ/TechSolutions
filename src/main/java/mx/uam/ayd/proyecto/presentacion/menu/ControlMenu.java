package mx.uam.ayd.proyecto.presentacion.menu;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// --- IMPORTACIONES NUEVAS ---
import mx.uam.ayd.proyecto.datos.PsicologoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Psicologo;
import mx.uam.ayd.proyecto.presentacion.listarpacientes.ControlListarPacientes;

@Component
public class ControlMenu {

    private final VentanaMenu ventana;
    
    // --- NUEVOS CAMPOS PARA INYECTAR ---
    private final ControlListarPacientes controlListarPacientes;
    private final PsicologoRepository psicologoRepository;
    
    @Autowired
    public ControlMenu(
            VentanaMenu ventana,
            // Spring inyectará estas dependencias automáticamente
            ControlListarPacientes controlListarPacientes,
            PsicologoRepository psicologoRepository
        ) {
        this.ventana = ventana;
        this.controlListarPacientes = controlListarPacientes;
        this.psicologoRepository = psicologoRepository;
    }
    
    @PostConstruct
    public void init() {
        ventana.setControlMenu(this);
    }
    
    public void inicia() {
        ventana.muestra();
    }
    
    public void agregarPaciente() {
        System.out.println("Agregar paciente - Funcionalidad pendiente");
    }
    
    /**
     * MÉTODO CORREGIDO: Ahora inicia el flujo correcto para listar pacientes.
     */
    public void listarPacientes() {
        // Buscamos el psicólogo de prueba que se crea al iniciar la aplicación
        Psicologo psicologo = psicologoRepository.findById(1).orElse(null);

        if (psicologo != null) {
            // Le decimos al controlador de listar pacientes que inicie su flujo
            controlListarPacientes.inicia(psicologo);
        } else {
            // En caso de que no se encuentre el psicólogo de prueba
            System.out.println("Error: No se encontró el psicólogo de prueba para iniciar el listado.");
            // Opcional: podrías mostrar un diálogo de error aquí.
        }
    }
    
    public void agregarPsicologo() {
        System.out.println("Agregar psicólogo - Funcionalidad pendiente");
    }
    
    public void listarPsicologo() {
        System.out.println("Listar psicólogos - Funcionalidad pendiente");
    }
    
    public void salir() {
        System.exit(0);
    }
}