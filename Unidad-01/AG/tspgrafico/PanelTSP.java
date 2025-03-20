package tspgrafico;

import java.awt.*;
import javax.swing.JPanel;
import modelos.tsp.*;

/**
 * @author Rafael Rivera-López
 */
public class PanelTSP extends JPanel {

  private GrafoTSP problema;
  private double maxX;
  private double maxY;
  private double minX;
  private double minY;
  private double factor;  // para determinar cuantos pixeles por unidad
  private double ajusteX; // Para centrar horizontalmente el grafo
  private double ajusteY; // Para centrar verticalmente el grafo

  public PanelTSP(GrafoTSP problema) {
    setBackground(Color.CYAN);
    this.problema = problema;
  }

//  public void setProblema(GrafoTSP problema) {
//    this.problema = problema;
//  }

  public void calcularRangos(int ancho, int alto) {
    maxX = Double.MIN_VALUE;
    maxY = Double.MIN_VALUE;
    minX = Double.MAX_VALUE;
    minY = Double.MAX_VALUE;
    for (Nodo ciudad : problema.getCiudades()) {
      double x = ciudad.getX();
      double y = ciudad.getY();
      maxX = (x > maxX) ? x : maxX;
      maxY = (y > maxY) ? y : maxY;
      minX = (x < minX) ? x : minX;
      minY = (y < minY) ? y : minY;
    }
    double factorX = (ancho - 50) / (maxX - minX);
    double factorY = (alto - 50) / (maxY - minY);
    factor = Math.min(factorX, factorY);
    ajusteX = (ancho - (maxX - minX) * factor) / 2;
    ajusteY = (alto - (maxY - minY) * factor) / 2;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (problema == null) {
      return;
    }
    int ancho = this.getWidth();
    int alto = this.getHeight();
    Font fuente = new Font("Arial", Font.PLAIN, 8);
    g.setFont(fuente);
    Nodo[] ciudades = problema.getCiudades();
    if (ciudades != null) {
      calcularRangos(ancho, alto);
      for (Nodo ciudad : ciudades) {
        int x = (int) ((ciudad.getX() - minX) * factor + ajusteX);
        int y = alto - (int) ((ciudad.getY() - minY) * factor + ajusteY);
        g.setColor(Color.BLUE);
        g.fillOval(x - 2, y - 2, 4, 4);
        g.setColor(Color.BLACK);
        g.drawOval(x - 2, y - 2, 4, 4);
        g.setColor(Color.DARK_GRAY);
        g.drawString("" + ciudad.getNumero(), x - 4, y - 4);
      }
      if (problema.getRuta() != null) {
        g.setColor(Color.RED);
        int[] r = problema.getRuta();
        for (int i=1; i < r.length; i++) {
          Nodo origen = ciudades[r[i - 1]];
          Nodo destino = ciudades[r[i]];
          dibujarLinea(g, origen, destino, alto);
        }
        Nodo origen = ciudades[r[r.length - 1]];
        Nodo destino = ciudades[r[0]];
        dibujarLinea(g, origen, destino, alto);
      }
    }
  }

  private void dibujarLinea(Graphics g, Nodo origen, Nodo destino, int alto) {
    int x1 = (int) ((origen.getX() - minX) * factor + ajusteX);
    int y1 = alto - (int) ((origen.getY() - minY) * factor + ajusteY);
    int x2 = (int) ((destino.getX() - minX) * factor + ajusteX);
    int y2 = alto - (int) ((destino.getY() - minY) * factor + ajusteY);
    g.drawLine(x1, y1, x2, y2);
  }
}
