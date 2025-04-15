package grafo;

/**
 *
 * @author Rafael Rivera-López
 */
public class Arco {
  private final Nodo origen;
  private final Nodo destino;
  private double costo;
  
  public Arco(Nodo origen,Nodo destino,double costo){
    this.origen = origen;
    this.destino = destino;
    this.costo = costo;
  }
  
  public Arco(Nodo origen,Nodo destino){
    this(origen,destino,0);
  }

  public Nodo getOrigen() {
    return origen;
  }

  public Nodo getDestino() {
    return destino;
  }

  public double getCosto() {
    return costo;
  }

  public void setCosto(double costo) {
    this.costo = costo;
  }
  
  
}
