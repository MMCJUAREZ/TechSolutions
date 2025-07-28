package mx.uam.ayd.proyecto.negocio.modelo;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // Esto le dice a Spring que esta es una entidad persistente
public class HistorialClinico {
    @Id // Esto le dice a Spring que este es el identificador
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Le dice a Spring que genere el id
	
    private long idHistorialClinico;

    private boolean concentimientoAceptado;


    /**
	 * @return the idHistorialClinico
	 */
	public long getIdHistorialClinico() {
		return idHistorialClinico;
	}

    /**
	 * @param idHistorialClinico the idHistorialClinco to set
	 */
	public void setIdHistorialClinico(long idHistorialClinico) {
		this.idHistorialClinico = idHistorialClinico;
	}

    /**
     *  @return the concentimientoAceptado
     */
    public boolean getConcentimientoAceptado(){
        return concentimientoAceptado;
    }

    /**
	 * @param concentimientoAceptado concentimientoAceptado to set
	 */
	public void setConcentimientoAceptado(boolean concentimientoAceptado) {
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
