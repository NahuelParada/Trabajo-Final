package Clases;

import org.json.JSONObject;

import java.time.LocalDate;

public class CheckIn {

    /// ATRIBUTOS

    private int idCheckIn;
    private static int contador = 1;
    private LocalDate fecha;
    private Reserva reserva;

    /// CONSTRUCTOR

    public CheckIn(Reserva reserva, LocalDate fecha) {
        this.idCheckIn = contador++;
        this.fecha = fecha;
        this.reserva = reserva;
    }

    /// ToString

    @Override
    public String toString() {
        return "Información de Check-In Nº" + idCheckIn + ":\n" +
                "-Fecha: " + fecha + "\n" +
                "-Reserva: Nº" + reserva.getIdReserva();
    }

    /// SERIALIZACION
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("idCheckIn", idCheckIn);
        json.put("fecha", fecha.toString());
        json.put("reserva", reserva.toJson());
        return json;
    }

    /// DESERIALIZACION
    public CheckIn(JSONObject obj) {
        this.idCheckIn = obj.getInt("idCheckIn");
        this.fecha = LocalDate.parse(obj.getString("fecha"));
        this.reserva = new Reserva(obj.getJSONObject("reserva"));
    }

}
