package ppal;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import clases.Juego;
import excepciones.BlackJackException;
import excepciones.FinDeJuegoException;

public class Ppal {

	public static void main(String[] args) {
		
		boolean seguir =true;
		Scanner teclado = new Scanner(System.in);
		System.out.println("Bienvenido al BlackJack");
		Juego juego = new Juego();
		
		System.out.println("Comienzas con la siguiente carta:");
		System.out.println(juego.getManoJugador());
		
		do {
			System.out.println("¿Quieres pedir una carta (0) o plantarte (1)?");
			int respuesta = teclado.nextInt();
			seguir=false;
			
			if (respuesta == 0) {
				try {
					juego.pideCarta();
					seguir=true;

				} catch (FinDeJuegoException e) {
					System.out.println("Te has pasado.");
				} catch (BlackJackException e) {
					System.out.println("Tienes BlackJack!!!");
				} finally {
					System.out.println("Llevas en la mano:");
					System.out.println(juego.getManoJugador());
				}

			}
		}while (seguir);
		
		System.out.println("Juega la banca...");
		System.out.println("La banca lleva en la mano:");
		System.out.println(juego.getManoBanca());
		try {
			while (juego.juegaBanca()){
				TimeUnit.SECONDS.sleep(3);
				System.out.println("La banca lleva en la mano:");
				System.out.println(juego.getManoBanca());
				
				
			}
			System.out.println("La banca lleva en la mano:");
			System.out.println(juego.getManoBanca());
		} catch (FinDeJuegoException e) {
			System.out.println("La banca se ha pasado.");
			System.out.println("La banca lleva en la mano:");
			System.out.println(juego.getManoBanca());
		} catch (BlackJackException e) {
			System.out.println("La banca ha obtenido BlackJack.");
			System.out.println("La banca lleva en la mano:");
			System.out.println(juego.getManoBanca());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		switch(juego.ganador()) {
		case "banca": System.out.println("Ha ganado la banca"); break;
		case "jugador": System.out.println("Ha ganado el jugador"); break;
		default: System.out.println("No hay ganador...");
		}
		
	}

}
