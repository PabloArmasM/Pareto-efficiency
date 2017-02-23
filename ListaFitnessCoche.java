package algoritmogenetico;

import java.util.ArrayList;

public class ListaFitnessCoche {

    private static ArrayList <Double> fitnessCoches = new ArrayList<>();

    public static void add(double e) {
        fitnessCoches.add(e);
    }

    static void imprime() {
        for (Double fitnessCoche : fitnessCoches) {
            System.out.println(fitnessCoche);
        }
    }

    static double calculaFitness() {
        double fitness = 0.0;
        double sumaCambios = 0.0;
        for (Double fitnes : fitnessCoches) {
            sumaCambios += fitnes;
        }
        fitness = sumaCambios/fitnessCoches.size();
        return fitness;
    }
    
    public static void clear() {
        fitnessCoches.clear();
    }
    
}
