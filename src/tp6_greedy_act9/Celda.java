package tp6_greedy_act9;

public class Celda {
private int fila;
private int col;


public Celda(int fila, int col) {
	this.fila = fila;
	this.col = col;
}


public int getFila() {
	return fila;
}


public void setFila(int fila) {
	this.fila = fila;
}


public int getCol() {
	return col;
}


public void setCol(int col) {
	this.col = col;
}


@Override
public boolean equals(Object obj) {
   Celda otra = (Celda) obj;
    return this.fila == otra.fila && this.col == otra.col;
}
}
