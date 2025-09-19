package tp2_arboles;

public class NodeVocales {

	private Character valor;
	private NodeVocales izquierda;
	private NodeVocales derecha;


	public NodeVocales(Character valor) {
		this.valor = valor;
		this.izquierda = null;
		this.derecha = null;
		
	}


	public Character getValor() {
		return valor;
	}


	public void setValor(Character valor) {
		this.valor = valor;
	}


	public NodeVocales getIzquierda() {
		return izquierda;
	}


	public void setIzquierda(NodeVocales izquierda) {
		this.izquierda = izquierda;
	}


	public NodeVocales getDerecha() {
		return derecha;
	}


	public void setDerecha(NodeVocales derecha) {
		this.derecha = derecha;
	}
	
	
}
