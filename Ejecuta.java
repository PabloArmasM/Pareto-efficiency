package algoritmogenetico;

import java.util.ArrayList;

public class Ejecuta {

    public static void ejecuta() {
        ArrayList<ArrayList<Integer>> pobSemaforo1 = new ArrayList<>();
        ArrayList<ArrayList<Integer>> pobSemaforo2 = new ArrayList<>();
        ArrayList<ArrayList<Integer>> pobSemaforo3 = new ArrayList<>();
        ArrayList<ArrayList<Integer>> pobSemaforo4 = new ArrayList<>();

        ArrayList<Double> fitness = new ArrayList<>();
        ArrayList<Double> fitnessCoche = new ArrayList<>();
        ArrayList<Double> fitnessConjunto = new ArrayList<>();

        ArrayList<Double> mejorFitness = new ArrayList<>();
        ArrayList<Double> mejorFitnessCoche = new ArrayList<>();
        ArrayList<Double> mejorFitnessConjunto = new ArrayList<>();

        ArrayList<Double> fitnessFinal = new ArrayList<>();
        ArrayList<Double> fitnessCocheFinal = new ArrayList<>();
        ArrayList<Double> fitnessConjuntoFinal = new ArrayList<>();

        Double fitCarretera;
        Double fitCoche;

        ArrayList<ArrayList<Double>> fronteraPareto = new ArrayList<>();

        

       

        for (int l = 0; l < 200; l++) {
            
            System.out.println("GENERACION NUEVA " + l);
        pobSemaforo1.clear();
        pobSemaforo2.clear();
        pobSemaforo3.clear();
        pobSemaforo4.clear();
        for (int i = 0; i < 100; i++) {
            pobSemaforo1.add(GeneraCodificacionSemaforo.generaCodificacionSemaforo());
            pobSemaforo2.add(GeneraCodificacionSemaforo.generaCodificacionSemaforo());
            pobSemaforo3.add(GeneraCodificacionSemaforo.generaCodificacionSemaforo());
            pobSemaforo4.add(GeneraCodificacionSemaforo.generaCodificacionSemaforo());
        }
            
            Semaforos.setPoblacion(1, pobSemaforo1);
            Semaforos.setPoblacion(2, pobSemaforo2);
            Semaforos.setPoblacion(3, pobSemaforo3);
            Semaforos.setPoblacion(4, pobSemaforo4);
            for (int k = 0; k < 40; k++) {


                for (int i = 0; i < pobSemaforo1.size(); i++) {
                    Semaforos.setSemaforo1(pobSemaforo1.get(i));
                    Semaforos.setSemaforo2(pobSemaforo2.get(i));
                    Semaforos.setSemaforo3(pobSemaforo4.get(i));
                    Semaforos.setSemaforo4(pobSemaforo4.get(i));
                    for (int j = 0; j < 200; j++) {
                        Carretera.actualizaEstados();
                        ListaCoche.actualizarEstado();
                        Tiempo.aumentarTiempo();
                    }
                    ListaCoche.fitnessCoche();
                    Carretera.setCarretera(Carretera.iniciliazar());
                    ListaCoche.reset();

                    fitCoche = ListaFitnessCoche.calculaFitness();
                    fitCarretera = Fitness.resultadoFitness();

                    fitnessCoche.add(fitCoche);
                    ListaFitnessCoche.clear();
                    fitness.add(fitCarretera);
                    fitnessConjunto.add(((0.3 * fitCoche)) + (0.7 * fitCarretera));
                    
                    CeldaEntrada.reset();
                    CeldaSalida.reset();

                    Tiempo.reset();
                }
               
                Ordenar.ordenarC(pobSemaforo1, pobSemaforo2, pobSemaforo3, pobSemaforo4, fitnessConjunto, fitness, fitnessCoche);

                mejorFitness.add(fitness.get(0));
                mejorFitnessCoche.add(fitnessCoche.get(0));
                mejorFitnessConjunto.add(fitnessConjunto.get(0));

                    pobSemaforo1 = Criba.criba(pobSemaforo1, 60);
                    pobSemaforo2 = Criba.criba(pobSemaforo2, 60);
                    pobSemaforo3 = Criba.criba(pobSemaforo3, 60);
                    pobSemaforo4 = Criba.criba(pobSemaforo4, 60);
                    pobSemaforo1 = Cruce.cruce(pobSemaforo1, 2, 2, fitnessConjunto);
                    pobSemaforo2 = Cruce.cruce(pobSemaforo2, 2, 2, fitnessConjunto);
                    pobSemaforo3 = Cruce.cruce(pobSemaforo3, 2, 2, fitnessConjunto);
                    pobSemaforo4 = Cruce.cruce(pobSemaforo4, 2, 2, fitnessConjunto);

                 pobSemaforo1 = Mutacion.mutacion(pobSemaforo1, 0.05);
                 pobSemaforo2 = Mutacion.mutacion(pobSemaforo2, 0.05);
                 pobSemaforo3 = Mutacion.mutacion(pobSemaforo3, 0.05);
                 pobSemaforo4 = Mutacion.mutacion(pobSemaforo4, 0.05);

                fitness.clear();
                fitnessCoche.clear();
                fitnessConjunto.clear();
            }
            OrdenaFitness.ordenarTriple(mejorFitnessConjunto, mejorFitness, mejorFitnessCoche);
            
            fitnessConjuntoFinal.add(mejorFitnessConjunto.get(0));
            fitnessFinal.add(mejorFitness.get(0));
            fitnessCocheFinal.add(mejorFitnessCoche.get(0));
            
            mejorFitness.clear();
            mejorFitnessConjunto.clear();
            mejorFitnessCoche.clear();
        }
        Semaforos.setSemaforo1(pobSemaforo1.get(0));
        Semaforos.setSemaforo2(pobSemaforo2.get(0));
        Semaforos.setSemaforo3(pobSemaforo4.get(0));
        Semaforos.setSemaforo4(pobSemaforo4.get(0));

        for (int i = 0; i < 12; i++) {
            Imprime.imprime();
            System.out.println("----------------Iteracion: " + i + "-----------------------");
            Carretera.actualizaEstados();
            Tiempo.aumentarTiempo();
        }

        System.out.println(CeldaEntrada.getCantidadEntrada());
        System.out.println(CeldaSalida.getCochesFuera());

        Grafica grafica = new Grafica(fitnessConjuntoFinal);
        Grafica pareto = new Grafica(fitnessFinal, fitnessCocheFinal, fitnessConjuntoFinal);
        grafica.execute();
        pareto.execute();
    }
}
