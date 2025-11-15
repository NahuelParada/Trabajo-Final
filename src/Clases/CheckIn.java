package Clases;

import java.time.LocalDate;

public class CheckIn {

    /// ATRIBUTOS

    private int idCheckIn;
    private static int contador = 1;
    private LocalDate fecha;
    private Reserva reserva;

    /// CONSTRUCTOR

    public CheckIn(Reserva reserva) {
        this.idCheckIn = contador++;
        this.fecha = LocalDate.now();
        this.reserva = reserva;
    }




}
