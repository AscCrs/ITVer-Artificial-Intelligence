package satisfaccionderestricciones;

import java.util.List;

/**
 * @author Rafael Rivera-Lopez
 */
public interface Restriccion {

  List<String> getVariables();

  boolean esSatisfecha(Estado asignacion);
}


