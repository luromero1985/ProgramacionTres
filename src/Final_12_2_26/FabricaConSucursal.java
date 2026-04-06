package Final_12_2_26;

import java.util.ArrayList;
import java.util.Iterator;

import tp4_grafos.Grafo;

/*
 * Ejercicio 1
 * 
 * Dado un grafo no dirigido G(V, A) que representa las ciudades y rutas de la provincia de Buenos Aires, donde los arcos del grafo 
 * indican la distancia en kilómetros entre dos ciudades.
 * 
 * En 150 ciudades de la provincia hay sucursales de una cadena de mueblerías. Dicha cadena de mueblerías tiene dos fábricas, una ubicada
 *  en Tandil y la otra en La Plata.
 *  
 *  Se le pide diseñar un algoritmo que determine de forma eficiente de qué fábrica conviene abastecer cada sucursal, teniendo en cuenta 
 *  que la distancia en kilómetros debe ser la menor posible.
 *  
 *  a) Describa claramente con sus palabras el algoritmo que aplicaría para resolver el problema de forma eficiente y cómo se aplicaría para
 *   resolver el problema dado. Mencione también a qué técnica algorítmica pertenece la solución planteada.
 *   
 *  b) Escribir un código JAVA que resuelva el problema.
 * */


public class FabricaConSucursal {
	
	private ArrayList<Integer>asignacionTandil;
	private ArrayList<Integer>asignacionLaPLata;

	    // Dijkstra desde un origen, devuelve distancias mínimas a todos los nodos
	    private int[] dijkstra(int[][] grafo, int origen) {
	        int n = grafo.length;
	        int[] dist = new int[n];
	        boolean[] visitado = new boolean[n];

	     // inicializamos todas las distancias en infinito
	        for (int i = 0; i < n; i++) {
	            dist[i] = Integer.MAX_VALUE;
	        }
	        dist[origen] = 0;

	        for (int i = 0; i < n; i++) {
	            // elegimos el nodo no visitado más cercano
	            int u = -1;
	            for (int j = 0; j < n; j++) {
	                if (!visitado[j] && (u == -1 || dist[j] < dist[u])) {
	                    u = j;
	                }
	            }

	            if (dist[u] == Integer.MAX_VALUE) break; // nodos inaccesibles
	            visitado[u] = true;

	            // actualizamos distancias de los vecinos
	            for (int v = 0; v < n; v++) {
	                if (grafo[u][v] > 0 && dist[u] + grafo[u][v] < dist[v]) {
	                    dist[v] = dist[u] + grafo[u][v];
	                }
	            }
	        }
	        return dist;
	    }

	    
	    
	    public void asignarFabricas(int[][] grafo, int tandil, int laPlata, int[] sucursales) {
	        // corremos Dijkstra desde cada fábrica
	        int[] distTandil  = dijkstra(grafo, tandil);
	        int[] distLaPlata = dijkstra(grafo, laPlata);
	        this.asignacionLaPLata=new ArrayList<>();
	        this.asignacionTandil=new ArrayList<>();
	        // para cada sucursal comparamos y asignamos
	        for (int sucursal : sucursales) {
	            if (distTandil[sucursal] <= distLaPlata[sucursal]) {
	            	
	            	asignacionTandil.add(sucursal);
	            } else {
	            	asignacionLaPLata.add(sucursal);
	            }
	        }
	    
	    }
		
		
		
	
}
