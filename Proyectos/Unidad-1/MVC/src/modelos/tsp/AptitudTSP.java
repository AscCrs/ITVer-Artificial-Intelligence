package modelos.tsp;

import org.jgap.FitnessFunction;
import org.jgap.Gene;
import org.jgap.IChromosome;
import org.jgap.impl.IntegerGene;

/**
 *
 * @author rafael
 */
public class AptitudTSP extends FitnessFunction {

  private final GrafoTSP tsp;

  public AptitudTSP(GrafoTSP tsp) {
    this.tsp = tsp;
  }

  @Override
  protected double evaluate(IChromosome ic) {
    double distTotal = 0;
    Gene[] ciudades = ic.getGenes();
    for (int i = 0; i < ciudades.length - 1; i++) {
      IntegerGene origen = (IntegerGene)ciudades[i];
      IntegerGene destino = (IntegerGene)ciudades[i+1];
      distTotal += tsp.distancia(origen.intValue(), destino.intValue());
    }
    // De la última ciudad en la ruta a la ciudad inicial
    IntegerGene ultima = (IntegerGene)ciudades[ciudades.length - 1];
    IntegerGene primera = (IntegerGene)ciudades[0];
    distTotal += tsp.distancia(ultima.intValue(), primera.intValue());
    return distTotal;

  }

}
