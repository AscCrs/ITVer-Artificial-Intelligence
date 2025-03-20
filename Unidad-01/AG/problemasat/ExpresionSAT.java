package problemasat;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import modelos.jgap.Problema;

import util.Archivo;

/**
 *
 * @author rafael
 */
public class ExpresionSAT extends Problema{

  private int nLiterales;     // Número de variables lógicas
  private int nClausulas;     // Número de claúsulas
  private int[][] clausulas;  // Arreglo de claúsulas

  public ExpresionSAT(InputStream archivo) {
    super(archivo);
  }
  
  public ExpresionSAT(String archivo) {
    super(archivo);
  }
  
  //crearFormulaSAT
  
  
  @Override
  public final void crearProblema(InputStream flujo) {
    ArrayList<String> lineas = Archivo.leerArchivo(flujo);
    int i = 0;
    for (String linea : lineas) {
      linea = linea.trim();
      switch (linea.charAt(0)) {
        case '%' -> {
          // Fin de archivo
          return;
        }
        case 'c' -> {
          // Comentario
          continue;
        }
        case 'p' ->  {
          // Datos del problema
          String[] tokens = linea.split("\\s+");
          nLiterales = Integer.parseInt(tokens[2]);
          nClausulas = Integer.parseInt(tokens[3]);
          clausulas = new int[nClausulas][];
        }
        default -> {
          // Cláusula
          String[] tokens = linea.split("\\s+");
          int[] clausula = new int[tokens.length-1];
          for (int j = 0; j < clausula.length; j++) {
            clausula[j] = Integer.parseInt(tokens[j]);
          }
          clausulas[i++] = clausula;
        }
      }
    }
   
  }

  public int evaluarFormulaSAT(boolean[] interpretacion) {
    int cSAT = 0;
    for (int[] clausula : clausulas) {
      boolean clausulaVerdadera = false;
      for (int j = 0; j < clausula.length; j++) {
        int literal = clausula[j];
        boolean valorAsignado = interpretacion[Math.abs(literal)-1];
        if(valorAsignado){
          if(literal>0){
            clausulaVerdadera=true;
            break;
          }  
        } else {
          if(literal<0){
            clausulaVerdadera=true;
            break;
          }
        }
      }
      if (clausulaVerdadera) {
        cSAT++;
      }
    }
    return cSAT;
  }
  
  @Override
  public String toString(){
    String fnc="";
    for (int[] clausula : clausulas) {
      fnc+=Arrays.toString(clausula).replace(" ","");
    }    
    return fnc;    
  }
  
  public void generarTabla(boolean comprobar) {
    int variables = this.nLiterales;
    int total = (int) Math.pow(2, variables);
    System.out.println("Número de intentos = "+total);
    for (int fila = 0; fila < total; fila++) {
      boolean[] valores = new boolean[variables];
      for (int v = 0; v < variables; v++) {
        int particion = total / (int) (Math.pow(2, v + 1));
        int k = fila / particion % 2;
        valores[v] = k == 0;
      }
      int ncs = evaluarFormulaSAT(valores);
      System.out.print(Arrays.toString(valores).replace("true","T").replace("false","F")+" "+ncs);
      System.out.println(ncs == this.nClausulas?" SAT":" INSAT");
      if(comprobar && ncs == this.nClausulas){
        System.out.println((fila+1)+" Intentos");
        return;
      }
    }
  }
  
  public void generarTabla(){
    generarTabla(false);
  }


  /**
   * @return the nLiterales
   */
  public int getnLiterales() {
    return nLiterales;
  }

  /**
   * @param nLiterales the nLiterales to set
   */
  public void setnLiterales(int nLiterales) {
    this.nLiterales = nLiterales;
  }

  /**
   * @return the nClausulas
   */
  public int getnClausulas() {
    return nClausulas;
  }

  /**
   * @param nClausulas the nClausulas to set
   */
  public void setnClausulas(int nClausulas) {
    this.nClausulas = nClausulas;
  }

  /**
   * @return the clausulas
   */
  public int[][] getClausulas() {
    return clausulas;
  }

  /**
   * @param clausulas the clausulas to set
   */
  public void setClausulas(int[][] clausulas) {
    this.clausulas = clausulas;
  }

}
