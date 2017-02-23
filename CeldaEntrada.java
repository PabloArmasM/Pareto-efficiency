package algoritmogenetico;

public class CeldaEntrada extends Celda{
    
    private static double cantidadEntrada;
    private static int colaConjunto;
    private int cola;
    
    
    public CeldaEntrada(int i, int j, int direccion) {
        super(i, j, direccion);
        cola = 0;
    }

    public static double getCantidadEntrada() {
        return cantidadEntrada;
    }


    public int getCola() {
        return cola;
    }

    public static int getColaConjunto(){
        return colaConjunto;
    }
    
    public void aumentarEntrada(){
        cantidadEntrada++;
    }

    public Celda estadoFuturo() {
        boolean estadoFuturo = estado;
        if(Tiempo.getTiempo()%5==0){
            this.cola++;
            colaConjunto++;
            aumentarEntrada();
        }
        if(this.isEstado()){
            if(!Carretera.getEstadoSiguiente(this.getPosicion(),this.getDireccion())){
                if(cola > 0){
                    ListaCoche.add(new Coche(this, direccion));
                    cola--;
                    colaConjunto--;
                    estadoFuturo = true;
                }if(cola == 0)
                    estadoFuturo = false;
            }else
                estadoFuturo = estado;
        }else{
            if(cola > 0){
                cola--;
                colaConjunto--;
                ListaCoche.add(new Coche(this, direccion));
                estadoFuturo = true;
            }else
                estadoFuturo = estado;
        }
        CeldaEntrada celdaFutura = new CeldaEntrada(posicion[0], posicion[1], direccion);
        celdaFutura.setEstado(estadoFuturo);
        return celdaFutura;
    } 
    
    public static void reset(){
        cantidadEntrada = 0;
        colaConjunto = 0;
    }

}
