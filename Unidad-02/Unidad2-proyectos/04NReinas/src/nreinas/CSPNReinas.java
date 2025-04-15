package nreinas;

import satisfaccionderestricciones.CSP;
import satisfaccionderestricciones.Dominio;
import satisfaccionderestricciones.RestriccionDiferente;

/**
 *
 * @author Rafael Rivera-López
 */
public class CSPNReinas extends CSP{
  
  private int n;

  public CSPNReinas(int n) {
    this.n = n;
    this.configurarProblema();
  }
  
  @Override
  public void crearVariables() {
    for (int i = 0; i < n; i++) {
      this.addVariable("R" + i);
    }    
  }

  @Override
  public void crearDominios() {
    Object[] c = new Object[n];
    for (int i = 0; i < c.length; i++) {
      c[i] = i;
    }
    Dominio dominio = new Dominio(c);
    for (String var : this.getVariables()) {
      this.setDominio(var, dominio);
    }  
  }

  @Override
  public void crearRestricciones() {
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        String xI = this.getVariable(i);
        String xJ = this.getVariable(j);
        addRestriccion(new RestriccionDiferente(xI, xJ));
        addRestriccion(new RestriccionDiagonal(this,xI,xJ));
      }
    }
  }  
}
