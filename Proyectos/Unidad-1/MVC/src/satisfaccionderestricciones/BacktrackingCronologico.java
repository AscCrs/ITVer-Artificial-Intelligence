package satisfaccionderestricciones;

/**
 * @author Rafael Rivera-Lopez
 */
public class BacktrackingCronologico {

  private final CSP problema;

  public BacktrackingCronologico(CSP problema) {
    this.problema = problema;
  }

  public Estado buscarSolucion() {
    Estado asignacion = new Estado();
    return backtrackingRecursivo(0, asignacion);
  }

  private Estado backtrackingRecursivo(int k, Estado estado) {
    seleccionar(k, estado);
    System.out.println(estado);
    if (comprobar(k, estado)) {
      if (k == problema.getVariables().size() - 1) {
        System.out.println("EXITO!");
        return estado;
      } else {
        return backtrackingRecursivo(k + 1, estado);
      }
    } else {
      if (quedanValores(k, estado)) {
        System.out.println("Otro valor para k=" + k + " (" + problema.getVariable(k) + ")");
        return backtrackingRecursivo(k, estado);
      } else if (k == 0) {
        System.out.println("No hay solución!!");
        return null;
      } else {
        estado.eliminarAsignacion(problema.getVariable(k));
        System.out.println("Regresa a la variable " + (k - 1));
        return backtrackingRecursivo(k - 1, estado);
      }
    }
  }

  public void seleccionar(int k, Estado estado) {
    String key = problema.getVariable(k);
    Dominio dominio = problema.getDominio(k);
    Object value = estado.get(key);
    int lugar = 0;
    if (value != null) {
      lugar = dominio.indexOf(value) + 1;
    }
    if (lugar < dominio.size()) {
      estado.setAsignacion(key, dominio.get(lugar));
    } else {
      estado.setAsignacion(key, null);
    }
  }

  private boolean comprobar(int k, Estado estado) {
    if (estado.tieneAsignacion(problema.getVariable(k))) {
      for (int i = 0; i < k; i++) {
        String var = problema.getVariable(i);
        for (Restriccion rest : problema.getRestricciones(var)) {
          if (!rest.esSatisfecha(estado)) {
            return false;
          }
        }
      }
      return true;
    } else {
      return false;
    }
  }

  private boolean quedanValores(int k, Estado asignacion) {
    String var = problema.getVariable(k);
    Dominio dominio = problema.getDominio(k);
    if (asignacion.tieneAsignacion(var)) {
      Object valor = asignacion.getAsignacion(var);
      return dominio.indexOf(valor) < dominio.size() - 1;
    }
    return false;
  }
}
