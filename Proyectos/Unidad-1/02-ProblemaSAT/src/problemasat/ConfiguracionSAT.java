package problemasat;

import modelos.jgap.ConfiguracionBase;
import modelos.jgap.Problema;
import org.jgap.BaseGeneticOperator;
import org.jgap.DefaultFitnessEvaluator;
import org.jgap.FitnessEvaluator;
import org.jgap.FitnessFunction;
import org.jgap.Gene;
import org.jgap.InvalidConfigurationException;
import org.jgap.NaturalSelector;
import org.jgap.impl.BooleanGene;
import org.jgap.impl.CrossoverOperator;
import org.jgap.impl.MutationOperator;
import org.jgap.impl.TournamentSelector;

public class ConfiguracionSAT extends ConfiguracionBase{

  public ConfiguracionSAT(Problema problema, int tamPoblacion, int longIndividuo) {
    super(problema, tamPoblacion, longIndividuo);
  }
  
  @Override
  public FitnessFunction setFuncionAptitud() {
    return new AptitudSAT((ExpresionSAT)problema);   //Número de claúsulas verdaderas
  }

  @Override
  public FitnessEvaluator setTipoEvaluador() {
    return new DefaultFitnessEvaluator();   //Maximizacion
  }

  @Override
  public Gene[] setGen(int longIndividuo) throws InvalidConfigurationException {
    Gene[] genes = new Gene[longIndividuo];
    for (int i = 0; i < genes.length; i++) {
      genes[i] = new BooleanGene(this);
    }
    return genes;
  }

  @Override
  public BaseGeneticOperator setOperadorDeCruzamiento() throws InvalidConfigurationException {
    return new CrossoverOperator(this, .9); //80% de la población
  }

  @Override
  public BaseGeneticOperator setOperadorDeMutacion() throws InvalidConfigurationException {
    return new MutationOperator(this, 20);  //10% de la población (1/10)
   }

  @Override
  public NaturalSelector setOperadorDeSeleccion() throws InvalidConfigurationException {
    return new TournamentSelector(this, tamPoblacion, 0.1); 
  }

  @Override
  public boolean setElitismo() {
    return true;
  }
  
}
