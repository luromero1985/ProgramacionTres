package tp5_backtracking_act11;

import java.util.ArrayList;

public class Robot {

	private Celda posicionCarga;
	private int [][] matriz;
	private ArrayList<Celda> mejorRecorrido;
	private boolean[][] visitados;
	
	
	
			
	public ArrayList<Celda> buscarRecorrido(Celda origen) {
	    mejorRecorrido = new ArrayList<>();
	    visitados = new boolean[matriz.length][matriz[0].length];

	    backCamino(origen, new ArrayList<>());

	    return mejorRecorrido;
	}


	
	
	private void backCamino(Celda actual, ArrayList<Celda> camino) {

	    // si ya es peor que el mejor, corto
	    if (!mejorRecorrido.isEmpty() && camino.size() >= mejorRecorrido.size()) {
	        return;
	    }

	    if (actual.equals(posicionCarga)) {
	        camino.add(actual);
	        mejorRecorrido = new ArrayList<>(camino);
	        camino.remove(camino.size() - 1);
	        return;
	    }

	    visitados[actual.getFila()][actual.getColumna()] = true;
	    camino.add(actual);

	    for (Celda v : getVecinos(actual)) {
	        if (!visitados[v.getFila()][v.getColumna()]) {
	            backCamino(v, camino);
	        }
	    }

	    camino.remove(camino.size() - 1);
	    visitados[actual.getFila()][actual.getColumna()] = false;
	}
	
	
	private ArrayList<Celda> getVecinos(Celda c) {
	    ArrayList<Celda> vecinos = new ArrayList<>();

	    int f = c.getFila();
	    int col = c.getColumna();

	    // arriba
	    if (f - 1 >= 0 && matriz[f - 1][col] == 0) {
	        vecinos.add(new Celda(f - 1, col));
	    }

	    // abajo
	    if (f + 1 < matriz.length && matriz[f + 1][col] == 0) {
	        vecinos.add(new Celda(f + 1, col));
	    }

	    // izquierda
	    if (col - 1 >= 0 && matriz[f][col - 1] == 0) {
	        vecinos.add(new Celda(f, col - 1));
	    }

	    // derecha
	    if (col + 1 < matriz[0].length && matriz[f][col + 1] == 0) {
	        vecinos.add(new Celda(f, col + 1));
	    }

	    return vecinos;
	}

}
