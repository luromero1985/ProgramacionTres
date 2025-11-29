package tp5_Backtracking_act2;

import java.util.ArrayList;
import java.util.List;

public class Laberinto {
	  private Celda[][] matriz;
	  private int n;

	    Laberinto(int n) {
	        this.n = n;
	        this.matriz = new Celda[n][n];
	    }

	    public int getN() {
	        return n;
	    }

	    public void setCelda(int fila, int col, Celda c) {
	        this.matriz[fila][col] = c;
	    }

	    public int getValor(Posicion p) {
	        return matriz[p.getFila()][p.getCol()].getValor();
	    }

	    // Devuelve vecinos válidos según los booleanos de la celda
	    public List<Posicion> obtenerVecinos(Posicion p) {
	        List<Posicion> lista = new ArrayList<>();
	        Celda c = matriz[p.getFila()][p.getCol()];

	        if (c.isNorte() && p.getFila() > 0)
	            lista.add(new Posicion(p.getFila() - 1, p.getCol()));

	        if (c.isSur() && p.getFila() < n - 1)
	            lista.add(new Posicion(p.getFila() + 1, p.getCol()));

	        if (c.isOeste() && p.getCol() < n - 1)
	            lista.add(new Posicion(p.getFila(), p.getCol() + 1));

	        if (c.isOeste() && p.getCol() > 0)
	            lista.add(new Posicion(p.getFila(), p.getCol() - 1));

	        return lista;
	    }
}
