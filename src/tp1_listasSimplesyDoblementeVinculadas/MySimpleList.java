package tp1_listasSimplesyDoblementeVinculadas;

public class MySimpleList<T> implements Iterable<T> {
	private Node<T> first;
	private int size;

	public MySimpleList() {
		this.first = null;
		this.size=0;
	}

	public void insertFront(T info) {
		Node<T> tmp = new Node<T>(info,null);
		tmp.setNext(this.first);
		this.first = tmp;
		this.size=this.size+1;
	}

	public T extractFront() {		
		   T info = this.first.getInfo(); 
		    this.first = this.first.getNext();
		    this.size--;
		    return info;
	}

	public boolean isEmpty() {      
		return this.first==null;
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


	public int size() {
		return this.size;
	}

/*	@Override
	public String toString() {
		Node<T> tmp =this.first;
		String info="";
		while(tmp!=null){
			info+=tmp.getInfo().toString();
			tmp=tmp.getNext();
		}
		return info;
	}
*/
	//toString usando iterator
	@Override
	public String toString() {
	    String info = "";
	    for (T elemento : this) {  // Usa el iterador en un for-each
	        info += elemento.toString();
	    }
	    return info;
	}


	public Node<T> getFirst(){
		return this.first;
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



	public void agregarUtimo(T nuevo){
	    Node<T> nuevoNodo = new Node<T>(nuevo, null);

	    if (this.first == null) {
	        this.first = nuevoNodo;
	    } else {
	        Node<T> temp = this.first;
	        while (temp.getNext() != null) {
	            temp = temp.getNext();
	        }
	        temp.setNext(nuevoNodo);
	    }
	}
	
	public void insertarOrdenado(T nuevo) {
	    if (this.isEmpty()) {  // Si la lista está vacía, insertamos al inicio
	        this.insertFront(nuevo);
	        return;
	    }

	    Node<T> temp = this.first;
	    
	    // Si el nuevo valor es menor o igual al primer nodo, insertamos al frente
	    if (((Comparable<T>) nuevo).compareTo(temp.getInfo()) <= 0) {
	        this.insertFront(nuevo);
	        return;
	    }

	    // Recorrer hasta encontrar el lugar correcto
	    while (temp.getNext() != null && ((Comparable<T>) nuevo).compareTo(temp.getNext().getInfo()) > 0) {
	        temp = temp.getNext();
	    }

	    // Insertar el nuevo nodo en la posición correcta
	    Node<T> nuevoNodo = new Node<T>(nuevo, temp.getNext());
	    temp.setNext(nuevoNodo);
	}
	
	public MyIterator<T> iterator(){
		return new MyIterator<T>(this.first);
	}
}
