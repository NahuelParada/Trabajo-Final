package Clases;

import Interfaces.Identificador;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;


//Agrego la interfaz al objeto generico para que estos metodos solo funcionen con Registros de clases que implementen dicha interfaz
public class Registro <T extends Identificador> implements Iterable<T> {
    /// Atriburto
    private HashSet<T> registro;
    /// Constructor
    public Registro() {
        this.registro = new HashSet<T>();
    }


    public boolean agregarRegistro(T item){ return registro.add(item); }

    public boolean eliminarRegistro(T item){ return registro.remove(item); }

    //Busca por numero un objeto en la lista y lo elimina
    public boolean eliminarRegistroPorId(int id){
        Iterator<T> lista = registro.iterator();

        while(lista.hasNext()){
            T item = lista.next();
            if(item.getIdentificador() == id){
                 return registro.remove(item);
            }
        }
        return false;
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


    @Override
    public Iterator<T> iterator() {
        return Collections.unmodifiableSet(registro).iterator();
    }

}
