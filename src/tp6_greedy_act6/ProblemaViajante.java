package tp6_greedy_act6;

import java.util.ArrayList;

public class ProblemaViajante {
	/*
	 * Ejercicio 2
	Dado un conjunto de n ciudades y conociendo las distancias entre cada par de ellas (decida Ud. en qué estructura/s de datos le vendrá 
	dada esta información), se desea encontrar un circuito cerrado que:

	comience y termine en la misma ciudad,
	visite cada ciudad exactamente una vez (debe visitar todas),
	tenga distancia total mínima.

	El algoritmo debe devolver la lista de ciudades que deberá visitar, desde una ciudad origen A dada como parámetro. Se supone que termina el viaje yendo desde la última ciudad de la lista hacia la ciudad A cerrando el circuito requerido.
	Diseñe un algoritmo que resuelva el problema mediante estrategia greedy.
	Responda:
	a) Explique y justifique la estrategia greedy que utilizó.
	b) ¿Esta estrategia dará siempre la mejor solución? Justifique.
	c) Escriba en JAVA el algoritmo greedy.
	 * 
	 * 
	 * */
	//resolución con greedy
	public ArrayList<Integer> tspGreedy(int[][] dist, int origen) {

	    int n = dist.length;

	    boolean[] visitado = new boolean[n];
	    ArrayList<Integer> recorrido = new ArrayList<>();

	    int actual = origen;
	    visitado[actual] = true;
	    recorrido.add(actual);

	    int ciudadesRestantes=n-1;
	    while (ciudadesRestantes>0) {

	        int siguiente = -1;
	        int mejorDist = Integer.MAX_VALUE;

	        for (int j = 0; j < n; j++) {
	            if (!visitado[j] && dist[actual][j] < mejorDist) {
	                mejorDist = dist[actual][j];
	                siguiente = j;
	            }
	        }

	        actual = siguiente;
	        visitado[actual] = true;
	        recorrido.add(actual);
	        ciudadesRestantes--;
	    }

	    // volver al origen
	    recorrido.add(origen);

	    return recorrido;
	}

	
	
	//distancia total
	
	private int costoRecorrido(ArrayList<Integer> rec, int[][] dist) {
	    int total = 0;
	    for (int i = 0; i < rec.size() - 1; i++) {
	        total += dist[rec.get(i)][rec.get(i + 1)];
	    }
	    return total;
	}

	
	
	
	
	
	
	
	/*
	 * Ejercicio 3
	 * 	Dado el mismo problema del ejercicio 2, resuélvalo mediante la técnica Backtracking.
	 * 
	 * PROBLEMA:
	 * Dado un conjunto de n ciudades y conociendo las distancias entre cada par de ellas (decida Ud. en qué estructura/s de datos
	 * le vendrá dada esta información), se desea encontrar un circuito cerrado que:
	 * 	comience y termine en la misma ciudad,
	 * 	visite cada ciudad exactamente una vez (debe visitar todas),
	 * 	tenga distancia total mínima.
	 * El algoritmo debe devolver la lista de ciudades que deberá visitar, desde una ciudad origen A dada como parámetro. 
	 * Se supone que termina el viaje yendo desde la última ciudad de la lista hacia la ciudad A cerrando el circuito requerido.
	 * 
	 * 
	 * 		a) ¿Es de alguna utilidad el resultado que da el algoritmo greedy para ser aplicado al algoritmo que lo resuelve mediante Backtracking? Explique.
	 * 	
	 * El resultado del greedy es útil como cota superior inicial para la poda del backtracking. Si el greedy encontró un circuito de distancia X, 
	 * durante el backtracking podemos podar cualquier rama cuya distancia parcial ya supere X, ya que nunca podrá mejorar la solución conocida. 
	 * Esto reduce significativamente el espacio de búsqueda. Sin embargo el greedy por sí solo no garantiza el óptimo, por eso se necesita el 
	 * backtracking para explorar todas las posibilidades.		
	 * 
	 * b) Dibuje el árbol de búsqueda con todas las marcaciones que considere necesarias.
	 * 
	 * 		[ A | B |  C|  D]
	 * 	 A  [ 0 | 1 | 5 | 2 ]
	 * 	 B  [ 1 | 0 | 10| 12]        con greedy el camino solución es : [A-B-C-D-A]=1+10+20+2=33
	 * 	 C  [ 5 | 10| 0 | 20]
	 * 	 D  [ 2 | 12| 20| 0 ]
	 * cota inicial=33
	 				     	A 
	 				      (d=0)
	          +1/           |+5              \+2
	           B		    C			       D
	         (d=1)         (d=5)  	         (d=2)
	    +10 /    \+12    +10/   \+20     +12 /    \+20
	       C      D        B      D          B     C
	     (d=11) (d=13)   (d=15) (d=25)✗  (d=14)   (d=22)
	   +20 |    +20|    +12|     +12|     +10|    +10|
	       D       C       D        B        C       B
	     d=31    d=33     d=27    d=37✗    ✓d=24     d=32✗
	      +2|     +5|      +2|     poda      +5|        poda 
	        A       A        A                 A
	     ✓d=33    d=38✗     d=29✓          Empate,d=29            
	  mejorCosto   peor    mejorCosto          
	                                    
	    
	    costo:d=29      MejorRecorrido[A-C-B-D-A]->  distancia=5+10+12+2=29
	    
	 * 
	 * 
	 * 
	 * 		c) Escriba el código JAVA que resuelva el problema mediante Backtracking. El algoritmo debe tener una estrategia de poda.
	 * */

	
	private int mejorCosto;
	private ArrayList<Integer> mejorRecorrido;
	
	
	public ArrayList<Integer>tspBacktracking(int [][] dist, int origen){
		int n=dist.length;
		boolean[] visitado = new boolean[n];
	    ArrayList<Integer> parcial = new ArrayList<>();
	    
	 // cota inicial con greedy
	    mejorCosto = costoRecorrido(tspGreedy(dist, origen), dist);
	    mejorRecorrido = new ArrayList<>();

	    parcial.add(origen);
	    visitado[origen] = true;

	    back(dist, origen, origen, visitado, parcial, 0);

	    return mejorRecorrido;
	}
	
	private void  back(int[][]dist, int actual, int origen, boolean[]visitado, ArrayList<Integer>parcial, int costoActual) {
		   // PODA
	    if (costoActual >= mejorCosto) {
	        return;
	    }
		
	    // CASO BASE
	    if (parcial.size() == dist.length) {
	        int costoTotal = costoActual + dist[actual][origen];

	        if (costoTotal < mejorCosto) {
	            mejorCosto = costoTotal;
	            mejorRecorrido = new ArrayList<>(parcial);
	            mejorRecorrido.add(origen); // cierra el ciclo
	        }
	        return;
	    }
	    
	    // PROBAR SIGUIENTES
	    for (int i = 0; i < dist.length; i++) {
	        if (!visitado[i]) {

	            visitado[i] = true;
	            parcial.add(i);

	            back(dist, i, origen, visitado, parcial, costoActual + dist[actual][i]);

	            // BACKTRACK
	            visitado[i] = false;
	            parcial.remove(parcial.size() - 1);
	        }
	    }
	}    
	

}
