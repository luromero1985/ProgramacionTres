package tp5_backtracking_act6_forma2;
import java.util.ArrayList;
import java.util.List;


public class CaballoDeAtilia {

    private boolean[][] visitadas;
    private List<Posicion> camino;
    private Posicion origen;
    private int cantSinPasto;
    private int n;

    // Punto de entrada
    public List<Posicion> resolver(int n, Posicion origen, int cantSinPasto) {
        this.n = n;
        this.origen = origen;
        this.cantSinPasto = cantSinPasto;

        this.visitadas = new boolean[n][n];
        this.camino = new ArrayList<>();

        visitadas[origen.getFila()][origen.getColumna()] = true;
        camino.add(origen);

        if (back(origen, 1)) {
            return camino;
        }

        return new ArrayList<>(); // no hay solución
    }

    // ================= BACKTRACKING =================

    private boolean back(Posicion actual, int nroPisada) {

        // 🔴 PODA 1: me pasé de pisadas
        if (camino.size() > cantSinPasto) {
            return false;
        }

        // 🔴 PODA 2: existe casilla aislada
        if (hayCasillaAislada()) {
            return false;
        }

        // 🟢 Caso base
        if (nroPisada == cantSinPasto) {
            return actual.esVecina(origen); // cerrar ciclo
        }

        // 🔁 Probar movimientos
        for (Posicion sig : actual.vecinos()) {

            if (esFactible(sig)) {

                marcar(sig);
                camino.add(sig);

                if (back(sig, nroPisada + 1)) {
                    return true;
                }

                desmarcar(sig);
                camino.remove(camino.size() - 1);
            }
        }

        return false;
    }

    // ================= PODAS =================

    private boolean hayCasillaAislada() {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (!visitadas[i][j]) {

                    Posicion p = new Posicion(i, j);
                    boolean tieneVecinoLibre = false;

                    for (Posicion v : p.vecinos()) {
                        if (estaDentro(v) && !visitadas[v.getFila()][v.getColumna()]) {
                            tieneVecinoLibre = true;
                            break;
                        }
                    }

                    if (!tieneVecinoLibre) {
                        return true; // 🔥 poda fuerte
                    }
                }
            }
        }
        return false;
    }

    // ================= AUXILIARES =================

    private boolean esFactible(Posicion p) {
        return estaDentro(p) && !visitadas[p.getFila()][p.getColumna()];
    }

    private boolean estaDentro(Posicion p) {
        int f = p.getFila();
        int c = p.getColumna();
        return f >= 0 && f < n && c >= 0 && c < n;
    }

    private void marcar(Posicion p) {
        visitadas[p.getFila()][p.getColumna()] = true;
    }

    private void desmarcar(Posicion p) {
        visitadas[p.getFila()][p.getColumna()] = false;
    }
}
