package mx.uam.ayd.proyecto.negocio;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.transaction.Transactional;

import mx.uam.ayd.proyecto.datos.BateriaClinicaRepository;
import mx.uam.ayd.proyecto.negocio.modelo.BateriaClinica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import mx.uam.ayd.proyecto.datos.BateriaClinicaRepository;
import mx.uam.ayd.proyecto.datos.PacienteRepository;
import mx.uam.ayd.proyecto.negocio.modelo.BateriaClinica;
import mx.uam.ayd.proyecto.negocio.modelo.Paciente;
import mx.uam.ayd.proyecto.negocio.modelo.TipoBateria;


@Service
public class ServicioBateriaClinica {
    
    private final BateriaClinicaRepository bateriaClinicaRepository;
    private final PacienteRepository pacienteRepository;

    @Autowired
    public ServicioBateriaClinica(BateriaClinicaRepository bateriaClinicaRepository,
                                    PacienteRepository pacienteRepository) {
                                        this.bateriaClinicaRepository=bateriaClinicaRepository;
                                        this.pacienteRepository=pacienteRepository;
    }

    /**
     * Registra las respuestas de y obtiene el puntaje para despues asociar la bateria al paciente
     * @param pacienteID el id del paciente a quien se le va asociar la bateria
     * @param tipo el tipo de bateria que se va a registrar (CEPER, BAI, BDI-II)
     * @param respuestas una lista que contiene las respuestas dadas por el usuario
     * @param comentarios los comentarios que el psicologo hace respecto al puntaje obtenido 
     * @return una nueva bateria 
     */
    @Transactional
    public BateriaClinica registrarBateria(Long pacienteID,
                                            TipoBateria tipo,
                                            List<Integer> respuestas,
                                            String comentarios) {
        if(pacienteID==null) throw new IllegalArgumentException("pacienteID obligatorio");
        if(tipo==null) throw new IllegalArgumentException("Tipo es obligatorio");
        if(respuestas==null || respuestas.size() !=5) throw new IllegalArgumentException("Se requieren las 5 respuestas");

        Paciente paciente = pacienteRepository.findById(pacienteID).orElseThrow(() -> new IllegalArgumentException("Paciente no encontrado "+pacienteID));

        // Revisamos si no hay una bateria existente
        BateriaClinica bateria = bateriaClinicaRepository.findByPacienteAndTipoDeBateria(paciente, tipo).orElseGet(BateriaClinica::new);

        if (bateria.getId() == 0){
            bateria.setPaciente(paciente);
            bateria.setTipoDeBateria(tipo);
        }
        
        bateria.setFechaAplicacion(new Date());
        bateria.setRespuesta1(respuestas.get(0));
        bateria.setRespuesta2(respuestas.get(1));
        bateria.setRespuesta3(respuestas.get(2));
        bateria.setRespuesta4(respuestas.get(3));
        bateria.setRespuesta5(respuestas.get(4));

        //Regla del negocio, la calificacion es la suma
        int calificacion = respuestas.stream().filter(Objects::nonNull).mapToInt(Integer::intValue).sum();
        bateria.setCalificacion(calificacion);

        bateria.setComentarios(comentarios != null ? comentarios.trim() : " ");

        return bateriaClinicaRepository.save(bateria);
    }

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