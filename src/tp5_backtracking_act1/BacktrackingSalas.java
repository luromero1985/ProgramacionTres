package tp5_backtracking_act1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import tp4_grafos.GrafoDirigido;
/*
 * Ejercicio 1
Se tiene un conjunto de salas comunicadas entre sí a través de puertas que se abren solamente en
un sentido. Una de las salas se denomina entrada y la otra salida. Construir un algoritmo que
permita ir desde la entrada a la salida atravesando la máxima cantidad de salas. Idea: podría
representar el problema mediante un grafo dirigido, donde cada nodo es una habitación, y cada
puerta es un arco dirigido hacia otra habitación.

 * */
public class BacktrackingSalas {

    private ArrayList<Integer> visitadas;
    private ArrayList<Integer> mejorRecorrido;
    private int mejorLongitud;

    private GrafoDirigido<Integer> grafo;
    private int entrada;
    private int salida;

    public BacktrackingSalas(GrafoDirigido<Integer> g, int entrada, int salida) {
        this.grafo = g;
        this.entrada = entrada;
        this.salida = salida;
        this.mejorRecorrido = new ArrayList<>();
        this.mejorLongitud = 0;
    }

    public ArrayList<Integer> buscarCaminoMaximo() {

        visitadas = new ArrayList<>();
        ArrayList<Integer> recorrido = new ArrayList<>();

        visitadas.add(entrada);
        recorrido.add(entrada);

        backtracking(entrada, recorrido);

        return mejorRecorrido;
    }

    private void backtracking(int salaActual, ArrayList<Integer> recorrido) {

    	// lista de vecinos
    	ArrayList<Integer> vecinos = new ArrayList<>();
    	Iterator<Integer> it = grafo.obtenerAdyacentes(salaActual);
    	
    	while (it.hasNext()) {
    	    vecinos.add(it.next());
    	}
        // PODA 1: si no hay forma de mejorar la mejor solución actual
        int nodosRestantes = grafo.cantidadVertices() - visitadas.size();
        if (recorrido.size() + nodosRestantes <= mejorLongitud) {
            return;
        }

        // Estado final
        if (salaActual == salida) {
            if (recorrido.size() > mejorLongitud) {
                mejorLongitud = recorrido.size();
                mejorRecorrido = new ArrayList<>(recorrido);
            }
            return;
        }

        // PODA 2: si la sala actual no tiene salidas (y no es la salida)
        if (vecinos.isEmpty()) {
            return;
        }

        // PODA 3: evitar expandir dos veces el mismo vecino
        HashSet<Integer> usadosEnEsteNivel = new HashSet<>();

        // Expandir vecinos
        for (Integer vecino : vecinos) {

            if (!visitadas.contains(vecino)) {
                if (!usadosEnEsteNivel.contains(vecino))
               
                	usadosEnEsteNivel.add(vecino);

            // Avanzar
                visitadas.add(vecino);
                recorrido.add(vecino);

                backtracking(vecino, recorrido);
            
            
            // Retroceder
            visitadas.remove(vecino);
            recorrido.remove(recorrido.size() - 1);
        }
    }
        
    }
    }
