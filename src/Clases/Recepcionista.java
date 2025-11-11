package Clases;

import Enums.estadoHabitacion;
import Enums.tipoHabitacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Recepcionista {

    private List<String> capacitacion;
    private String puntuacion;

    public Recepcionista(String puntuacion) {
        this.capacitacion = new ArrayList<>();
        this.puntuacion = puntuacion;
    }

    public List<String> getCapacitacion() {
        return capacitacion;
    }

    public void setCapacitacion(List<String> capacitacion) {
        this.capacitacion = capacitacion;
    }

    public String getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(String puntuacion) {
        this.puntuacion = puntuacion;
    }

    public boolean realizarReservaCheckIn(MetodoPago pago, Date fechaInicio, Date fechaFinal, String dniP, String nombreP, String apellidoP, String gmailP, String telefonoP, String origenP, String domicilioP, int numeroH, tipoHabitacion tipoH, estadoHabitacion estadoH)
    {
        Pasajero pasajeroAux = new Pasajero(dniP, nombreP, apellidoP, gmailP, telefonoP, origenP, domicilioP);
        Habitacion habitacionAux = new Habitacion(numeroH, tipoH, estadoH);
        return new Reserva (pago, fechaInicio, fechaFinal, pasajeroAux, habitacionAux);
    }

    public void realizarReservaCheckOut(MetodoPago pago, Date fechaInicio, Date fechaFinal, String dniP, String nombreP, String apellidoP, String gmailP, String telefonoP, String origenP, String domicilioP, int numeroH, tipoHabitacion tipoH, estadoHabitacion estadoH)
    {
        Pasajero pasajeroAux = new Pasajero(dniP, nombreP, apellidoP, gmailP, telefonoP, origenP, domicilioP);
        Habitacion habitacionAux = new Habitacion(numeroH, tipoH, estadoH);
        return new Reserva (pago, fechaInicio, fechaFinal, pasajeroAux, habitacionAux);
    }

    @Override
    public String toString() {
        return "Recepcionista{" +
                "puntuacion='" + puntuacion + '\'' +
                ", capacitacion=" + getCapacitacion() +
                '}';
    }
}
