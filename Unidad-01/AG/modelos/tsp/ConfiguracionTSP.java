package modelos.tsp;

import modelos.jgap.ConfiguracionBase;
import modelos.jgap.Problema;
import java.util.ArrayList;
import java.util.Collections;
import org.jgap.BaseGeneticOperator;
import org.jgap.Chromosome;
import org.jgap.FitnessEvaluator;
import org.jgap.FitnessFunction;
import org.jgap.Gene;
import org.jgap.IChromosome;
import org.jgap.InvalidConfigurationException;
import org.jgap.NaturalSelector;
import org.jgap.impl.IntegerGene;
import org.jgap.impl.TournamentSelector;

/**
 *
 * @author rafael
 */
public class ConfiguracionTSP extends ConfiguracionBase {

  public ConfiguracionTSP(Problema problema, int tamPoblacion, int longIndividuo) {
    super(problema, tamPoblacion, longIndividuo);
  }

  @Override
  public FitnessFunction setFuncionAptitud() {
    return new AptitudTSP((GrafoTSP) problema);
  }

  @Override
  public FitnessEvaluator setTipoEvaluador() {
    return new EvaluadorTSP();
  }

  @Override
  public Gene[] setGen(int longIndividuo) throws InvalidConfigurationException {
    Gene[] genes = new Gene[longIndividuo];
    // Create a sample chromosome from consecutive gene numbers
    for (int i = 0; i < genes.length; i++) {
      genes[i] = new IntegerGene(this, 0, longIndividuo - 1);
      genes[i].setAllele(i);
    }
    return genes;
  }

  @Override
  public BaseGeneticOperator setOperadorDeCruzamiento() throws InvalidConfigurationException {
    return new TravelingSalesmanHeuristicCrossover(this, (GrafoTSP) problema);
  }

  @Override
  public BaseGeneticOperator setOperadorDeMutacion() throws InvalidConfigurationException {
    return new SegmentSwappingMutation(this, 20, (GrafoTSP)problema);
  }

  @Override
  public NaturalSelector setOperadorDeSeleccion() throws InvalidConfigurationException {
    return new TournamentSelector(this, tamPoblacion, 0.1);
  }

  @Override
  public boolean setElitismo() {
    return true;
  }

  public IChromosome[] crearPoblacionInicial(GrafoTSP tsp) throws InvalidConfigurationException {
    IChromosome[] pop = new IChromosome[tamPoblacion];
    ArrayList<Integer> nodos = new ArrayList();
    Gene[] genes = this.getSampleChromosome().getGenes();
    for (int k = 0; k < longIndividuo; k++) {
      nodos.add(k);
    }
    for (int i = 0; i < pop.length; i++) {
      Collections.shuffle(nodos);
      Gene[] nuevos = new Gene[genes.length];
      for (int k = 0; k < genes.length; k++) {
        nuevos[k] = genes[nodos.get(k)];
        nuevos[k].setAllele(genes[nodos.get(k)].getAllele());
      }
//      for(Gene x:nuevos){
//        System.out.print(x.getAllele()+",");
//      }
//      System.out.println("");
      pop[i] = new Chromosome(this, nuevos);
    }
    return pop;
  }
}
