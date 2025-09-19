package tp1_listasSimplesyDoblementeVinculadas;

import java.util.Iterator;

public class MyIterator<T> implements Iterator<T>{
   	private Node<T> cursor;

   	public MyIterator(Node<T> cursor) {
   		this.cursor=cursor;
   	 }

public boolean hasNext() {
 		return cursor !=null;
}

public T next() {
 T tmp=cursor.getInfo();
 		 cursor=cursor.getNext();
  return tmp;
}

public T get() {
   		return cursor.getInfo();
}

}








