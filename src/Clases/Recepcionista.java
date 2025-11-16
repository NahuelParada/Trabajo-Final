package Clases;

import Enums.Turno;

import java.util.Objects;

public class Recepcionista extends Usuario {
    /// Atributos
    public static int contador = 1;
    private int id;
    private Turno turno;

    /// Constructor

    public Recepcionista(Turno turno,String nombre, String contraseña) {
        super(nombre,contraseña);
        this.id = contador++;
        this.turno = turno;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Recepcionista that)) return false;
        if (!super.equals(o)) return false;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    @Override
    public String toString() {
        return "Recepcionista{" +
                "id=" + id +
                ", turno=" + turno +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
