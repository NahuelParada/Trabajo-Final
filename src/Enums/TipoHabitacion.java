package Enums;

public enum TipoHabitacion {
    ESTANDAR("ESTANDAR"),
    ESTANDARPLUS("ESTANDAR PLUS"),
    SUIT("SUIT");

    private final String tipo;

    TipoHabitacion(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}
