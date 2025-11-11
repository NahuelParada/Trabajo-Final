package Clases;

import java.util.ArrayList;
import java.util.List;

public class Administrador extends Usuario{

    private List<String> permisos;
    private List<String> sectores;

    public Administrador(String nombre, String contraseña) {
        super(nombre, contraseña);
        permisos = new ArrayList<String>();
        sectores = new ArrayList<String>();
    }

    public List<String> getPermisos() {
        return permisos;
    }

    public void setPermisos(List<String> permisos) {
        this.permisos = permisos;
    }

    public List<String> getSectores() {
        return sectores;
    }

    public void setSectores(List<String> sectores) {
        this.sectores = sectores;
    }

    @Override
    public String toString() {
        return "Administrador{" +
                "nombre=" + super.getNombre() +
                "permisos=" + getPermisos() +
                ", sectores=" + getSectores() +
                '}';
    }
}
