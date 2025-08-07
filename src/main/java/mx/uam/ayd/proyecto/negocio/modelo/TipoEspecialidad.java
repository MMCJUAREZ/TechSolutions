package mx.uam.ayd.proyecto.negocio.modelo;

public enum TipoEspecialidad {
    INFANTIL("Psicología Infantil"),
    MARITAL("Terapia Marital"),
    FAMILIAR("Terapia Familiar"),
    DELAMUJER("Psicología de la Mujer");
    
    private final String descripcion;
    
    TipoEspecialidad(String descripcion) {
        this.descripcion = descripcion;
    }
    
    @Override
    public String toString() {
        return descripcion;
    }
}