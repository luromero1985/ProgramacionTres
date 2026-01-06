package tp5_Backtracking_act2;

import java.util.ArrayList;
import java.util.List;

/*
 * Ejercicio 2
Dado un laberinto consistente en una matriz cuadrada que tiene en cada posición un valor natural y
cuatro valores booleanos, indicando estos últimos si desde esa casilla se puede ir al norte, este, sur
y oeste, encontrar un camino de longitud mínima entre dos casillas dadas, siendo la longitud de un
camino la suma de los valores naturales de las casillas por las que pasa. Idea: podría representarse
el laberinto como una matriz, de objetos, donde cada objeto tiene el valor natural, y cuatro
booleanos, uno para cada dirección a la que se permite ir desde allí.
 * */

public class BacktrackingLaberinto {
	private Laberinto lab;
    private boolean[][] visitado;

    private int mejorCosto = Integer.MAX_VALUE;
    private List<Posicion> mejorCamino = new ArrayList<>();

    BacktrackingLaberinto(Laberinto lab) {
        this.lab = lab;
        this.visitado = new boolean[lab.getN()][lab.getN()];  
    }

    public List<Posicion> buscar(Posicion inicio, Posicion fin) {

        List<Posicion> caminoActual = new ArrayList<>();
        caminoActual.add(inicio);

        backtracking(inicio, fin, lab.getValor(inicio), caminoActual);

        return mejorCamino;
    }

    private void backtracking(Posicion actual, Posicion destino, int costo, List<Posicion> caminoActual) {

        // Poda: ya no puede ser un mejor camino
        if (costo >= mejorCosto)
            return;

        // Caso base: llegamos al destino
        if (actual.equals(destino)) {
            mejorCosto = costo;
            mejorCamino = new ArrayList<>(caminoActual);
            return;
        }

        // Marco visitado
        visitado[actual.getFila()][actual.getCol()] = true;

        // Explorar vecinos válidos
        for (Posicion vecino : lab.obtenerVecinos(actual)) {
            if (!visitado[vecino.getFila()][vecino.getCol()]) {

                caminoActual.add(vecino);

                backtracking(vecino, destino, costo + lab.getValor(vecino), caminoActual);

                caminoActual.remove(caminoActual.size() - 1);
            }
        }

        // Desmarco (backtracking)
        visitado[actual.getFila()][actual.getCol()] = false;
    }
}
