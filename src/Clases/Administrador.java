package Clases;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Administrador extends Usuario{
    /// Atributos
    public static int contador = 0;
    private int id;

    /// Constructor
    public Administrador(String nombre, String contraseña) {
        super(nombre, contraseña);
        this.id = contador++;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Administrador that)) return false;
        if (!super.equals(o)) return false;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    @Override
    public String toString() {
        return "Administrador{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
