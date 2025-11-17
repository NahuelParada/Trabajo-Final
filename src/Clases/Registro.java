package Clases;

import Interfaces.Identificador;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;


//Agrego la interfaz al objeto generico para que estos metodos solo funcionen con Registros de clases que implementen dicha interfaz
public class Registro <T extends Identificador>  {
    /// Atriburto
    private HashSet<T> registro;
    /// Constructor
    public Registro() {
        this.registro = new HashSet<T>();
    }


    public String mostrarLista(){
        String info = "";
        for(T item: registro){
            info += item.toString();
        }
        return info;
    }

    public boolean agregarRegistro(T item){ return registro.add(item); }

    public boolean eliminarRegistro(T item){ return registro.remove(item); }

    //Busca por numero un objeto en la lista y lo elimina
    public void eliminarRegistroPorId(int id){
        Iterator<T> lista = registro.iterator();

        while(lista.hasNext()){
            T item = lista.next();
            if(item.getIdentificador() == id){
                 registro.remove(item);
            }
        }
    }

    public T buscarPorNumero(int id){
        T item;
        Iterator<T> lista = registro.iterator();
        while(lista.hasNext()){
            item = lista.next();
            if(item.getIdentificador() == id){
                return item;
            }
        }
        return null;
    }

    public boolean contiene(T item){
        return registro.contains(item);
    }

    public HashSet<T> listarTodos() {
        return new HashSet<>(registro);
    }
}
