package espaciodeestados;

import java.util.ArrayList;

/**
 *
 * @author Rafael Rivera-López
 */
public class BusquedaPorAmplitud extends EstrategiaBusqueda{

  public BusquedaPorAmplitud(ProblemaBusqueda problema) {
    super(problema);
    nombreEstrategia = "Búsqueda por Amplitud";
  }

  @Override
  public void encolarSucesores(Estado estadoActual, ArrayList<Estado> sucesores) {
    for(Estado sucesor : sucesores){
      if(validar(sucesor)){
        sucesor.setPredecesor(estadoActual);
        colaBusqueda.encolarAlFinal(sucesor);
      }
    }
  }
}
