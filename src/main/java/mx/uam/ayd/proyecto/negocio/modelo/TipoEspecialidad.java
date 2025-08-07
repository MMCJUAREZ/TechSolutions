package mx.uam.ayd.proyecto.negocio.modelo;

public enum TipoEspecialidad {
    INFANTIL("Psicologia Infantil"),
    MARITAL("Terapia Marital"),
    FAMILIAR("Terapia Familiar"),
    DELAMUJER("Psicologia de la Mujer");
    
    private final String descripcion;
    
    TipoEspecialidad(String descripcion) {
        this.descripcion = descripcion;
    }
    
    @Override
    public String toString() {
        return descripcion;
    }
}