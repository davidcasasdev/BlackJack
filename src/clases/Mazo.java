package clases;

import java.util.ArrayList;
import java.util.Collections;

public class Mazo {

	protected ArrayList<Carta> baraja;
	
	public Mazo() {
		this.baraja = new ArrayList<Carta>();
		
		for (Palo p: Palo.values()) {
			for (int i=1;i<=13;i++) {
				try {
					Carta c = new Carta(i,p);
					this.baraja.add(c);
				} catch (CartaIncorrectaException e) {
					// TODO Bloque catch generado automáticamente
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public String toString() {
		return "Lista de cartas: " + baraja;
	}
	
	public void barajar() {
		Collections.shuffle(baraja);
	}
	
	protected Carta solicitarCarta() {
		Carta c = baraja.get(0);
		baraja.remove(c);
		return c;
	}
	
	
}
