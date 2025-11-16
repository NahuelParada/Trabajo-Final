package Clases;

import java.util.Objects;

public abstract class Usuario {
    /// Atributos
    String nombre;
    String contraseña;

    /// Constructor
    public Usuario(String nombre, String contraseña) {
        this.nombre = nombre;
        this.contraseña = contraseña;
    }
    /// Metodos
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public boolean iniciarSesion(String nombre, String contraseña)
    {
        if(this.nombre.equalsIgnoreCase(nombre) && this.contraseña.equalsIgnoreCase(contraseña))
        {
            return true;
        }
        return false;
    }

    public boolean cerrarSesion()
    {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Usuario usuario)) return false;
        return Objects.equals(nombre, usuario.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, contraseña);
    }

}
