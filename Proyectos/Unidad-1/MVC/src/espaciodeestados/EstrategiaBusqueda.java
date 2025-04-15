package espaciodeestados;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Rafael Rivera-López
 */
public abstract class EstrategiaBusqueda {
  
  public static final int BUSQUEDA_POR_AMPLITUD = 1;
  public static final int BUSQUEDA_POR_PROFUNDIDAD = 2;
  public static final int BUSQUEDA_POR_COSTO_UNIFORME = 3;
  public static final int BUSQUEDA_BEST_FIRST = 4;
  public static final int BUSQUEDA_A_ESTRELLA = 5;
  public static final int BUSQUEDA_POR_PROFUNDIDAD_LIMITADA = 6;
  public static final int BUSQUEDA_POR_PROFUNDIDAD_ITERATIVA = 7;
  public static final boolean CON_REPETICION = true;
  public static final boolean SIN_REPETICION = false;
  
  protected boolean conRepeticion;
  protected final Cola colaBusqueda;
  protected final Arbol arbol;
  protected final ProblemaBusqueda problema;
  protected String nombreEstrategia;
  
  public EstrategiaBusqueda(ProblemaBusqueda problema){
    this.problema = problema;
    this.colaBusqueda = new Cola();
    this.arbol = new Arbol();
  }  

  public abstract void encolarSucesores(Estado estadoActual, ArrayList<Estado> sucesores);
  
  public Estado realizarBusqueda(boolean repeticion){
    System.out.println(nombreEstrategia);
    colaBusqueda.clear();
    arbol.clear();
    conRepeticion = repeticion;
    colaBusqueda.encolarAlInicio(problema.estadoInicial);
    int i=0;
    while(!colaBusqueda.isEmpty()){
      System.out.println(""+(i++)+" "+colaBusqueda); 
      try {
        Thread.sleep(1000);
      } catch (InterruptedException ex) {
        System.out.println("Error de interrupción "+ex);
        System.exit(-1);
      }
      Estado estadoActual = colaBusqueda.desencolar();
//      System.out.println(estadoActual);
      arbol.insertar(estadoActual);
      if(estadoActual.getInfo().equals(problema.estadoMeta.getInfo())){
        return estadoActual;
      }
      ArrayList<Estado> sucesores = problema.getSucesores(estadoActual);
      encolarSucesores(estadoActual,sucesores);
    }
    return null;
  }
  
  protected boolean validar(Estado sucesor){
    if(!conRepeticion){
      return !arbol.contiene(sucesor);
    }
    return true;
  }
  
  public String getRuta(Estado estado){
    return "RUTA = "+arbol.getRuta(estado).toString();
  }
  
}
