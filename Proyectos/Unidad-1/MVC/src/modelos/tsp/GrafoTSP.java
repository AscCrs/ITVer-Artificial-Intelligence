package modelos.tsp;

import java.io.InputStream;
import java.util.ArrayList;
import modelos.jgap.Problema;
import util.Archivo;

/**
 *
 * @author rafael
 */
public class GrafoTSP extends Problema{

  private String nombre;
  private String comentario;
  private String tipo;
  private int dimension;
  private String costoArco;
  private Nodo[] ciudades;
  private int[] ruta; // RUTA CREADA
  
  public GrafoTSP(InputStream archivo) {
    super(archivo);
  }  
  
  public GrafoTSP(){
    this(null);
  }

  @Override
  public final void crearProblema(InputStream archivo) {
    if(archivo == null){
      return;
    }
    ArrayList<String> lineas = Archivo.leerArchivo(archivo);
    int i = 0;
    boolean cabecera = true;
    while (cabecera) {
      String linea = lineas.get(i++).trim();
      String[] tokens = linea.split(":");
      String token = tokens[0].trim();
      switch (token) {
        case "NAME":
          nombre = tokens[1].trim();
          break;
        case "COMMENT":
          comentario = tokens[1].trim();
          break;
        case "TYPE":
          tipo = tokens[1].trim();
          break;
        case "DIMENSION":
          dimension = Integer.parseInt(tokens[1].trim());
          ciudades = new Nodo[dimension];
          break;
        case "EDGE_WEIGHT_TYPE":
          costoArco = tokens[1].trim();
          break;
        case "NODE_COORD_SECTION":
          cabecera = false;
      }
    }
    for (int j = 0; j < dimension ; j++) {
      String[] tokens = lineas.get(j+i).trim().split("\\s+");
      int numNodo = Integer.parseInt(tokens[0]);
      double x = Double.parseDouble(tokens[1]);
      double y = Double.parseDouble(tokens[2]);
      ciudades[j] = new Nodo(numNodo, x, y); 
    }
  }

  public double distancia(int a, int b) {
    Nodo origen = this.getCiudades()[a];
    Nodo destino = this.getCiudades()[b];
    double x2 = Math.pow(origen.getX() - destino.getX(), 2);
    double y2 = Math.pow(origen.getY() - destino.getY(), 2);
    return Math.sqrt(x2 + y2);
  }

  /**
   * @return the nombre
   */
  public String getNombre() {
    return nombre;
  }

  /**
   * @param nombre the nombre to set
   */
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  /**
   * @return the comentario
   */
  public String getComentario() {
    return comentario;
  }

  /**
   * @param comentario the comentario to set
   */
  public void setComentario(String comentario) {
    this.comentario = comentario;
  }

  /**
   * @return the tipo
   */
  public String getTipo() {
    return tipo;
  }

  /**
   * @param tipo the tipo to set
   */
  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  /**
   * @return the dimension
   */
  public int getDimension() {
    return dimension;
  }

  /**
   * @param dimension the dimension to set
   */
  public void setDimension(int dimension) {
    this.dimension = dimension;
  }

  /**
   * @return the costoArco
   */
  public String getCostoArco() {
    return costoArco;
  }

  /**
   * @param costoArco the costoArco to set
   */
  public void setCostoArco(String costoArco) {
    this.costoArco = costoArco;
  }

  /**
   * @return the ciudades
   */
  public Nodo[] getCiudades() {
    return ciudades;
  }

  /**
   * @param ciudades the ciudades to set
   */
  public void setCiudades(Nodo[] ciudades) {
    this.ciudades = ciudades;
  }

  public int[] getRuta() {
    return ruta;
  }

  public void setRuta(int[] ruta) {
    this.ruta = ruta;
  }

}
