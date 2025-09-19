package tp2_arboles;

import java.util.ArrayList;

public class ArbolVocales {
	    private NodeVocales raiz;

	    public ArbolVocales() {
	        this.raiz = null;
	    }

	    public ArrayList<String> palabrasConNVocales(int n) {
	        ArrayList<String> resultado = new ArrayList<>();
	        if (this.raiz != null) {
	            buscarPalabras(this.raiz, "", resultado, n);
	        }
	        return resultado;
	    }

	    
	    private void buscarPalabras(NodeVocales nodo, String palabraActual, ArrayList<String> resultado, int n) {
	        if (nodo == null) {
	        	return;
	        }

	        palabraActual += nodo.getValor(); // Suponiendo getValor() devuelve String o char

	        if (nodo.getIzquierda() == null && nodo.getDerecha() == null) {
	            // Es hoja
	            if (contarVocales(palabraActual) == n) {
	                resultado.add(palabraActual);
	            }
	            return;
	        }

	        buscarPalabras(nodo.getIzquierda(), palabraActual, resultado, n);
	        buscarPalabras(nodo.getDerecha(), palabraActual, resultado, n);
	    }

	    private int contarVocales(String palabra) {
	        int cantidadDeVocales = 0;

	        // Convertimos la palabra a minúsculas para no preocuparnos por mayúsculas
	        palabra = palabra.toLowerCase();

	        for (int i = 0; i < palabra.length(); i++) {
	            char letra = palabra.charAt(i); 

	            if (letra == 'a' || letra == 'e' || letra == 'i' || letra == 'o' || letra == 'u') {
	                cantidadDeVocales++;
	            }
	        }

	        return cantidadDeVocales;
	    }

	    
	    
	    //version mas simple, con devolución del array
	    public ArrayList<ArrayList<Character>> ramasConNVocales(int n) {
	    	ArrayList<ArrayList<Character>> resultado = new ArrayList<>();

	        if (this.raiz != null) {
	            buscarRamas(this.raiz, new ArrayList<>(), 0, resultado, n);
	        }

	        return resultado;
	    }

	    private void buscarRamas(NodeVocales nodo, ArrayList<Character> ramaActual, int vocalesActuales, ArrayList<ArrayList<Character>> resultado, int n) {
	        if (nodo == null) return;

	        // Agregamos el carácter del nodo a la rama actual
	        ramaActual.add(nodo.getValor());

	        // Contamos si es vocal
	        if (esVocal(nodo.getValor())) {
	            vocalesActuales++;
	        }

	        // Si es una hoja
	        if (nodo.getIzquierda() == null && nodo.getDerecha() == null) {
	            if (vocalesActuales == n) {
	                // Si la cantidad de vocales es la pedida, agregamos una copia de la rama
	                resultado.add(new ArrayList<>(ramaActual));
	            }
	        } else {
	            // Recorremos los hijos
	            buscarRamas(nodo.getIzquierda(), ramaActual, vocalesActuales, resultado, n);
	            buscarRamas(nodo.getDerecha(), ramaActual, vocalesActuales, resultado, n);
	        }

	        // Importante: retroceder (backtrack) para no modificar otras ramas
	        ramaActual.remove(ramaActual.size() - 1);
	    }

	    private boolean esVocal(char c) {
	        c = Character.toLowerCase(c);
	        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
	    }
	}


