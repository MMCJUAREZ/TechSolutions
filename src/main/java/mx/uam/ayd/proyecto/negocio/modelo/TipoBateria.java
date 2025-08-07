package mx.uam.ayd.proyecto.negocio.modelo;

public enum TipoBateria {
    CEPER("CEPER"),
    BDI_II("BDI-II"),
    BAI("BAI");

    private final String descripcion;

    TipoBateria(String descripcion){
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}