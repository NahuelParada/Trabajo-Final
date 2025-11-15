package Clases;

import Enums.Turno;

public class Recepcionista extends Usuario {
    /// Atributos
    public static int contador = 0;
    private int id;
    private Turno turno;

    /// Constructor

    public Recepcionista(Turno turno,String nombre, String contraseña) {
        super(nombre,contraseña);
        this.id = contador++;
        this.turno = turno;
    }

}
