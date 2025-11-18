package Clases;

import Enums.EstadoHabitacion;
import Enums.TipoHabitacion;
import Interfaces.Identificador;
import org.json.JSONObject;

import java.util.Objects;

public class Habitacion implements Identificador {
    /// Atributos
    private int numHabitacion;
    private TipoHabitacion tipo;
    private EstadoHabitacion estado;
    private double precioXNoche;
    /// Constructor
    public Habitacion(int numHabitacion, TipoHabitacion tipo, EstadoHabitacion estado, double precioXNoche) {
        this.numHabitacion = numHabitacion;
        this.tipo = tipo;
        this.estado = estado;
        this.precioXNoche = precioXNoche;
    }
    /// Metodos

    public EstadoHabitacion getEstado() {
        return estado;
    }

    public void setEstado(EstadoHabitacion estado) {
        this.estado = estado;
    }

    public double getPrecioXNoche() {
        return precioXNoche;
    }

    public void setPrecioXNoche(double precioXNoche) {
        this.precioXNoche = precioXNoche;
    }

    public TipoHabitacion getTipo() {
        return tipo;
    }

    public int getNumHabitacion() {
        return numHabitacion;
    }

    public boolean disponibleParaReserva() {
        return estado == EstadoHabitacion.LIBRE;
    }

    public boolean disponibleParaCheckIn() {
        return estado == EstadoHabitacion.RESERVADA;
    }

    public boolean ocupadaParaCheckOut() {
        return estado == EstadoHabitacion.OCUPADA;
    }

    /// Interfaz

    @Override
    public int getIdentificador() {
        return this.numHabitacion;
    }

    /// Equals & Hashcode

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Habitacion that = (Habitacion) o;
        return numHabitacion == that.numHabitacion;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(numHabitacion);
    }

    /// ToString

    @Override
    public String toString()
    {
        return "Habitacion: \n" +
                "-Numero de Habitacion: " + numHabitacion + "\n" +
                "-Tipo de Habitacion: " + tipo.toString() + "\n" +
                "-Estado Actual: " + estado.toString() + "\n" +
                "-Precio por Noche: " + precioXNoche + "\n";
    }

    /// SERIALIZACION
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("numHabitacion", numHabitacion);
        json.put("tipo", tipo.toString());
        json.put("estado", estado.toString());
        json.put("precioXNoche", precioXNoche);
        return json;
    }

    /// DESERIALIZACION
    public Habitacion(JSONObject obj) {
        this.numHabitacion = obj.getInt("numHabitacion");
        this.tipo = TipoHabitacion.valueOf(obj.getString("tipo"));
        this.estado = EstadoHabitacion.valueOf(obj.getString("estado"));
        this.precioXNoche = obj.getDouble("precioXNoche");
    }

}
