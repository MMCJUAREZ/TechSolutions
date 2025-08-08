package mx.uam.ayd.proyecto.presentacion.menu;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// --- IMPORTACIONES NUEVAS ---
import mx.uam.ayd.proyecto.datos.PsicologoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Psicologo;
import mx.uam.ayd.proyecto.presentacion.listarpacientes.ControlListarPacientes;
import mx.uam.ayd.proyecto.presentacion.agregarPsicologo.ControlAgregarPsicologo;
import mx.uam.ayd.proyecto.presentacion.listarPsicologo.ControlListarPsicologo;
import mx.uam.ayd.proyecto.presentacion.agregarPaciente.ControlAgregarPaciente;


@Component
public class ControlMenu {

    private final VentanaMenu ventana;
    
    // --- NUEVOS CAMPOS PARA INYECTAR ---
    private final ControlListarPacientes controlListarPacientes;
    private final PsicologoRepository psicologoRepository;

    //Control para agregar paciente
    private final ControlAgregarPaciente controlAgregarPaciente;

    // campos para agregar psicologo
    private final ControlAgregarPsicologo controlAgregarPsicologo;
    
    // campo para listar psicologo
    private final ControlListarPsicologo controlListarPsicologo;
    
    @Autowired
    public ControlMenu(
            VentanaMenu ventana,
            // Spring inyectará estas dependencias automáticamente
            ControlListarPacientes controlListarPacientes,
            PsicologoRepository psicologoRepository,
            ControlAgregarPsicologo controlAgregarPsicologo,
            ControlListarPsicologo controlListarPsicologo,
            ControlAgregarPaciente controlAgregarPaciente
        ) {
        this.ventana = ventana;
        this.controlListarPacientes = controlListarPacientes;
        this.psicologoRepository = psicologoRepository;
        this.controlAgregarPsicologo = controlAgregarPsicologo;
        this.controlListarPsicologo = controlListarPsicologo;
        this.controlAgregarPaciente = controlAgregarPaciente;
    }
    
    @PostConstruct
    public void init() {
        ventana.setControlMenu(this);
    }
    
    public void inicia() {
        ventana.muestra();
    }
    
    //Metodo que inicia el flujo para agregar paciente
    public void agregarPaciente() {
        controlAgregarPaciente.inicia();
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
        controlAgregarPsicologo.inicia();
    }
    
    public void listarPsicologo() {
        controlListarPsicologo.inicia();
    }
    
    public void salir() {
        System.exit(0);
    }
}