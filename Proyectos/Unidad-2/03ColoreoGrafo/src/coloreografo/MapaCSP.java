package coloreografo;

import grafo.Arco;
import grafo.Grafo;
import grafo.Nodo;
import satisfaccionderestricciones.*;

/**
 * @author Rafael Rivera-Lopez
 */
public class MapaCSP extends CSP {

  private final Grafo grafo;

  public MapaCSP(String archivo) {
    grafo = new Grafo(archivo);
    configurarProblema();
  }

  @Override
  public void crearVariables() {
    for(Nodo nodo: grafo.getNodos().values()){
      this.addVariable(nodo.getNombre()); 
    }
  }

  @Override
  public void crearDominios() {
    Dominio dominio = new Dominio(new Object[]{"ROJO","VERDE","AZUL","MAGENTA"});
    for(String var : this.getVariables()){
      this.setDominio(var, dominio);
    }
  }

  @Override
  public void crearRestricciones() {
    for(Arco arco : grafo.getArcos().values()){
      String origen = arco.getOrigen().getNombre();
      String destino = arco.getDestino().getNombre();
      addRestriccion(new RestriccionDiferente(origen,destino));
    }
  }
}
