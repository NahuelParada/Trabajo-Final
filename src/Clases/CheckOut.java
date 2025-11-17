package Clases;

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
}
