package problemasat;

import org.jgap.FitnessFunction;
import org.jgap.IChromosome;
import org.jgap.impl.BooleanGene;

/**
 *
 * @author Rafael Rivera-López
 */
public class AptitudSAT extends FitnessFunction {

  private final ExpresionSAT problema;

  public AptitudSAT(ExpresionSAT problema) {
    this.problema = problema;
  }

  @Override
  protected double evaluate(IChromosome ic) {
    boolean[] valores = new boolean[problema.getnLiterales()];
    for (int i = 0; i < valores.length; i++) {
      BooleanGene gene = (BooleanGene) ic.getGene(i);
      valores[i] = gene.booleanValue();
    }
    return problema.evaluarFormulaSAT(valores);
  }

}
