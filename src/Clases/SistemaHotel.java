package Clases;

import Enums.EstadoHabitacion;
import Excepciones.AccesoNoAutorizadoException;
import Excepciones.HabitacionNoDisponibleException;
import Excepciones.ReservaNoEncontradaException;

import java.time.LocalDate;
import java.util.HashSet;

public class SistemaHotel {
    /// Atributo

    private Hotel hotel;
    /// Constructor

    public SistemaHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    /// HABITACIONES

    public HashSet<Habitacion> listarHabitacionesDisponibles(){
        HashSet<Habitacion> Disponibles = new HashSet<>();

        for (Habitacion h: hotel.getHabitaciones().listarTodos()){
            if(h.disponibleParaReserva()){
                Disponibles.add(h);
            }
        }
        return Disponibles;
    }

    public HashSet<Habitacion> listarHabitacionesOcupadas(){
        HashSet<Habitacion> noDisponibles = new HashSet<>();

        for (Habitacion h: hotel.getHabitaciones().listarTodos()){
            if(!h.disponibleParaReserva()){
                noDisponibles.add(h);
            }
        }
        return noDisponibles;
    }


    /// RESERVAS

    public Reserva crearReserva(Pasajero p, int numHab, LocalDate fechaInicio, LocalDate fechaFin, Usuario u)throws HabitacionNoDisponibleException, NullPointerException, AccesoNoAutorizadoException {
        Habitacion hab = hotel.getHabitaciones().buscarPorNumero(numHab);
        /// Compruebo si la reserva existe
        if(hab == null){
            throw new NullPointerException("Habitacion no encontrada");
        }
        /// Compruebo estado de la habitacion
        if(!hab.disponibleParaReserva()){
            throw new HabitacionNoDisponibleException("Habitacion no disponible( estado :"+ hab.getEstado() +")");
        }
        /// Compruebo si el usuario sea un recepcionista
        if(!(u instanceof Recepcionista)){
            throw new  AccesoNoAutorizadoException("Solo un recepcionista puede crear reservas");
        }

        Reserva r = new Reserva(null, fechaInicio,fechaFin,p,hab);

        hotel.agregarReserva(r);
        hab.setEstado(EstadoHabitacion.RESERVADA);

        return r;

    }

    public boolean cancelarReserva(int id, Usuario u)throws ReservaNoEncontradaException, AccesoNoAutorizadoException {
        Reserva r = hotel.getReservas().buscarPorNumero(id);
        /// Compruebo si la reserva existe
        if(r == null){
            throw new ReservaNoEncontradaException("La reserva con ID " + id +" no existe.");
        }
        /// Compruebo si el usuario sea un recepcionista
        if(!(u instanceof Recepcionista)){
            throw new  AccesoNoAutorizadoException("Solo un recepcionista puede crear reservas");
        }

        r.getHabitacion().setEstado(EstadoHabitacion.LIBRE);
        return hotel.getReservas().eliminarRegistro(r);
    }

    /// CheckIn & CheckOut

    public boolean realizarCheckIN (int idReserva ,Usuario u) throws ReservaNoEncontradaException, AccesoNoAutorizadoException, HabitacionNoDisponibleException {
        Reserva r = hotel.getReservas().buscarPorNumero(idReserva);
        /// Compruebo si la reserva existe
        if (r == null) {
            throw new ReservaNoEncontradaException("La reserva con ID " + idReserva + " no existe.");
        }
        /// Compruebo si el usuario sea un recepcionista
        if(!(u instanceof Recepcionista)){
            throw new  AccesoNoAutorizadoException("Solo un recepcionista puede crear reservas");
        }
        /// Compruebo si la habitacion esta reservada
        if(!r.getHabitacion().disponibleParaCheckIn()){
            throw new HabitacionNoDisponibleException("Habitacion no disponible");
        }

        r.getHabitacion().setEstado(EstadoHabitacion.OCUPADA);
        return true;
    }

    public boolean realizarCheckOut(int idReserva, Usuario u) throws ReservaNoEncontradaException, AccesoNoAutorizadoException, HabitacionNoDisponibleException {
        Reserva r = hotel.getReservas().buscarPorNumero(idReserva);

        /// Compruebo si la reserva existe
        if (r == null) {
            throw new ReservaNoEncontradaException("La reserva con ID " + idReserva + " no existe.");
        }

        /// Compruebo si el usuario sea un recepcionista
        if(!(u instanceof Recepcionista)){
            throw new  AccesoNoAutorizadoException("Solo un recepcionista puede crear reservas");
        }
        /// Compruebo si la habitacion esta ocupada
        if(!r.getHabitacion().ocupadaParaCheckOut()){
            throw new HabitacionNoDisponibleException("La habitacion esta liberada");
        }

        r.getHabitacion().setEstado(EstadoHabitacion.MANTENIMIENTO); ///Mas tarde se pondra libre
        return true;
    }




}
