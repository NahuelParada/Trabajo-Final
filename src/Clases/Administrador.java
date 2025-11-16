package Clases;

import Interfaces.Identificador;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Administrador extends Usuario implements Identificador {
    /// Atributos
    public static int contador = 1;
    private int id;

    /// Constructor
    public Administrador(String nombre, String contraseña) {
        super(nombre, contraseña);
        this.id = contador++;
    }
    /// Getter
    public int getId() {
        return id;
    }

    /// Equals & Hascode
    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        Administrador that = (Administrador) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    /// ToString

    @Override
    public String toString() {
        return """
           Información del usuario:
           %s
           - Rango : Administrador
           - ID    : %d
           """.formatted(super.toString(), id);
    }

    /// Interfaz
    @Override
    public int getIdentificador() {
        return this.id;
    }
}
