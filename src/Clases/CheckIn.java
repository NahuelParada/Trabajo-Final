package Clases;

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
}
