package satisfaccionderestricciones;

import java.util.*;
/**
 * @author Rafael Rivera-López
 */
public class Estado extends HashMap<String, Object> {

  public List<String> getVariables() {
    String[] variables = (String[]) this.keySet().toArray();
    return Arrays.asList(variables);
  }

  public Object getAsignacion(String var) {
    return this.get(var);
  }

  public void setAsignacion(String key, Object valor) {
     this.put(key, valor);
  }
  
  public void eliminarAsignacion(String var) {
    this.remove(var);
  }

  public boolean tieneAsignacion(String var) {
    return this.get(var)!=null;
  }

  @Override
  public String toString() {
    Iterator<String> iterador = this.keySet().iterator();
    String result = "{";
    while (iterador.hasNext()) {
      String key = iterador.next();
      Object valor = this.get(key);
      if (valor != null) {
        result += key + " = " + valor + ", ";
      }
    }
    result += "}";
    return result.replace(", }", "}");
  }
}
