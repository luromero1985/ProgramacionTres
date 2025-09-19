package tp1_listasSimplesyDoblementeVinculadas;

public class Main {

	public static void main(String[] args) {
		MySimpleList<Integer>l1=new MySimpleList<>();
		MySimpleList<Integer>l2=new MySimpleList<>();

		//carga de lista 1 y lista 2
		l1.insertFront(5);
		l1.insertFront(1);
		l1.insertFront(3);
		l1.insertFront(6);
		l1.insertFront(2);
		

	
		l2.insertFront(3);
		l2.insertFront(6);
		l2.insertFront(2);
		l2.insertFront(4);
		
	
	
		
		 System.out.println("Lista 1: " + l1);
		 System.out.println("Lista 2: " + l2);
		
		  MySimpleList<Integer> l3 = ListasCombinadasOrdenadas.listaCombinadaYOrdenada(l1, l2);
	        System.out.println("Lista combinada y ordenada: " + l3);
	        
	  
	      MySimpleList<Integer> l4 = ListasCombinadasOrdenadas.listasOrdenadasOrdenada(l1, l2);
	        System.out.println("Elementos comunes en listas ordenadas: " + l4);

	      MySimpleList<Integer> l5 = ListasCombinadasOrdenadas.elementosSoloPrimerLista(l1, l2);
	        System.out.println("Elementos solo en la primera lista: " + l5);
	        
	        MyDoubleList<String> lista = new MyDoubleList<>();
	        lista.insertFront("C");
	        lista.insertFront("B");
	        lista.insertFront("A");
	        lista.agregarUltimo("D"); // A-B-C-D

	   	 System.out.println("Lista: " + lista);
	}
	
		}


