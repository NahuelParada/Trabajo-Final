package Clases;

import Enums.EstadoHabitacion;
import Enums.TipoHabitacion;

public class Habitacion {
    private int numHabitacion;
    private TipoHabitacion tipo;
    private EstadoHabitacion estado;

    public Habitacion(int numHabitacion, TipoHabitacion tipo, EstadoHabitacion estado) {
        this.numHabitacion = numHabitacion;
        this.tipo = tipo;
        this.estado = estado;
    }

}
