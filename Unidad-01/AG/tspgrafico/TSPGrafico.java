package tspgrafico;

import modelos.tsp.GrafoTSP;

/**
 *
 * @author Rafael Rivera-López
 */
public class TSPGrafico {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    GrafoTSP tsp = new GrafoTSP();                          // MODELO
    FrameTSP f = new FrameTSP(tsp);                   // VISTA
    OyenteTSP oyente = new OyenteTSP(tsp, f);  // CONTROLADOR
    f.addEventos(oyente);                                  // REGISTRO
    f.setLocation(100, 100);
    f.setSize(800, 600);
    f.setVisible(true);
  }

}
