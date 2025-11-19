package Clases;

import Enums.EstadoHabitacion;
import Enums.MetodoPago;
import Excepciones.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Locale;

public class SistemaHotel {
    /// Atributo

    private Hotel hotel;
    /// Constructor

    public SistemaHotel( String nombre, String direccion) {
        this.hotel = new Hotel(nombre, direccion);
    }

    /// HABITACIONES

    public String listarHabitacionesDisponibles(){
        return hotel.listarHabitacionesDisponibles();
    }

    public String  listarHabitacionesNoDisponibles(){
        return hotel.listarHabitacionesNoDisponibles();
    }

    /// RESERVAS
    /// Consultar si con las excepciones no hace falta un retorno falso

    public Reserva crearReserva(Pasajero p, MetodoPago pago, int numHab, LocalDate fechaInicio, LocalDate fechaFin, Usuario u)throws HabitacionNoDisponibleException, NullPointerException, AccesoNoAutorizadoException, PasajeroNoValidoException {
        LocalDate fechaActual = LocalDate.now();

        Habitacion hab = hotel.buscarHabitacionPorNumero(numHab);

        /// Comprobacion fechas
        if (fechaInicio.isBefore(fechaActual)) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser anterior a hoy");
        }

        if (!fechaFin.isAfter(fechaInicio)) {
            throw new IllegalArgumentException("La fecha de fin debe ser posterior a la fecha de inicio");
        }
        /// Compruebo que el pasajero sea valido
        if (p == null) {
            throw new PasajeroNoValidoException("Pasajero nulo");
        }
        /// Compruebo si la reserva existe
        if (hab == null) {
            throw new NullPointerException("Habitacion no encontrada");
        }
        /// Compruebo estado de la habitacion
        if (hab.getEstado() != EstadoHabitacion.LIBRE) {
            throw new HabitacionNoDisponibleException("Habitacion no disponible( estado :" + hab.getEstado() + ")");
        }

        Reserva r = new Reserva(pago, fechaInicio, fechaFin, p, hab);

        hotel.agregarReserva(r);
        hab.setEstado(EstadoHabitacion.RESERVADA);

        return r;
    }

    public boolean cancelarReserva(int id, Usuario u)throws ReservaNoEncontradaException, AccesoNoAutorizadoException {
        Reserva r = hotel.buscarReserva(id);
        /// Compruebo si la reserva existe
        if(r == null){
            throw new ReservaNoEncontradaException("La reserva con ID " + id +" no existe.");
        }
        /// Compruebo si el usuario sea un recepcionista
        if(!(u instanceof Recepcionista)){
            throw new  AccesoNoAutorizadoException("Solo un recepcionista puede cancelar reservas");
        }

        r.setEstadoHabitacionReserva(EstadoHabitacion.RESERVADA);
        return hotel.eliminarReserva(r);
    }

    /// CheckIn & CheckOut

    public boolean realizarCheckIN (int idReserva ,Usuario u) throws AccesoNoAutorizadoException{
        /// Compruebo si el usuario sea un recepcionista
        if(!(u instanceof Recepcionista)){
            throw new  AccesoNoAutorizadoException("Solo un recepcionista puede realizar CheckIns");
        }
        hotel.hacerCheckIn(idReserva);
        return true;
    }

    public boolean realizarCheckOut(int idReserva, Usuario u) throws AccesoNoAutorizadoException, HabitacionNoDisponibleException{

        /// Compruebo si el usuario sea un recepcionista
        if(!(u instanceof Recepcionista)){
            throw new  AccesoNoAutorizadoException("Solo un recepcionista puede realizar CheckOuts");
        }
        /// Compruebo si la habitacion esta ocupada antes de hacer CheckOut
        if(!hotel.hacerCheckOut(idReserva)){
            throw new HabitacionNoDisponibleException("La habitacion no esta ocupada");
        }

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


        return hotel.eliminarUsuario(RecepcionistaAeliminar);
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

        return hotel.eliminarUsuario(AdminAEliminar);
    }

    /// PASAJEROS
    public boolean agregarPasajero(Pasajero p, Usuario u)throws PasajeroNoValidoException, AccesoNoAutorizadoException {
        /// Comprobar de que el pasajero no sea nulo
        if(p == null){
            throw new PasajeroNoValidoException("El pasajero que ingreso es nulo");
        }
        /// Compruebo si el usuario sea un recepcionista
        if(!(u instanceof Recepcionista)){
            throw new  AccesoNoAutorizadoException("Solo un recepcionista puede agregar pasajeros");
        }

        return hotel.agregarPasajero(p);
    }

    public Pasajero buscarPasajero(String dni) {
        return hotel.buscarPasajero(dni);
    }

    public double calcularRecaudacionTotal() {
        return hotel.calcularRecaudacionTotalEnReservas();
    }

}
