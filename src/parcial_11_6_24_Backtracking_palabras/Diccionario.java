package parcial_11_6_24_Backtracking_palabras;

public class Diccionario {
	private String palabra;
	
	public Diccionario() {
		this.palabra=new String();
	}
    	
    	public boolean esPalabraValida(String palabra) {
    		return this.palabra.equals(palabra);
    }
}
