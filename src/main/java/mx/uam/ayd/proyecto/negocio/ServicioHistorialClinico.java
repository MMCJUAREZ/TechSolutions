package mx.uam.ayd.proyecto.negocio;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.uam.ayd.proyecto.datos.HistorialClinicoRepository;
import mx.uam.ayd.proyecto.datos.PacienteRepository;
import mx.uam.ayd.proyecto.negocio.modelo.HistorialClinico;
import mx.uam.ayd.proyecto.negocio.modelo.Paciente;

@Service
public class ServicioHistorialClinico {

    @Autowired
    private HistorialClinicoRepository historialClinicoRepository;
    //Si se van a inyectar por atributo cada dependencia lleva autowired
    @Autowired
    private PacienteRepository pacienteRepository;

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

    //No estaba guardando historial clínico porque no recibia la entidad
    //previamente creada
    @Transactional
    public HistorialClinico guardarHistorialClinico(
            String nombre, 
            String correo, 
            String motivo, 
            String consumoDrogas, 
            String descripcion,
            boolean consentimientoAceptado) {

        // 1. VALIDACIONES
            //Buscar paciente dentro de la misma transacción
        Paciente paciente = pacienteRepository.findByCorreo(correo);
        if (paciente == null) {
            throw new IllegalArgumentException("No existe un paciente con el correo: " + correo);
        }
            //Validar que el nombre coincida con el paciente guardado
        if (!paciente.getNombre().equalsIgnoreCase(nombre.trim())) {
            throw new IllegalArgumentException("El nombre no coincide con el registrado para el correo proporcionado.");
        }
            //Validar consentimiento
        if (!consentimientoAceptado) {
            throw new IllegalArgumentException("No se puede guardar el historial sin consentimiento aceptado.");
        }
            //Validar fecha del sistema (no antigua ni futura)
        Date fechaActual = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fechaSistemaStr = sdf.format(fechaActual);
        try {
            fechaActual = sdf.parse(fechaSistemaStr); // quitar horas/minutos
        } catch (Exception e) {
            throw new RuntimeException("Error al formatear la fecha del sistema.");
        }
        // Validar que la fecha no sea antigua ni futura
        if (fechaActual.before(new Date(0)) || fechaActual.after(new Date())) {
            throw new IllegalArgumentException("La fecha del sistema no puede ser antigua ni futura.");
        }

        // 2. Crear historial clínico y enlazar ambos lados
        HistorialClinico historialClinico = new HistorialClinico();
        historialClinico.setPaciente(paciente); // @MapsId usará el ID del paciente
        paciente.setHistorialClinico(historialClinico); // Enlace inverso

        // 3. Llenar campos
        historialClinico.setMotivo(motivo);
        historialClinico.setConsumoDrogas(consumoDrogas);
        historialClinico.setDescripcionDrogas(descripcion);
        historialClinico.setFechaElaboracion(new java.util.Date());
        historialClinico.setConsentimientoAceptado(true); // O manejarlo desde parámetro

        // 4. Guardar historial (paciente ya está gestionado en la sesión)
        return historialClinicoRepository.save(historialClinico);
    }
}
