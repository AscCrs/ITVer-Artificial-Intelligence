package nreinas;

import satisfaccionderestricciones.Estado;
import satisfaccionderestricciones.RestriccionDiferente;

/**
 *
 * @author Rafael Rivera-López
 */
public class RestriccionDiagonal extends RestriccionDiferente {

  private final CSPNReinas problema;

  public RestriccionDiagonal(CSPNReinas problema, String var1, String var2) {
    super(var1, var2);
    this.problema = problema;
  }

  @Override
  public boolean esSatisfecha(Estado solucion) {
    int lugar1 = problema.getVariables().indexOf(variable1);
    int lugar2 = problema.getVariables().indexOf(variable2);
    Integer valor1 = (Integer) solucion.getAsignacion(variable1);
    Integer valor2 = (Integer) solucion.getAsignacion(variable2);
    return valor1 == null || valor2 == null
      || Math.abs(lugar1 - lugar2) != Math.abs(valor1 - valor2);
  }

}
