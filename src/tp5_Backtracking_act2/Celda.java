package tp5_Backtracking_act2;

public class Celda {
	   private int valor;         // valor natural de la celda
	   private boolean norte;     // true = se puede ir al norte
	   private boolean sur;
	   private boolean este;
	   private boolean oeste;

	   public Celda(int valor, boolean norte, boolean sur, boolean este, boolean oeste) {
	        this.valor = valor;
	        this.norte = norte;
	        this.sur = sur;
	        this.este = este;
	        this.oeste = oeste;
	    }

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public boolean isNorte() {
		return norte;
	}

	public void setNorte(boolean norte) {
		this.norte = norte;
	}

	public boolean isSur() {
		return sur;
	}

	public void setSur(boolean sur) {
		this.sur = sur;
	}

	public boolean isEste() {
		return este;
	}

	public void setEste(boolean este) {
		this.este = este;
	}

	public boolean isOeste() {
		return oeste;
	}

	public void setOeste(boolean oeste) {
		this.oeste = oeste;
	}
	    
	    
	}


