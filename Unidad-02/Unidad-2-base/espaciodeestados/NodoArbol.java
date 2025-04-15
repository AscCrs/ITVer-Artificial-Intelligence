package espaciodeestados;

import java.util.ArrayList;

/**
 *
 * @author Rafael Rivera-López
 * @param <T> Clase que hereda de Estado
 */
public class NodoArbol <T extends Estado> {
  private final T estado;
  private NodoArbol padre;
  private final ArrayList<NodoArbol> hijos;
  
  public NodoArbol(T estado){
    this.estado = estado;
    hijos = new ArrayList();
  }

  public T getEstado() {
    return estado;
  }

  public NodoArbol getPadre() {
    return padre;
  }

  public void setPadre(NodoArbol padre) {
    this.padre = padre;
  }

  public ArrayList<NodoArbol> getHijos() {
    return hijos;
  }
}
