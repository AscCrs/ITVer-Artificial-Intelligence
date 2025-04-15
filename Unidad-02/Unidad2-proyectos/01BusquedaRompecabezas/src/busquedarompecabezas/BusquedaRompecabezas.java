package busquedarompecabezas;

import static espaciodeestados.EstrategiaBusqueda.*;

/**
 *
 * @author rafael
 */
public class BusquedaRompecabezas {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    String estadoInicial = "1,2,3,8,6,0,7,5,4";
    String estadoMeta = "1,2,3,8,0,4,7,6,5";
    ProblemaRompecabezas problema = 
      new ProblemaRompecabezas(estadoInicial,estadoMeta);
    System.out.println("Estado Inicial = "+estadoInicial);
    System.out.println("Estado Meta = "+estadoMeta);
    System.out.println(problema.getEstadoInicial());
    System.out.println(problema.getEstadoMeta());
    problema.buscarSolucion(BUSQUEDA_POR_COSTO_UNIFORME, SIN_REPETICION);
  }
  
}
