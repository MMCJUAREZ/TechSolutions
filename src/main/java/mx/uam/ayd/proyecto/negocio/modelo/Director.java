package mx.uam.ayd.proyecto.negocio.modelo;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data // lombok para generar automáticamente los getters, setters y constructores.
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String correo;
    private String nombre;

    // Un Director puede conceder permisos a muchos Psicologos
    @OneToMany(mappedBy = "director", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Psicologo> psicologos;
}