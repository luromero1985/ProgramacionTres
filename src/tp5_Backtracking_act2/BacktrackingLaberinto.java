package tp5_Backtracking_act2;

import java.util.ArrayList;
import java.util.List;

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
