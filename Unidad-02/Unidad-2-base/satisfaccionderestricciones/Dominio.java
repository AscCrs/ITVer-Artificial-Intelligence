package satisfaccionderestricciones;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Rafael Rivera-Lopez
 */
public class Dominio extends ArrayList<Object> {
  
  public Dominio(Object[] objetos){
    this.addAll(Arrays.asList(objetos));
  }

}


