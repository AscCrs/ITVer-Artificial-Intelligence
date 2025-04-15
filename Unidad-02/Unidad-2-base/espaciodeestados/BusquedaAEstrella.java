package espaciodeestados;

import java.util.ArrayList;

/**
 *
 * @author Rafael Rivera-López
 */
public class BusquedaAEstrella extends EstrategiaBusqueda {

  public BusquedaAEstrella(ProblemaBusqueda problema) {
    super(problema);
    nombreEstrategia = "Búsqueda A*";
  }

  @Override
  public void encolarSucesores(Estado estadoActual, ArrayList<Estado> sucesores) {
    double costoAcumulado = estadoActual.getCostoAcumulado();
    for (Estado sucesor : sucesores) {
      if (validar(sucesor)) {
        sucesor.setPredecesor(estadoActual);   //<===== OJO
        double acumulado = costoAcumulado + problema.getCosto(estadoActual, sucesor);
        double estimado = problema.getCostoEstimado(sucesor);
        sucesor.setCostoAcumulado(acumulado);
        sucesor.setCostoEstimado(estimado);
        colaBusqueda.encolarConPrioridad(sucesor, acumulado + estimado);   // Con Prioridad
      }
    }
  }
}
