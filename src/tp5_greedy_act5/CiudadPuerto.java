package tp5_greedy_act5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CiudadPuerto {
	private static final int INF = 9999999;


    public void dijkstra(Grafo g, int origen, int[] dist, int[] padre) {

	    int n = g.cantidadVertices();
	    boolean[] usados = new boolean[n];

	    for (int i = 0; i < n; i++) {
	        dist[i] = INF;
	        padre[i] = -1;
	    }

	    dist[origen] = 0;

	    for (int k = 0; k < n; k++) {

	        int u = seleccionarMin(dist, usados);
	        if (u == -1) break;

	        usados[u] = true;

	        for (Arista a : g.vecinos(u)) {

	            int v = a.destino();
	            int peso = a.peso();

	            if (!usados[v] && dist[u] != INF && dist[u] + peso < dist[v]) {

	                dist[v] = dist[u] + peso;
	                padre[v] = u;
	            }
	        }
	    }
	}

	public int seleccionarMin(int[] dist, boolean[] usados) {

	    int min = INF;
	    int pos = -1;

	    for (int i = 0; i < dist.length; i++) {
	        if (!usados[i] && dist[i] < min) {
	            min = dist[i];
	            pos = i;
	        }
	    }

	    return pos;
	}

	
	
	 public void transporteMinimo(Grafo g, int[] puertos) {

	        int n = g.cantidadVertices();
	        int k = puertos.length;

	        int[][] distPuerto = new int[k][n];
	        int[][] padrePuerto = new int[k][n];

	        // 1) ejecutar Dijkstra desde cada puerto
	        for (int p = 0; p < k; p++) {
	            dijkstra(g, puertos[p], distPuerto[p], padrePuerto[p]);
	        }

	        // 2) elegir mejor puerto para cada ciudad
	        for (int ciudad = 0; ciudad < n; ciudad++) {

	            int mejorPuerto = 0;
	            int mejorDist = distPuerto[0][ciudad];

	            for (int p = 1; p < k; p++) {
	                if (distPuerto[p][ciudad] < mejorDist) {
	                    mejorDist = distPuerto[p][ciudad];
	                    mejorPuerto = p;
	                }
	            }

	            System.out.println("Ciudad " + ciudad +
	                               " -> Puerto " + puertos[mejorPuerto] +
	                               " costo " + mejorDist);

	            imprimirCamino(padrePuerto[mejorPuerto], ciudad);
	            System.out.println();
	        }
	    }

	    // ---------- RECONSTRUIR CAMINO ----------
	    private void imprimirCamino(int[] padre, int destino) {

	        List<Integer> camino = new ArrayList<>();
	        int actual = destino;

	        while (actual != -1) {
	            camino.add(actual);
	            actual = padre[actual];
	        }

	        Collections.reverse(camino);
	        System.out.print("Camino: " + camino);
	    }
	}
	}

	
	
