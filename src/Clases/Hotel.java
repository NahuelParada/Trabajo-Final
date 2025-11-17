package Clases;

import Enums.MetodoPago;

import javax.swing.text.html.HTMLDocument;
import java.time.LocalDate;
import java.util.Iterator;

public class Hotel {

    /// Atributos
    private String nombre;
    private String direccion;
    private Registro<Reserva> reservas;
    private Registro<Usuario> usuarios;
    private Registro<Pasajero> pasajeros;
    private Registro<Habitacion> habitaciones;

    public Hotel(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.reservas = new Registro<>();
        this.usuarios = new Registro<>();
        this.pasajeros = new Registro<>();
        this.habitaciones = new Registro<>();
    }

    /// Getter y Setter


    public Registro<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public Registro<Reserva> getReservas() {
        return reservas;
    }

    public Registro<Usuario> getUsuarios() {
        return usuarios;
    }

    public Registro<Pasajero> getPasajeros() {
        return pasajeros;
    }

    /// METODOS
    public boolean agregarReserva(Reserva reserva){
        if (reservas.contiene(reserva)) {
            return false;
        }
        return reservas.agregarRegistro(reserva);
    }

    public boolean agregarUsuario(Usuario usuario){
        if (usuarios.contiene(usuario)) {
            return false;
        }
        return usuarios.agregarRegistro(usuario);
    }

    public boolean agregarPasajero(Pasajero pasajero){
        if (pasajeros.contiene(pasajero)) {
            return false;
        }
        return pasajeros.agregarRegistro(pasajero);
    }

    public boolean agregarHabitacion(Habitacion habitacion){
        if(habitaciones.contiene(habitacion)) {
            return false;
        }
        return habitaciones.agregarRegistro(habitacion);
    }

    public boolean verificarDisponible(Habitacion habitacion){
        return habitacion.disponibilidad();
    }

    public boolean cancelarReserva(Reserva reserva){
        return reservas.eliminarRegistro(reserva);
    }

}
