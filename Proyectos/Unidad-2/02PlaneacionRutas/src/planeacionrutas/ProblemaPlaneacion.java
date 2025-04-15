package planeacionrutas;

import espaciodeestados.ProblemaBusqueda;
import grafo.Grafo;
import grafo.Nodo;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Rafael Rivera-López
 */
public class ProblemaPlaneacion extends ProblemaBusqueda<EstadoNodo>{
  private final Grafo grafo;

  public ProblemaPlaneacion(Grafo grafo, EstadoNodo nodoInicial, EstadoNodo nodoMeta) {
    super(nodoInicial, nodoMeta);
    this.grafo = grafo;
  }

  @Override
  public double getCosto(EstadoNodo estadoActual, EstadoNodo estadoSucesor) {
    String idArco = estadoActual.getInfo()+estadoSucesor.getInfo();
    return grafo.getArcos().get(idArco).getCosto();
  }

  @Override
  public double getCostoEstimado(EstadoNodo estadoActual) {
    return estadoActual.getNodo().distance(estadoMeta.getNodo());
  }

  @Override
  public ArrayList<EstadoNodo> getSucesores(EstadoNodo estadoActual) {
    ArrayList<EstadoNodo> sucesores = new ArrayList();
    Iterator<Nodo> iterador = grafo.getNodos().values().iterator();
    while (iterador.hasNext()) {
      Nodo destino = iterador.next();
      String idArco = estadoActual.getInfo()+destino.getNombre();
      if(grafo.getArcos().containsKey(idArco)){
        sucesores.add(new EstadoNodo(destino));
      }
    }
    return sucesores;
  }
}
