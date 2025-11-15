package Clases;

import java.util.ArrayList;
import java.util.List;

public class Administrador extends Usuario{
    /// Atributos
    public static int contador = 0;
    private int id;

    /// Constructor
    public Administrador(String nombre, String contraseña) {
        super(nombre, contraseña);
        this.id = contador++;
    }

}
