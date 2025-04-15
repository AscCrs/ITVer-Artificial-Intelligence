package coloreografo;

import java.net.URL;
import satisfaccionderestricciones.BacktrackingCronologico;
import satisfaccionderestricciones.Estado;

/**
 *
 * @author rafael
 */
public class ColoreoGrafo {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    URL path = ColoreoGrafo.class.getResource("/problemas");
    String archivo = path.getPath().replace("%20", " ") + "/republica-mexicana.graph";
    MapaCSP mapa = new MapaCSP(archivo);
    System.out.println("Variables = " + mapa.getVariables().toString());
    BacktrackingCronologico algoritmo = new BacktrackingCronologico(mapa);
    Estado solucion = algoritmo.buscarSolucion();
    if (solucion != null) {
      System.out.println("Solución = " + solucion);
    }
  }

}
