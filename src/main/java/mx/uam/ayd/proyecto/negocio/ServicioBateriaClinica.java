package mx.uam.ayd.proyecto.negocio;

import mx.uam.ayd.proyecto.datos.BateriaClinicaRepository;
import mx.uam.ayd.proyecto.negocio.modelo.BateriaClinica;
import mx.uam.ayd.proyecto.negocio.modelo.TipoBateria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

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

    public void guardarBateriaBDI(ToggleGroup q1, ToggleGroup q2, ToggleGroup q3, ToggleGroup q4, ToggleGroup q5){
        Integer r1 = obtenerValorSeleccionado(q1);
        Integer r2 = obtenerValorSeleccionado(q1);
        Integer r3 = obtenerValorSeleccionado(q1);
        Integer r4 = obtenerValorSeleccionado(q1);
        Integer r5 = obtenerValorSeleccionado(q1);

        TipoBateria tipoDeBateria = TipoBateria.BDI_II;

        //Validamos que todas las respuestas esten contestadas
        if(r1 == null || r2 == null || r3 == null || r4 == null || r5 == null){
            throw new IllegalArgumentException("Debe de contestar todas las preguntas");
        }

        BateriaClinica BDI = new BateriaClinica(tipoDeBateria, r1, r2, r3, r4 ,r5);
    
    }

    private Integer obtenerValorSeleccionado(ToggleGroup grupo) {
        Toggle toggle = grupo.getSelectedToggle();
        if (toggle != null) {
            return Integer.valueOf(toggle.getUserData().toString());
        }
        return null; // o 0 si prefieres un valor por defecto
    }
}