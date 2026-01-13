package tp5_backtracking_act6_forma2;


import java.util.ArrayList;
import java.util.List;

	public class CaballoDeAtilia {

	    private boolean[][] visitadas;
	    private List<Posicion> camino;
	    private Posicion origen;
	    private int cantSinPasto;
	    private int n;

	    private int[][] movimientos = {
	        {1, 0}, {-1, 0}, {0, 1}, {0, -1}
	    };

	    // 🔹 Punto de entrada (inicio desconocido)
	    public List<Posicion> resolver(int n, int cantSinPasto) {

	        this.n = n;
	        this.cantSinPasto = cantSinPasto;

	        for (int i = 0; i < n; i++) {
	            for (int j = 0; j < n; j++) {

	                visitadas = new boolean[n][n];
	                camino = new ArrayList<>();

	                origen = new Posicion(i, j);
	                visitadas[i][j] = true;
	                camino.add(origen);

	                if (back(origen, 1)) {
	                    return camino;
	                }
	            }
	        }

	        return null; // no existe recorrido
	    }

	    // 🔹 Backtracking
	    private boolean back(Posicion actual, int nroPisada) {

	        // ✅ Poda fuerte
	        if (hayCasillaAislada())
	            return false;

	        // ✅ Caso base
	        if (nroPisada == cantSinPasto) {
	            return actual.esVecina(origen);
	        }

	        for (int[] mov : movimientos) {

	            int nf = actual.getFila() + mov[0];
	            int nc = actual.getColumna() + mov[1];

	            if (esFactible(nf, nc)) {

	                Posicion sig = new Posicion(nf, nc);

	                visitadas[nf][nc] = true;
	                camino.add(sig);

	                if (back(sig, nroPisada + 1))
	                    return true;

	                // 🔙 back
	                visitadas[nf][nc] = false;
	                camino.remove(camino.size() - 1);
	            }
	        }

	        return false;
	    }

	    // 🔹 Movimiento válido
	    private boolean esFactible(int f, int c) {
	        return f >= 0 && f < n && c >= 0 && c < n && !visitadas[f][c];
	    }

	    // 🔥 PODA FUERTE: casilla aislada
	    private boolean hayCasillaAislada() {

	        for (int i = 0; i < n; i++) {
	            for (int j = 0; j < n; j++) {

	                if (!visitadas[i][j]) {

	                    boolean tieneSalida = false;

	                    for (int[] m : movimientos) {
	                        int ni = i + m[0];
	                        int nj = j + m[1];

	                        if (ni >= 0 && ni < n && nj >= 0 && nj < n) {
	                            if (!visitadas[ni][nj]) {
	                                tieneSalida = true;
	                                break;
	                            }
	                        }
	                    }

	                    if (!tieneSalida)
	                        return true; // 💣 rama muerta
	                }
	            }
	        }

	        return false;
	    }
	}

