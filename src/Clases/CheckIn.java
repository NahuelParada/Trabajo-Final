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

    public JSONObject serializar() {
        JSONObject o = new JSONObject();

        o.put("id_checkin", this.idCheckIn);
        o.put("fecha", this.fecha.toString());
        o.put("id_reserva", this.reserva.getIdReserva());

        return o;
    }

    /// DESERIALIZACION

    public CheckIn(JSONObject obj) {
        this.idCheckIn = obj.getInt("idCheckIn");
        this.fecha = LocalDate.parse(obj.getString("fecha"));
        this.reserva = new Reserva(obj.getJSONObject("reserva"));

        if (idCheckIn >= contador) contador = idCheckIn + 1;
    }


}
