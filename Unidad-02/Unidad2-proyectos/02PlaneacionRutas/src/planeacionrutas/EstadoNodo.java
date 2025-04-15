package planeacionrutas;

import espaciodeestados.Estado;
import grafo.Nodo;

/**
 *
 * @author Rafael Rivera-López
 */
public class EstadoNodo extends Estado{
  
  private final Nodo nodo;
  
  public EstadoNodo(Nodo nodo){
    this.nodo = nodo;
  }
  
  public Nodo getNodo(){
    return nodo;
  }
  
  @Override
  public String getInfo() {
    return nodo.getNombre();
  }

}
