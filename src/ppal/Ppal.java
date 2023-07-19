package ppal;

import java.util.Scanner;

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
		
		
		
	}

}
