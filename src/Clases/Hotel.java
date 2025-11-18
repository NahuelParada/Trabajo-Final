package Clases;

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

    /// SERIALIZACION
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("nombre", nombre);
        json.put("direccion", direccion);

        JSONArray arrayUsuarios = new JSONArray();
        for (Usuario u : usuarios.listarTodos())
            arrayUsuarios.put(u.toJson());
        json.put("usuarios", arrayUsuarios);

        JSONArray arrayPasajeros = new JSONArray();
        for (Pasajero p : pasajeros.listarTodos())
            arrayPasajeros.put(p.toJson());
        json.put("pasajeros", arrayPasajeros);

        JSONArray arrayHabitaciones = new JSONArray();
        for (Habitacion h : habitaciones.listarTodos())
            arrayHabitaciones.put(h.toJson());
        json.put("habitaciones", arrayHabitaciones);

        JSONArray arrayReservas = new JSONArray();
        for (Reserva r : reservas.listarTodos())
            arrayReservas.put(r.toJson());
        json.put("reservas", arrayReservas);

        return json;
    }

    /// DESERIALIZACION
    public Hotel(JSONObject obj) {
        this.nombre = obj.getString("nombre");
        this.direccion = obj.getString("direccion");

        this.reservas = new Registro<>();
        JSONArray arrayReservas = obj.getJSONArray("reservas");
        for (int i = 0; i < arrayReservas.length(); i++)
            reservas.agregarRegistro(new Reserva(arrayReservas.getJSONObject(i)));

        this.usuarios = new Registro<>();
        JSONArray arrayUsuarios = obj.getJSONArray("usuarios");
        for (int i = 0; i < arrayUsuarios.length(); i++) {

            JSONObject ujson = arrayUsuarios.getJSONObject(i);
            String tipo = ujson.getString("tipo");

            Usuario u;

            if (tipo.equals("Administrador"))
                u = new Administrador(ujson);
            else if (tipo.equals("Recepcionista"))
                u = new Recepcionista(ujson);
            else
                throw new RuntimeException("Tipo de usuario no reconocido: " + tipo);

            usuarios.agregarRegistro(u);
        }

        this.pasajeros = new Registro<>();
        JSONArray arrayPasajeros = obj.getJSONArray("pasajeros");
        for (int i = 0; i < arrayPasajeros.length(); i++)
            pasajeros.agregarRegistro(new Pasajero(arrayPasajeros.getJSONObject(i)));

        this.habitaciones = new Registro<>();
        JSONArray arrayHabitaciones = obj.getJSONArray("habitaciones");
        for (int i = 0; i < arrayHabitaciones.length(); i++)
            habitaciones.agregarRegistro(new Habitacion(arrayHabitaciones.getJSONObject(i)));
    }

}
