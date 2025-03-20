package problematsp;

import java.io.InputStream;
import java.util.Arrays;
import modelos.tsp.ConfiguracionTSP;
import modelos.tsp.GrafoTSP;
import org.jgap.*;

/**
 *
 * @author rafael
 */
public class ProblemaTSP {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    // TODO code application logic here
    InputStream file = ProblemaTSP.class.getResourceAsStream("/tsplib/berlin52.tsp");
    GrafoTSP tsp = new GrafoTSP(file);
    System.out.println("CIUDADES =" + tsp.getDimension());
    System.out.println(tsp.getNombre());
    int tamPop = tsp.getDimension() * 50;  //<=== PARAMETRO
    ConfiguracionTSP conf = new ConfiguracionTSP(tsp, tamPop, tsp.getDimension());
    try {
      IChromosome[] poblacionInicial = conf.crearPoblacionInicial(tsp);
      Genotype prueba = new Genotype(conf, new Population(conf, poblacionInicial));
//      for(IChromosome ind: poblacionInicial){
//        System.out.println(Arrays.toString(ind.getGenes()));
//      }

      System.out.print("Generación 0 ");
      System.out.println(prueba.getFittestChromosome().getFitnessValue());
      
      for (int i = 1; i < 500; i++) {
        prueba.evolve();
        System.out.print("Generación " + i + " "); //  + prueba.getPopulation().size() + " ");
        System.out.println(prueba.getFittestChromosome().getFitnessValue());

      }
      Gene[] genes = prueba.getFittestChromosome().getGenes();
      int[] ruta = new int[genes.length];
      for (int i = 0; i < genes.length; i++) {
        ruta[i] = (Integer) genes[i].getAllele();
      }
      tsp.setRuta(ruta);
      System.out.println("Mejor ruta:");
      System.out.println(Arrays.toString(tsp.getRuta()));

    } catch (InvalidConfigurationException ex) {
      System.out.println("Error de configuracion!");
      System.exit(-1);
    }
    
  }
  
}
