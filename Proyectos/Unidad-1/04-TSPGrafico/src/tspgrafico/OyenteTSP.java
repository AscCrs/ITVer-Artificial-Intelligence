package tspgrafico;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jgap.Gene;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.InvalidConfigurationException;
import org.jgap.Population;
import modelos.tsp.ConfiguracionTSP;
import modelos.tsp.GrafoTSP;

/**
 * @author Rafael Rivera-López
 */
public class OyenteTSP implements ActionListener {

  private final GrafoTSP grafo;
  private final FrameTSP vista;

  OyenteTSP(GrafoTSP modelo, FrameTSP vista) {
    this.grafo = modelo;
    this.vista = vista;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    Component origen = (Component) e.getSource();
    switch (origen.getName()) {
      case "abrir":
        abrirProblemaTSP();
        break;
      case "salir":
        System.exit(0);
        break;
      case "iniciar":
        new HiloAG().start();
    }
  }

  private void abrirProblemaTSP() {
    JFileChooser seleccion = new JFileChooser();
    FileNameExtensionFilter filtro1 = new FileNameExtensionFilter("Archivos de TSP", "tsp");
    seleccion.setFileFilter(filtro1);
    URL url = getClass().getResource(".");
    seleccion.setCurrentDirectory(new File(url.getPath()));
    int opcion = seleccion.showOpenDialog(vista);
    if (opcion == JFileChooser.APPROVE_OPTION) {
      try {
        grafo.crearProblema(new FileInputStream(seleccion.getSelectedFile()));
        String datos = "Problema : " + grafo.getNombre()
          + "  Nodos : " + grafo.getDimension();
        vista.getEtiquetaDatos().setText(datos);
        vista.getBotonIniciar().setEnabled(true);
        vista.repaint();
      } catch (FileNotFoundException ex) {
        System.out.println("Error de archivo! "+ex);
        System.exit(-1);
      }
    }
  }

  public class HiloAG extends Thread {

    public void run() {
      vista.getBotonIniciar().setEnabled(false);
      int tamPop = grafo.getCiudades().length * 50;
      ConfiguracionTSP conf = new ConfiguracionTSP(grafo, tamPop, grafo.getDimension());
      IChromosome[] poblacionInicial;
      try {
        poblacionInicial = conf.crearPoblacionInicial(grafo);
        Genotype prueba = new Genotype(conf, new Population(conf, poblacionInicial));
        actualizarEtiqueta(prueba,0);
        actualizarRuta(prueba);
        for (int i = 1; i < 1000; i++) {
          prueba.evolve();
          actualizarEtiqueta(prueba,i);
          actualizarRuta(prueba);
          try {
            Thread.sleep(5);
          } catch (InterruptedException ex) {
            System.out.println("Error!!");
          }
        }
      } catch (InvalidConfigurationException ex) {
        System.out.println("Error de configuracion!");
        System.exit(-1);
      }
      vista.getBotonIniciar().setEnabled(true);
    }

    public void actualizarEtiqueta(Genotype prueba,int i) {
      String aptitud = String.format(
        "Generación %d.  Mejor aptitud = %5.2f ",
        i,prueba.getFittestChromosome().getFitnessValue());
      vista.getEtiquetaAptitud().setText(aptitud);
    }

    private void actualizarRuta(Genotype prueba) {
      Gene[] genes = prueba.getFittestChromosome().getGenes();
      int[] ruta = new int[genes.length];
      for(int i=0;i<genes.length;i++){
        ruta[i] = (Integer)genes[i].getAllele();
      }
      grafo.setRuta(ruta);
      vista.repaint();
    }
  }

}
