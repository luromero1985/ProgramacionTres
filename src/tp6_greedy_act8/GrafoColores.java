package tp6_greedy_act8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/*
 * Coloreo de un grafo. Dado un grafo se desea colorear cada uno de sus vértices utilizando la menor
cantidad posible de colores totales, sabiendo que dos vértices adyacentes no podrán utilizar el
mismo color
 * */
public class GrafoColores {
	
	public HashMap<Integer, Integer> colorearGrafo(Grafo<Integer> g) {

	    HashMap<Integer, Integer> colores = new HashMap<>();
	    ArrayList<Integer> vertices = new ArrayList<>();
	    Iterator<Integer> it = g.obtenerVertices();

	    while (it.hasNext()) {
	        vertices.add(it.next());
	    }

	    // 🔥 PRECALCULAMOS grados originales UNA sola vez
	    HashMap<Integer, Integer> cantAdy = new HashMap<>();
	    for (Integer v : vertices) {
	        cantAdy.put(v, calcularGrado(g, v));
	    }

	    while (colores.size() < vertices.size()) {

	        Integer elegido = null;
	        int mayorSaturacion = -1;
	        int mayorGrado = -1;

	        for (Integer v : vertices) {

	            if (!colores.containsKey(v)) {

	                int saturacion = calcularSaturacion(g, v, colores);
	                int calcularGrado = cantAdy.get(v); // 🔥 usamos el map

	                if (saturacion > mayorSaturacion ||
	                   (saturacion == mayorSaturacion && calcularGrado > mayorGrado)) {

	                    mayorSaturacion = saturacion;
	                    mayorGrado = calcularGrado;
	                    elegido = v;
	                }
	            }
	        }

	        int colorAsignado = menorColorDisponible(g, elegido, colores, vertices.size());

	        colores.put(elegido, colorAsignado);
	    }

	    return colores;
	}
	
	
	
	//elijo el vertice con vecinos con mas colores diferentes (estrategia greedy)
	private int calcularSaturacion(Grafo<Integer> g, Integer v, HashMap<Integer, Integer> colores) {

		HashSet<Integer> coloresVecinos = new HashSet<>();
		Iterator<Integer> ady = g.obtenerAdyacentes(v);

		while (ady.hasNext()) {
			Integer vecino = ady.next();
			if (colores.containsKey(vecino)) {
				coloresVecinos.add(colores.get(vecino));
			}
		}

		return coloresVecinos.size();
	}
	
	
	//este metodo permite comenzar con el que mas cant de vecinos tenga, sirve para el comienzo que no hay ninguno pintado (saturacion 0)
	private int calcularGrado(Grafo<Integer> g, Integer v) {

	    int contador = 0;
	    Iterator<Integer> ady = g.obtenerAdyacentes(v);

	    while (ady.hasNext()) {
	        ady.next();
	        contador++;
	    }

	    return contador;
	}
	
	
	
	private int menorColorDisponible(Grafo<Integer> g, Integer v, HashMap<Integer, Integer> colores, int totalVertices) {

		boolean[] coloresUsados = new boolean[totalVertices+1];

		Iterator<Integer> ady = g.obtenerAdyacentes(v);

		while (ady.hasNext()) {
			Integer vecino = ady.next();
			if (colores.containsKey(vecino)) {
				int colorVecino = colores.get(vecino);
				coloresUsados[colorVecino] = true;
			}
		}

		int color = 0;
		while (color < totalVertices && coloresUsados[color]) {
			color++;
		}

		return color;
	}
}