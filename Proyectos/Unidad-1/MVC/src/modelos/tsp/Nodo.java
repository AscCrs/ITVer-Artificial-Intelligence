package modelos.tsp;

/**
 *
 * @author rafael
 */
public class Nodo {

  private int numero;
  private double x;
  private double y;
  
  public Nodo(int numero,double x, double y){
    this.numero=numero;
    this.x = x;
    this.y = y;
  }

  /**
   * @return the numero
   */
  public int getNumero() {
    return numero;
  }

  /**
   * @param numero the numero to set
   */
  public void setNumero(int numero) {
    this.numero = numero;
  }

  /**
   * @return the x
   */
  public double getX() {
    return x;
  }

  /**
   * @param x the x to set
   */
  public void setX(double x) {
    this.x = x;
  }

  /**
   * @return the y
   */
  public double getY() {
    return y;
  }

  /**
   * @param y the y to set
   */
  public void setY(double y) {
    this.y = y;
  }

}
