package tp5_backtracking_act4;

import java.util.ArrayList;
import java.util.List;

public class Particion {

	 private int[] nums;
	   
	    private List<Integer> solucion;
	    private boolean encontrada;
	    
	    
	    public Particion(int[] nums) {
	        this.nums = nums;
	        this.encontrada = false;
	        this.solucion = new ArrayList<>();
	    }
	    
	    
	    private int calcularSuma() {
	        int suma = 0;
	        for (int n : nums)
	            suma += n;
	        return suma;
	    }
	    
	    private boolean esValido() {
	        return calcularSuma() % 2 == 0;
	    }

	    public List<Integer> encontrar() {

	        if (!esValido())
	            return new ArrayList<>();

	        int objetivo = calcularSuma() / 2;

	        List<Integer> actual = new ArrayList<>();
	        backtracking(0, 0, objetivo, actual);

	        return solucion;
	    }

	    
	    private void backtracking(int idx, int sumaActual, int objetivo, List<Integer> actual) {

	        // poda: ya encontramos
	        if (encontrada) return;

	        // poda: suma inválida
	        if (sumaActual > objetivo) return;

	        // caso base: suma correcta
	        if (sumaActual == objetivo) {
	            solucion = new ArrayList<>(actual);
	            encontrada = true;
	            return;
	        }

	        // sin más elementos
	        if (idx == nums.length) return;

	        // OPCIÓN 1: incluir
	        actual.add(nums[idx]);
	        backtracking(idx + 1, sumaActual + nums[idx], objetivo, actual);
	        actual.remove(actual.size() - 1); // DESHACER

	        // OPCIÓN 2: no incluir
	        backtracking(idx + 1, sumaActual, objetivo, actual);
	    }
	}


