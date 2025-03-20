/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package problemasat;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jgap.Gene;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.InvalidConfigurationException;

/**
 *
 * @author alemr
 */
public class GraficoSAT {
   public static void main(String[] args) {
        // Archivo de salida donde se guardarán los datos
        String nombreArchivo = "resultados.csv";

        // Se crea un bloque try-with-resources para asegurar que el archivo se cierre correctamente
        try (FileWriter fw = new FileWriter(nombreArchivo);
             PrintWriter pw = new PrintWriter(fw)) {

            // Encabezado de columnas para el archivo CSV
            pw.println("Generacion,Mejor,Promedio,Peor");

            // Lee el archivo .cnf
            InputStream flujo = ProblemaSAT.class.getResourceAsStream("/satlib/uf200-01.cnf");
            ExpresionSAT sat = new ExpresionSAT(flujo);
            System.out.println("Literales = " + sat.getnLiterales());
            System.out.println("Cláusulas = " + sat.getnClausulas());
            System.out.println(sat);

            // Configuración del Algoritmo Genético
            int tamIndividuo = sat.getnLiterales();
            int tamPoblacion = (int)(tamIndividuo * 1.5);
            ConfiguracionSAT conf = new ConfiguracionSAT(sat, tamPoblacion, tamIndividuo);

            try {
                // Genera la población inicial
                Genotype poblacion = Genotype.randomInitialGenotype(conf);

                // --- GENERACIÓN 0 ---
                List<IChromosome> pop = poblacion.getPopulation().getChromosomes();
                double mejor = Double.NEGATIVE_INFINITY;
                double peor  = Double.POSITIVE_INFINITY;
                double suma  = 0.0;

                // Calcula mejor, peor y promedio en la población inicial
                for (IChromosome ind : pop) {
                    double valor = ind.getFitnessValue();
                    if (valor > mejor) mejor = valor;
                    if (valor < peor)  peor  = valor;
                    suma += valor;
                }
                double promedio = suma / pop.size();

                // Guarda la información de la generación 0 en el CSV
                pw.println("0," + mejor + "," + promedio + "," + peor);

                // Evoluciona hasta 10,000 generaciones o hasta encontrar la solución
                for (int i = 1; i < 10000; i++) {
                    poblacion.evolve();
                    pop = poblacion.getPopulation().getChromosomes();

                    // Recalcula mejor, peor y promedio
                    mejor = Double.NEGATIVE_INFINITY;
                    peor  = Double.POSITIVE_INFINITY;
                    suma  = 0.0;
                    for (IChromosome ind : pop) {
                        double valor = ind.getFitnessValue();
                        if (valor > mejor) mejor = valor;
                        if (valor < peor)  peor  = valor;
                        suma += valor;
                    }
                    promedio = suma / pop.size();

                    // Guarda en el CSV
                    pw.println(i + "," + mejor + "," + promedio + "," + peor);

                    // Comprueba si se satisfacen todas las cláusulas
                    if (mejor == sat.getnClausulas()) {
                        IChromosome mejorIndividuo = poblacion.getFittestChromosome();
                        Gene[] genes = mejorIndividuo.getGenes();
                        StringBuilder salida = new StringBuilder("[");
                        for (Gene gene : genes) {
                            salida.append(gene.getAllele().toString().equals("true") ? "T," : "F,");
                        }
                        // Reemplaza la última coma
                        String sol = salida.toString().replaceAll(",$", "") + "]";
                        
                        System.out.println("¡Se encontró una solución SAT!");
                        System.out.println("Solución: " + sol);
                        
                        // Cierra el programa (el archivo se cerrará automáticamente por el try-with-resources)
                        return;
                    }
                }

            } catch (InvalidConfigurationException ex) {
                System.out.println("Error de configuración del AG: " + ex);
                System.exit(-1);
            }

        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo CSV: " + e.getMessage());
        }
    }

}
