package grafo;

import java.awt.Point;

/**
 *
 * @author Rafael Rivera-López
 */
public class Nodo extends Point{
  private final String nombre;
  
  public Nodo(String nombre,int x,int y){
    super(x,y);
    this.nombre = nombre;
  }
  
  public Nodo(String nombre){
    this(nombre,0,0);
  }
  
  public String getNombre(){
    return nombre;
  }

}
