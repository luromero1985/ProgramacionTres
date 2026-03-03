package tp6_greedy_act7;

/*
 * Ejercicio 7
Armando CDs. Dado un conjunto de archivos de canciones, donde cada uno tiene la información de
nombre, género, duración del tema, y tamaño en kilobytes, se desea grabar un disco CD (que tiene
una capacidad máxima de M kilobytes) de modo tal de:
● Variante A: Maximizar la capacidad ocupada del disco CD.
● Variante B: Maximizar la cantidad de canciones que se pueden grabar en el CD.
Para ambas variantes se quiere, además, que el CD no contenga más de 3 canciones de un mismo
género.

 * */

public class Cancion {

	private String nombre;
	private String genero;
	private int duracion;
	private int tamanio;
	
	
	
	public Cancion (String n, String g, int d, int t) {
		this.nombre=n;
		this.genero=g;
		this.duracion=d;
		this.tamanio=t;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getGenero() {
		return genero;
	}


	public void setGenero(String genero) {
		this.genero = genero;
	}


	public int getDuracion() {
		return duracion;
	}


	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}


	public int getTamanio() {
		return tamanio;
	}


	public void setTamanio(int tamanio) {
		this.tamanio = tamanio;
	}

	
	 @Override
	    public String toString() {
	        return nombre + " (" + genero + ") - " + tamanioKB + "KB";
	    }
	
}
