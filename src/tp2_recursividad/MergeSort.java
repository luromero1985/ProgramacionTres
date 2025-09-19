package tp2_recursividad;

public class MergeSort {
	private int[] arr;
	private int[] helper;
	private int size;


	public int[] getArr() {
		return arr;
	}
	public void setArr(int[] arr) {
		this.arr = arr;
	}
	public int[] getHelper() {
		return helper;
	}
	public void setHelper(int[] helper) {
		this.helper = helper;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}	
		

	public int[]  sort(int[]values){
		this.arr=values;
		this.size=values.length;
		this.helper=new int[size];
		this.mergesort(0,size-1);
		 return this.arr;
	}


	private void mergesort(int low, int high){
		//si low es menor que high continua ordenamiento
		//si low no es menor que high entonces el array esta ordenado.
		if(low<high){
			//busco el indice del elemento del medio, con int se Redondea al menor
			int middle=(low+high)/2;

			//metodo recusivo para ordenar la mitad izq 
			this.mergesort(low, middle);

			//metodo recusivo para ordenar la mitad der
			this.mergesort(middle+1, high);

			//combina ambas mitades (izq y derecha) ordenadas.
			this.merge(low, middle, high);
		}
	}


	private void merge(int low, int middle, int high){
		//copio ambas mitades al array helper
		for(int i=low; i<=high; i++){
			helper[i]=arr[i];
		}
		int i = low;
		int j = middle + 1; 
		int k = low;
		// copiar de manera ordenada al array original los valores de la mitad izquierda o de la derecha
		while (i <= middle && j <= high) {
			if (helper[ i ] <= helper[ j ]) { 
				arr[ k ] = helper[ i ]; 
				i++;
			}
			else {
				arr[ k ] = helper[ j ]; 
				j++;
			}
			k++;
		}
		// si quedaron elementos copiarlos al array original
		while (i <= middle) { 
			arr[ k ] = helper[ i ]; 
			k++;
			i++; 
		}
		while (j <= high) { 
			arr[ k ] = helper[ j ]; 
			k++;
			j++;
		}
	} 



}
