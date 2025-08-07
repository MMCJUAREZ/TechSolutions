package mx.uam.ayd.proyecto.presentacion.listarpacientes;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import mx.uam.ayd.proyecto.negocio.ServicioBateriaClinica;
import mx.uam.ayd.proyecto.negocio.ServicioHistorialClinico;
import mx.uam.ayd.proyecto.negocio.ServicioPsicologo;
import mx.uam.ayd.proyecto.negocio.modelo.BateriaClinica;
import mx.uam.ayd.proyecto.negocio.modelo.Paciente;
import mx.uam.ayd.proyecto.negocio.modelo.Psicologo;

@Component
public class ControlListarPacientes {

    @Autowired
    private VentanaListarPacientes ventana;

    @Autowired
    private ServicioPsicologo servicioPsicologo;
    @Autowired
    private ServicioHistorialClinico servicioHistorialClinico;
    @Autowired
    private ServicioBateriaClinica servicioBateriaClinica;
    
    private Psicologo psicologo;

    public void inicia(Psicologo psicologo) {
        this.psicologo = psicologo;
        List<Paciente> pacientesConCuestionarios = servicioPsicologo.obtenerPacientesConCuestionarios();
        ventana.muestra(this, pacientesConCuestionarios);
    }

    public void seleccionarPaciente(Paciente paciente) {
        if (paciente != null) {
            ventana.limpiarDetallesDeBateria();
            ventana.mostrarBaterias(paciente.getBateriasClinicas());
            ventana.habilitarBotonHistorial(paciente.getHistorialClinico() != null);
        } else {
            ventana.limpiarDetallesDeBateria();
        }
    }

    public void solicitarHistorial(Paciente paciente) {
        // El controlador pide al servicio el historial ya formateado
        String historialTexto = servicioHistorialClinico.obtenerHistorialFormateado(paciente);
        ventana.mostrarHistorialEnDialogo(historialTexto);
    }

    public void seleccionarBateria(BateriaClinica bateria) {
        if (bateria != null) {
            ventana.mostrarDetallesBateria(bateria);
        }
    }

    public void abrirDetallesBateria(BateriaClinica bateria) {
        // El controlador pide al servicio los detalles de la batería
        String detalles = servicioBateriaClinica.obtenerDetallesBateria(bateria);
        ventana.muestraDialogoDeInformacion(detalles);
    }

    public void guardarComentarios(BateriaClinica bateria, String comentarios) {
        if (bateria != null) {
            try {
                // El controlador le pide al servicio que guarde
                servicioBateriaClinica.guardarComentarios(bateria, comentarios);
                ventana.muestraDialogoDeInformacion("Comentarios guardados con éxito.");
            } catch (Exception e) {
                ventana.muestraDialogoDeError("Error al guardar: " + e.getMessage());
            }
        } else {
            ventana.muestraDialogoDeError("No hay una batería seleccionada.");
        }
    }

    public void cerrar() {
        ventana.cierra();
    }
}