package tp5_backtracking_act8;

import java.util.ArrayList;
// =============================
/*
 * Colocar un entero positivo (menor que cierto valor entero k dado)
 * en cada casilla de una piramide de base B (valor entero dado) de modo que 
 * cada numero sea iagual a la suma de las casillas sobre las que 
 * está apoyado. Los npumeros de todas las casillas debens ser diferentes.
 * */
// =============================
public class Piramide_forma2 {

    private int B;
    private int k;
    private boolean[] usados;
    private ArrayList<ArrayList<Integer>> solucion; // guardo la solución final
    // =============================
    // Punto de entrada
    // =============================
    public ArrayList<ArrayList<Integer>> resolver(int B, int k) {

        this.B = B;
        this.k = k;
        this.usados = new boolean[k];
        this.solucion = null;
        
        backBase(0, new ArrayList<>());

        return solucion;
    }

    // =============================
    // Backtracking sobre la base
    // =============================
    private boolean backBase(int pos, ArrayList<Integer> base) {

        if (pos == B) {
        	 ArrayList<ArrayList<Integer>> piramide = construirPiramide(base);
        	 if (piramide != null) {
                 solucion = piramide;
                 return true; // corto al encontrar solución
             }
        return false;  //esta base no sirve
        }

        for (int num = 1; num < k; num++) {

            if (!usados[num]) {

                base.add(num);
                usados[num] = true;

                if (esBaseParcialValida(base)) {
                	if (backBase(pos + 1, base)) {
                        return true;
                }
                }
                // BACKTRACK (siempre)
                base.remove(base.size() - 1);
                usados[num] = false;
            }
        }

        return false;
    }

    // =============================
    // Poda parcial sobre la base
    // =============================
    private boolean esBaseParcialValida(ArrayList<Integer> base) {

        int n = base.size();
        if (n < 2) return true;

        int suma = base.get(n - 1) + base.get(n - 2);
        return suma < k;
    }

    // =============================
    // Construcción de la pirámide
    // =============================
    private ArrayList<ArrayList<Integer>> construirPiramide(ArrayList<Integer> base) {

        ArrayList<ArrayList<Integer>> piramide = new ArrayList<>();
        boolean[] vistos = new boolean[k];

        // base
        piramide.add(new ArrayList<>(base));
        for (int v : base) {
            vistos[v] = true;
        }

        // construir hacia arriba
        while (piramide.get(0).size() > 1) {

            ArrayList<Integer> filaInferior = piramide.get(0);
            ArrayList<Integer> nuevaFila = new ArrayList<>();

            for (int i = 0; i < filaInferior.size() - 1; i++) {

                int val = filaInferior.get(i) + filaInferior.get(i + 1);

                if (val >= k || vistos[val]) {
                    return null;
                }

                nuevaFila.add(val);
                vistos[val] = true;
            }

            piramide.add(0, nuevaFila);
        }

        return piramide;
    }
}
