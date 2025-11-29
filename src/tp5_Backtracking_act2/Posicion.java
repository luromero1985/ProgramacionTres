package tp5_Backtracking_act2;

import java.util.Objects;

public class Posicion {
	   private int fila;
	    private int col;

	    public Posicion(int fila, int col) {
	        this.fila = fila;
	        this.col = col;
	    }

	    @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (!(o instanceof Posicion)) return false;
	        Posicion p = (Posicion) o;
	        return fila == p.fila && col == p.col;
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(fila, col);
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
	    
	    
	}


