package satisfaccionderestricciones;

import java.util.*;
/**
 * @author Rafael Rivera-Lopez
 */
public abstract class CSP {
  private List<String> variables;
  private HashMap<String, Dominio> dominios;
  private List<Restriccion> restricciones;
  private HashMap<String, List<Restriccion>> restriccionesDeVariable;

  public abstract void crearVariables();

  public abstract void crearDominios();

  public abstract void crearRestricciones();

  public final void configurarProblema() {
    variables = new ArrayList();
    dominios = new HashMap(); 
    restricciones = new ArrayList();
    restriccionesDeVariable = new HashMap();
    crearVariables();
    crearDominios();
    for (String var : variables) {
      restriccionesDeVariable.put(var, new ArrayList());
    }
    crearRestricciones();
  }

  public List<String> getVariables() {
    return variables;
  }

  public void addVariable(String variable){
    variables.add(variable);
  }

  public String getVariable(int k){
    return variables.get(k);
  }

  public Dominio getDominio(String var) {
    return dominios.get(var);
  }
  
  public Dominio getDominio(int k){
    return dominios.get(variables.get(k));
  }

  public void setDominio(String var, Dominio dominio) {
    dominios.put(var, dominio);
  }

  public List<Restriccion> getRestricciones() {
    return restricciones;
  }

  public List<Restriccion> getRestricciones(String variable) {
    return restriccionesDeVariable.get(variable);
  }

  public void addRestriccion(Restriccion restriccion) {
    restricciones.add(restriccion);
    for (String var : restriccion.getVariables()) {
      restriccionesDeVariable.get(var).add(restriccion);
    }
  }  
}
