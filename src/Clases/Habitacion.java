package Clases;

import Enums.estadoHabitacion;
import Enums.tipoHabitacion;

public class Habitacion {
    private int numHabitacion;
    private tipoHabitacion tipo;
    private estadoHabitacion estado;

    public Habitacion(int numHabitacion, tipoHabitacion tipo, estadoHabitacion estado) {
        this.numHabitacion = numHabitacion;
        this.tipo = tipo;
        this.estado = estado;
    }

}
