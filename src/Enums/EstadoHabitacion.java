package Enums;

public enum EstadoHabitacion {
    OCUPADA("OCUPADA"),
    LIBRE("LIBRE"),
    RESERVADA("RESERVADA"),
    MANTENIMIENTO("EN MANTENIMIENTO");

    public final String estado;

    EstadoHabitacion (String estado) {
       this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }
}
