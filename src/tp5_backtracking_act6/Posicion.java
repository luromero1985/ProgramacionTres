package tp5_backtracking_act6;

	public class Posicion {

	    private int fila;
	    private int columna;

	    public Posicion(int fila, int columna) {
	        this.fila = fila;
	        this.columna = columna;
	    }

	    public int getFila() {
	        return fila;
	    }

	    public int getColumna() {
	        return columna;
	    }

	    // Dos posiciones son vecinas si difieren en una unidad horizontal o vertical
	    public boolean esVecina(Posicion otra) {
	        int df = Math.abs(this.fila - otra.fila);
	        int dc = Math.abs(this.columna - otra.columna);
	        return (df + dc) == 1;
	    }

	    @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (!(o instanceof Posicion)) return false;
	        Posicion p = (Posicion) o;
	        return fila == p.fila && columna == p.columna;
	    }

	    @Override
	    public String toString() {
	        return "(" + fila + ", " + columna + ")";
	    }
	}
