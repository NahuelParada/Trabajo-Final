package Clases;

import org.json.JSONObject;

import java.time.LocalDate;

public class CheckOut {

    /// ATRIBUTOS

    private int idCheckOut;
    private static int contador = 1;
    private LocalDate fecha;
    private Reserva reserva;

    /// CONSTRUCTOR

    public CheckOut(Reserva reserva, LocalDate fecha) {
        this.idCheckOut = contador++;
        this.fecha = fecha;
        this.reserva = reserva;
    }

    /// ToString

    @Override
    public String toString() {
        return "Información de Check-Out Nº" + idCheckOut + ":\n" +
                "-Fecha: " + fecha + "\n" +
                "-Reserva: Nº" + reserva.getIdReserva();
    }

    /// SERIALIZACION

    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        obj.put("idCheckOut", idCheckOut);
        obj.put("fecha", fecha.toString());
        obj.put("reserva", reserva.toJson());
        return obj;
    }

    /// DESERIALIZACION

    public CheckOut(JSONObject obj) {
        this.idCheckOut = obj.getInt("idCheckOut");
        this.fecha = LocalDate.parse(obj.getString("fecha"));
        this.reserva = new Reserva(obj.getJSONObject("reserva"));

        if (idCheckOut >= contador) contador = idCheckOut + 1;
    }


}
