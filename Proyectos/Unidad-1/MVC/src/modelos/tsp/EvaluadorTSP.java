package modelos.tsp;

import org.jgap.FitnessEvaluator;
import org.jgap.IChromosome;
import org.jgap.util.ICloneable;

/**
 * An implementation of a fitness evaluator. This implementation is straight
 * forward: a lower fitness value is seen as fitter.
 *
 * @author Aaron Foltz
 */
public class EvaluadorTSP implements FitnessEvaluator, ICloneable, Comparable {

  @Override
  public Object clone() {
    return new EvaluadorTSP();
  }

  @Override
  public int compareTo(Object otro) {
    if (otro.getClass().equals(getClass())) {
      return 0;
    } else {
      return getClass().getName().compareTo(otro.getClass().getName());
    }
  }

  @Override
  public boolean isFitter(final double fitness1,final double fitness2) {
    return fitness1 < fitness2;
  }

  @Override
  public boolean isFitter(IChromosome a_chrom1, IChromosome a_chrom2) {
    return isFitter(a_chrom1.getFitnessValue(), a_chrom2.getFitnessValue());
  }
}