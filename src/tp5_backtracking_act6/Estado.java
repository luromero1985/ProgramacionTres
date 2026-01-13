package tp5_backtracking_act6;

import java.util.ArrayList;
import java.util.List;

	public class Estado {

	    private boolean[][] visitadas;
	    private Posicion actual;
	    private Posicion origen;
	    private List<Posicion> camino;
	    private int cantSinPasto;

	    public Estado(int n, Posicion origen, int cantSinPasto) {
	        this.visitadas = new boolean[n][n];
	        this.actual = origen;
	        this.origen = origen;
	        this.cantSinPasto = cantSinPasto;

	        this.camino = new ArrayList<>();
	        this.camino.add(origen);
	        visitadas[origen.getFila()][origen.getColumna()] = true;
	    }

	    public boolean hayMovimientos() {
	        return camino.size() < cantSinPasto;
	    }

	    public boolean vecinaOrigen() {
	        return actual.esVecina(origen);
	    }

	    public boolean esFactible(Posicion sig) {
	        int f = sig.getFila();
	        int c = sig.getColumna();

	        // no salir del tablero
	        if (f < 0 || f >= visitadas.length || c < 0 || c >= visitadas.length)
	            return false;

	        // no repetir casilla
	        if (visitadas[f][c])
	            return false;

	        return true;
	    }

	    public Estado aplicarMov(Posicion sig) {
	        Estado nuevo = new Estado(visitadas.length, origen, cantSinPasto);

	        // copiar visitadas
	        for (int i = 0; i < visitadas.length; i++)
	            for (int j = 0; j < visitadas.length; j++)
	                nuevo.visitadas[i][j] = this.visitadas[i][j];

	        // copiar camino
	        nuevo.camino = new ArrayList<>(this.camino);

	        // aplicar movimiento
	        nuevo.actual = sig;
	        nuevo.visitadas[sig.getFila()][sig.getColumna()] = true;
	        nuevo.camino.add(sig);

	        return nuevo;
	    }

	    public Posicion getActual() {
	        return actual;
	    }

	    public List<Posicion> getCamino() {
	        return camino;
	    }

	    public int cantSinPasto() {
	        return cantSinPasto;
	    }
	}
