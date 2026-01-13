package tp5_backtracking_act6_forma2;


import java.util.ArrayList;
import java.util.List;

public class CaballoDeAtilia {

    private boolean[][] visitadas;
    private List<Posicion> camino;
    private Posicion origen;
    private int cantSinPasto;
    private int n; //tamanio de la matriz

    // Punto de entrada
    public List<Posicion> resolver(int n, Posicion origen, int cantSinPasto) {
        this.n = n;
        this.origen = origen;
        this.cantSinPasto = cantSinPasto;

        this.visitadas = new boolean[n][n];
        this.camino = new ArrayList<>();

        this.back(origen, 1);

        return camino;
    }

    private boolean back(Posicion actual, int nroPisada) {

        // marco
        visitadas[actual.getFila()][actual.getColumna()] = true;
        camino.add(actual);

        // caso base
        if (nroPisada == cantSinPasto) {
            if (actual.esVecina(origen)) {
                return true; // solución encontrada
            }
        } else {

            // movimientos posibles
            int[][] movimientos = {
                {1, 0}, {-1, 0}, {0, 1}, {0, -1}
            };

            for (int[] mov : movimientos) {

                Posicion sig = new Posicion(
                    actual.getFila() + mov[0],
                    actual.getColumna() + mov[1]
                );

                if (esFactible(sig)) {
                    if (back(sig, nroPisada + 1)) {
                        return true;
                    }
                }
            }
        }

        // deshacer (backtrack)
        visitadas[actual.getFila()][actual.getColumna()] = false;
        camino.remove(camino.size() - 1);

        return false;
    }

    private boolean esFactible(Posicion p) {
        int f = p.getFila();
        int c = p.getColumna();

        if (f < 0 || f >= n || c < 0 || c >= n)
            return false;

        return !visitadas[f][c];
    }
}
