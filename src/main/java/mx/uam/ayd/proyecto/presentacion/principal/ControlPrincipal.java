package mx.uam.ayd.proyecto.presentacion.principal;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import mx.uam.ayd.proyecto.presentacion.agregarUsuario.ControlAgregarUsuario;
import mx.uam.ayd.proyecto.presentacion.listarUsuarios.ControlListarUsuarios;
import mx.uam.ayd.proyecto.presentacion.listarGrupos.ControlListarGrupos;
import mx.uam.ayd.proyecto.datos.PsicologoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Psicologo;
import mx.uam.ayd.proyecto.presentacion.listarpacientes.ControlListarPacientes;

/**
 * Esta clase lleva el flujo de control de la ventana principal
 * * @author humbertocervantes
 *
 */
@Component
public class ControlPrincipal {

	private final ControlAgregarUsuario controlAgregarUsuario;
	private final ControlListarUsuarios controlListarUsuarios;
	private final ControlListarGrupos controlListarGrupos;
	private final VentanaPrincipal ventana;

	// --- CAMPOS PARA LA NUEVA FUNCIONALIDAD ---
	private final ControlListarPacientes controlListarPacientes;
	private final PsicologoRepository psicologoRepository;

	@Autowired
	public ControlPrincipal(
			ControlAgregarUsuario controlAgregarUsuario,
			ControlListarUsuarios controlListarUsuarios,
			ControlListarGrupos controlListarGrupos,
			VentanaPrincipal ventana,
			// Spring inyecta estas dependencias automáticamente
			ControlListarPacientes controlListarPacientes,
			PsicologoRepository psicologoRepository
		) {
		this.controlAgregarUsuario = controlAgregarUsuario;
		this.controlListarUsuarios = controlListarUsuarios;
		this.controlListarGrupos = controlListarGrupos;
		this.ventana = ventana;
		this.controlListarPacientes = controlListarPacientes;
		this.psicologoRepository = psicologoRepository;
	}
	
	@PostConstruct
	public void init() {
		ventana.setControlPrincipal(this);
	}
	
	public void inicia() {
		ventana.muestra();
	}

	public void agregarUsuario() {
		controlAgregarUsuario.inicia();
	}
	
	public void listarUsuarios() {
		controlListarUsuarios.inicia();
	}

	public void listarGrupos() {
		controlListarGrupos.inicia();
	}

	public void listarPacientes() {
    	controlListarPacientes.inicia(); 
	}
}