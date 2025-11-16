package Clases;

import Interfaces.Identificador;
import java.util.ArrayList;
import java.util.Iterator;


//Agrego la interfaz al objeto generico para que estos metodos solo funcionen con Registros de clases que implementen dicha interfaz
public class Registro <T extends Identificador>  {
    /// Atriburto
    private ArrayList<T> registro;
    /// Constructor
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

    public boolean agregarRegistro(T item){ return registro.add(item); }


    //Busca por numero un objeto en la lista y lo elimina
    public void eliminarRegistro(int id){
        Iterator<T> lista = registro.iterator();

        while(lista.hasNext()){
            T item = lista.next();
            if(item.getIdentificador() == id){
                 registro.remove(item);
            }
        }
    }

}
