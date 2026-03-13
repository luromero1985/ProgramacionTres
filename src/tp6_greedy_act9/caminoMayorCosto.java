package tp6_greedy_act9;

import java.util.ArrayList;

public class caminoMayorCosto {

	private int [][]mat;

	
	
	
	public caminoMayorCosto(int[][] mat) {
	this.mat = mat;
	
	}


	public ArrayList<Celda> caminoDeMayorCosto(int [][] mat, Celda origen, Celda destino){
		
		ArrayList<Celda>camino=new ArrayList<>();
		int n= mat.length;
		boolean [][] visitados=new boolean [n][n];
		
		Celda actual =origen;
		camino.add(actual);
		visitados[actual.getFila()][actual.getCol()]=true;
		
		while(!actual.equals(destino)) {
			
			Celda mejor=null;
			int mayor=-1;
			
			ArrayList<Celda>vecinos=vecinos(actual);
			
			
			for(Celda celda: vecinos) {
				int f=celda.getFila();
				int c=celda.getCol();
				if(mat[f][c]>mat[actual.getFila()][actual.getCol()] && !visitados[f][c] && mat[f][c]>mayor) {
					mejor=celda;
					mayor=mat[f][c];
				}
			}
			
			if(mejor==null) {
				return null;
			}
			actual=mejor;
			visitados[actual.getFila()][actual.getCol()]=true;
			camino.add(actual);
		}
		
		return camino;
	}
	
	
	public  ArrayList<Celda> vecinos(Celda actual){
		ArrayList<Celda>vecinos=new ArrayList<>();
		int n= mat.length;
		int f=actual.getFila();
		int c= actual.getCol();
		
		if(f!=0) {
			vecinos.add(new Celda(f-1,c));
		}
		if(f!=n-1) {
			vecinos.add(new Celda(f+1,c));
		}
		
		if(c!=0) {
			vecinos.add(new Celda(f,c-1));
		}
		if(c!=n-1) {
			vecinos.add(new Celda(f,c+1));
		}
		
		
		return vecinos;
	}
}
