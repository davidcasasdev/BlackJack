package clases;

import excepciones.BlackJackException;
import excepciones.FinDeJuegoException;

public class Juego {
	
	private Mazo baraja;
	private Mano jugador;
	private Mano banca;
	
	public Juego() {
		incializar();
	}
	
	public void incializar() {
		baraja = new Mazo();
		baraja.barajar();
		jugador = new Mano();
		banca = new Mano();
		pedirCarta(jugador);
		pedirCarta(banca);
		
	}
	
	protected void pedirCarta(Mano m) {
		m.pedirCarta(baraja);
	}
	
	public Mano getManoJugador() {
		return this.jugador;
	}
	
	public void pideCarta() throws FinDeJuegoException, BlackJackException {
		pedirCarta(jugador);
		if (this.jugador.finDeJuego()) {
			throw new FinDeJuegoException();
		}
		if (this.jugador.valorMano()==21) {
			throw new BlackJackException();
		}
	}
	
	public boolean juegaBanca() {
		pedirCarta(banca);
		if (banca.finDeJuego()){
			throw new FinDeJuegoException();
		}
		if (this.banca.valorMano()==21) {
			throw new BlackJackException();
		}
		if (banca.valorMano()<17) {
			return true;
		} else {
			if (banca.valorMano()>=jugador.valorMano() || jugador.valorMano()>21 ) {
				return false;
			} else {
				return true;
			}
		}
	}


}
