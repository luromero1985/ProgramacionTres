package tp6_greedy_act4;

/*
 * Ejercicio 4
Algoritmo de Dijkstra. Dado un grafo con pesos no negativos, implemente el algoritmo de Dijkstra
para determinar el array de distancias y de predecesores en el camino más corto, desde un vértice
orígen dado como parámetro hacia el resto de los vértices. Una vez realizado, imprima el camino
más corto que se debe seguir desde el orígen hacia cada vértice
 * */
public class DistanciasDijkstra {

	void dijkstra(Grafo G, int origen) {
		
	    int n = G.cantidadVertices();

	    int[] dist = new int[n];
	    int[] padre = new int[n];
	    boolean[] enS = new boolean[n];

	    // Inicialización
	    for (int v = 0; v < n; v++) {
	        dist[v] = Integer.MAX_VALUE; // infinito
	        padre[v] = -1;               // indefinido
	        enS[v] = false;
	    }
	    dist[origen] = 0;

	    // Mientras queden vértices fuera de S
	    for (int k = 0; k < n; k++) {

	        int u = seleccionarMinNoEnS(dist, enS);
	        if (u == -1) break; // no alcanzables restantes

	        enS[u] = true; // S = S U {u}

	        // Relajación de vecinos de u
	        for (Arco a : G.adyacentes(u)) {
	            int v = a.destino();
	            int w = a.peso(); // peso(u, v)

	            if (!enS[v] && dist[u] != Integer.MAX_VALUE
	                    && dist[u] + w < dist[v]) {
	                dist[v] = dist[u] + w;
	                padre[v] = u;
	            }
	        }
	    }

	    imprimirResultados(origen, dist, padre);
	}

	int seleccionarMinNoEnS(int[] dist, boolean[] enS) {
	    int mejor = -1;
	    for (int v = 0; v < dist.length; v++) {
	        if (!enS[v]) {
	            if (mejor == -1 || dist[v] < dist[mejor]) {
	                mejor = v;
	            }
	        }
	    }
	    return mejor;
	}

	void imprimirResultados(int origen, int[] dist, int[] padre) {
	    for (int v = 0; v < dist.length; v++) {
	        System.out.print("Destino " + v + " | dist = " + dist[v] + " | camino: ");
	        imprimirCamino(origen, v, padre);
	        System.out.println();
	    }
	}

	void imprimirCamino(int origen, int v, int[] padre) {
	    if (v == -1) return;
	    if (v == origen) {
	        System.out.print(origen);
	    } else if (padre[v] == -1) {
	        System.out.print("no alcanzable");
	    } else {
	        imprimirCamino(origen, padre[v], padre);
	        System.out.print(" -> " + v);
	    }
	}

}
