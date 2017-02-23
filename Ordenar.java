package algoritmogenetico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

class Ordenar {

    public static void ordenar(ArrayList<ArrayList<Integer>> pobSemaforo, ArrayList<Double> fitness) {
        ArrayList<Double> fitnessCopia = (ArrayList<Double>) fitness.clone();
        int salto, i;
        double aux;
        ArrayList<Integer> arrayAux = new ArrayList<>();
        boolean cambios;
        for (salto = fitnessCopia.size() / 2; salto != 0; salto /= 2) {
            cambios = true;
            while (cambios) { // Mientras se intercambie algún elemento
                cambios = false;
                for (i = salto; i < fitnessCopia.size(); i++) // se da una pasada
                {
                    if (fitnessCopia.get(i-salto) < fitnessCopia.get(i)) { // y si están desordenados
                        aux = fitnessCopia.get(i); // se reordenan
                        arrayAux = pobSemaforo.get(i);
                        fitnessCopia.remove(i);
                        pobSemaforo.remove(i);
                        fitnessCopia.add(i, fitnessCopia.get(i-salto));
                        pobSemaforo.add(i, pobSemaforo.get(i-salto));
                        fitnessCopia.remove(i-salto);
                        pobSemaforo.remove(i-salto);
                        fitnessCopia.add(i-salto, aux);
                        pobSemaforo.add(i-salto,arrayAux);
                        cambios = true; // y se marca como cambio.
                    }
                }
            }
        }
    }

    public static void ordenarC(ArrayList<ArrayList<Integer>> s1, ArrayList<ArrayList<Integer>> s2, ArrayList<ArrayList<Integer>> s3, ArrayList<ArrayList<Integer>> s4, ArrayList<Double> fitnessConjunto, ArrayList<Double> fitness, ArrayList<Double> coche) {

        int salto, i;
        double aux;
        double auxCoche;
        double auxFitness;
        
        ArrayList<Integer> s1Aux = new ArrayList<>();
        ArrayList<Integer> s2Aux = new ArrayList<>();
        ArrayList<Integer> s3Aux = new ArrayList<>();
        ArrayList<Integer> s4Aux = new ArrayList<>();
        
        int resta;
        
        boolean cambios;
        for (salto = fitnessConjunto.size() / 2; salto != 0; salto /= 2) {
            cambios = true;
            while (cambios) { // Mientras se intercambie algún elemento
                cambios = false;
                for (i = salto; i < fitnessConjunto.size(); i++) // se da una pasada
                {
                    if (fitnessConjunto.get(i-salto) < fitnessConjunto.get(i)) { // y si están desordenados
                        resta = i-salto;
                        aux = fitnessConjunto.get(i); // se reordenan
                        auxCoche = coche.get(i);
                        auxFitness = fitness.get(i);
                        s1Aux = s1.get(i);
                        s2Aux = s2.get(i);
                        s3Aux = s3.get(i);
                        s4Aux = s4.get(i);
                        
                        fitnessConjunto.remove(i);
                        coche.remove(i);
                        fitness.remove(i);
                        s1.remove(i);
                        s2.remove(i);
                        s3.remove(i);
                        s4.remove(i);
                        
                        fitnessConjunto.add(i, fitnessConjunto.get(resta));
                        coche.add(i, coche.get(resta));
                        fitness.add(i, fitness.get(resta));
                        s1.add(i, s1.get(resta));
                        s2.add(i, s2.get(resta));
                        s3.add(i, s3.get(resta));
                        s4.add(i, s4.get(resta));
                        
                        fitnessConjunto.remove(resta);
                        coche.remove(resta);
                        fitness.remove(resta);
                        s1.remove(resta);
                        s2.remove(resta);
                        s3.remove(resta);
                        s4.remove(resta);
                        
                        fitnessConjunto.add(resta, aux);
                        coche.add(resta, auxCoche);
                        fitness.add(resta, auxFitness);
                        s1.add(resta,s1Aux);
                        s2.add(resta,s2Aux);
                        s3.add(resta,s3Aux);
                        s4.add(resta,s4Aux);
                        
                        cambios = true; // y se marca como cambio.
                    }
                }
            }
        }
        
    }

}
