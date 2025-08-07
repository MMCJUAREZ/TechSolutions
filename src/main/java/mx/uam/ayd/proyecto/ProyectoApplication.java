package mx.uam.ayd.proyecto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import mx.uam.ayd.proyecto.datos.GrupoRepository;
import mx.uam.ayd.proyecto.datos.PsicologoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Grupo;
import mx.uam.ayd.proyecto.negocio.modelo.Psicologo;
import mx.uam.ayd.proyecto.presentacion.principal.ControlPrincipal;
import mx.uam.ayd.proyecto.presentacion.principal.ControlPrincipalCentro;


/**
 * 
 * Clase principal que arranca la aplicación 
 * construida usando el principio de 
 * inversión de control
 * Adaptada para usar JavaFX
 * 
 * @author Humberto Cervantes (c) 21 Nov 2022
 */
@SpringBootApplication
public class ProyectoApplication {

    private final ControlPrincipal controlPrincipal;
    private final ControlPrincipalCentro controlPrincipalCentro;
    private final GrupoRepository grupoRepository;
    private final PsicologoRepository psicologoRepository;
    
    @Autowired
    public ProyectoApplication(
            ControlPrincipal controlPrincipal,
            ControlPrincipalCentro controlPrincipalCentro,
            GrupoRepository grupoRepository,
            PsicologoRepository psicologoRepository) {
        this.controlPrincipal = controlPrincipal;
        this.controlPrincipalCentro = controlPrincipalCentro;
        this.grupoRepository = grupoRepository;
        this.psicologoRepository = psicologoRepository;
    }

    /**
     * Método principal
     *
     * @param args argumentos de la línea de comando
     */
    public static void main(String[] args) {
        // Launch JavaFX application
        Application.launch(JavaFXApplication.class, args);
    }
    
    /**
     * Clase interna para manejar la inicialización de JavaFX
     */
    public static class JavaFXApplication extends Application {
        
        private static ConfigurableApplicationContext applicationContext;
        
        @Override
        public void init() throws Exception {
            // Create Spring application context
            SpringApplicationBuilder builder = new SpringApplicationBuilder(ProyectoApplication.class);
            builder.headless(false);
            applicationContext = builder.run(getParameters().getRaw().toArray(new String[0]));
        }
        
        @Override
        public void start(Stage primaryStage) {
            // Initialize the application on the JavaFX thread
            Platform.runLater(() -> {
                applicationContext.getBean(ProyectoApplication.class).inicia();
            });
        }
        
        @Override
        public void stop() throws Exception {
            applicationContext.close();
            Platform.exit();
        }
    }
    
    /**
     * Metodo que arranca la aplicacion
     * inicializa la bd y arranca los controladores
     */
    public void inicia() {
        inicializaBD();
        
        // Make sure controllers are created on JavaFX thread
        Platform.runLater(() -> {
            // Nuestro proyecto arranca con loggin
            controlPrincipalCentro.inicia();      
            
            // Se comenta el del profe
            // controlPrincipal.inicia();
        });
    }
    
    /**
     * Se mantiene como referencia
     */
    public void iniciarSistemaProfesor() {
        Platform.runLater(() -> {
            controlPrincipal.inicia();  
        });
    }
    
    /**
     * Inicializa la BD con datos
     */
    public void inicializaBD() {
        // Vamos a crear los dos grupos de usuarios
        Grupo grupoAdmin = new Grupo();
        grupoAdmin.setNombre("Administradores");
        grupoRepository.save(grupoAdmin);
        
        Grupo grupoOps = new Grupo();
        grupoOps.setNombre("Operadores");
        grupoRepository.save(grupoOps);

        //Nuestros datos iniciales prueba
        Psicologo psicologo1 = new Psicologo();
        psicologo1.setNombre("Dr. Ana María González");
        psicologo1.setCorreo("ana.gonzalez@techsolutions.com");
        psicologo1.setTelefono("555-0101");
        psicologo1.setEspecialidad("Psicología Clínica");
        psicologoRepository.save(psicologo1);
    }
}
