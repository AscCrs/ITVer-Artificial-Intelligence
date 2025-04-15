package planeacionrutas;

import grafo.Grafo;
import  static espaciodeestados.EstrategiaBusqueda.*;

/**
 *
 * @author rafael
 */
public class PlaneacionRutas {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    String archivo = PlaneacionRutas.class.
            getResource("/problemas/Ejemplo-mundo-bloques.graph").getPath().replace("%20", " ");
    Grafo grafo = new Grafo(archivo);
    EstadoNodo estadoInicial = new EstadoNodo(grafo.getNodos().get("C-A B"));
    EstadoNodo estadoMeta = new EstadoNodo(grafo.getNodos().get("A-B-C"));
    System.out.println(estadoInicial);
    System.out.println(estadoMeta);
    ProblemaPlaneacion problema =  new ProblemaPlaneacion(grafo,estadoInicial,estadoMeta);
    long tiempo = System.currentTimeMillis();
    problema.buscarSolucion(BUSQUEDA_POR_PROFUNDIDAD_ITERATIVA, SIN_REPETICION, 5);
    System.out.println(System.currentTimeMillis() - tiempo);
  }

}
