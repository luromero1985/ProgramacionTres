package final_4_12_25;
import java.util.ArrayList;
import java.util.Collections;

public class Dijkstra {
    private static final int INF = Integer.MAX_VALUE;
    private int costototal;

    public  ArrayList<Integer> dijkstra(int[][] grafo, int origen, int destino) {
        int n = grafo.length;
        int[] dist     = new int[n];
        int[] padre    = new int[n];
        boolean[] visitado = new boolean[n];


        //inicializo todas las distancias en infinito
       	for (int i = 0; i < n; i++) {
       	     dist[i]=INF;
       	     padre[i]=-1;
       	        }
       	       
        dist[origen] = 0;

        for (int i = 0; i < n; i++) {

            // Paso greedy: nodo no visitado con menor distancia
            int u = -1;
            for (int v = 0; v < n; v++) {
                if (!visitado[v] && (u == -1 || dist[v] < dist[u]))
                    u = v;
            }

            if (u == -1 || dist[u] == INF) break;
            visitado[u] = true;

            if (u == destino) break; // freno anticipado

            // Relajar vecinos
            for (int v = 0; v < n; v++) {
                if (grafo[u][v] != 0 && dist[u] + grafo[u][v] < dist[v]) {
                    dist[v] = dist[u] + grafo[u][v];
                    padre[v] = u;
                }
            }
        }

        if (dist[destino] == INF) {
            System.out.println("No hay camino de " + (origen+1) + " a " + (destino+1));
            return new ArrayList<>();
        }

        // Reconstruir camino
        ArrayList<Integer> camino = new ArrayList<>();
        int actual = destino;
        while (actual != -1) {
            camino.add(actual);
            actual = padre[actual];
        }
        Collections.reverse(camino);

        System.out.println("Camino: " + camino);
        System.out.println("Costo total: " + dist[destino]);

        this.costototal=dist[destino];
        return camino;
    }
    
    
    public int costoOrigenDestrino() {
    	return this.costototal;
    }

    public static void main(String[] args) {
    	
    	Dijkstra d=new Dijkstra();
        //        1    2    3    4    5    6    7    8
        int[][] grafo = {
            {  0,  14,   0,   1,   0,   0,   1,   2 },  // nodo 1
            { 14,   0,   3,   8,   2,   0,   3,   0 },  // nodo 2
            {  0,   3,   0,   4,   4,   2,   0,   0 },  // nodo 3
            {  1,   8,   4,   0,  15,   0,   0,   0 },  // nodo 4
            {  0,   2,   4,  15,   0,   1,   0,   0 },  // nodo 5
            {  0,   0,   2,   0,   1,   0,   0,   0 },  // nodo 6
            {  1,   3,   0,   0,   0,   0,   0,  11 },  // nodo 7
            {  2,   0,   0,   0,   0,   0,  11,   0 },  // nodo 8
        };

        // De computadora 5 (índice 4) a computadora 1 (índice 0)
        d.dijkstra(grafo, 4, 0);
        
        //Camino: [5, 2, 7, 1]
       // Costo total: 6
    }
}