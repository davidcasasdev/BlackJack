package clases;

public class Carta {
	
	private int numero;
	private Palo palo;
	
	public Carta(int numero, Palo palo) throws CartaIncorrectaException {
		if (numero<1 || numero >13) {
			throw new CartaIncorrectaException();
		}
		this.numero = numero;
		this.palo = palo;
	}

	public int getNumero() {
		return numero;
	}

	public Palo getPalo() {
		return palo;
	}
	
	
	public int getValor() {
		if (this.numero==1) return 11;
		else if (this.numero>=10) return 10;
		else return this.numero;
	}
	
	public String mostrarNumero() {
		switch (this.numero) {
		case 1:  return "AS";
		case 11: return "J";
		case 12: return "Q";
		case 13: return "K";
		default: return ""+this.numero;
		}
	}

	@Override
	public String toString() {
		return "[" + mostrarNumero() + "-" + palo+"]";
	}

}
