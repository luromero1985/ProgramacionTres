package final_4_12_25;

import java.util.ArrayList;

public class Arbol {

	
	private Nodo<T> raiz;
	
	
	public Arbol() {
		this.raiz = null;
	}

	public void agregar(int clave, T elemento) {
	    raiz = agregarRec(raiz, clave, elemento);
	}

	private Nodo<T> agregarRec(Nodo<T> nodo, int clave, T elemento) {

	    // caso base: lugar vacío, creo nodo nuevo
	    if (nodo == null) {
	        Nodo<T> nuevo = new Nodo<T>();
	        nuevo.setClave(clave);
	        nuevo.setElementos(new ArrayList<>());
	        nuevo.getElementos().add(elemento);
	        return nuevo;
	    }

	    // clave ya existe → agrego a la lista
	    if (clave == nodo.getClave()) {
	        nodo.getElementos().add(elemento);

	    // clave menor → voy a la izquierda
	    } else if (clave < nodo.getClave()) {
	        nodo.setIzq(agregarRec(nodo.getIzq(), clave, elemento));

	    // clave mayor → voy a la derecha
	    } else {
	        nodo.setDer(agregarRec(nodo.getDer(), clave, elemento));
	    }

	    return nodo;
	}
	
	/*
	 * complejidad O(n) si es degenerado     n: cant nodos
	 *             O(log n) si está balanceado
	 *             O(h)  caso general     donde h: altura
	 * */
	
	
	
	
	public ArrayList<T> elementosPorNivel(int nivel) {
		ArrayList<T> resultado = new ArrayList<>();
	    elementosPorNivelRec(raiz, nivel, 0, resultado);
	    return resultado;
	}

	private void elementosPorNivelRec(Nodo<T> nodo, int nivelBuscado, int nivelActual, ArrayList<T> resultado) {

	    // caso base: nodo vacío
	    if (nodo == null) return;

	    // llegué al nivel buscado → agrego todos los elementos de la lista
	    if (nivelActual == nivelBuscado) {
	        resultado.addAll(nodo.getElementos());
	        return;
	    }

	    // sigo bajando por ambos lados
	    elementosPorNivelRec(nodo.getIzq(), nivelBuscado, nivelActual + 1, resultado);
	    elementosPorNivelRec(nodo.getDer(), nivelBuscado, nivelActual + 1, resultado);
	}
	
	
	/*
	 * complejidad O(n), ya que hay que en el peor caso, hay que visitar todos los nodos del arbol para llegar al último nivel
	 * */
	
	
	
	public int elementosHojas() {
	    return elementosHojasRec(raiz);
	}

	private int elementosHojasRec(Nodo<T> nodo) {

	    // caso base: nodo vacío
	    if (nodo == null) return 0;

	    // es hoja → retorno la cantidad de elementos de su lista
	    if (nodo.getIzq() == null && nodo.getDer() == null) {
	        return nodo.getElementos().size();
	    }

	    // no es hoja → sigo bajando por ambos lados
	    return elementosHojasRec(nodo.getIzq()) + elementosHojasRec(nodo.getDer());
	}
	
	/*
	 * complejidad O(n), ya que hay que visitar todos los nodos para buscar las hojas
	 * */
	
	
	
	public int maxElementos() {
	    return maxElementosRec(raiz);
	}

	private int maxElementosRec(Nodo<T> nodo) {

	    // caso base: nodo vacío
	    if (nodo == null) return 0;

	    // es hoja → no la consideramos, retorno 0
	    if (nodo.getIzq() == null && nodo.getDer() == null) return 0;

	    // obtengo el máximo de los subárboles izquierdo y derecho
	    int maxIzq = maxElementosRec(nodo.getIzq());
	    int maxDer = maxElementosRec(nodo.getDer());

	    // retorno el máximo entre: mi lista, el máximo izquierdo y el máximo derecho
	    int maxSubarboles;
	    if (maxIzq > maxDer)
	        maxSubarboles = maxIzq;
	    else
	        maxSubarboles = maxDer;

	    int miLista = nodo.getElementos().size();

	    if (miLista > maxSubarboles)
	        return miLista;
	    else
	        return maxSubarboles;
	}
	
	
	/*
	 * La complejidad es O(n) porque hay que visitar todos los nodos del árbol para encontrar el máximo.
	 * 
	 * */
	
	//////////////////////////////////////////
	
	public class Nodo<T>{
		private int clave;
		private ArrayList<T>elementos;
		private Nodo<T>izq;
		private Nodo<T>der;
		
		public Nodo() {
			this.clave=(Integer) null;
			this.elementos=new ArrayList<>();
			this.izq=null;
			this.der=null;
		}

		public int getClave() {
			return clave;
		}

		public void setClave(int clave) {
			this.clave = clave;
		}

		public ArrayList<T> getElementos() {
			return elementos;
		}

		public void setElementos(ArrayList<T> elementos) {
			this.elementos = elementos;
		}

		public Nodo<T> getIzq() {
			return izq;
		}

		public void setIzq(Nodo<T> izq) {
			this.izq = izq;
		}

		public Nodo<T> getDer() {
			return der;
		}

		public void setDer(Nodo<T> der) {
			this.der = der;
		}
		
		
		
	}
}
