package Clases;

import Enums.EstadoHabitacion;
import Excepciones.*;

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

    /// USUARIOS
    public boolean crearAdministrador(Administrador admin, Usuario u) throws AccesoNoAutorizadoException, UsuarioNoValidoException{
        /// Compruebo que el usuario que hace la accion es un admin
        if(!(u instanceof Administrador)){
            throw new AccesoNoAutorizadoException("Solo un administrador puede crear usuarios");
        }
        /// Compruebo que ambos usuarios no sean nulos
        if(admin == null || u == null){
            throw new UsuarioNoValidoException("El usuario que ingreso es nulo");
        }

        return hotel.agregarUsuario(admin);
    }

    public boolean crearRecepcionista(Recepcionista rec,Usuario u) throws AccesoNoAutorizadoException, UsuarioNoValidoException {
        /// Compruebo que el usuario que hace la accion es un admin
        if(!(u instanceof Administrador)){
            throw new AccesoNoAutorizadoException("Solo un administrador puede crear usuarios");
        }
        /// Compruebo que ambos usuarios no sean nulos
        if(rec == null || u == null){
            throw new UsuarioNoValidoException("El usuario que ingreso es nulo");
        }
        return hotel.agregarUsuario(rec);
    }

    public boolean eliminarRecepcionista(Usuario u1, Usuario RecepcionistaAeliminar) throws AccesoNoAutorizadoException, UsuarioNoValidoException {
        /// Compruebo que el usuario que hace la accion es un admin
        if(!(u1 instanceof Administrador)){
            throw new AccesoNoAutorizadoException("Solo un administrador puede eliminar usuarios");
        }
        /// Compruebo que el usuario a eliminar sea un Recepcionista
        if(!(RecepcionistaAeliminar instanceof Recepcionista)){
            throw new UsuarioNoValidoException("El usuario que desea eliminar no es un receptorista");
        }
        /// Compruebo que ambos usuarios no sean nulos
        if(RecepcionistaAeliminar == null || u1 == null){
            throw new UsuarioNoValidoException("El usuario que ingreso es nulo");
        }


        return hotel.getUsuarios().eliminarRegistro(RecepcionistaAeliminar);
    }

    public boolean eliminarAdministrador(Usuario u1, Usuario AdminAEliminar) throws AccesoNoAutorizadoException, UsuarioNoValidoException {
        /// Compruebo que el usuario que hace la accion es un admin
        if(!(u1 instanceof Administrador)){
            throw new AccesoNoAutorizadoException("Solo un administrador puede eliminar usuarios");
        }
        /// Compruebo que el usuario a eliminar sea un Admin
        if(!(AdminAEliminar instanceof Administrador)){
            throw new UsuarioNoValidoException("El usuario que desea eliminar no es un administrador");
        }
        /// Compruebo que ambos usuarios no sean nulos
        if(AdminAEliminar == null || u1 == null){
            throw new UsuarioNoValidoException("El usuario que ingreso es nulo");
        }

        return hotel.getUsuarios().eliminarRegistro(AdminAEliminar);
    }

    /// PASAJEROS
    public boolean agregarPasajero(Pasajero p)throws PasajeroNoValidoException {
        /// Comprobar de que el pasajero no sea nulo
        if(p == null){
            throw new PasajeroNoValidoException("El pasajero que ingreso es nulo");
        }

        return hotel.agregarPasajero(p);
    }
    /// CONSULTAR MAÑANA SOBRE GETTERS EN LA CLASE HOTEL SI ROMPE EL ENCAPSULAMIENTO
    public Pasajero buscarPasajero(String dni) {
        return hotel.getPasajeros().buscarPorNumero(Integer.parseInt(dni));
    }

}
