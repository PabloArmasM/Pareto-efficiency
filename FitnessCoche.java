package algoritmogenetico;

import java.util.ArrayList;

public class FitnessCoche {

    static double calculaFitness(ArrayList<Integer> movimiento) {
        int estado = 0;
        double sumaCambios = 0.0;
        for (Integer movimiento1 : movimiento) {
            if(estado != movimiento1){
                sumaCambios++;
                estado = movimiento1;
            }
        }
            return 1-(sumaCambios/24);
    }

}
