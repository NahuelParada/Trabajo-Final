package Enums;

public enum tipoHabitacion {
    ESTANDAR("ESTANDAR"),
    ESTANDARPLUS("ESTANDAR PLUS"),
    SUIT("SUIT");

    private final String tipo;

    tipoHabitacion(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}
