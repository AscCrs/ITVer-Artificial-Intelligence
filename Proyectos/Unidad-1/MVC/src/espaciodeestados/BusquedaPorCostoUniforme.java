package espaciodeestados;

import java.util.ArrayList;

/**
 *
 * @author Rafael Rivera-López
 */
public class BusquedaPorCostoUniforme extends EstrategiaBusqueda{

  public BusquedaPorCostoUniforme(ProblemaBusqueda problema) {
    super(problema);
    nombreEstrategia = "Búsqueda por Costo Uniforme";
  }

  @Override
  public void encolarSucesores(Estado estadoActual, ArrayList<Estado> sucesores) {
    double costoAcumulado = estadoActual.getCostoAcumulado();  // <==
    for(Estado sucesor : sucesores){
      if(validar(sucesor)){
        sucesor.setPredecesor(estadoActual);
        double costo = costoAcumulado+problema.getCosto(estadoActual, sucesor);
        sucesor.setCostoAcumulado(costo);
        colaBusqueda.encolarConPrioridad(sucesor, costo);
      }
    }
  }
}














