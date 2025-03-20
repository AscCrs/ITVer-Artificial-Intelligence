
package problemasat;

import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jgap.Gene;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.InvalidConfigurationException;

/**
 *
 * @author Rafael Rivera-López
 */
public class ProblemaSAT {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    InputStream flujo = ProblemaSAT.class.
      getResourceAsStream("/satlib/uf50-01.cnf");
    ExpresionSAT sat = new ExpresionSAT(flujo);
    System.out.println("Literales = " + sat.getnLiterales());
    System.out.println("Cláusulas = " + sat.getnClausulas());
    System.out.println(sat);
    //Configurar el Algoritmo Genético
    int tamIndividuo = sat.getnLiterales();     
    int tamPoblacion = (int)(tamIndividuo*1.5);
    ConfiguracionSAT conf = 
      new ConfiguracionSAT(sat, tamPoblacion, tamIndividuo);
    try {   
      Genotype poblacion = Genotype.randomInitialGenotype(conf);
      IChromosome mejorIndividuo = poblacion.getFittestChromosome();  //<==
      System.out.print("Generación 0 = ");
      List<IChromosome> pop = poblacion.getPopulation().getChromosomes();
      System.out.println("Mejor aptitud = "+mejorIndividuo.getFitnessValue());
      for (int i = 1; i < 1000; i++) {
        System.out.print("Generación " + i + " ");
        poblacion.evolve();  //<====
        mejorIndividuo = poblacion.getFittestChromosome();
        System.out.println("Mejor aptitud = "+mejorIndividuo.getFitnessValue());
                if (mejorIndividuo.getFitnessValue() == sat.getnClausulas()) {
          Gene[] genes=mejorIndividuo.getGenes();
          String salida="[";
          for(Gene gene:genes){
            salida+=(gene.getAllele().toString().equals("true")?"T":"F")+",";
          }
          salida+="]";
          salida=salida.replace(",]","]");
          System.out.println(salida+"\nEs SAT!!");
          return;
        }

      }      
    } catch (InvalidConfigurationException ex) {
      System.out.println("Error de AG = "+ex);
      System.exit(-1);
    }
   
  }
  
}
