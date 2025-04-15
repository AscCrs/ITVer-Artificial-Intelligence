package grafo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import util.Archivo;

/**
 *
 * @author Rafael Rivera-López
 */
public class Grafo {

  private HashMap<String, Nodo> nodos;
  private HashMap<String, Arco> arcos;

  public Grafo(String archivo) {
    crearGrafo(archivo);
  }

  private void crearGrafo(String archivo) {
    ArrayList<String> lineas = Archivo.leerArchivo(archivo);
    nodos = new HashMap();
    arcos = new HashMap();
    Iterator<String> lista = lineas.iterator();
    while (lista.hasNext()) {
      if (lista.next().equals("//ESTADOS")) {
        String linea = lista.next().trim();
        do {
          if (!linea.isEmpty()) {
            String[] datos = linea.split(",");
            String nombre = datos[0].trim();
            Nodo nodo = new Nodo(nombre);
            if(datos.length>1){
              nodo.x = Integer.parseInt(datos[1].trim());
              nodo.y = Integer.parseInt(datos[2].trim());
            }
            nodos.put(nombre, nodo);
          }
          linea = lista.next().trim();
        } while (!linea.equals("//ENLACES"));
        linea = lista.next().trim();
        do {
          if (!linea.isEmpty()) {
            String[] datos = linea.split(",");
            Nodo origen = nodos.get(datos[0].trim());
            Nodo destino = nodos.get(datos[1].trim());
            double costo = origen.distance(destino);
            if(datos.length==4){
              costo = Double.parseDouble(datos[3].trim());
            }
            Arco arco1 = new Arco(origen, destino, costo);
            arcos.put(origen.getNombre() + destino.getNombre(), arco1);
            if (datos[2].trim().equals("=")) {
              Arco arco2 = new Arco(destino, origen, costo);
              arcos.put(destino.getNombre() + origen.getNombre(), arco2);
            }
          }
          linea = lista.next().trim();
        } while (!linea.equals("//FIN"));
        break;
      }
    }
  }

  public HashMap<String, Nodo> getNodos() {
    return nodos;
  }

  public HashMap<String, Arco> getArcos() {
    return arcos;
  }

}
