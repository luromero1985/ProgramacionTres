package tp1_listasSimplesyDoblementeVinculadas;

public class ListasCombinadasOrdenadas {

	public static <T extends Comparable<T>> MySimpleList<T> listaCombinadaYOrdenada(MySimpleList<T> l1, MySimpleList<T> l2){

		MySimpleList<T>list3=new MySimpleList<>();

		MyIterator<T> itl1 = l1.iterator();

		for(T elemento1: l1){

			MyIterator<T> itl2 = l2.iterator();

			boolean encontrado= false;
			while(itl2.hasNext() && !encontrado ){
				T elemento2= itl2.next();

				if(elemento1.equals(elemento2)){
					list3.insertarOrdenado(elemento1);
					encontrado=true;
				}
			}
		}
		return list3;
	}

	/*	Ejercicio 5 b
			Dos listas ordenadas. formar una lista con elementos comunes, ordenada
			En el Main creamos la funcion:*/

	public static <T extends Comparable<T>> MySimpleList<T> listasOrdenadasOrdenada (MySimpleList<T> l1, MySimpleList<T> l2){

		MySimpleList<T>list3=new MySimpleList<>();

		MyIterator<T> itl1 = l1.iterator();
		MyIterator<T> itl2 = l2.iterator();


		while (itl1.hasNext() && itl2.hasNext()) {
			T e1 = itl1.get();
			T e2 = itl2.get();

			int cmp = e1.compareTo(e2);

			if (cmp < 0) {
				itl1.next(); // e1 es menor → avanzar l1
			} else if (cmp > 0) {
				itl2.next(); // e2 es menor → avanzar l2
			} else {
				list3.agregarUtimo(e1); // son iguales → agregar a list3
				itl1.next();
				itl2.next();
			}
		}
		return list3;
	}



	/*Ejercicio 6: Escriba una funcion que dadas dos listas construya otra con los elementos que estan en la primera pero no en la segunda. 
			en el main:*/

	public static <T extends Comparable<T>>MySimpleList<T> elementosSoloPrimerLista(MySimpleList<T>l1, MySimpleList<T>l2){
	    MySimpleList<T> l3 = new MySimpleList<>();

	    for (T elemento1 : l1) {
	        boolean encontrado = false;

	        for (T elemento2 : l2) {
	            if (elemento1.equals(elemento2)) {
	                encontrado = true;
	                break;
	            }
	        }

	        if (!encontrado) {
	            l3.insertFront(elemento1);
	        }
	    }

	    return l3;
	}
	
	 
}
