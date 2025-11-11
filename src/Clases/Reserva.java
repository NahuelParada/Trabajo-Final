package Clases;
import Enums.MetodoPago;
import Enums.estadoHabitacion;
import Enums.tipoHabitacion;

import java.time.LocalDate;
import java.util.Objects;

public class Reserva {

    /// ATRIBUTOS

    private int idReserva;
    private static int contador = 1;
    private MetodoPago pago;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Pasajero pasajero;
    private Habitacion habitacion;

    /// RESERVA

    public Reserva(MetodoPago pago, int anio, int mes, int dia, Pasajero pasajeroReserva, Habitacion habitacionReserva, String dni, String nombre, String apellido, String gmail, String telefono, String origen, String domicilio, int numHabitacion, tipoHabitacion tipo, estadoHabitacion estado) {
        idReserva = contador++;
        this.pago = pago;
        this.fechaInicio = LocalDate.now();
        this.fechaFin = LocalDate.of(anio, mes, dia);
        this.pasajero = new Pasajero(dni, nombre, apellido, gmail, telefono, origen, domicilio);
        this.habitacion = new Habitacion(numHabitacion, tipo, estado);
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
}
