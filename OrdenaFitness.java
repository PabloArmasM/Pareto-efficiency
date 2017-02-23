package algoritmogenetico;

import java.util.ArrayList;

public class OrdenaFitness {

    public static void ordenar(ArrayList<Double> fitness) {
        int salto, i;
        double aux;
        ArrayList<Integer> arrayAux = new ArrayList<>();
        boolean cambios;
        for (salto = fitness.size() / 2; salto != 0; salto /= 2) {
            cambios = true;
            while (cambios) { // Mientras se intercambie algún elemento
                cambios = false;
                for (i = salto; i < fitness.size(); i++) // se da una pasada
                {
                    if (fitness.get(i - salto) < fitness.get(i)) { // y si están desordenados
                        aux = fitness.get(i); // se reordenan
                        fitness.remove(i);
                        fitness.add(i, fitness.get(i - salto));
                        fitness.remove(i - salto);
                        fitness.add(i - salto, aux);
                        cambios = true; // y se marca como cambio.
                    }
                }
            }
        }
    }
    
     public static void ordenarTriple(ArrayList<Double> fitnessConjunto, ArrayList<Double> fitnessCarretera, ArrayList<Double> fitnessCoche) {
        int salto, i;
        double aux;
        double auxCarretera;
        double auxCoche;
        ArrayList<Integer> arrayAux = new ArrayList<>();
        boolean cambios;
        for (salto = fitnessConjunto.size() / 2; salto != 0; salto /= 2) {
            cambios = true;
            while (cambios) { // Mientras se intercambie algún elemento
                cambios = false;
                for (i = salto; i < fitnessConjunto.size(); i++) // se da una pasada
                {
                    if (fitnessConjunto.get(i - salto) < fitnessConjunto.get(i)) { // y si están desordenados
                        aux = fitnessConjunto.get(i); // se reordenan
                        auxCarretera = fitnessCarretera.get(i);
                        auxCoche = fitnessCoche.get(i);
                        
                        fitnessConjunto.remove(i);
                        fitnessCarretera.remove(i);
                        fitnessCoche.remove(i);
                        
                        fitnessConjunto.add(i, fitnessConjunto.get(i - salto));
                        fitnessCarretera.add(i, fitnessCarretera.get(i - salto));
                        fitnessCoche.add(i, fitnessCoche.get(i - salto));
                        
                        fitnessConjunto.remove(i - salto);
                        fitnessCarretera.remove(i - salto);
                        fitnessCoche.remove(i - salto);
                        
                        fitnessConjunto.add(i - salto, aux);
                        fitnessCarretera.add(i - salto, auxCarretera);
                        fitnessCoche.add(i - salto, auxCoche);
                        
                        cambios = true; // y se marca como cambio.
                    }
                }
            }
        }
    }
}
