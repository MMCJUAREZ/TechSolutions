package mx.uam.ayd.proyecto.datos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mx.uam.ayd.proyecto.negocio.modelo.Psicologo;

//Repositorio para psicologo


public interface PsicologoRepository extends CrudRepository <Psicologo, Long>{
    
    public Psicologo findByTelefono(String telefono);

    public List <Psicologo> findByEspecialidad(String especialidad);

}