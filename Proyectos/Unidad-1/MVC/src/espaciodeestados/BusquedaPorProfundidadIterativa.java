/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espaciodeestados;
import java.util.ArrayList;

/**
 *
 * @author crist
 */
public class BusquedaPorProfundidadIterativa extends EstrategiaBusqueda {

    private final int limiteMaximo;
    private Estado estadoFinal = null;

    public BusquedaPorProfundidadIterativa(ProblemaBusqueda problema, int limiteMaximo) {
        super(problema);
        this.limiteMaximo = limiteMaximo;
        this.nombreEstrategia = "Búsqueda por Profundidad Iterativa hasta: " + limiteMaximo;
    }

    @Override
    public void encolarSucesores(Estado estadoActual, ArrayList<Estado> sucesores) {
        // Este método no es usado directamente aquí.
    }

    @Override
    public Estado realizarBusqueda(boolean repeticion) {
        System.out.println(nombreEstrategia);

        for (int limite = 0; limite <= limiteMaximo; limite++) {
            System.out.println("Buscando con límite de profundidad: " + limite);

            BusquedaPorProfundidadLimitada limitada = new BusquedaPorProfundidadLimitada(problema, limite);
            Estado resultado = limitada.realizarBusqueda(repeticion);

            if (resultado != null) {
                this.estadoFinal = resultado;
                this.arbol.clear();
                ArrayList<Estado> ruta = limitada.arbol.getRuta(resultado);
                for (Estado e : ruta) {
                    this.arbol.insertar(e);
                }

                return resultado;
            }
        }

        return null;
    }

    @Override
    public String getRuta(Estado estado) {
        return super.getRuta(estado);
    }
}
