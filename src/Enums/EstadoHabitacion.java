package Enums;

public enum EstadoHabitacion {
    OCUPADA("OCUPADA"),
    LIBRE("LIBRE"),
    MANTENIMIENTO("EN MANTENIMIENTO");

    public final String estado;

    EstadoHabitacion (String estado) {
       this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }
}
