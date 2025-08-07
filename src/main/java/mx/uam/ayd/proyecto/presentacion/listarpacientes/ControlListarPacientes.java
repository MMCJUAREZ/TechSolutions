package mx.uam.ayd.proyecto.presentacion.listarpacientes;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import mx.uam.ayd.proyecto.negocio.ServicioPsicologo;
import mx.uam.ayd.proyecto.negocio.modelo.BateriaClinica;
import mx.uam.ayd.proyecto.negocio.modelo.Paciente;
import mx.uam.ayd.proyecto.negocio.modelo.Psicologo;

/**
 * Controlador para la HU-BA-6.
 * Maneja la lógica para que un psicólogo liste y vea los detalles de sus pacientes.
 */
@Component
public class ControlListarPacientes {

    @Autowired
    private ServicioPsicologo servicioPsicologo;

    @Autowired
    private VentanaListarPacientes ventana;
    
    private Psicologo psicologo;

    /**
     * Inicia el flujo de la HU-BA-6.
     * @param psicologo El psicólogo que ha iniciado sesión.
     */
    public void inicia(Psicologo psicologo) {
        this.psicologo = psicologo;
        // Llama al servicio para obtener solo los pacientes que tienen cuestionarios
        List<Paciente> pacientesConCuestionarios = servicioPsicologo.obtenerPacientesConCuestionarios();
        // Muestra la ventana con la lista de pacientes
        ventana.muestra(this, pacientesConCuestionarios);
    }

    /**
     * Se llama cuando el psicólogo selecciona un paciente de la lista en la ventana.
     * @param paciente el paciente seleccionado.
     */
    public void seleccionarPaciente(Paciente paciente) {
        if (paciente != null) {
            ventana.limpiarDetallesDeBateria();
            ventana.mostrarBaterias(paciente.getBateriasClinicas());
        }
    }
    
    /**
     * Se llama cuando el psicólogo selecciona una batería de la lista de un paciente.
     * @param bateria la batería clínica seleccionada.
     */
    public void seleccionarBateria(BateriaClinica bateria) {
        if (bateria != null) {
            ventana.mostrarDetallesBateria(bateria);
        }
    }

    /**
     * Guarda los comentarios realizados por el psicólogo sobre una batería.
     * @param bateria La batería clínica a actualizar.
     * @param comentarios Los nuevos comentarios.
     */
    public void guardarComentarios(BateriaClinica bateria, String comentarios) {
        if (bateria != null) {
            bateria.setComentarios(comentarios);
            // Aquí iría la llamada al servicio para guardar los cambios en la base de datos
            // ej: servicioBateria.actualizar(bateria);
            ventana.muestraDialogoDeInformacion("Comentarios guardados con éxito.");
        } else {
            ventana.muestraDialogoDeError("No hay una batería seleccionada para guardar comentarios.");
        }
    }
    
    /**
     * Cierra la ventana de listado.
     */
    public void cerrar() {
        ventana.cierra();
    }
}