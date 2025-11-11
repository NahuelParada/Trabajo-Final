package Clases;

import java.time.LocalDate;

public class CheckIn {

    /// ATRIBUTOS

    private int idCheckIn;
    private static int contador = 1;
    private LocalDate fecha;
    private Reserva reserva;

    /// CONSTRUCTOR

    public CheckIn() {
        this.idCheckIn = contador++;
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
