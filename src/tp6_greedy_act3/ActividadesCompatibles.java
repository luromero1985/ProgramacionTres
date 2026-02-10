package tp6_greedy_act3;

import java.util.ArrayList;

/*
 * Ejercicio 3
Maximizar el número de actividades compatibles. Se tienen n actividades que necesitan utilizar un
recurso, tal como una sala de conferencias, en exclusión mutua. Cada actividad i tiene asociado un
tiempo de comienzo ci y un tiempo de finalización fi de utilización del recurso, con ci < fi. Si la
actividad i es seleccionada se llevará a cabo durante el intervalo [ci, fi). Las actividades i y j son
compatibles si los intervalos [ci, fi) y [cj, fj) no se superponen (es decir, ci > fj o cj > fi). El problema
consiste en encontrar la cantidad máxima de actividades compatibles entre sí.
 * 
 */


public class ActividadesCompatibles {

	
	public ArrayList<Integer> actividadesCompatibles(int[]c, int[]f){
		
		ArrayList<Integer> solucion= new ArrayList<>();
		int n=c.length;
		boolean [] usados=new boolean [n];
		int finUltima=-1;
		
		while (existeNoUsado(usados)) {
		    int i = seleccionarMenorFinal(usados, f);
		    usados[i] = true;

		    if (c[i] >= finUltima) {
		        solucion.add(i);
		        finUltima = f[i];
		    }
		}
	}

	
	
	private boolean existeNoUsado(boolean[] usados) {
	    for (boolean u : usados)
	        if (!u) return true;
	    return false;
	}

	
	private int seleccionarMenorFinal(boolean[] usados, int[] f) {
	    int mejor = -1;

	    for (int i = 0; i < f.length; i++) {
	        if (!usados[i]) {
	            if (mejor == -1 || f[i] < f[mejor]) {
	                mejor = i;
	            }
	        }
	    }
	    return mejor;
	}

}
