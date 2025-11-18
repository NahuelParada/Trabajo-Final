package Clases;
import Enums.MetodoPago;
import Enums.EstadoHabitacion;
import Enums.TipoHabitacion;
import Interfaces.Identificador;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Reserva implements Identificador {

    /// ATRIBUTOS

    private int idReserva;
    private static int contador = 1;
    private MetodoPago pago;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Pasajero pasajero;
    private Habitacion habitacion;

    /// Constructor

    public Reserva(MetodoPago pago, LocalDate fechaInicio,LocalDate fechaFin, Pasajero pasajero, Habitacion habitacion) {
        idReserva = contador++;
        this.pago = pago;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.pasajero = pasajero;
        this.habitacion = habitacion;
    }
    /// Getter y Setter

    public Habitacion getHabitacion() {
        return habitacion;
    }


    /// Metodo
    public int getIdReserva() {
        return idReserva;
    }

    public double calcularCosto() {
        long noches = ChronoUnit.DAYS.between(fechaInicio, fechaFin);
        return noches * habitacion.getPrecioXNoche();
    }

    ///Equals & Hashcode

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Reserva reserva = (Reserva) o;
        return idReserva == reserva.idReserva;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idReserva);
    }

    /// Interfaz

    @Override
    public int getIdentificador() {
        return this.idReserva;
    }

    /// ToString

    @Override
    public String toString() {
        return "Información de reserva Nº" + idReserva +
                ":" + "\n" +
                "-Metodo de pago: " + pago + "\n" +
                "-Fecha de inicio: " + fechaInicio + "\n" +
                "-Fecha de fin: " + fechaFin + "\n" +
                "-Pasajero: " + pasajero + "\n" +
                "-Habitacion: " + habitacion;
    }

    /// SERIALIZACION
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("idReserva", idReserva);
        json.put("pago", pago.toString());
        json.put("fechaInicio", fechaInicio.toString());
        json.put("fechaFin", fechaFin.toString());
        json.put("pasajero", pasajero.toJson());
        json.put("habitacion", habitacion.toJson());
        return json;
    }

    /// DESERIALIZACION
    public Reserva(JSONObject obj) {
        this.idReserva = obj.getInt("idReserva");
        this.pago = MetodoPago.valueOf(obj.getString("pago"));
        this.fechaInicio = LocalDate.parse(obj.getString("fechaInicio"));
        this.fechaFin = LocalDate.parse(obj.getString("fechaFin"));
        this.pasajero = new Pasajero(obj.getJSONObject("pasajero"));
        this.habitacion = new Habitacion(obj.getJSONObject("habitacion"));
    }


}
