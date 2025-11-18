package Clases;

import Interfaces.Identificador;

import java.util.Objects;

public abstract class Usuario implements  Identificador {
    /// Atributos

    private String nombre;
    private String contraseña;

    /// Constructor
    public Usuario(String nombre, String contraseña) {
        this.nombre = nombre;
        this.contraseña = contraseña;
    }
    /// Metodos

    public String getNombre() {
        return nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public boolean iniciarSesion(String nombre, String contraseña) {
        return this.nombre.equals(nombre) && this.contraseña.equals(contraseña);
    }

    public boolean cerrarSesion()
    {
        return true;
    }

    /// Interfaz(no se usa)

    @Override
    public abstract int getIdentificador();

    /// Equals & hashcode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false; // <-- Clave

        Usuario usuario = (Usuario) o;
        return Objects.equals(nombre, usuario.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }
    /// ToString
    @Override
    public String toString() {
        return "- Nombre: %s".formatted(nombre);
    }

}
