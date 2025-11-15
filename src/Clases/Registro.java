package Clases;

import java.util.ArrayList;

public class Registro <T> {
    private ArrayList<T> registro;

    public Registro() {
        this.registro = new ArrayList<T>();
    }

    public String mostrarLista(){
        String info = "";
        for(T item: registro){
            info += item.toString();
        }

        return info;
    }

}
