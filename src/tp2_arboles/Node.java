package tp2_arboles;

public class Node {

		private Integer valor;
		private Node izquierda;
		private Node derecha;
		private Node anterior; //ESTA BIEN LLEVAR UN PUNTERO AL ANTERIOR. NO ROMPE ESTRUCTURA!! ES COMO UNA LISTA DOBLEMENTE VINCULADA

		public Node(Integer valor) {
			this.valor = valor;
			this.izquierda = null;
			this.derecha = null;
			this.anterior = null;
		}
		
		public Node getAnterior() {
			return anterior;
		}


		public void setAnterior(Node anterior) {
			this.anterior = anterior;
		}



		public Node getIzq() {
			return izquierda;
		}

		public void setIzq(Node izquierda) {
			this.izquierda = izquierda;
		}

		public Node getDer() {
			return derecha;
		}

		public void setDer(Node derecha) {
			this.derecha = derecha;
		}

		public Integer getValor() {
			return valor;
		}
		
		public void setValor(int valor) {
			this.valor = valor;
		}
	}


