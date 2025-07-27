package mx.uam.ayd.proyecto.negocio.modelo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // Esto le dice a Spring que esta es una entidad persistente
public class Paciente {
    //Entidad de negocio Paciente

	@Id // Esto le dice a Spring que este es el identificador
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Le dice a Spring que genere el id
	private long idPaciente;

	private String nombre;
	
	private String correo;
	
	private int telefono;
	
	/**
	 * @return the idPaciente
	 */
	public long getIdPaciente() {
		return idPaciente;
	}

	/**
	 * @param idPaciente the idPaciente to set
	 */
	public void setIdPaciente(long idPaciente) {
		this.idPaciente = idPaciente;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the correo
	 */
	public String getCorreo() {
		return correo;
	}

	/**
	 * @param correo the correo to set
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	/**
	 * @return the telefono
	 */
	public int getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Paciente other = (Paciente) obj;
		return idPaciente == other.idPaciente;
	}
	
	@Override
	public int hashCode() {
		return (int) (31 * idPaciente);
	}
	
	@Override
	public String toString() {
		return "Usuario [idPaciente=" + idPaciente + ", nombre=" + nombre + ", correo=" + correo + ", telefono=" + telefono + "]";
	}
}
