import Clases.*;
import Enums.EstadoHabitacion;
import Enums.MetodoPago;
import Enums.TipoHabitacion;
import Enums.Turno;
import Excepciones.AccesoNoAutorizadoException;
import Excepciones.HabitacionNoDisponibleException;
import Excepciones.ReservaNoEncontradaException;
import Excepciones.UsuarioNoValidoException;

import java.time.LocalDate;
import java.util.HashSet;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        /// Clase Hotel Unica
        Hotel hotel = new Hotel("Hoteleria Cielos y Mares", "Barrio Alberdi 5404");

        /// El Sistema debe tener un Usuario para que pueda agregar Administradores y Recepcionistas
        Administrador AdministradorSupremo = new Administrador("Paulo Rucks", "dinosaurio123");
        Recepcionista RecepcionistaSupremo = new Recepcionista(Turno.TARDE, "tiro34lor", "eltoro789");

        /// Vamos a agregarlos
        hotel.agregarUsuario(AdministradorSupremo);
        hotel.agregarUsuario(RecepcionistaSupremo);

        /// Habitaciones Estandar
        Habitacion hab1 = new Habitacion(1, TipoHabitacion.ESTANDAR, EstadoHabitacion.LIBRE, 105450.75);
        Habitacion hab2 = new Habitacion(2, TipoHabitacion.ESTANDAR, EstadoHabitacion.OCUPADA, 105450.75);
        Habitacion hab3 = new Habitacion(3, TipoHabitacion.ESTANDAR, EstadoHabitacion.MANTENIMIENTO, 105450.75);
        Habitacion hab4 = new Habitacion(4, TipoHabitacion.ESTANDAR, EstadoHabitacion.OCUPADA, 105450.75);
        Habitacion hab5 = new Habitacion(5, TipoHabitacion.ESTANDAR, EstadoHabitacion.RESERVADA, 105450.75);
        /// Habitaciones EstandarPlus
        Habitacion hab6 = new Habitacion(6, TipoHabitacion.ESTANDARPLUS, EstadoHabitacion.LIBRE, 153500.25);
        Habitacion hab7 = new Habitacion(7, TipoHabitacion.ESTANDARPLUS, EstadoHabitacion.OCUPADA, 153500.25);
        Habitacion hab8 = new Habitacion(8, TipoHabitacion.ESTANDARPLUS, EstadoHabitacion.LIBRE, 153500.25);
        /// Habitaciones Suit
        Habitacion hab9 = new Habitacion(9, TipoHabitacion.SUIT, EstadoHabitacion.OCUPADA, 176900.40);
        Habitacion hab10 = new Habitacion(10, TipoHabitacion.SUIT, EstadoHabitacion.MANTENIMIENTO, 176900.40);
        Habitacion hab11 = new Habitacion(11, TipoHabitacion.SUIT, EstadoHabitacion.LIBRE, 176900.40);

        /// Se inserta las Habitaciones en la Clase Hotel
        hotel.agregarHabitacion(hab1);
        hotel.agregarHabitacion(hab2);
        hotel.agregarHabitacion(hab3);
        hotel.agregarHabitacion(hab4);
        hotel.agregarHabitacion(hab5);
        hotel.agregarHabitacion(hab6);
        hotel.agregarHabitacion(hab7);
        hotel.agregarHabitacion(hab8);
        hotel.agregarHabitacion(hab9);
        hotel.agregarHabitacion(hab10);
        hotel.agregarHabitacion(hab11);

        /// Se crea una Clase Envolvente con los parametros de el Hotel. Solo el Hotel y las Habitaciones
        SistemaHotel hotel_envolvente = new SistemaHotel(hotel.getNombre(), hotel.getDireccion());

        /// Guardamos la coleccion de Habitaciones Ocupadas y Libres
        HashSet<Habitacion> libre = hotel_envolvente.listarHabitacionesDisponibles();
        HashSet<Habitacion> ocupado = hotel_envolvente.listarHabitacionesNoDisponibles();

        /// Hay un problema a la hora de mostrar las habitaciones y es que:
        /// En la lista de Ocupados, tambien se muestran los de Mantenimiento:
        /// En la lista de Disponibles, tambien se muestran los Reservados
        System.out.println(mostrarHabitaciones(libre, "Habitaciones en Estado Libres")); ///Contenido de Habitaciones Libres
        System.out.println(mostrarHabitaciones(ocupado, "Habitaciones en Estado Ocupados")); ///Contenido de Habitaciones Ocupadas
        //System.out.println(mostrarHotel(hotel, "Informacion Completa de el Hotel")); ///Contenido del Hotel

        /// Agregamos un pasajero
        Pasajero p1 = new Pasajero("5038543", "Leonardo", "Paco", "SirPacoLarto35@hotmail.com", "3795436002", "Santa María de Punilla", "Cetrangolo 1432");
        Pasajero p2 = new Pasajero("5345675", "Marta", "Cielo", "miMartita07@outlook.com.ar", "3799184673", "Santa María de Punilla", "Artigas 0754");

        //Hacemos el Try/Catch para la creacion de la Reserva (POST, Las Excepciones funcionan muy bien)
        try
        {
            hotel_envolvente.crearReserva(p1, 1, LocalDate.of(1994, 4, 23), LocalDate.of(1994, 5, 13), RecepcionistaSupremo);
            System.out.println("Reservado");
        } catch (AccesoNoAutorizadoException e) {
            throw new RuntimeException(e);
        } catch (HabitacionNoDisponibleException e) {
            throw new RuntimeException(e);
        }

        try
        {
            hotel_envolvente.crearReserva(p2, 6, LocalDate.of(1994, 4, 23), LocalDate.of(1994, 5, 13), RecepcionistaSupremo);
            System.out.println("Reservado");
        } catch (AccesoNoAutorizadoException e) {
            throw new RuntimeException(e);
        } catch (HabitacionNoDisponibleException e) {
            throw new RuntimeException(e);
        }

        /// Hay un problema con mostrar habitaciones y es que el cliente Reserva la habitacion pero aparece como Habitacion en estado Libre
        System.out.println("Habitacion 6 y 1 Estan en estado Reservado");
        System.out.println(mostrarHabitaciones(libre, "Habitaciones en Estado Libres"));
        System.out.println(mostrarHabitaciones(ocupado, "Habitaciones en Estado Ocupados"));

        /// Se puede solucionar con meter en el metodo mostrarHotel solo los estados deseados, pero no lo agrego por si no lo quieren de esa manera

        /// Vamos a cancelar una de las dos Reservas (POST, las Excepciones funcionan bien)
        try
        {
            /// Hay que hacer una funcion para saber las id de las Reservas Creadas
            hotel_envolvente.cancelarReserva(1, RecepcionistaSupremo);
            System.out.println("Reserva Cancelada");
        } catch (AccesoNoAutorizadoException e) {
            throw new RuntimeException(e);
        } catch (ReservaNoEncontradaException e) {
            throw new RuntimeException(e);
        }

        /// Chequeamos que aparesca como libre
        System.out.println("Habitacion 1 Esta en estado Libre");
        System.out.println(mostrarHabitaciones(libre, "Habitaciones en Estado Libres"));

        /// Vamos a realizar un Check IN (POST, Funcionan bien las Excepciones pero no se como entra HabitacionNoDisponibleException)
        try {
            hotel_envolvente.realizarCheckIN(2, RecepcionistaSupremo);
            System.out.println("Reserva Realizada");
        }
        catch (ReservaNoEncontradaException e)
        {
            throw new RuntimeException(e);
        }
        catch (AccesoNoAutorizadoException e)
        {
            throw new RuntimeException(e);
        }
        catch (HabitacionNoDisponibleException e)
        {
            throw new RuntimeException(e);
        }

        /// Chequeamos que aparesca como ocupado
        System.out.println("Habitacion 6 Esta en estado Ocupado");
        System.out.println(mostrarHabitaciones(libre, "Habitaciones en Estado Libres"));

        /// Vamos a realizar un Check OUT (POST, Funcionan bien las Excepciones pero no se como entra HabitacionNoDisponibleException)
        try {
            hotel_envolvente.realizarCheckOut(2, RecepcionistaSupremo);
            System.out.println("Reserva Realizada");
        }
        catch (ReservaNoEncontradaException e)
        {
            throw new RuntimeException(e);
        }
        catch (AccesoNoAutorizadoException e)
        {
            throw new RuntimeException(e);
        }
        catch (HabitacionNoDisponibleException e)
        {
            throw new RuntimeException(e);
        }

        /// Chequeamos que este libre
        System.out.println("Habitacion 6 Esta en estado Libre");
        System.out.println(mostrarHabitaciones(libre, "Habitaciones en Estado Libres"));

        /// Crearemos nuevos Administradores y Recepcionistas
        Administrador nuevoAdmin = new Administrador("FreddyFazbear123", "Lop342df");
        Recepcionista nuevoRecep = new Recepcionista(Turno.MAÑANA, "MarielaVizuera03", "r594Op");

        /// Intentamos crear ambos para mas rapidez(POST, Funciona el Acceso no Autorizado, pero no me toma la Excepcion de UsuarioNoValidoException)
        /// Correjime colo con esa Excepcion, porque les meti null y sigue funcionando
        try
        {
            hotel_envolvente.crearAdministrador(nuevoAdmin, AdministradorSupremo);
            hotel_envolvente.crearRecepcionista(nuevoRecep, AdministradorSupremo);
        } catch (AccesoNoAutorizadoException e) {
            throw new RuntimeException(e);
        } catch (UsuarioNoValidoException e) {
            throw new RuntimeException(e);
        }

        /// Comprobar existencia
        System.out.println(hotel.getUsuarios().mostrarLista());

        /// Eliminarlos
        try
        {
            hotel_envolvente.eliminarAdministrador(AdministradorSupremo, nuevoAdmin);
            hotel_envolvente.eliminarRecepcionista(AdministradorSupremo, nuevoRecep);
        } catch (AccesoNoAutorizadoException e) {
            throw new RuntimeException(e);
        } catch (UsuarioNoValidoException e) {
            throw new RuntimeException(e);
        }

        /// Comprobar existencia
        System.out.println(hotel.getUsuarios().mostrarLista());

        /// Hay otro metodo que tiene que preguntar Nahuel ///

    }

    /// Preguntar si es necesario que el Hotel muestre todo su Contenido
    public static String mostrarHotel(Hotel x, String estado)
    {
        String contenido = "\n\n"+ estado + "\n ================================================================= \n";
        contenido += x.toString();
        return contenido += "================================================================= \n";
    }

    /// Metodo para Mostrar Habitaciones
    public static String mostrarHabitaciones(HashSet<Habitacion> x, String estado)
    {
        String contenido = "\n\n"+ estado + "\n ================================================================= \n";
        for(Habitacion h : x)
        {
            contenido = contenido + h.toString() + "\n";
        }
        return contenido += "================================================================= \n";
    }
}