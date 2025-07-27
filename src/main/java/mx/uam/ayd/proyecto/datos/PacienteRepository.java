package mx.uam.ayd.proyecto.datos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mx.uam.ayd.proyecto.negocio.modelo.Paciente;

//Repositorio para usuarios


public interface PacienteRepository extends CrudRepository <Paciente, Long>{

    public Paciente findByCorreo(String correo);
	
	public List <Paciente> findByEdadBetween(int edad1, int edad2);
    
} 
