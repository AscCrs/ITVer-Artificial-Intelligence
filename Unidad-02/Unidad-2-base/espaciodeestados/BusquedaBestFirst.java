package espaciodeestados;

import java.util.ArrayList;

/**
 *
 * @author Rafael Rivera-López
 */
public class BusquedaBestFirst extends EstrategiaBusqueda{

  public BusquedaBestFirst(ProblemaBusqueda problema) {
    super(problema);
    nombreEstrategia = "Búsqueda Best-First";
  }

  @Override
  public void encolarSucesores(Estado estadoActual, ArrayList<Estado> sucesores) {
    for(Estado sucesor : sucesores){
      if(validar(sucesor)){
        sucesor.setPredecesor(estadoActual);
        double costo = problema.getCostoEstimado(sucesor);
        sucesor.setCostoEstimado(costo);
        colaBusqueda.encolarConPrioridad(sucesor, costo);
      }
    }
  }

}















