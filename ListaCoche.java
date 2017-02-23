package algoritmogenetico;

import java.util.ArrayList;

public class ListaCoche {

    
    private static ArrayList <Coche> listaCoches = new ArrayList<>();

    public static Coche get(int index) {
        return listaCoches.get(index);
    }

    public static void add(Coche e) {
        listaCoches.add(e);
    }

    public static void remove(Object o) {
        listaCoches.remove(o);
    }
     
    public static void reset(){
        listaCoches.clear();
    }
    
    public static void fitnessCoche (){
        for (Coche ferrari : listaCoches) {
           ferrari.fitnessCoche();
        }
    }
    
    public static void actualizarEstado(){
        for (Coche coche : listaCoches) {
            coche.estadoFuturo();
        }
        for (int i = 0; i < listaCoches.size(); i++) {
            if(listaCoches.get(i).getEliminar() == -1){
                listaCoches.remove(i);
                i--;
            }
            
        }
    }
}
