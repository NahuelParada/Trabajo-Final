package Clases;

import Enums.estadoHabitacion;
import Enums.tipoHabitacion;

public class Habitacion {
    private int numHabitacion;
    private tipoHabitacion tipo;
    private EstadoHabitacion estado;

    public Habitacion(int numHabitacion, tipoHabitacion tipo, EstadoHabitacion estado) {
        this.numHabitacion = numHabitacion;
        this.tipo = tipo;
        this.estado = estado;
    }

}
