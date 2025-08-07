package mx.uam.ayd.proyecto.negocio;

import mx.uam.ayd.proyecto.datos.HistorialClinicoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.HistorialClinico;
import mx.uam.ayd.proyecto.negocio.modelo.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioHistorialClinico {
    
    @Autowired
    private HistorialClinicoRepository historialClinicoRepository;

    /**
     * Obtiene el historial de un paciente y lo devuelve como un texto formateado.
     * La lógica de negocio (formatear el texto) reside aquí.
     * @param paciente El paciente cuyo historial se quiere obtener.
     * @return Un String con el historial formateado, o un mensaje si no existe.
     */
    public String obtenerHistorialFormateado(Paciente paciente) {
        if (paciente.getHistorialClinico() != null) {
            // Llama al método del modelo para generar el texto
            return paciente.getHistorialClinico().toStringFormateado();
        }
        return "No hay un historial clínico registrado para este paciente.";
    }
}
