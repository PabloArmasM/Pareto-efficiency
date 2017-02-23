package algoritmogenetico;

import java.util.ArrayList;

public class Coche {

    private  Celda posicion;
    private  int direccion;
    private  ArrayList<Integer> movimiento;
    private int eliminar = 0;
    private int empieza = 1;

    public Coche(Celda posicion, int direccion) {
        this.posicion = posicion;
        this.direccion = direccion;
        movimiento = new ArrayList<>();
    }

    public Celda getPosicion() {
        return posicion;
    }

    public void setPosicion(Celda posicion) {
        this.posicion = posicion;
    }

    public int getDireccion() {
        return direccion;
    }

    public void setDireccion(int direccion) {
        this.direccion = direccion;
    }

    public ArrayList<Integer> getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(int movimiento) {
        this.movimiento.add(movimiento);
    }

    public int getEliminar() {
        return eliminar;
    }

    
    public void fitnessCoche (){
        ListaFitnessCoche.add(FitnessCoche.calculaFitness(movimiento));
    }
    
    
    public void estadoFuturo() {
        if (empieza == 1) {
            empieza = 0;
        } else {
            if (this.posicion instanceof CeldaSalida) {
                ListaFitnessCoche.add(FitnessCoche.calculaFitness(movimiento));
                eliminar = -1;
            } else {
                if (this.posicion instanceof CeldaSemaforo) {
                    if (Semaforos.estadoSemaforo((CeldaSemaforo) posicion) == 0 && !Carretera.getEstadoSiguiente(posicion.getPosicion(), direccion)) {
                        this.posicion = Carretera.getSiguiente(posicion.getPosicion(), direccion);
                        movimiento.add(1);
                    } else {
                        movimiento.add(0);
                    }
                }
                if (!Carretera.getEstadoSiguiente(posicion.getPosicion(), direccion)) {
                    this.posicion = Carretera.getSiguiente(posicion.getPosicion(), direccion);
                    movimiento.add(1);
                } else {
                    movimiento.add(0);
                }
            }
        }
    }

}
