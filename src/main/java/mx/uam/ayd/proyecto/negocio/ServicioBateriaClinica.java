package mx.uam.ayd.proyecto.negocio;

import mx.uam.ayd.proyecto.datos.BateriaClinicaRepository;
import mx.uam.ayd.proyecto.negocio.modelo.BateriaClinica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioBateriaClinica {
    
    @Autowired
    private BateriaClinicaRepository bateriaClinicaRepository;

    /**
     * Guarda los comentarios para una batería clínica específica.
     * @param bateria La batería a actualizar.
     * @param comentarios Los nuevos comentarios.
     * @return La batería actualizada y guardada.
     */
    public BateriaClinica guardarComentarios(BateriaClinica bateria, String comentarios) {
        if (bateria == null) {
            throw new IllegalArgumentException("La batería no puede ser nula.");
        }
        bateria.setComentarios(comentarios);
        return bateriaClinicaRepository.save(bateria);
    }
    
    /**
     * Obtiene los detalles de una batería para mostrarlos.
     * (En el futuro, aquí se podrían buscar preguntas y respuestas desde el repositorio)
     * @param bateria La batería seleccionada.
     * @return Un string con los detalles.
     */
    public String obtenerDetallesBateria(BateriaClinica bateria) {
        if (bateria == null) {
            return "No se ha seleccionado ninguna batería.";
        }
        
        // La lógica de negocio es construir el string de detalles
        StringBuilder detalles = new StringBuilder();
        detalles.append("Detalles para la batería: ").append(bateria.getTipoDeBateria()).append("\n");
        detalles.append("Fecha de Aplicación: ").append(bateria.getFechaAplicacion()).append("\n");
        detalles.append("Puntaje: ").append(bateria.getCalificacion()).append("\n\n");
        detalles.append("(Funcionalidad para ver preguntas y respuestas en desarrollo)");
        
        return detalles.toString();
    }
}