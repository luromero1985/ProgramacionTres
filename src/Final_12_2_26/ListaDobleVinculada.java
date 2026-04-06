package Final_12_2_26;

/*
 * Muestre en JAVA la declaración de la clase Lista doblemente vinculada, sus atributos de instancia y la implementación lo más eficiente 
 * que pueda de sólo los siguientes dos métodos:
 * 
 * public void agregarPosicion(<T> elemento, int posicion)
 * 
 * public bool eliminarElemento(<T> elemento)   /// devuelve true si el elemento existía sino false
 * 
 * 
 * Para ambos métodos indique su complejidad temporal en notación Big-O.
 * 
 * agregarPosicion es O(n) en el caso general, pero la búsqueda del nodo empieza desde el extremo más cercano (first o last según si la
 *  posición está en la primera o segunda mitad), lo que lo hace dos veces más rápido en la práctica. Los casos borde de inicio y fin
 *   son O(1) gracias al atributo last.
 *   eliminarElemento es O(n) porque hay que recorrer hasta encontrar el elemento. Una vez encontrado, el revinculado es O(1) gracias a
 *    los punteros prev y next, que es justamente la ventaja de la lista doble sobre la simple.
 * */

public class ListaDobleVinculada<T> {

	private Nodo<T> first;
	private Nodo<T>last;
	private int size;
	
	
	
	public void agregarPosicion(T elemento, int posicion) {
	    if (posicion < 0 || posicion > size)
	        return;

	    Nodo<T> nuevo = new Nodo<>(elemento, null, null);

	    // Caso lista vacía o agregar al inicio
	    if (posicion == 0) {
	        nuevo.setNext(first);
	        if (first != null)
	            first.setPrev(nuevo);
	        first = nuevo;
	        if (size == 0)
	            last = nuevo;
	        size++;
	        return;
	    }

	    // Caso agregar al final
	    if (posicion == size) {
	        nuevo.setPrev(last);
	        last.setNext(nuevo);
	        last = nuevo;
	        size++;
	        return;
	    }

	    // Caso medio: buscar desde el extremo más cercano
	    Nodo<T> actual;
	    if (posicion <= size / 2) {
	        actual = first;
	        for (int i = 0; i < posicion - 1; i++)
	            actual = actual.getNext();
	    } else {
	        actual = last;
	        for (int i = size - 1; i > posicion - 1; i--)
	            actual = actual.getPrev();
	    }

	    // actual es el nodo anterior a donde insertamos
	    nuevo.setNext(actual.getNext());
	    nuevo.setPrev(actual);
	    actual.getNext().setPrev(nuevo);
	    actual.setNext(nuevo);
	    size++;
	}
	
	
	
	public boolean eliminarElemento(T elemento) {
	    Nodo<T> actual = first;

	    while (actual != null) {
	        if (actual.getInfo().equals(elemento)) {

	            // Actualizar anterior
	            if (actual.getPrev() != null)
	                actual.getPrev().setNext(actual.getNext());
	            else
	                first = actual.getNext(); // era el primero

	            // Actualizar siguiente
	            if (actual.getNext() != null)
	                actual.getNext().setPrev(actual.getPrev());
	            else
	                last = actual.getPrev(); // era el último

	            size--;
	            return true;
	        }
	        actual = actual.getNext();
	    }
	    return false;
	}
	
	
	
	public class Nodo<T>{
		private T info;
		private Nodo<T> next;
		private Nodo<T> prev;
	
	
	public Nodo(){
		this.info=null;
		this.next=null;
		this.prev=null;	
	}
	
	public Nodo(T info, Nodo<T>sig){
		this.info=info;
		this.next=sig;	
	}
	 
		public Nodo(T info, Nodo<T> next, Nodo<T> prev) {
		    this.info = info;
		    this.next = next;
		    this.prev = prev;
		}	
	

	public T getInfo() {
		return info;
	}

	public void setInfo(T info) {
		this.info = info;
	}

	public Nodo<T> getNext() {
		return next;
	}

	public void setNext(Nodo<T> siguiente) {
		this.next = siguiente;
	}

	public Nodo<T> getPrev() {
		return prev;
	}

	public void setPrev(Nodo<T> anterior) {
		this.prev = anterior;
	}
	
	
	
	}
	
}
