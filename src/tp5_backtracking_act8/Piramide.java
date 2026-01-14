package tp5_backtracking_act8;

import java.util.HashSet;
import java.util.Set;

public class Piramide {
	
	 private int B;
	    private int k;
	    private int[][] piramide;
	    private boolean[] usados;

	    public Piramide(int B, int k) {
	        this.B = B;
	        this.k = k;
	        this.piramide = new int[B][];
	        for (int i = 0; i < B; i++) {
	            piramide[i] = new int[i + 1];
	        }
	        this.usados = new boolean[k];
	    }

	    
	    public boolean resolver() {
	        return backBase(0);
	    }

	    // Backtracking SOLO sobre la base
	    private boolean backBase(int col) {

	        // base completa
	        if (col == B) {
	            return construirPiramide();
	        }

	        for (int num = 1; num < k; num++) {
	            if (!usados[num]) {
	                usados[num] = true;
	                piramide[B - 1][col] = num;

	                if (backBase(col + 1))
	                    return true;

	                usados[num] = false;
	            }
	        }
	        return false;
	    }

	    // Construye la pirámide desde abajo hacia arriba
	    private boolean construirPiramide() {

	        Set<Integer> vistos = new HashSet<>();

	        // agregar base
	        for (int x : piramide[B - 1]) {
	            vistos.add(x);
	        }

	        for (int fila = B - 2; fila >= 0; fila--) {
	            for (int col = 0; col <= fila; col++) {

	                int val = piramide[fila + 1][col] + piramide[fila + 1][col + 1];

	                if (val <= 0 || val >= k)
	                    return false;

	                if (vistos.contains(val))
	                    return false;

	                piramide[fila][col] = val;
	                vistos.add(val);
	            }
	        }
	        return true;
	    }

	    public void imprimir() {
	        for (int i = 0; i < B; i++) {
	            for (int j = 0; j <= i; j++) {
	                System.out.print(piramide[i][j] + " ");
	            }
	            System.out.println();
	        }
	    }
	}
	
