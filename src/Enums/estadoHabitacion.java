package Enums;

public enum estadoHabitacion {
    OCUPADO("OCUPADA"),
    LIBRE("LIBRE"),
    MANTENIMIENTO("EN MANTENIMIENTO");

    public final String estado;

    estadoHabitacion (String estado) {
       this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }
}
