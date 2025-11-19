package Clases;

import Enums.EstadoHabitacion;
import Enums.MetodoPago;
import org.json.JSONArray;
import org.json.JSONObject;

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

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
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

    public boolean eliminarUsuario(Usuario u){
        return usuarios.eliminarRegistro(u);
    }

    public Pasajero buscarPasajero(String dni) {
        return pasajeros.buscarPorNumero(Integer.parseInt(dni));
    }

    public String listarHabitacionesDisponibles() {
        StringBuilder habDisponible = new StringBuilder();
        for(Habitacion h : habitaciones){
            if(h.getEstado() == EstadoHabitacion.LIBRE){
                habDisponible.append(h.toString()).append("\n");
            }
        }
        return habDisponible.toString();
    }

    public String listarHabitacionesNoDisponibles() {
        StringBuilder habNoDisponible = new StringBuilder();
        for(Habitacion h : habitaciones){
            if(h.getEstado() == EstadoHabitacion.OCUPADA || h.getEstado() == EstadoHabitacion.RESERVADA){
                habNoDisponible.append(h.toString()).append("\n");
            }
        }
        return habNoDisponible.toString();
    }

    public Habitacion buscarHabitacionPorNumero(int numHab){
       return habitaciones.buscarPorNumero(numHab);
    }

    public Reserva buscarReserva(int idReserva){
        return reservas.buscarPorNumero(idReserva);
    }

    public boolean eliminarReserva(Reserva reserva){
        return reservas.eliminarRegistro(reserva);
    }


    public boolean hacerCheckIn(int id) {
        Reserva r = buscarReserva(id);
        r.setEstadoHabitacionReserva(EstadoHabitacion.OCUPADA);
        return true;
    }

    public boolean hacerCheckOut(int id) {
        Reserva r = buscarReserva(id);
        Habitacion h = r.getHabitacion();
        if(h.getEstado() == EstadoHabitacion.OCUPADA) {
            r.setEstadoHabitacionReserva(EstadoHabitacion.MANTENIMIENTO);
            return true;
        }
        return false;
    }

    public double calcularRecaudacionTotalEnReservas() {
        double total = 0;
        for(Reserva r : reservas){
             total += r.calcularCosto();

        }
        return total;
    }

    /// SERIALIZADOR
    public JSONObject toJson() {

        JSONObject json = new JSONObject();

        json.put("nombre", nombre);
        json.put("direccion", direccion);

        JSONArray arrayUsuarios = new JSONArray();
        for (Usuario u : usuarios) {
            JSONObject obj = u.toJson();

            if (u instanceof Administrador) {
                obj.put("tipo", "ADMIN");
            } else {
                obj.put("tipo", "RECEPCIONISTA");
            }

            arrayUsuarios.put(obj);
        }
        json.put("usuarios", arrayUsuarios);

        JSONArray arrayPasajeros = new JSONArray();
        for (Pasajero p : pasajeros)
            arrayPasajeros.put(p.toJson());
        json.put("pasajeros", arrayPasajeros);

        JSONArray arrayHabitaciones = new JSONArray();
        for (Habitacion h : habitaciones)
            arrayHabitaciones.put(h.toJson());
        json.put("habitaciones", arrayHabitaciones);

        JSONArray arrayReservas = new JSONArray();
        for (Reserva r : reservas)
            arrayReservas.put(r.toJson());
        json.put("reservas", arrayReservas);

        return json;
    }

    /// DESERIALIZACION
    public Hotel(JSONObject obj) {
        this.nombre = obj.getString("nombre");
        this.direccion = obj.getString("direccion");

        this.usuarios = new Registro<>();
        JSONArray arrayUsuarios = obj.getJSONArray("usuarios");
        for (int i = 0; i < arrayUsuarios.length(); i++) {
            JSONObject uJson = arrayUsuarios.getJSONObject(i);
            String tipo = uJson.getString("tipo");

            if (tipo.equals("Administrador"))
                usuarios.agregarRegistro(new Administrador(uJson));
            else if (tipo.equals("Recepcionista"))
                usuarios.agregarRegistro(new Recepcionista(uJson));
        }

        this.pasajeros = new Registro<>();
        JSONArray arrayPasajeros = obj.getJSONArray("pasajeros");
        for (int i = 0; i < arrayPasajeros.length(); i++)
            pasajeros.agregarRegistro(new Pasajero(arrayPasajeros.getJSONObject(i)));

        this.habitaciones = new Registro<>();
        JSONArray arrayHabitaciones = obj.getJSONArray("habitaciones");
        for (int i = 0; i < arrayHabitaciones.length(); i++)
            habitaciones.agregarRegistro(new Habitacion(arrayHabitaciones.getJSONObject(i)));

        this.reservas = new Registro<>();
        JSONArray arrayReservas = obj.getJSONArray("reservas");
        for (int i = 0; i < arrayReservas.length(); i++)
            reservas.agregarRegistro(new Reserva(arrayReservas.getJSONObject(i)));
    }


}
