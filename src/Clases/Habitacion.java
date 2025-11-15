package Clases;

import Enums.EstadoHabitacion;
import Enums.TipoHabitacion;

public class Habitacion {
    /// Atributos
    private int numHabitacion;
    private TipoHabitacion tipo;
    private EstadoHabitacion estado;
    /// Constructor
    public Habitacion(int numHabitacion, TipoHabitacion tipo, EstadoHabitacion estado) {
        this.numHabitacion = numHabitacion;
        this.tipo = tipo;
        this.estado = estado;
    }

}
