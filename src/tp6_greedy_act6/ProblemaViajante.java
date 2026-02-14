package tp6_greedy_act6;

import java.util.ArrayList;

public class ProblemaViajante {
	ArrayList<Integer> tspGreedy(int[][] dist, int origen) {

	    int n = dist.length;

	    boolean[] visitado = new boolean[n];
	    ArrayList<Integer> recorrido = new ArrayList<>();

	    int actual = origen;
	    visitado[actual] = true;
	    recorrido.add(actual);

	    for (int paso = 1; paso < n; paso++) {

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
	    }

	    // volver al origen
	    recorrido.add(origen);

	    return recorrido;
	}

	
	
	//distancia total
	
	int costoRecorrido(ArrayList<Integer> rec, int[][] dist) {
	    int total = 0;
	    for (int i = 0; i < rec.size() - 1; i++) {
	        total += dist[rec.get(i)][rec.get(i + 1)];
	    }
	    return total;
	}

}
