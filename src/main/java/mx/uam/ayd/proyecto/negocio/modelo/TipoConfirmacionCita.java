package mx.uam.ayd.proyecto.negocio.modelo;

public enum TipoConfirmacionCita {
    PENDIENTE("Pendiente"),
    CONFIRMADA("Confirmada"),
    CANCELADA("Cancelada"),
    CONCLUIDA("Concluida");

    private final String descripcion;

    TipoConfirmacionCita(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}