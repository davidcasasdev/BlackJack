package clases;

import java.util.ArrayList;

public class Mano extends Mazo {

	public Mano() {
		this.baraja = new ArrayList<Carta>();
	}
	
	public int valorMano() {
		int suma=0;
		int numAses=0;
		for (Carta carta : baraja) {
			if (carta.getNumero()==1) {
				suma = suma+1;
				numAses++;
			} else {
				suma = suma + carta.getValor();
			}
		}
		if (numAses>0 && suma+10<=21) {
			suma=suma+10;
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

	public int getSize()
	{
		return this.baraja.size();
	}
	
	public Carta getCarta(int i) {
		return this.baraja.get(i);
	}
	
}
