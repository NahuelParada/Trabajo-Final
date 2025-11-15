package Clases;

import java.time.LocalDate;

public class CheckOut {

    /// ATRIBUTOS

    private int idCheckOut;
    private static int contador = 1;
    private LocalDate fecha;
    private Reserva reserva;

    /// CONSTRUCTOR

    public CheckOut(Reserva reserva) {
        this.idCheckOut = contador++;
        this.fecha = LocalDate.now();
        this.reserva = reserva;
    }




}
