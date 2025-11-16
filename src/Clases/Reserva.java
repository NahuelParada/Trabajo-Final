package Clases;
import Enums.MetodoPago;
import Enums.EstadoHabitacion;
import Enums.TipoHabitacion;
import Interfaces.Identificador;

import java.time.LocalDate;
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

    public Reserva(MetodoPago pago, int anio, int mes, int dia, Pasajero pasajeroReserva, Habitacion habitacionReserva) {
        idReserva = contador++;
        this.pago = pago;
        this.fechaInicio = LocalDate.now();
        this.fechaFin = LocalDate.of(anio, mes, dia);
        this.pasajero = pasajeroReserva;
        this.habitacion = habitacionReserva;
    }

    public int getIdReserva() {
        return idReserva;
    }

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

    @Override
    public int getIdentificador() {
        return this.idReserva;
    }
}
