package tp5_backtracking_act6_forma2;
import java.util.ArrayList;
import java.util.List;

public class CaballoDeAtilia {

    private boolean[][] visitadas;
    private List<Posicion> camino;
    private Posicion origen;
    private int cantSinPasto;
    private int n;

    //  Punto de entrada
    public List<Posicion> resolver(int n, int cantSinPasto) {
        this.n = n;
        this.cantSinPasto = cantSinPasto;

        // Como el origen NO está dado → pruebo todos
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                origen = new Posicion(i, j);
                visitadas = new boolean[n][n];
                camino = new ArrayList<>();

                visitadas[i][j] = true;
                camino.add(origen);

                if (back(origen, 1))
                    return camino;
            }
        }

        return null;
    }

    // Backtracking
    private boolean back(Posicion actual, int pasos) {

        // Condición de solución
        if (pasos == cantSinPasto && actual.esVecina(origen))
            return true;

        for (Posicion sig : actual.vecinos()) {

            if (esFactible(sig)) {

                marcar(sig);
                if (back(sig, pasos + 1))
                    return true;
                desmarcar(sig);
            }
        }

        return false;
    }

    // Factibilidad
    private boolean esFactible(Posicion p) {
        int f = p.getFila();
        int c = p.getColumna();

        return f >= 0 && f < n &&
               c >= 0 && c < n &&
               !visitadas[f][c];
    }

    private void marcar(Posicion p) {
        visitadas[p.getFila()][p.getColumna()] = true;
        camino.add(p);
    }

    private void desmarcar(Posicion p) {
        visitadas[p.getFila()][p.getColumna()] = false;
        camino.remove(camino.size() - 1);
    }
}
