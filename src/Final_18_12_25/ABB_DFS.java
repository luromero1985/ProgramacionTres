package Final_18_12_25;

import java.util.ArrayList;

public class ABB_DFS {
	
	private Nodo raiz;
	private ArrayList<Integer>lista;
	
	/*
	 * Ejercicio 1
Escriba un algoritmo eficiente en JAVA que dado un árbol binario de búsqueda y dos valores de umbral M y N retorne una lista de todos los valores en el rango [M, N] contenidos en el árbol. La lista resultante debe estar ordenada de menor a mayor. 
Por ejemplo, para el árbol de la derecha y la entrada M = 6 y N = 17, el algoritmo debe dar como salida la lista [6, 7, 9, 13, 16, 16].

	 * */
	public ABB_DFS() {
		this.raiz=null;
	}
	
	public Nodo getRaiz() {
		return this.raiz;
	}

	
	
	public ArrayList<Integer> listaOrdenadaNodos(int m, int n){
		lista=new ArrayList<>();
		this.dfs(this.raiz, m, n);
		return lista;
	
	}
	
	private void dfs(Nodo nodo, int m, int n){
		
		if(nodo==null) {
			return;
		}
		
		if (nodo.getValor() > m) {
	        dfs(nodo.getIzq(), m, n);
		}
		
		 if (nodo.getValor() >= m && nodo.getValor() <= n) {
		        lista.add(nodo.getValor());
		 }
		 
		  if (nodo.getValor() < n) {
		        dfs(nodo.getDer(), m, n);
		}

	}
	
	
	
	
	public class Nodo{
		private Integer valor;
		private Nodo izq;
		private Nodo der;
		
		public Nodo(Integer valor) {
			this.valor=valor;
			this.der=null;
			this.izq=null;
			}

		public Nodo getIzq() {
			return izq;
		}

		public void setIzq(Nodo izq) {
			this.izq = izq;
		}

		public Nodo getDer() {
			return der;
		}

		public void setDer(Nodo der) {
			this.der = der;
		}

		public Integer getValor() {
			return valor;
		}

		public void setValor(Integer valor) {
			this.valor = valor;
		}
		
		
	}
	
	
}
