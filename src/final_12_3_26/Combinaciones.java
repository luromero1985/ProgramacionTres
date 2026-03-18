package final_12_3_26;

import java.util.ArrayList;

/*
 * Ejercicio 3
Dado un número entero positivo N y un valor objetivo V se desean encontrar todas las posibles combinaciones de números entre 1 y N
cuya suma dé como resultado V. Los números utilizados pueden aparecer más de una vez dentro de una combinación, pero no pueden aparecer
más de 4 veces. No deben aparecer combinaciones duplicadas en el resultado final.

Por ejemplo, si N= 3 y V= 5, la solución sería { [1,1,1,2]; [1, 1, 3]; [1, 2, 2]; [2, 3] }

Casos no válidos: por ej. las combinaciones [1,1,1,2] y [1,2,1,1] son consideradas duplicadas, la solución [1,1,1,1,1] no sería posible 
ya que el 1 se repite más de 4 veces y eso no está permitido.

a) Dibuje el árbol de exploración del algoritmo, indicando qué decisiones se toman en cada paso y qué información se lleva en los estados.
b) Escriba el algoritmo solicitado en JAVA.
c) Indique su complejidad computacional.
La complejidad en el peor caso es O(N^V) ya que el árbol de exploración tiene profundidad V y hasta N ramas por nodo. 
Las podas reducen significativamente el tiempo real de ejecución pero no cambian el orden en el peor caso.
 * */
public class Combinaciones {
	private ArrayList<ArrayList<Integer>> lista;
	private static final int  MAXIMO=4;
	
	
	public ArrayList<ArrayList<Integer>>combinaciones(int n, int v){
		this.lista=new ArrayList<>();
		
		back(n,v,new ArrayList<Integer>(),0,1);   //start en 1
		return lista;
	}
	
	private void back(int n, int v, ArrayList<Integer>parcial, int suma, int start) {
		
		if(suma==v) {
			lista.add(new ArrayList<>(parcial));  //siempre hacer copia!
			return;
		}
		
		for (int i = start; i <= n; i++) {
	        if (suma + i > v) {
	        	break; // poda por monotonía
	        }
	     // contar repeticiones de i en parcial
	        int repeticion = 0;
	        for (int x : parcial) {
	        	if (x == i) {
	        		repeticion++;
	        	}
	        }
	        if (repeticion < MAXIMO) {
	            parcial.add(i);
	            back(n, v, parcial, suma + i, i); // i como nuevo start
	            parcial.remove(parcial.size() - 1);
	    	}
		}
	}
}
