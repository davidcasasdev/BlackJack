package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import clases.Juego;
import clases.Mano;
import excepciones.BlackJackException;
import excepciones.FinDeJuegoException;
import net.miginfocom.swing.MigLayout;

public class VentanaJuego extends JFrame {

	private JPanel contentPane;
	
	private Juego juego;
	
	private JLabel listaCartasJugador [];
	private JLabel listaCartasBanca [];

	private JButton btnPedirCarta;

	private JButton btnNuevoJuego;

	private JPanel panelJugador;

	private JPanel panelBanca;

	private JButton btnPlantarse;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaJuego frame = new VentanaJuego();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaJuego() {
		juego = new Juego();
		setTitle("BlackJack");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1156, 780);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[185.00][150.00][150px,grow]", "[17.00][274.00,grow][][274px,grow]"));
		
		JLabel lblNewLabel = new JLabel("");
		ImageIcon imageIcon = new ImageIcon(new ImageIcon(VentanaJuego.class.getResource("/imgs/TRASERA.png"))
				.getImage().getScaledInstance(150, (int)(150*1.404), Image.SCALE_SMOOTH));
		lblNewLabel.setIcon(imageIcon);
		
		JLabel lblNewLabel_1 = new JLabel("Banca:");
		lblNewLabel_1.setFont(new Font("Roboto", Font.BOLD, 18));
		contentPane.add(lblNewLabel_1, "cell 0 0");
		lblNewLabel.setIcon(imageIcon);
		contentPane.add(lblNewLabel, "cell 0 1");
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "flowx,cell 1 1,alignx center,aligny center");
		panel.setLayout(new MigLayout("", "[103px]", "[25px][][][][25px]"));
		
		
		
		btnPedirCarta = new JButton("Pedir Carta");
		panel.add(btnPedirCarta, "cell 0 0,growx,aligny center");
		btnPedirCarta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pedirCarta();
			}
		});
		btnPedirCarta.setFont(new Font("Roboto", Font.PLAIN, 14));
		
		btnNuevoJuego = new JButton("Nuevo Juego");
		btnNuevoJuego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nuevoJuego();
				btnPedirCarta.setEnabled(true);
				btnNuevoJuego.setEnabled(false);
			}
		});
		
		btnPlantarse = new JButton("Plantarse");
		btnPlantarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				plantarse();
			}
		});
		btnPlantarse.setFont(new Font("Roboto", Font.PLAIN, 14));
		panel.add(btnPlantarse, "cell 0 2,growx");
		panel.add(btnNuevoJuego, "cell 0 4,alignx left,aligny center");
		btnNuevoJuego.setFont(new Font("Roboto", Font.PLAIN, 14));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		contentPane.add(scrollPane_1, "cell 2 1,grow");
		
		panelBanca = new JPanel();
		panelBanca.setBackground(new Color(50, 205, 50));
		scrollPane_1.setViewportView(panelBanca);
		
		JLabel lblNewLabel_1_1 = new JLabel("Jugador:");
		lblNewLabel_1_1.setFont(new Font("Roboto", Font.BOLD, 18));
		contentPane.add(lblNewLabel_1_1, "cell 0 2");
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 3 3 1,grow");
		
		panelJugador = new JPanel();
		panelJugador.setBackground(new Color(50, 205, 50));
		FlowLayout flowLayout = (FlowLayout) panelJugador.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		scrollPane.setViewportView(panelJugador);
		
		nuevoJuego();
		
	}

	protected void plantarse() {
		juegaBanca();
		btnPlantarse.setEnabled(false);
	}

	protected void nuevoJuego() {
		juego.incializar();
		
		// máximo 9 cartas
		listaCartasJugador = new JLabel[9];
		listaCartasBanca = new JLabel[9];
		// borramos las cartas del panel
		panelJugador.removeAll();
		panelBanca.removeAll();
		panelJugador.revalidate();
		panelJugador.repaint();
		panelBanca.revalidate();
		panelBanca.repaint();
		for (int i=0;i<9; i++) {
			listaCartasJugador[i] = new JLabel();
			panelJugador.add(listaCartasJugador[i]);
			
			listaCartasBanca[i] = new JLabel();
			panelBanca.add(listaCartasBanca[i]);
		}
		btnPlantarse.setEnabled(true);
		btnNuevoJuego.setEnabled(false);
		
	}

	protected void pedirCarta() {
		String mensaje="";
		boolean fin =false;
		 try {
			 
			juego.pideCarta();
					
		 } catch (FinDeJuegoException e) {
				mensaje="Te has pasado...";
				fin=true;
		} catch (BlackJackException e) {
			mensaje="Tienes BlackJack!!!";
			fin=true;
		} finally {
			ImageIcon imagen = getImagen(juego.getManoJugador(),juego.getManoJugador().getSize()-1);
			listaCartasJugador[juego.getManoJugador().getSize()-1].setIcon(imagen);
			if(!mensaje.isBlank()) JOptionPane.showMessageDialog(contentPane,mensaje);
			if(fin) juegaBanca();
		}
	}

	private ImageIcon getImagen(Mano m, int n) {
		String ruta = "/imgs/"+m.getCarta(n).getPalo()+m.getCarta(n).getNumero()+".png";
		System.out.println(ruta);
		ImageIcon imageIcon = new ImageIcon(new ImageIcon(VentanaJuego.class.getResource(ruta))
				.getImage().getScaledInstance(150, (int)(150*1.404), Image.SCALE_SMOOTH));
		return imageIcon;
	}
	
	private void juegaBanca() {
		String mensaje="";
		btnPedirCarta.setEnabled(false);
		btnNuevoJuego.setEnabled(true);
		btnPlantarse.setEnabled(false);
		
		try {
			while (juego.juegaBanca()){
				ImageIcon imagen = getImagen(juego.getManoBanca(),juego.getManoBanca().getSize()-1);
				listaCartasBanca[juego.getManoBanca().getSize()-1].setIcon(imagen);
			}
		} catch (FinDeJuegoException e) {
			mensaje="La banca se ha pasado";
		} catch (BlackJackException e) {
			mensaje="La banca BlackJack!!!";
		} finally {
			String ruta = "/imgs/"+juego.getManoBanca().getCarta(juego.getManoBanca().getSize()-1).getPalo()+juego.getManoBanca().getCarta(juego.getManoBanca().getSize()-1).getNumero()+".png";
			System.out.println(ruta);
			ImageIcon imageIcon = new ImageIcon(new ImageIcon(VentanaJuego.class.getResource(ruta))
					.getImage().getScaledInstance(150, (int)(150*1.404), Image.SCALE_SMOOTH));
			listaCartasBanca[juego.getManoBanca().getSize()-1].setIcon(imageIcon);
			if (!mensaje.isBlank()) {
				JOptionPane.showMessageDialog(contentPane,mensaje);
			}
		}
		mostrarGanador();
	}
	
	
	private void mostrarGanador() {
		switch(juego.ganador()) {
		case "jugador": JOptionPane.showMessageDialog(contentPane,"Ha gando el jugador"); break;
		case "banca": JOptionPane.showMessageDialog(contentPane,"Ha ganado la banca"); break;
		case "empate": JOptionPane.showMessageDialog(contentPane,"Empate, el jugador recupera la apuesta");break;
		}
	}
	

}
