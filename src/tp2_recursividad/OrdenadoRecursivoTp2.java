package tp2_recursividad;

public class OrdenadoRecursivoTp2 {

	//actividad 1
	
	public static boolean estaOrdenado(int[] arr) {
		return estaOrdenado(arr,0);
	}
	
	
	private static boolean estaOrdenado(int []arr, int i) {
		if(i>=arr.length-1) {   //llegamos al final
			return true;
		}
		if(arr[i]>arr[i+1]) {
			return false;      //esta desordenado
		}
		
		return estaOrdenado(arr,i+1);  //llamada recursiva
	}
	
	
	//actividad 2
	
	
	public static boolean buscarElemento(int[] arr, int valor) {
        return buscarElemento(arr, valor, 0, arr.length - 1);
    }

    private static boolean buscarElemento(int[] arr, int valor, int inicio, int fin) {
        if (inicio > fin) {
            return false; // Caso base: no está
        }

        int medio = (inicio + fin) / 2;

        if (arr[medio] == valor) {
            return true; // Encontrado
        } else if (valor < arr[medio]) {
            return buscarElemento(arr, valor, inicio, medio - 1); // Buscar en la mitad izquierda
        } else {
            return buscarElemento(arr, valor, medio + 1, fin); // Buscar en la mitad derecha
        }
    }
    
    
    public int decimalABinario(int numero) {
    	if(numero<0) {
    		return -1;
    	} else {
    		if(numero==0) {
    			return 0;
    		}else {
    			int mitad=numero/2;
    			int resto=numero-mitad*2;
    			return  decimalABinario(mitad)*10+resto;
    		}
    	}
    	
    	
    }
    
    
    

    //actividad 4 con arreglos
    public static int[] obtenerFibonacci(int n) {
        int[] fibos = new int[n];
        llenarFibonacci(fibos, 0);
        return fibos;
    }

    private static void llenarFibonacci(int[] arr, int i) {
        if (i >= arr.length) return;

        if (i == 0) {
            arr[0] = 0;
        } else if (i == 1) {
            arr[1] = 1;
        } else {
            arr[i] = arr[i - 1] + arr[i - 2];
        }

        llenarFibonacci(arr, i + 1);
    }
    
    //actividad 4 con string
    
    public static String fibonacciConString(int n) {
    	if(n<0) {
    		return "";
    	}
    	return fiboAux(n,0);
    }
    
    private static String fiboAux(int n, int i) {
    	if(i>n) {
    		return "";
    	}
    	return fibo(i)+"-"+fiboAux(n,i+1);
    }
    
    private static int fibo(int i) {
    	if(i==0) {
    		return 0;
    	}
    	if(i==1) {
    		return 1;
    	}
    	return fibo(i-1)+fibo(i-2);
    }
    
    //actividad 5
    
    public static boolean coincideValorPosicion(int []arr) {
    	return valorIgualPosicion(arr, 0);
    }
    
    private static boolean valorIgualPosicion(int[]arr, int i) {
    	   	
    	if(i>=arr.length) {
    		return false;
    	}
    	if(arr[i]>i) {
    		return false;   			
    	} else if(arr[i]==i) {
    		return true;
    	}else {
    		return valorIgualPosicion(arr, i+1);
    	}
    	
    }
    
    //actividad 6
    
    //ordenamiento por burbujeo en un arreglo. complejidad O(n^2).
    public static void bubbleSortAdapt(int[]arr) {
    	boolean intercambiado=true;
    	int j=0;
    	int tmp;
    	while(intercambiado) {
    		intercambiado=false;
    		j++;
    		for(int i=0; i<arr.length-j;i++) {
    			if(arr[i]>arr[i+1]) {
    				tmp=arr[i];
    				arr[i]=arr[i+1];
    				arr[i+1]=tmp;
    				intercambiado=true;
    			}
    		}
    	}
    }
    
	//ALGORITMO DE SELECCION TIENE UNA COMPLEJIDAD DE O(n^2).
    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

	
    //ordenamiento por mezcla mergeSort
     
}

