package mx.uam.ayd.proyecto.negocio.modelo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity  // Notacion para Spring de que debe crear una entodad persistente
public class Psicologo {
    //Entidad psicologo

    @Id //Identificador
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Para que Spring genere el id
    private long idPsicologo;

    private String nombre;

    private String especialidad;

    //@Column(unique = true)
    private String telefono;

    /*
    * Se establecen las funciones geter and seter para cada uno
    * de los campos definidos en el modelo
    */

    public long getIdPsicologo() {
        return idPsicologo;
    }

    public void setIdPsicologo(long idPsicologo){
        this.idPsicologo=idPsicologo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre=nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad){
        this.especialidad=especialidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono){
        this.telefono=telefono;
    }

    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Psicologo other = (Psicologo) obj;
		return idPsicologo == other.idPsicologo;
	}

    @Override
	public int hashCode() {
		return (int) (31 * idPsicologo);
	}
	
	@Override
	public String toString() {
		return "Psicologo [idPsicologo=" + idPsicologo + ", nombre=" + nombre + ", especialidad=" + especialidad + ", telefono=" + telefono + "]";
	}
}