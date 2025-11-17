package Clases;

import Enums.EstadoHabitacion;
import Excepciones.AccesoNoAutorizadoException;
import Excepciones.HabitacionNoDisponibleException;

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
            if(h.disponibilidad()){
                Disponibles.add(h);
            }
        }
        return Disponibles;
    }

    public HashSet<Habitacion> listarHabitacionesOcupadas(){
        HashSet<Habitacion> noDisponibles = new HashSet<>();

        for (Habitacion h: hotel.getHabitaciones().listarTodos()){
            if(!h.disponibilidad()){
                noDisponibles.add(h);
            }
        }
        return noDisponibles;
    }


    /// RESERVAS

    public Reserva crearReserva(Pasajero p, int numHab, LocalDate fechaInicio, LocalDate fechaFin, Usuario u)throws HabitacionNoDisponibleException, NullPointerException, AccesoNoAutorizadoException {
        Habitacion hab = hotel.getHabitaciones().buscarPorNumero(numHab);

        if(hab == null){
            throw new NullPointerException("Habitacion no encontrada");
        }

        if(!hab.disponibilidad()){
            throw new HabitacionNoDisponibleException("Habitacion no disponible");
        }

        if(!(u instanceof Recepcionista)){
            throw new  AccesoNoAutorizadoException("Solo un recepcionista puede crear reservas");
        }

        Reserva r = new Reserva(null, fechaInicio,fechaFin,p,hab);

        hotel.agregarReserva(r);
        hab.setEstado(EstadoHabitacion.OCUPADA);

        return r;

    }

    public boolean cancelarReserva(int id)throws NullPointerException{
        Reserva r = hotel.getReservas().buscarPorNumero(id);

        if(r == null){
            throw new NullPointerException("Reserva no encontrada");
        }
        r.getHabitacion().setEstado(EstadoHabitacion.LIBRE);
        return hotel.getReservas().eliminarRegistro(r);
    }

    /// CheckIn & CheckOut

    public boolean realizarCheckOut (int idReserva) throws NullPointerException{
        Reserva r = hotel.getReservas().buscarPorNumero(idReserva);

        if(r == null){
            throw new NullPointerException("Reserva no encontrada");
        }

        r.getHabitacion().setEstado(EstadoHabitacion.OCUPADA);
        return true;
    }

}
