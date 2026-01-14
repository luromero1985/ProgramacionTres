package tp5_backtracking_act7;

/*
 * Tablero mágico. Dado un tablero de tamaño nxn, construir un algoritmo que ubique (si es posible)
 *  nxn numeros naturales diferentes, entre 1 y k (con k>n*n), de manera tal que la suma de las columnas
 *   y de las filas sea igual a S.
 * */
public class TableroMagico {
	 private int n;
	 private int k;
	 private int S;

	 private int[][] tablero;
	 private boolean[] usado;

	 private int[] sumaFilas;
	 private int[] sumaColumnas;

	 
	 
	    public int[][] resolver(int n, int k, int S) {

	        this.n = n;
	        this.k = k;
	        this.S = S;

	        this.tablero = new int[n][n];
	        this.usado = new boolean[k + 1];

	        this.sumaFilas = new int[n];
	        this.sumaColumnas = new int[n];

	        boolean existe = back(0, 0);

	        if (existe) {
	            return tablero;
	        }else {
	            return null;
	    }
	    }
	    
	    private boolean back(int fila, int col) {

	        // Caso base: tablero completo
	        if (fila == n) {
	            return true;
	        }

	        // Calcular siguiente posición
	        int sigFila = fila;
	        int sigCol = col + 1;

	        if (sigCol == n) {
	            sigCol = 0;
	            sigFila++;
	        }

	        // Probar números posibles
	        for (int num = 1; num <= k; num++) {

	            if (!usado[num]) {

	                // Verificar que no exceda la suma
	                if (sumaFilas[fila] + num <= S &&
	                    sumaColumnas[col] + num <= S) {

	                    // Colocar
	                    tablero[fila][col] = num;
	                    usado[num] = true;
	                    sumaFilas[fila] += num;
	                    sumaColumnas[col] += num;

	                    // cerrar fila/col
	                    if (esValidoCierre(fila, col)) {

	                        if (back(sigFila, sigCol)) {
	                            return true;
	                        }
	                    }

	                    // Deshacer
	                    tablero[fila][col] = 0;
	                    usado[num] = false;
	                    sumaFilas[fila] -= num;
	                    sumaColumnas[col] -= num;
	                }
	            }
	        }

	        return false;
	    }
	    
	    
	    private boolean esValidoCierre(int fila, int col) {

	        // Si se completa una fila, debe sumar S
	        if (col == n - 1 && sumaFilas[fila] != S)
	            return false;

	        // Si se completa una columna, debe sumar S
	        if (fila == n - 1 && sumaColumnas[col] != S)
	            return false;

	        return true;
	    }
	}



