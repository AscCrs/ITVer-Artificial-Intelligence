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
public class BusquedaPorProfundidadLimitada extends EstrategiaBusqueda {

    private final int profundidadMaxima;

    public BusquedaPorProfundidadLimitada(ProblemaBusqueda problema, int profundidadMaxima) {
        super(problema);
        this.profundidadMaxima = profundidadMaxima;
        this.nombreEstrategia = "Búsqueda por Profundidad Limitada con: " + profundidadMaxima;
    }

    private int calcularProfundidad(Estado estado) {
        int profundidad = 0;
        while (estado.getPredecesor() != null) {
            estado = estado.getPredecesor();
            profundidad++;
        }
        return profundidad;
    }

    @Override
    public void encolarSucesores(Estado estadoActual, ArrayList<Estado> sucesores) {
        int profundidadActual = calcularProfundidad(estadoActual);
        if (profundidadActual >= profundidadMaxima) return;

        for (Estado sucesor : sucesores) {
            if (!validar(sucesor)) continue;

            sucesor.setPredecesor(estadoActual);
            colaBusqueda.encolarAlInicio(sucesor);
            
        }
    }
}
