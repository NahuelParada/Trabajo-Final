package Clases;

import java.time.LocalDate;

public class CheckOut {

    /// ATRIBUTOS

    private int idCheckOut;
    private static int contador = 1;
    private LocalDate fecha;
    private Reserva reserva;

    /// CONSTRUCTOR

    public CheckOut() {
        this.idCheckOut = contador++;
        this.fecha = LocalDate.now();
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    /// METODOS

    public void VincularReserva(int id)
    {
        Reserva r1 = null;
        for (Reserva R : lista){
            if (R.getIdReserva() == id){
                r1 = R;
                setReserva(r1);
            }
        }
    }

}
