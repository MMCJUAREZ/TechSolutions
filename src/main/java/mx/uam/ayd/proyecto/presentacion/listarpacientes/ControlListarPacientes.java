package mx.uam.ayd.proyecto.presentacion.listarpacientes;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import mx.uam.ayd.proyecto.negocio.ServicioBateriaClinica;
import mx.uam.ayd.proyecto.negocio.ServicioPaciente; 
import mx.uam.ayd.proyecto.negocio.modelo.BateriaClinica;
import mx.uam.ayd.proyecto.negocio.modelo.HistorialClinico;
import mx.uam.ayd.proyecto.negocio.modelo.Paciente;

@Component
public class ControlListarPacientes {

    @Autowired
    private VentanaListarPacientes ventana;    
    @Autowired
    private ServicioPaciente servicioPaciente; 
    @Autowired
    private ServicioBateriaClinica servicioBateriaClinica;

    public void inicia() {
        List<Paciente> todosLosPacientes = servicioPaciente.recuperarTodosLosPacientes();
        ventana.muestra(this, todosLosPacientes);
    }

    public void seleccionarPaciente(Paciente paciente) {
        ventana.limpiarDetallesDeBateria();
        ventana.limpiarHistorialEnPestana();

        if (paciente != null) {
            ventana.mostrarBaterias(paciente.getBateriasClinicas());
            
            HistorialClinico historial = paciente.getHistorialClinico();
            if (historial != null) {
                ventana.mostrarHistorialEnPestana(historial);
            }
        }
    }
    
    public void seleccionarBateria(BateriaClinica bateria) {
        if (bateria != null) {
            ventana.mostrarDetallesBateria(bateria);
        }
    }

    public void abrirDetallesBateria(BateriaClinica bateria) {
        String detalles = servicioBateriaClinica.obtenerDetallesBateria(bateria);
        ventana.muestraDialogoDeInformacion(detalles);
    }

    public void guardarComentarios(BateriaClinica bateria, String comentarios) {
        if (bateria != null) {
            try {
                servicioBateriaClinica.guardarComentarios(bateria, comentarios);
                ventana.muestraDialogoDeInformacion("Comentarios guardados con éxito.");
            } catch (Exception e) {
                ventana.muestraDialogoDeError("Error al guardar: " + e.getMessage());
            }
        } else {
            ventana.muestraDialogoDeError("No hay una batería seleccionada para guardar comentarios.");
        }
    }
    
    public void cerrar() {
        ventana.cierra();
    }
}