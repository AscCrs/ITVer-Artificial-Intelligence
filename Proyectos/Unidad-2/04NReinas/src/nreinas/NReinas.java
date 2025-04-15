package nreinas;

import satisfaccionderestricciones.BacktrackingCronologico;
import satisfaccionderestricciones.Estado;

/**
 *
 * @author rafael
 */
public class NReinas {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    CSPNReinas problema = new CSPNReinas(4);
    System.out.println("Variables = " + problema.getVariables().toString());
    BacktrackingCronologico algoritmo = new BacktrackingCronologico(problema);
    Estado solucion = algoritmo.buscarSolucion();
    if (solucion != null) {
      System.out.println("Solución = " + solucion);
    } 
  }

}
