package mx.uam.ayd.proyecto.presentacion.principal;
import mx.uam.ayd.proyecto.presentacion.menu.ControlMenu;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Controlador de Login para el Centro Psicológico
 * 
 * @author TechSolutions
 */
@Component
public class ControlPrincipalCentro {

    private final VentanaPrincipalCentro ventanaLogin;
    private final ControlMenu controlMenu;
    
    @Autowired
    public ControlPrincipalCentro(VentanaPrincipalCentro ventanaLogin, ControlMenu controlMenu) {
        this.ventanaLogin = ventanaLogin;
        this.controlMenu = controlMenu;
    }
    
    @PostConstruct
    public void init() {
        ventanaLogin.setControlPrincipalCentro(this);
    }
    
    /**
     * Inicia el flujo de la ventana de login
     */
    public void inicia() {
        ventanaLogin.muestra();
    }
    
    /**
     * Autentica las credenciales del usuario
     * 
     * @param usuario nombre de usuario
     * @param contrasena contraseña
     */
    public void autenticar(String usuario, String contrasena) {
        // Validación de campos vacíos
        if (usuario == null || usuario.trim().isEmpty()) {
            ventanaLogin.mostrarError("Por favor ingrese un usuario");
            return;
        }
        
        if (contrasena == null || contrasena.trim().isEmpty()) {
            ventanaLogin.mostrarError("Por favor ingrese una contraseña");
            return;
        }
        
        // Autenticación para el centro psicológico
        if ("Admin".equals(usuario) && "admin1234".equals(contrasena)) {
            ventanaLogin.cerrarLogin();
            mostrarSistemaPrincipal();
        } else {
            ventanaLogin.mostrarError("Usuario o contraseña incorrectos");
        }
    }
    
    /**
     * Muestra el sistema principal del centro psicológico después del login exitoso
     */
    private void mostrarSistemaPrincipal() {
        ventanaLogin.cerrarLogin();
        controlMenu.inicia();
    }
}