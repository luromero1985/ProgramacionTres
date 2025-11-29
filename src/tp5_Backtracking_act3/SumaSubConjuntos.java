package tp5_Backtracking_act3;

import java.util.ArrayList;
import java.util.List;
/*
 * Ejercicio 3
Suma de subconjuntos. Dados n números positivos distintos, se desea encontrar todas las
combinaciones de esos números tal que la suma sea igual a M
 * */


public class SumaSubConjuntos {

	 private int[] numeros;
	 private int objetivo;
	 private ArrayList<ArrayList<Integer>> soluciones;

	    public SumaSubConjuntos(int[] numeros, int objetivo) {
	        this.numeros = numeros;
	        this.objetivo = objetivo;
	        this.soluciones = new ArrayList<>();
	    }

	    public ArrayList<ArrayList<Integer>> resolver() {
	        ArrayList<Integer> parcial = new ArrayList<>();
	        this.backtracking( 0, 0, parcial);
	        return soluciones;
	    }

	    private void backtracking(int idx, int sumaActual, ArrayList<Integer> parcial) {

	        // poda: si me paso del objetivo → cortar
	        if (sumaActual > objetivo) return;

	        // caso base: llegué a la suma exacta
	        if (sumaActual == objetivo) {
	            soluciones.add(new ArrayList<>(parcial));
	            return;
	        }

	        // caso base: no hay más números
	        if (idx == numeros.length) return;

	        int num = numeros[idx];

	        // opcion 1: incluir el número
	        parcial.add(num);
	        backtracking(idx + 1, sumaActual + num, parcial);
	        parcial.remove(parcial.size() - 1);   // backtrack

	        // opcion 2: NO incluir el número
	        backtracking(idx + 1, sumaActual, parcial);
	    }
	}
	

