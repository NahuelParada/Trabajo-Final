package Clases;

import Enums.EstadoHabitacion;
import Enums.TipoHabitacion;
import Interfaces.Identificador;

public class Habitacion implements Identificador {
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

    @Override
    public int getIdentificador() {
        return this.numHabitacion;
    }

    public String toString()
    {
        return "Habitacion: \n" +
                "-Numero de Habitacion: " + numHabitacion + "\n" +
                "-Tipo de Habitacion: " + tipo.toString() + "\n" +
                "-Estado Actual: " + estado.toString() + "\n";
    }
}
