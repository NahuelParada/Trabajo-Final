import Clases.*;
import Enums.EstadoHabitacion;
import Enums.MetodoPago;
import Enums.TipoHabitacion;
import Enums.Turno;
import Excepciones.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("====== Bienvenido Usuario ======");

        boolean acceso = false;

        while(acceso == false)
        {
            System.out.printf("Ingrese su Nombre: ");
            String nombreUs = sc.nextLine();

            System.out.printf("Ingrese su Contraseña: ");
            String contraseñaUs = sc.nextLine();

            acceso = IniciarSesionUsuario(nombreUs, contraseñaUs);

            if(acceso == false)
            {
                System.out.println("Nombre o Contraseña Incorrecto");
            }
        }

        SistemaHotel gestionador_hotel;

        if(acceso == true && AccederContenidoHotel() != true)
        {
            System.out.println("\n");
            System.out.println("Accediendo a Crear un Nuevo Hotel:");
            System.out.printf("Nombre del Hotel: ");
            String nombreHotel = sc.nextLine();
            System.out.printf("Direccion o Ubicacion del Hotel: ");
            String direccionHotel = sc.nextLine();

            gestionador_hotel = new SistemaHotel(nombreHotel, direccionHotel);
            /// En teoria deberia haber una funcion que agrege el Usuario Principal a la lista ya que es otro Usuario

            JSONObject objJSON = new JSONObject();
            objJSON.put("nombre", nombreHotel);
            objJSON.put("direccion", direccionHotel);
            JSONUtiles.uploadJSON(objJSON, "DataHotel");
        }
        else
        {
            String contenido_json = JSONUtiles.downloadJSON("DataHotel");
            JSONObject objJSON = new JSONObject(contenido_json);
            gestionador_hotel = new SistemaHotel(objJSON.getString("nombre"), objJSON.getString("direccion"));

        }

        int opcion = 0;

        while(acceso == true)
        {
            System.out.println("================= Menu Principal =================\n\n");
            System.out.println("1. Mostrar Habitaciondes Disponibles");
            System.out.println("2. Mostrar Habitaciondes No Disponibles");
            System.out.println("3. Crear una Reserva");
            System.out.println("4. Cancelar una Reserva");
            System.out.println("5. Realizar un Check IN");
            System.out.println("6. Realizar un Check OUT");
            System.out.println("7. Crear un Administrador");
            System.out.println("8. Crear un Recepcionista");
            System.out.println("9. Eliminar un Administrador");
            System.out.println("10. Eliminar un Recepcionista");
            System.out.println("11. Agregar un Pasajero");
            System.out.println("12. Buscar un Pasajero");
            System.out.println("13. Calcular recaudacion Total");
            System.out.println("14. Guardar contenido en un Archivo");
            System.out.println("15. Cargar contenido en un Archivo");
            System.out.println("16. Crear una Habitacion");
            System.out.println("0. Salir del Sistema");

            System.out.printf("\n\n");
            System.out.println("Ingrese una opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion)
            {
                case 1:
                    if(gestionador_hotel.listarHabitacionesDisponibles().equals(""))
                    {
                        System.out.println("No hay Habitaicones Disponibles");
                    }
                    else
                    {
                        System.out.println(gestionador_hotel.listarHabitacionesDisponibles());
                    }
                    System.out.printf("Precione enter para volver al menu");
                    sc.nextLine();
                break;
                case 2:
                    if(gestionador_hotel.listarHabitacionesNoDisponibles().equals(""))
                    {
                        System.out.println("No hay Habitaicones Disponibles");
                    }
                    else
                    {
                        System.out.println(gestionador_hotel.listarHabitacionesNoDisponibles());
                    }
                break;
                case 3:
                    crearReserva(sc, gestionador_hotel);
                    System.out.printf("Precione enter para volver al menu");
                    sc.nextLine();
                break;
                case 4:
                    cancelarReserva(sc, gestionador_hotel);
                    System.out.printf("Precione enter para volver al menu");
                    sc.nextLine();
                break;
                case 5:
                    crearCheckIn(sc, gestionador_hotel);
                    System.out.printf("Precione enter para volver al menu");
                    sc.nextLine();
                break;
                case 6:
                    crearCheckOut(sc, gestionador_hotel);
                    System.out.printf("Precione enter para volver al menu");
                    sc.nextLine();
                    break;
                case 7:
                    crearNuevoAdministrador(sc, gestionador_hotel);
                    System.out.printf("Precione enter para volver al menu");
                    sc.nextLine();
                    break;
                case 8:
                    crearNuevoRecepcionista(sc, gestionador_hotel);
                    System.out.printf("Precione enter para volver al menu");
                    sc.nextLine();
                    break;
                case 9:
                    eliminarUnAdministrador(sc, gestionador_hotel);
                    System.out.printf("Precione enter para volver al menu");
                    sc.nextLine();
                    break;
                case 10:
                    eliminarUnRecepcionista(sc, gestionador_hotel);
                    System.out.printf("Precione enter para volver al menu");
                    sc.nextLine();
                    break;
                case 11:
                    agregarPasajero(sc, gestionador_hotel);
                    System.out.printf("Precione enter para volver al menu");
                    sc.nextLine();
                    break;
                case 12:
                    buscarPasajero(sc, gestionador_hotel);
                    System.out.printf("Precione enter para volver al menu");
                    sc.nextLine();
                    break;
                case 13:
                    System.out.println(recaudacionTotal(gestionador_hotel));
                    System.out.printf("Precione enter para volver al menu");
                    sc.nextLine();
                    break;
                case 14:
                    serializar(sc, gestionador_hotel);
                    System.out.printf("Precione enter para volver al menu");
                    sc.nextLine();
                    break;
                case 15:
                    deserealizar(sc, gestionador_hotel);
                    System.out.printf("Precione enter para volver al menu");
                    sc.nextLine();
                    break;
                case 16:
                    agregarHabitacion(sc, gestionador_hotel);
                    System.out.printf("Precione enter para volver al menu");
                    sc.nextLine();
                    break;
                case 0:
                    System.out.printf("Escriba (si) Si desea Salir del Sistema de Hotel: ");
                    String seguridad = sc.nextLine();
                    if(seguridad.equalsIgnoreCase("si"))
                    {
                        System.out.println("Saliendo del Sistema");
                        acceso = false;
                    }
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        }

    }

    /// Metodo para saber que el Hotel no esta vacio
    public static boolean AccederContenidoHotel()
    {
        /// El metodo solo devuelve TRUE o FALSE, no es necesario llamar a nadie

        String contenidoJson = "";
        try
        {
            contenidoJson = JSONUtiles.downloadJSON("DataHotel");
        }
        catch (JSONException e)
        {
            System.out.println("Archivo JSON no encontrado ERROR: " + e.getMessage());
            return false;
        }

        if(contenidoJson.equals("") || contenidoJson.isEmpty() || contenidoJson.equals("{}"))
        {
            return false;
        }

        return true;
    }

    /// Metodo para Iniciar Sesion como Administrador Supremo
    public static boolean IniciarSesionUsuario(String nombreUs, String contraseñaUs)
    {
        String contenidoJson = JSONUtiles.downloadJSON("ListaUsuarios");
        JSONArray jsonArray = new JSONArray(contenidoJson);

        for(int i = 0; i < jsonArray.length(); i++)
        {
            JSONObject objJSON = jsonArray.getJSONObject(i);
            String nombreAux = objJSON.getString("nombre");
            String contraseñaAux = objJSON.getString("contraseña");
            if(nombreAux.equalsIgnoreCase(nombreUs) && contraseñaAux.equals(contraseñaUs))
            {
                return true;
            }
        }

        return false;
    }

    public static Usuario obtenerUsuario(String nombreUs, String contraseñaUs)
    {
        String contenidoJson = JSONUtiles.downloadJSON("ListaUsuarios");
        JSONArray jsonArray = new JSONArray(contenidoJson);

        for(int i = 0; i < jsonArray.length(); i++)
        {
            JSONObject objJSON = jsonArray.getJSONObject(i);
            String nombreAux = objJSON.getString("nombre");
            String contraseñaAux = objJSON.getString("contraseña");
            String tipoAux = objJSON.getString("tipo");
            if(nombreAux.equalsIgnoreCase(nombreUs) && contraseñaAux.equals(contraseñaUs))
            {
                if(tipoAux.equals("recepcionista"))
                {
                    String t = objJSON.getString("turno");
                    Turno turno = Turno.valueOf(t);
                    return new Recepcionista(turno, nombreUs, contraseñaUs);
                }
                else if(tipoAux.equals("administrador"))
                {
                    return new Administrador(nombreUs, contraseñaUs);
                }
            }
        }

        return null;
    }

    public static Usuario buscarUsuario(String nombreUs, String contraseñaUs)
    {
        String contenidoJson = JSONUtiles.downloadJSON("ListaUsuarios");
        JSONArray jsonArray = new JSONArray(contenidoJson);

        for(int i = 0; i < jsonArray.length(); i++)
        {
            JSONObject objJSON = jsonArray.getJSONObject(i);
            String nombreAux = objJSON.getString("nombre");
            String contraseñaAux = objJSON.getString("contraseña");
            String tipo = objJSON.getString("tipo");
            if(nombreAux.equalsIgnoreCase(nombreUs) && contraseñaAux.equals(contraseñaUs))
            {
                if(tipo.equalsIgnoreCase("administrador"))
                {
                    return new Administrador(nombreUs, contraseñaUs);
                }
                else if(tipo.equalsIgnoreCase("recepcionista"))
                {
                    String turnoString = objJSON.getString("turno");
                    Turno turnoAux = Turno.valueOf(turnoString);
                    return new Recepcionista(turnoAux, nombreUs, contraseñaUs);
                }
            }

        }

        return null;
    }

    public static boolean identificarAdministradorPrincipal(String nombre, String contraseña)
    {
        if(buscarUsuario(nombre, contraseña).getIdentificador() == 1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static Turno buscarTurno(Scanner sc)
    {
        Turno turno = null;
        boolean comprobacion = false;
        while (comprobacion == false)
        {
            System.out.println("Tipo de Turno:");
            System.out.println("1. Mañana");
            System.out.println("2. Tarde");
            System.out.println("3. Noche");
            System.out.println("Ingrese una opcion: ");
            int opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion)
            {
                case 1:
                    turno = Turno.MAÑANA;
                    break;
                case 2:
                    turno = Turno.TARDE;
                    break;
                case 3:
                    turno = Turno.NOCHE;
                    break;
                default:
                    System.out.println("Ingreso una opcion Incorrecta. Vuelva a Intentarlo");
            }

            if(opcion == 1 || opcion == 2 || opcion == 3)
            {
                comprobacion = true;
            }
        }
        return turno;
    }

    public static void crearReserva(Scanner sc, SistemaHotel hotel) {
        System.out.println("======= Creando una Reserva =======");

        System.out.printf("Ingrese Nombre Recepcionsita: ");
        String nombreRep = sc.nextLine();
        System.out.printf("Ingrese Contraseña Recepcionsita: ");
        String contraseñaRep = sc.nextLine();

        System.out.printf("DNI: ");
        String dni = sc.nextLine();
        System.out.printf("Nombre: ");
        String nombre = sc.nextLine();
        System.out.printf("Apellido: ");
        String apellido = sc.nextLine();
        System.out.printf("Correo: ");
        String gmail = sc.nextLine();
        System.out.printf("Telefono: ");
        String telefono = sc.nextLine();
        System.out.printf("Origen: ");
        String origen = sc.nextLine();
        System.out.printf("Domicilio: ");
        String domicilio = sc.nextLine();

        MetodoPago metodoPago = null;
        boolean comprobacion = false;
        while (comprobacion == false)
        {
            System.out.println("Elige Metodo de Pago:");
            System.out.println("1. Tarjeta de Credito");
            System.out.println("2. Tarjeta de Debito");
            System.out.println("3. Efectivo");
            System.out.println("Escriba aqui: ");
            int opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion)
            {
                case 1:
                    metodoPago = MetodoPago.CREDITO;
                    break;
                case 2:
                    metodoPago = MetodoPago.DEBITO;
                    break;
                case 3:
                    metodoPago = MetodoPago.EFECTIVO;
                    break;
                    default:
                        System.out.println("Ingreso una opcion Incorrecta. Vuelva a Intentarlo");
            }

            if(opcion == 1 || opcion == 2 || opcion == 3)
            {
                comprobacion = true;
            }
        }


        System.out.printf("Numero de Habitacion: ");
        int numero_habitacion = sc.nextInt();

        System.out.println("Ingrese la fecha de Inicio");
        System.out.printf("Año: ");
        int añoI = sc.nextInt();
        sc.nextLine();
        System.out.printf("Mes: ");
        int mesI = sc.nextInt();
        sc.nextLine();
        System.out.printf("Dia: ");
        int diaI = sc.nextInt();
        sc.nextLine();
        LocalDate fechaInicio = LocalDate.of(añoI, mesI, diaI);

        System.out.println("Ingrese la fecha Final");
        System.out.printf("Año: ");
        int añoF = sc.nextInt();
        sc.nextLine();
        System.out.printf("Mes: ");
        int mesF = sc.nextInt();
        sc.nextLine();
        System.out.printf("Dia: ");
        int diaF = sc.nextInt();
        sc.nextLine();
        LocalDate fechaFinal = LocalDate.of(añoF, mesF, diaF);

        /// Hacemos Try/Catch para ver si todo salio bien
        try
        {
            hotel.crearReserva(new Pasajero(dni, nombre, apellido, gmail, telefono, origen, domicilio), metodoPago, numero_habitacion, fechaInicio, fechaFinal, buscarUsuario(nombreRep, contraseñaRep));
            System.out.println("Reserva creada con exito");
        }
        catch (HabitacionNoDisponibleException e)
        {
            System.out.println(e.getMessage());
        }
        catch (AccesoNoAutorizadoException e)
        {
            System.out.println(e.getMessage());
        }
        catch (PasajeroNoValidoException e)
        {
            System.out.println(e.getMessage());
        }
        catch (NullPointerException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void cancelarReserva(Scanner sc, SistemaHotel gestionador_hotel)
    {
        System.out.printf("Ingrese Nombre Recepcionsita: ");
        String nombreRep = sc.nextLine();
        System.out.printf("Ingrese Contraseña Recepcionsita: ");
        String contraseñaRep = sc.nextLine();

        int id = sc.nextInt();
        sc.nextLine();

        try
        {
            gestionador_hotel.cancelarReserva(id, buscarUsuario(nombreRep, contraseñaRep));
            System.out.println("Reserva cancelada con exito");
        }
        catch (ReservaNoEncontradaException e)
            {
            System.out.println(e.getMessage());
            }
        catch (AccesoNoAutorizadoException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void crearCheckIn(Scanner sc, SistemaHotel gestionador_hotel)
    {
        System.out.printf("Ingrese Nombre Recepcionsita: ");
        String nombreRep = sc.nextLine();
        System.out.printf("Ingrese Contraseña Recepcionsita: ");
        String contraseñaRep = sc.nextLine();

        System.out.println("Ingrese la ID de la Reserva");
        int id = sc.nextInt();
        sc.nextLine();

        try {
            gestionador_hotel.realizarCheckIN(id, buscarUsuario(nombreRep, contraseñaRep));
        } catch (AccesoNoAutorizadoException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void crearCheckOut(Scanner sc, SistemaHotel gestionador_hotel)
    {
        System.out.printf("Ingrese Nombre Recepcionsita: ");
        String nombreRep = sc.nextLine();
        System.out.printf("Ingrese Contraseña Recepcionsita: ");
        String contraseñaRep = sc.nextLine();

        System.out.println("Ingrese la ID de la Reserva");
        int id = sc.nextInt();
        sc.nextLine();

        try {
            gestionador_hotel.realizarCheckOut(id, buscarUsuario(nombreRep, contraseñaRep));
        } catch (AccesoNoAutorizadoException e) {
            System.out.println(e.getMessage());
        } catch (HabitacionNoDisponibleException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void guardarUsuarioEnJSON(Usuario u) {
        String contenido = JSONUtiles.downloadJSON("ListaUsuarios");

        JSONArray arr;

        if (contenido == null || contenido.isEmpty()) {
            arr = new JSONArray();
        } else {
            arr = new JSONArray(contenido);
        }

        JSONObject obj = new JSONObject();
        obj.put("nombre", u.getNombre());
        obj.put("contraseña", u.getContraseña());

        if (u instanceof Administrador) {
            obj.put("tipo", "administrador");
        } else if (u instanceof Recepcionista) {
            obj.put("tipo", "recepcionista");
            obj.put("turno", ((Recepcionista) u).getTurno().toString());
        }

        arr.put(obj);

        JSONUtiles.uploadJSON(arr, "ListaUsuarios");
    }

    public static void crearNuevoAdministrador(Scanner sc, SistemaHotel gestionador)
    {
        System.out.println("Inicie Sesion con el Usuario Administrador Autorizado");
        System.out.printf("Ingrese Nombre Administrador: ");
        String nombreRep = sc.nextLine();
        System.out.printf("Ingrese Contraseña Administrador: ");
        String contraseñaRep = sc.nextLine();

        System.out.println("Cree el nuevo Administrador");
        System.out.printf("Ingrese el Nuevo Nombre del Administrador: ");
        String nombreNuevo = sc.nextLine();
        System.out.printf("Ingrese la Nuevo Contraseña del Administrador: ");
        String contraseñaNuevo = sc.nextLine();

        try
        {
            Administrador adm = new Administrador(nombreNuevo, contraseñaNuevo);
            gestionador.crearAdministrador(adm, buscarUsuario(nombreRep, contraseñaRep));
            guardarUsuarioEnJSON(new Administrador(nombreNuevo, contraseñaNuevo));
            System.out.println("Administrador creado exitosamente");
        } catch (AccesoNoAutorizadoException e) {
            System.out.println(e.getMessage());
        } catch (UsuarioNoValidoException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void crearNuevoRecepcionista(Scanner sc, SistemaHotel gestionador)
    {
        System.out.println("Inicie Sesion con el Usuario Administrador Autorizado");
        System.out.printf("Ingrese Nombre Administrador: ");
        String nombreRep = sc.nextLine();
        System.out.printf("Ingrese Contraseña Administrador: ");
        String contraseñaRep = sc.nextLine();

        System.out.println("Cree el nuevo Recepcionista");
        System.out.printf("Ingrese el Nuevo Nombre del Recepcionista: ");
        String nombreNuevo = sc.nextLine();
        System.out.printf("Ingrese la Nuevo Contraseña del Recepcionista: ");
        String contraseñaNuevo = sc.nextLine();

        Turno turno = buscarTurno(sc);

        try
        {
            Recepcionista rep = new Recepcionista(turno, nombreNuevo, contraseñaNuevo);
            gestionador.crearRecepcionista(rep, buscarUsuario(nombreRep, contraseñaRep));
            guardarUsuarioEnJSON(new Recepcionista(turno, nombreNuevo, contraseñaNuevo));
            System.out.println("Recepcionista creado exitosamente");
        } catch (AccesoNoAutorizadoException e) {
            System.out.println(e.getMessage());
        } catch (UsuarioNoValidoException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void eliminarUsuarioDeJSON(String nombreUsuario, String contraseñaUsuario) {
        String contenido = JSONUtiles.downloadJSON("ListaUsuarios");

        if (contenido == null || contenido.isEmpty()) {
            return; // No hay usuarios
        }

        JSONArray arr = new JSONArray(contenido);
        JSONArray nuevoArr = new JSONArray();

        for (int i = 0; i < arr.length(); i++) {
            JSONObject obj = arr.getJSONObject(i);

            String nombre = obj.getString("nombre");
            String contraseña = obj.getString("contraseña");

            // Solo NO copio el usuario que quiero eliminar
            if (!(nombre.equalsIgnoreCase(nombreUsuario) && contraseña.equals(contraseñaUsuario))) {
                nuevoArr.put(obj);
            }
        }

        JSONUtiles.uploadJSON(nuevoArr, "ListaUsuarios");
    }

    public static void eliminarUnAdministrador(Scanner sc, SistemaHotel gestionador) {
        System.out.println("Inicie Sesion con el Usuario Administrador Autorizado");
        System.out.printf("Ingrese Nombre Administrador: ");
        String nombreAdm = sc.nextLine();
        System.out.printf("Ingrese Contraseña Administrador: ");
        String contraseñaAdm = sc.nextLine();

        System.out.println("Ingrese los datos de la cuenta que sera Eliminada");
        System.out.printf("Ingrese Nombre Administrador: ");
        String nombreElim = sc.nextLine();
        System.out.printf("Ingrese Contraseña Administrador: ");
        String contraseñaElim = sc.nextLine();

        /// Hay que proteger al unico Administrador Principal para no ser Eliminado.
        if (identificarAdministradorPrincipal(nombreElim, contraseñaElim) == true) {
            System.out.println("Esta prohibido eliminar al Administrador Principal. Regresando al Menu.");
        }
        else
        {
            try
            {
                gestionador.eliminarAdministrador(buscarUsuario(nombreAdm, contraseñaAdm), buscarUsuario(nombreElim, contraseñaElim));
                eliminarUsuarioDeJSON(nombreElim, contraseñaElim);
            } catch (AccesoNoAutorizadoException e) {
                System.out.println(e.getMessage());
            }
            catch (UsuarioNoValidoException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public static void eliminarUnRecepcionista(Scanner sc, SistemaHotel gestionador)
    {
        System.out.println("Inicie Sesion con el Usuario Administrador Autorizado");
        System.out.printf("Ingrese Nombre Administrador: ");
        String nombreRep = sc.nextLine();
        System.out.printf("Ingrese Contraseña Administrador: ");
        String contraseñaRep = sc.nextLine();

        System.out.println("Ingrese los datos de la cuenta que sera Eliminada");
        System.out.printf("Ingrese Nombre Recepcionista: ");
        String nombreElim = sc.nextLine();
        System.out.printf("Ingrese Contraseña Recepcionista: ");
        String contraseñaElim = sc.nextLine();

        try
        {
            gestionador.eliminarRecepcionista(buscarUsuario(nombreRep, contraseñaRep), buscarUsuario(nombreElim, contraseñaElim));
            eliminarUsuarioDeJSON(nombreElim, contraseñaElim);
        } catch (AccesoNoAutorizadoException e) {
            System.out.println(e.getMessage());
        }
        catch (UsuarioNoValidoException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void agregarPasajero(Scanner sc, SistemaHotel gestionador)
    {
        System.out.println("Inicie Sesion con el Usuario Administrador Autorizado");
        System.out.printf("Ingrese Nombre Administrador: ");
        String nombreRep = sc.nextLine();
        System.out.printf("Ingrese Contraseña Administrador: ");
        String contraseñaRep = sc.nextLine();

        System.out.printf("DNI: ");
        String dni = sc.nextLine();
        System.out.printf("Nombre: ");
        String nombre = sc.nextLine();
        System.out.printf("Apellido: ");
        String apellido = sc.nextLine();
        System.out.printf("Correo: ");
        String gmail = sc.nextLine();
        System.out.printf("Telefono: ");
        String telefono = sc.nextLine();
        System.out.printf("Origen: ");
        String origen = sc.nextLine();
        System.out.printf("Domicilio: ");
        String domicilio = sc.nextLine();

        try
        {
            gestionador.agregarPasajero(new Pasajero(dni, nombre, apellido, gmail, telefono, origen, domicilio), buscarUsuario(nombreRep, contraseñaRep));
        }
        catch (AccesoNoAutorizadoException e) {
            System.out.println(e.getMessage());
        } catch (PasajeroNoValidoException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String buscarPasajero(Scanner sc, SistemaHotel gestionador)
    {
        System.out.printf("DNI: ");
        String dni = sc.nextLine();

        if(gestionador.buscarPasajero(dni) != null)
        {
            return null;
        }
         return gestionador.buscarPasajero(dni).toString();
    }

    public static String recaudacionTotal(SistemaHotel gestionador)
    {
        String contenido = "El total recaudado es de " + gestionador.calcularRecaudacionTotal() + "$";
        return contenido;
    }

    public static void agregarHabitacion(Scanner sc, SistemaHotel gestionador)
    {
        System.out.printf("Numero de Habitaicon");
        int numHabitacion = sc.nextInt();
        sc.nextLine();

        TipoHabitacion tipo = null;
        boolean existenciaNumero = false;
        while (existenciaNumero == false)
        {
            System.out.println("Que tipo de Habitacion es");
            System.out.println("1. Estandar");
            System.out.println("2. Estandar Plus");
            System.out.println("3. Suit");
            int opciones = sc.nextInt();
            sc.nextLine();
            switch (opciones)
            {
                case 1:
                    tipo =  TipoHabitacion.ESTANDAR;
                    break;
                    case 2:
                    tipo =  TipoHabitacion.ESTANDARPLUS;
                    break;
                    case 3:
                        tipo =  TipoHabitacion.SUIT;
                        break;
                        default:
                            System.out.println("Ocurrio un error, numero no ingresado correctamente.");
            }

            if(opciones == 1 || opciones == 2 || opciones == 3)
            {
                existenciaNumero = true;
            }

        }

        EstadoHabitacion estado = null;
        boolean existenciaEstado = false;
        while (existenciaEstado == false)
        {
            System.out.println("En que estado se encuentra la Habitaicon");
            System.out.println("1. Ocupado");
            System.out.println("2. Libre");
            System.out.println("3. Reservado");
            System.out.println("4. Mantenimiento");
            int opciones = sc.nextInt();
            sc.nextLine();
            switch (opciones)
            {
                case 1:
                    estado =  EstadoHabitacion.OCUPADA;
                    break;
                case 2:
                    estado =  EstadoHabitacion.LIBRE;
                    break;
                case 3:
                    estado = EstadoHabitacion.RESERVADA;
                    break;
                case 4:
                    estado = EstadoHabitacion.MANTENIMIENTO;
                    break;
                default:
                    System.out.println("Ocurrio un error, numero no ingresado correctamente.");
            }

            if(opciones == 1 || opciones == 2 || opciones == 3 || opciones == 4)
            {
                existenciaEstado = true;
            }

        }

        System.out.printf("Ingrese el Precio x Noche: ");
        double precioXNoche = sc.nextDouble();
        sc.nextLine();

        gestionador.crearHabitacion(new Habitacion(numHabitacion, tipo, estado, precioXNoche));
    }

    public static void serializar(Scanner sc, SistemaHotel gestionador)
    {
        String archivo = sc.nextLine();
        gestionador.guardarHotel(archivo);
    }

    public static void deserealizar(Scanner sc, SistemaHotel gestionador) {
        gestionador.cargarHotel("DataHotel");

        JSONObject obj = new JSONObject(JSONUtiles.downloadJSON("DataHotel"));
        JSONArray usuarios = obj.getJSONArray("usuarios");

        JSONArray arrayFinal = new JSONArray();

        for (int i = 0; i < usuarios.length(); i++) {
            JSONObject usuario = usuarios.getJSONObject(i);

            JSONObject copia = new JSONObject();
            copia.put("tipo", usuario.getString("tipo"));
            copia.put("nombre", usuario.getString("nombre"));
            copia.put("contraseña", usuario.getString("contraseña"));

            if (usuario.getString("tipo").equalsIgnoreCase("recepcionista")) {
                copia.put("turno", usuario.getString("turno"));
            }

            arrayFinal.put(copia);
        }

        JSONUtiles.uploadJSON(arrayFinal, "ListaUsuarios");
    }
}