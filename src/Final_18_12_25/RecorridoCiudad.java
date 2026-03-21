package Final_18_12_25;

import java.util.ArrayList;

import tp4_grafos.GrafoNoDirigido;

/*
 * Ejercicio 2
 * Dado un conjunto de n ciudades y conociendo las distancias entre cada par de ellas (decida Ud. en qué estructura/s de datos
 *  le vendrá dada esta información), se desea encontrar un circuito cerrado que:
 *  comience y termine en la misma ciudad,visite cada ciudad exactamente una vez (debe visitar todas), tenga distancia total mínima.
 *  El algoritmo debe devolver la lista de ciudades que deberá visitar, desde una ciudad origen A dada como parámetro. Se supone que termina 
 *  el viaje yendo desde la última ciudad de la lista hacia la ciudad A cerrando el circuito requerido.
 *  Diseñe un algoritmo que resuelva el problema mediante estrategia greedy.
 *  Responda:
 *  	a) Explique y justifique la estrategia greedy que utilizó.
 *  La estrategia es visitar primero a las ciudades más cercanas no visitadas. 
 *  	b) ¿Esta estrategia dará siempre la mejor solución? Justifique. 
 *  		No garantiza solución optima.
 * 		c) Escriba en JAVA el algoritmo greedy.
 * 
 * 
 * Rta: La estructura es un grafo No dirigido ponderado
 * 
 * 			 |A |B |C |D |
 * 			A|0 |1 |5 |2 |
 * 			B|1 |0 |10|12|
 * 			C|5 |10|0 |20|
 * 			D|2 |12|20|0 |
 * 
 * */

public class RecorridoCiudad {

	public ArrayList<Integer>caminoMasCorto(int[][]dist, int origen){
		ArrayList<Integer>recorrido=new ArrayList<>();
		int n=dist.length;
		boolean[]visitado=new boolean[n];
			
		int actual=origen; 
		visitado[actual]=true;
		recorrido.add(origen);

		int ciudadesRestantes=n-1; //n-1 porque el origen ya está marcado
		while(ciudadesRestantes>0) { 
			int siguiente=-1;
			int mejorDist=Integer.MAX_VALUE;
			for(int j=0; j<n;j++) {
				if(!visitado[j]&& dist[actual][j]<mejorDist) {
					mejorDist=dist[actual][j];
					siguiente=j;
				}
			}
			actual=siguiente;
			visitado[actual]=true;
			recorrido.add(actual);
			ciudadesRestantes--;
		}
		recorrido.add(origen);
		return recorrido;
	}
}
