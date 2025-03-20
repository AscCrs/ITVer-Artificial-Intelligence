package modelos.jgap;

import java.io.*;

/**
 *
 * @author rafael
 */
public abstract class Problema implements Serializable{
  

  public Problema(String archivo){
    try {
      crearProblema(new FileInputStream(archivo));
    } catch (FileNotFoundException ex) {
      System.out.println("Error de archvo "+ex);
      System.exit(-1);
    }
  }
  
  public Problema(InputStream archivo){
    crearProblema(archivo);  
  }
    
  public abstract void crearProblema(InputStream archivo);
 
}
