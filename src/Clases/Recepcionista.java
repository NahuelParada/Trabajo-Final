package Clases;

import Enums.MetodoPago;
import Enums.Turno;
import Enums.estadoHabitacion;
import Enums.tipoHabitacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Recepcionista extends Usuario {
    public static int contador = 0;

    private int id;
    private Turno turno;

    public Recepcionista(Turno turno,String nombre, String contraseña) {
        super(nombre,contraseña);
        this.id = contador++;
        this.turno = turno;
    }

    public boolean realizarCheckIn(MetodoPago pago, Date fechaInicio, Date fechaFinal, String dniP, String nombreP, String apellidoP, String gmailP, String telefonoP, String origenP, String domicilioP, int numeroH, tipoHabitacion tipoH, estadoHabitacion estadoH)
    {
        Pasajero pasajeroAux = new Pasajero(dniP, nombreP, apellidoP, gmailP, telefonoP, origenP, domicilioP);
        Habitacion habitacionAux = new Habitacion(numeroH, tipoH, estadoH);
        return new Reserva (pago, fechaInicio, fechaFinal, pasajeroAux, habitacionAux);
    }

    public void realizarCheckOut(MetodoPago pago, Date fechaInicio, Date fechaFinal, String dniP, String nombreP, String apellidoP, String gmailP, String telefonoP, String origenP, String domicilioP, int numeroH, tipoHabitacion tipoH, estadoHabitacion estadoH)
    {
        Pasajero pasajeroAux = new Pasajero(dniP, nombreP, apellidoP, gmailP, telefonoP, origenP, domicilioP);
        Habitacion habitacionAux = new Habitacion(numeroH, tipoH, estadoH);
        return new Reserva (pago, fechaInicio, fechaFinal, pasajeroAux, habitacionAux);
    }





}
