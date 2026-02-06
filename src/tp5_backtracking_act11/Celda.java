package tp5_backtracking_act11;



public class Celda {
private int fila;
private int columna;


public Celda(int fila, int col) {
	this.columna=col;
	this.fila=fila;
}


public int getColumna() {
	return this.columna;
}


public void setColumna(int col) {
	this.columna=col;
}


public int getFila() {
	return fila;
}


public void setFila(int fila) {
	this.fila = fila;
}

}
