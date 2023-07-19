package clases;

import java.util.ArrayList;

public class Mano extends Mazo {

	public Mano() {
		this.baraja = new ArrayList<Carta>();
	}
	
	public int valorMano() {
		int suma=0;
		for (Carta carta : baraja) {
			suma = suma + carta.getValor();
		}
		return suma;
	}
	
	public boolean finDeJuego() {
		if (valorMano()>21) return true;
		else return false;
		
//		return valorMano()>21;
	}

	@Override
	public String toString() {
		return "Valor de la mano: "+valorMano()+"\n" + super.toString();
	}
	
	
	public void pedirCarta(Mazo mazo) {
		this.baraja.add(mazo.solicitarCarta());
	}

	
	
	
}
