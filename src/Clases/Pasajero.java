package Clases;

import Interfaces.Identificador;

import java.util.Objects;

public class Pasajero implements Identificador {
    /// Atributos
    private String dni;
    private String nombre;
    private String apellido;
    private String gmail;
    private String telefono;
    private String origen;
    private String domicilio;

    /// Constructor
    public Pasajero(String dni, String nombre, String apellido, String gmail, String telefono, String origen, String domicilio) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.gmail = gmail;
        this.telefono = telefono;
        this.origen = origen;
        this.domicilio = domicilio;
    }

    /// Interfaz

    @Override
    public int getIdentificador() {
        return Integer.parseInt(dni);
    }

    /// Equals & Hashout

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pasajero pasajero = (Pasajero) o;
        return Objects.equals(dni, pasajero.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dni);
    }

    ///  ToString

    @Override
    public String toString() {
        return "Información de pasajero: \n" +
                "  -DNI: " + dni + "\n" +
                "  -Nombre: " + nombre + "\n" +
                "  -Apellido: " + apellido + "\n" +
                "  -Gmail: " + gmail + "\n" +
                "  -Telefono: " + telefono + "\n" +
                "  -Origen: " + origen + "\n" +
                "  -Domicilio: " + domicilio ;
    }
}
