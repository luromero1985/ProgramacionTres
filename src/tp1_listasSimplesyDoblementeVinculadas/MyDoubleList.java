package tp1_listasSimplesyDoblementeVinculadas;

public class MyDoubleList<T>  implements Iterable<T>{
	
	private Node<T> first;
	private Node<T> last; // opcional, útil para agregar al final en O(1)
	private int size;
	
	public MyDoubleList() {
		this.first = null;
		this.last=null;
		this.size=0;
	}
	
	public boolean isEmpty() {      
		return this.first==null;
	}
	

	public int size() {
		return this.size;
	}
	
	@Override
	public String toString() {
	    String info = "";
	    for (T elemento : this) {  // Usa el iterador en un for-each
	        info += elemento.toString();
	    }
	    return info;
	}

	public T get(int index) {
		if(index<0 || index>=this.size()){
			return null;
		}
		int i=0;
		Node<T> auxiliar = this.first;
		while(index<this.size() && i<index){
			auxiliar=auxiliar.getNext();
			i++;
		}

		return auxiliar.getInfo();
	}


	
	public MyIterator<T> iterator(){
		return new MyIterator<T>(this.first);
	}
	
	
	public Node<T> getFirst(){
		return this.first;
	}
	public void insertFront(T info) {
		Node<T> nuevo = new Node<>(info, this.first, null);
		if (this.first != null) {
			this.first.setPrev(nuevo);
		}
		this.first = nuevo;
		if (this.last == null) {
			this.last = nuevo; // si era la primera inserción
		}
		this.size++;
	}
	
	public int indexOf(T elem){
		int i=0;
		Node<T>tmp=this.first;
		while(i<this.size()){
			if(tmp.getInfo().equals(elem)){
				return i;
			}
			tmp=tmp.getNext();
			i++;
		}
		return -1;
	}
	
	
	public void agregarUltimo(T info) {
		Node<T> nuevo = new Node<>(info, null, this.last);
		if (this.last != null) {
			this.last.setNext(nuevo);
		} else {
			this.first = nuevo; // lista vacía
		}
		this.last = nuevo;
		this.size++;
	}
	
	
	public T extractFront() {
		if (this.first == null) return null;

		T info = this.first.getInfo();
		this.first = this.first.getNext();

		if (this.first != null) {
			this.first.setPrev(null);
		} else {
			this.last = null; // la lista quedó vacía
		}
		this.size--;
		return info;
	}
	
	
	public void insertarOrdenado(T nuevo) {
	    if (this.isEmpty()) {
	        this.insertFront(nuevo);
	        return;
	    }

	    Node<T> temp = this.first;

	    if (((Comparable<T>) nuevo).compareTo(temp.getInfo()) <= 0) {
	        this.insertFront(nuevo);
	        return;
	    }

	    while (temp.getNext() != null &&
	           ((Comparable<T>) nuevo).compareTo(temp.getNext().getInfo()) > 0) {
	        temp = temp.getNext();
	    }

	    Node<T> siguiente = temp.getNext();
	    Node<T> nuevoNodo = new Node<T>(nuevo, siguiente, temp);
	    temp.setNext(nuevoNodo);

	    if (siguiente != null) {
	        siguiente.setPrev(nuevoNodo);
	    } else {
	        this.last = nuevoNodo; // estaba al final
	    }

	    this.size++;
	}
	
	public T extractLast() {
	    if (this.last == null) return null;
	    T info = this.last.getInfo();
	    this.last = this.last.getPrev();
	    if (this.last != null) {
	        this.last.setNext(null);
	    } else {
	        this.first = null;
	    }
	    this.size--;
	    return info;
	}
}
