package espaciodeestados;

import java.util.ArrayList;
import static espaciodeestados.EstrategiaBusqueda.*;

/**
 *
 * @author Rafael Rivera-López
 * @param <T> Clase que hereda de Estado
 */
public abstract class ProblemaBusqueda<T extends Estado> {

  protected T estadoInicial;
  protected T estadoMeta;

  public ProblemaBusqueda(T estadoInicial, T estadoMeta) {
    this.estadoInicial = estadoInicial;
    this.estadoMeta = estadoMeta;
  }

  public abstract double getCosto(T estadoActual, T estadoSucesor);

  public abstract double getCostoEstimado(T estadoActual);

  public abstract ArrayList<T> getSucesores(T estadoActual);

  public void buscarSolucion(int tipoEstrategia, boolean repeticion) {
    EstrategiaBusqueda busqueda;
    switch (tipoEstrategia) {
      case BUSQUEDA_POR_AMPLITUD: {
        busqueda = new BusquedaPorAmplitud(this);
        break;
      }
      case BUSQUEDA_POR_PROFUNDIDAD: {
        busqueda = new BusquedaPorProfundidad(this);
        break;
      }
      case BUSQUEDA_POR_COSTO_UNIFORME: {
        busqueda = new BusquedaPorCostoUniforme(this);
        break;
      }
      case BUSQUEDA_BEST_FIRST: {
        busqueda = new BusquedaBestFirst(this);
        break;
      }
      case BUSQUEDA_A_ESTRELLA: {
        busqueda = new BusquedaAEstrella(this);
        break;
      }
      default: {
        throw new UnsupportedOperationException("Estrategia de búsqueda no definida!");
      }
    }
    Estado meta = busqueda.realizarBusqueda(repeticion);
    if (meta != null) {
      System.out.println(busqueda.getRuta(meta));
    } else {
      System.out.println("No hay una ruta al estado meta");
    }
  }
  
  public void buscarSolucion(int tipoEstrategia, boolean repeticion, int limite) {
    EstrategiaBusqueda busqueda;
    switch (tipoEstrategia) {
      case BUSQUEDA_POR_AMPLITUD: {
        busqueda = new BusquedaPorAmplitud(this);
        break;
      }
      case BUSQUEDA_POR_PROFUNDIDAD: {
        busqueda = new BusquedaPorProfundidad(this);
        break;
      }
      case BUSQUEDA_POR_COSTO_UNIFORME: {
        busqueda = new BusquedaPorCostoUniforme(this);
        break;
      }
      case BUSQUEDA_BEST_FIRST: {
        busqueda = new BusquedaBestFirst(this);
        break;
      }
      case BUSQUEDA_A_ESTRELLA: {
        busqueda = new BusquedaAEstrella(this);
        break;
      }
      case BUSQUEDA_POR_PROFUNDIDAD_LIMITADA: {
        busqueda = new BusquedaPorProfundidadLimitada(this, limite);
        break;
      }
      case BUSQUEDA_POR_PROFUNDIDAD_ITERATIVA: {
        busqueda = new BusquedaPorProfundidadIterativa(this, limite);
        break;
      }
      default: {
        throw new UnsupportedOperationException("Estrategia de búsqueda no definida!");
      }
    }
    Estado meta = busqueda.realizarBusqueda(repeticion);
    if (meta != null) {
      System.out.println(busqueda.getRuta(meta));
    } else {
      System.out.println("No hay una ruta al estado meta");
    }
  }

  public T getEstadoInicial() {
    return estadoInicial;
  }

  public T getEstadoMeta() {
    return estadoMeta;
  }

}
