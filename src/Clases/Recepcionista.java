package Clases;

import Enums.Turno;
import Interfaces.Identificador;
import org.json.JSONObject;

import java.util.Objects;

public class Recepcionista extends Usuario implements Identificador {
    /// Atributos
    public static int contador = 1;
    private int id;
    private Turno turno;

    /// Constructor

    public Recepcionista(Turno turno, String nombre, String contraseña) {
        super(nombre,contraseña);
        this.id = contador++;
        this.turno = turno;
    }
    /// Getter y setters
    public int getId() {
        return id;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    /// Equals & Hashcode
    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        Recepcionista that = (Recepcionista) o;
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
           - Rango : Recepcionista
           - ID    : %d
           - Turno : %s
           """.formatted(super.toString(), id, turno);
    }

    /// Interfaz
    @Override
    public int getIdentificador() {
        return this.id;
    }

    /// SERIALIZACION
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("nombre", getNombre());
        json.put("contraseña", getContraseña());
        json.put("turno", turno.toString());
        json.put("tipo", "recepcionista");
        return json;
    }

    /// DESERIALIZACION
    public Recepcionista(JSONObject obj) {
        super(obj.getString("nombre"), obj.getString("contraseña"));
        this.id = obj.getInt("id");
        this.turno = Turno.valueOf(obj.getString("turno"));
        if (id >= contador) contador = id + 1;
    }

}
