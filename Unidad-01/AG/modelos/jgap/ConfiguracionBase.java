package modelos.jgap;

import org.jgap.*;
import org.jgap.event.EventManager;
import org.jgap.impl.*;

public abstract class ConfiguracionBase extends Configuration {

  protected int tamPoblacion;
  protected int longIndividuo;
  protected Problema problema;
  
  public ConfiguracionBase(Problema problema, 
                           int tamPoblacion,
                           int longIndividuo) {
    this.tamPoblacion = tamPoblacion;
    this.longIndividuo = longIndividuo;
    this.problema = problema;
    configurarAG();
  }
  
  public final void configurarAG() {
    try {
      setBreeder(new GABreeder());  
      setRandomGenerator(new SeededRandomGenerator(System.currentTimeMillis()));
      setEventManager(new EventManager());
      setPopulationSize(tamPoblacion);
      setFitnessFunction(setFuncionAptitud());
      setFitnessEvaluator(setTipoEvaluador());
      Gene[] genes = setGen(longIndividuo);
      IChromosome individuo = new Chromosome(this, genes);
      setSampleChromosome(individuo);
      addGeneticOperator(setOperadorDeCruzamiento());
      addGeneticOperator(setOperadorDeMutacion());      
      removeNaturalSelectors(true);                
      addNaturalSelector(setOperadorDeSeleccion(), true);
      setPreservFittestIndividual(setElitismo());
    } catch (InvalidConfigurationException ex) {
      System.out.println("Error" + ex);
      System.exit(0);
    }
  }

  public abstract FitnessFunction setFuncionAptitud();

  public abstract FitnessEvaluator setTipoEvaluador();
  
  public abstract Gene[] setGen(int longIndividuo) 
    throws InvalidConfigurationException;  
  
  public abstract BaseGeneticOperator setOperadorDeCruzamiento() 
    throws InvalidConfigurationException;

  public abstract BaseGeneticOperator setOperadorDeMutacion() 
    throws InvalidConfigurationException;

  public abstract NaturalSelector setOperadorDeSeleccion() 
    throws InvalidConfigurationException;

  public abstract boolean setElitismo() ;
}
