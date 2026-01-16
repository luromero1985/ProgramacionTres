package tp5_backtracking_act9;
//---------------------------------------------------------------//
	/*
	 * Dado un tablero de 4*4, en cuyas casillas se encuentran
	 * desordenados los números enteros del 1 al 15 y una casilla
	 * desocupada en una posición inicial dada, determinar una secuencia
	 * de pasos tal que intercambiando números contiguos (en 
	 * horizontal y en vertical) con la casilla desocupada, los
	 * números en el tablero queden ordenados de menor a mayor y la casilla
	 * desocupada quede en la posición 4,4.
	 * 
	 * Ejemplo:
	 * |1 |2 |3 |4 |
	 * |5 |6 |7 |8 |
	 * |9 |10|11|12|
	 * |13|14|15|  |
	 * 
	 *  * Ejemplo:
	 * |13|12 |3 |14|
	 * |5 |4 |7 |8 |
	 * |  |11|15| 2|
	 * |1|6 |10| 9 |
	 */
//---------------------------------------------------------------//
	
public class TableroNumerico {

	private int[][]matriz; 
	private Posicion celda;
	private int n;
	private boolean[] usados;
	
	public int [][] resolver(int n, int[][]cuadro, Posicion celda){
		this.n=n; 
		this.celda=celda;
		this.usados=new boolean[n*n];
		this.matriz=copia(cuadro);
		
		
		this.back(celda,matriz )
		return matriz;
	}
	
	
	private 
	public int[][] copia(int[][]matriz){
		int[][]copia=new int[n][n];
		for(int fila=0; fila<n; fila++) {
			for(int col=0; col<n; col++) {	
				copia[fila][col]=matriz[fila][col];
			}
		}
		return copia;
	}
}
