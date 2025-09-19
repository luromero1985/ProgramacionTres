package tp2_recursividad;

	public class QuickSort {
		
	    public void sort(int[] arr) {
	        quicksort(arr, 0, arr.length - 1);
	    }

	    private void quicksort(int[] arr, int low, int high) {
	        if (low < high) {
	            int pi = partition(arr, low, high);
	            quicksort(arr, low, pi - 1);
	            quicksort(arr, pi + 1, high);
	        }
	    }

	    private int partition(int[] arr, int low, int high) {
	        int pivot = arr[high]; // Elegimos el último elemento como pivote
	        int i = low - 1; // Índice del menor elemento

	        for (int j = low; j < high; j++) {
	            if (arr[j] < pivot) {
	                i++;
	                swap(arr, i, j);
	            }
	        }

	        swap(arr, i + 1, high); // Colocar el pivote en su lugar final
	        return i + 1; // Retornar índice del pivote
	    }

	    private void swap(int[] arr, int i, int j) {
	        int temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
	    }
	}

