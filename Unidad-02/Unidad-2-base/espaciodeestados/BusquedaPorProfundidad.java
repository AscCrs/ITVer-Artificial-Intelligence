/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package espaciodeestados;

import java.util.ArrayList;

/**
 *
 * @author Rafael Rivera-López
 */
public class BusquedaPorProfundidad extends EstrategiaBusqueda{

  public BusquedaPorProfundidad(ProblemaBusqueda problema) {
    super(problema);
    nombreEstrategia = "Búsqueda por Profundidad";
  }

  @Override
  public void encolarSucesores(Estado estadoActual, ArrayList<Estado> sucesores) {
    for(Estado sucesor : sucesores){
      if(validar(sucesor)){
        sucesor.setPredecesor(estadoActual);
        colaBusqueda.encolarAlInicio(sucesor);
      }
    }
  }

}













