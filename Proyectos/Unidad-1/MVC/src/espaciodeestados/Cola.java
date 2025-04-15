package espaciodeestados;

import java.util.ArrayList;

/**
 *
 * @author Rafael Rivera-López
 */
public class Cola extends ArrayList<Estado>{
  
  public void encolarAlInicio(Estado estado){  //LIFO
    this.add(0,estado);
  }
  
  public void encolarAlFinal(Estado estado){  //FIFO
    this.add(estado);
  }
  
  public void encolarConPrioridad(Estado estado, double costo){  
    for(int i=0;i<this.size();i++){
      double costoCombinado = this.get(i).getCostoCombinado();
      if(costoCombinado>costo){
        this.add(i, estado);
        return;
      }
    }
    this.add(estado);
  }
  
  public Estado desencolar(){
    if(!this.isEmpty()){
      return this.remove(0);
    }
    return null;
  }

}
