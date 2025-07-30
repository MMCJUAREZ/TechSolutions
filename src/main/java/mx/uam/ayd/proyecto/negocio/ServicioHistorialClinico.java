package mx.uam.ayd.proyecto.negocio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.ayd.proyecto.datos.HistorialClinicoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.HistorialClinico;


@Service
/**
 * Servicio relacionado con los pacientes
 * 
 * @author TechSolutions
 *
 */
public class ServicioHistorialClinico {

    private final HistorialClinicoRepository historialClinicoRepository;

    @Autowired
	public ServicioHistorialClinico(HistorialClinicoRepository historialClinicoRepository) {
		this.historialClinicoRepository = historialClinicoRepository;
	}

    /**
	 * 
	 * Recupera todos los historiales clinicos
	 * 
	 * @return una lista de historiales vacía o con los historiales existentes
	 */
	public List <HistorialClinico> recuperaHistoriales() {
		List <HistorialClinico> historialesclinico = new ArrayList<>();
		
		for(HistorialClinico historial:historialClinicoRepository.findAll()) {
			historialesclinico.add(historial);
		}
				
		return historialesclinico;
	}
}
