package tp5_backtracking_act8;

import java.util.ArrayList;

public class Piramide_forma2 {

    private int B;
    private int k;
    private boolean[] usados;

    // =============================
    // Punto de entrada
    // =============================
    public ArrayList<ArrayList<Integer>> resolver(int B, int k) {

        this.B = B;
        this.k = k;
        this.usados = new boolean[k];

        ArrayList<Integer> base = backBase(0, new ArrayList<>());

        if (base == null)
            return null;

        return construirPiramide(base);
    }

    // =============================
    // Backtracking sobre la base
    // =============================
    private ArrayList<Integer> backBase(int pos, ArrayList<Integer> base) {

        if (pos == B) {
            return base;
        }

        for (int num = 1; num < k; num++) {

            if (!usados[num]) {

                base.add(num);
                usados[num] = true;

                if (esBaseParcialValida(base)) {
                    ArrayList<Integer> res = backBase(pos + 1, base);
                    if (res != null) {
                        return res;
                    }
                }

                // BACKTRACK (siempre)
                base.remove(base.size() - 1);
                usados[num] = false;
            }
        }

        return null;
    }

    // =============================
    // Poda parcial
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
