package tp2_recursividad;

public class Main {

	    public static void main(String[] args) {
	      int[] arreglo1 = {1, 2, 3, 4, 9};
	        int[] arreglo2 = {5, 4, 3, 2, 1};
	        int[] arreglo3 = {2, 2, 3, 3, 4}; // también válido si se permite <=
	        int[] arreglo4 = {1, 3, 2, 4, 5};
	        int[] arreglo5 = {-5, 0, 2, 4, 5};

	        System.out.println("Arreglo 1 está ordenado? " + OrdenadoRecursivoTp2.estaOrdenado(arreglo1)); // true
	        System.out.println("Arreglo 2 está ordenado? " + OrdenadoRecursivoTp2.estaOrdenado(arreglo2)); // false
	        System.out.println("Arreglo 3 está ordenado? " + OrdenadoRecursivoTp2.estaOrdenado(arreglo3)); // true
	        System.out.println("Arreglo 4 está ordenado? " + OrdenadoRecursivoTp2.estaOrdenado(arreglo4)); // false
	        
	        System.out.println("Arreglo 1, esta elemento 3? "+OrdenadoRecursivoTp2.buscarElemento(arreglo1,3)); //true
	        System.out.println("Arreglo 1, esta elemento 8? "+OrdenadoRecursivoTp2.buscarElemento(arreglo1,8)); //false
	        System.out.println("fibonacci con arreglos");
	        int[] fibo=OrdenadoRecursivoTp2.obtenerFibonacci(10);
	   	        for (int num : fibo) {
	            System.out.print(num + " ");
	        }
	   	       
	   	     OrdenadoRecursivoTp2.fibonacciConString(7);
	   	     System.out.println( "\n Fibonacci con String :"+OrdenadoRecursivoTp2.fibonacciConString(7));
	   	     
	   	     System.out.println("coincide valor con posicion en arreglo 5 : "+OrdenadoRecursivoTp2.coincideValorPosicion(arreglo5)); //true
	   	  System.out.println("coincide valor con posicion en arreglo 1 : "+OrdenadoRecursivoTp2.coincideValorPosicion(arreglo1)); //false
	    
	    
	    //MERGESORT
	          int[] miArreglo = {38, 27, 43, 3, 9, 82, 10};

	          MergeSort ms = new MergeSort();
	          int[] resultado = ms.sort(miArreglo);

	          System.out.println("Arreglo ordenado:");
	          for (int num : resultado) {
	              System.out.print(num + " ");
	          }
	          
	          
	          
	          
	          QuickSort sorter = new QuickSort();
	          sorter.sort(miArreglo);

	          System.out.println("\n \n Arreglo ordenado con QuickSort:");
	          
	          for (int num : miArreglo) {
	              System.out.print(num + " ");
	          }
	        
	      }
	      }
	    
	    
	




