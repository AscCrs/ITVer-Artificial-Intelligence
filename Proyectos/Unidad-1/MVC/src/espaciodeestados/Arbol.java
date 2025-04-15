package espaciodeestados;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author Rafael Rivera-López
 */
public class Arbol extends HashMap<Long,NodoArbol>{
  private NodoArbol raiz;
  
  public void insertar(Estado estado){
    NodoArbol nodo = new NodoArbol(estado);
    this.put(estado.getId(),nodo);
    Estado padre = estado.getPredecesor();
    if(padre!=null){
      NodoArbol nodoPadre = this.get(padre.getId());
      nodoPadre.getHijos().add(nodo);
      nodo.setPadre(nodoPadre);
    } else {
      raiz = nodo;
    }
  }

  public boolean contiene(Estado estado){
    String info = estado.getInfo();
    Iterator<NodoArbol> nodos = this.values().iterator();
    while(nodos.hasNext()){
      NodoArbol siguiente  = nodos.next();
      if(siguiente.getEstado().getInfo().equals(info)){
        return true;
      }
    }
    return false;
  }
  
  public ArrayList<Estado> getRuta(Estado estado){
    ArrayList<Estado> ruta = new ArrayList();
    NodoArbol actual = this.get(estado.getId());
    while(actual!=null){
      ruta.add(0,actual.getEstado());
      actual = actual.getPadre();
    }
    return ruta;
  }
  
  public NodoArbol getRaiz() {
    return raiz;
  }
}
