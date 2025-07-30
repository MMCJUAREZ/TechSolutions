package mx.uam.ayd.proyecto.negocio.modelo;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import java.time.LocalDate;

@Entity // Esto le dice a Spring que esta es una entidad persistente
public class HistorialClinico {
    @Id // Esto le dice a Spring que este es el identificador
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Le dice a Spring que genere el id
	
    private Long idHistorialClinico;

    private Boolean concentimientoAceptado;

	private LocalDate fechaElaboracion;

	@OneToOne(targetEntity = Paciente.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)


	public LocalDate getFechaElaboracion(){
		return fechaElaboracion;
	}

	public void setFechaElaboracion(LocalDate fechaElaboracion){
		this.fechaElaboracion = fechaElaboracion;
	}

    /**
	 * @return the idHistorialClinico
	 */
	public Long getIdHistorialClinico() {
		return idHistorialClinico;
	}

    /**
	 * @param idHistorialClinico the idHistorialClinco to set
	 */
	public void setIdHistorialClinico(Long idHistorialClinico) {
		this.idHistorialClinico = idHistorialClinico;
	}

    /**
     *  @return the concentimientoAceptado
     */
    public Boolean getConcentimientoAceptado(){
        return concentimientoAceptado;
    }

    /**
	 * @param concentimientoAceptado concentimientoAceptado to set
	 */
	public void setConcentimientoAceptado(Boolean concentimientoAceptado) {
		this.concentimientoAceptado = concentimientoAceptado;
	}

    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HistorialClinico other = (HistorialClinico) obj;
		return idHistorialClinico == other.idHistorialClinico;
	}
}
