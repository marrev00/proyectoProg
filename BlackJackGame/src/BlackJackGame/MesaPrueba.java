package BlackJackGame;


import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Random;


public class MesaPrueba {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MarcoMesa marco = new MarcoMesa();
		marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		marco.getContentPane().setLayout(new BorderLayout(0, 0));
		
	}

}


class MarcoMesa extends JFrame {
	
	private static final long serialVersionUID = 1;
	//private static int puertoSalida;
	//private static int puertoEscucha;
	private int total1;
	private int total2;
	private boolean primeraCartaOponente=true;
	private boolean jugador1Stay=false;
	private boolean jugador2Stay=false;
	private String nombreJugador1="Jugador 1";
	private String nombreJugador2="Jugador 2";
	JLabel lblJ1;
	Baraja baraja = new Baraja();
	JLabel suma1;
	JLabel suma2;
	JTextArea status;
	
	public MarcoMesa() {
		
		setBounds(300, 200, 680, 450);
		setVisible(true);
		
		setLayout(new BorderLayout(0,0));
		
		PanelJugador2 pan2 = new PanelJugador2();
		PanelJugador1 pan1 = new PanelJugador1();
		status = new JTextArea("aplicación iniciada");
		add(status, BorderLayout.CENTER);
		add(pan1, BorderLayout.SOUTH);
		add(pan2, BorderLayout.NORTH);
		
		setVisible(true);

	}
	
	
	
	private class PanelJugador2 extends JPanel implements Runnable {
		private static final long serialVersionUID = 1;
		
		private boolean jugador2Preparado=false;
		private int sum=0;
		private int sumPrimeraCarta=0;
		JLabel lblNombreP2;
		public PanelJugador2() {
			lblNombreP2=new JLabel(nombreJugador2+": ");
			add(lblNombreP2);
			suma2=new JLabel(""+sum);
			add(suma2);
			Thread hilo = new Thread(this);
			hilo.start();
			
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				ServerSocket socketServidorCliente = new ServerSocket(9912);
				while(true) {
					Socket socketEntrada = socketServidorCliente.accept();
					ObjectInputStream inputLine = new ObjectInputStream(socketEntrada.getInputStream());
					
					Paquete paqueteRecibido = (Paquete) inputLine.readObject();
					
					if (paqueteRecibido.getTipo().equals("PaqueteDatos")) {
						//PaqueteDatos mensajeRecibido = (PaqueteDatos)inputLine.readObject();
						PaqueteDatos mensajeRecibido = (PaqueteDatos)paqueteRecibido;
						
						if (jugador2Preparado==false) {
							lblNombreP2.setText(mensajeRecibido.getNombreJugador());
							jugador2Preparado=true;
						}
						if (!mensajeRecibido.getPushedStay()) {
							Carta drawOponentCard = mensajeRecibido.getCard();
							
							JLabel lbl = new JLabel();
							if (primeraCartaOponente) {
								
								lbl.setText("X");
								sumPrimeraCarta+=drawOponentCard.getValor();
								suma2.setText("X+ "+sum);
							}
							else {
								lbl.setText(drawOponentCard.getNombreCarta());
								sum+=drawOponentCard.getValor();
								suma2.setText("X +"+sum);
							}
							
							add(lbl);
							suma2.setVisible(false);
							suma2.setVisible(true);
						
							baraja.oponenDraw(drawOponentCard);
						}
						else {
							total2=sum;
							jugador2Stay=true;
							
						}
						
						status.append("\n"+mensajeRecibido.getMensaje());
						
						if (jugador1Stay&&jugador2Stay) {
							suma2.setText(""+sum+sumPrimeraCarta);
							String winner = CheckWinner.checkWin(total1, total2, nombreJugador1, nombreJugador2);
							status.append(winner);
						}
					}
					
					if (paqueteRecibido.getTipo().equals("PaqueteLoginAccept")) {
						PaqueteLoginResponse loginResponse = (PaqueteLoginResponse) paqueteRecibido;
						nombreJugador1=loginResponse.getNombreUsuario();
						lblJ1.setText(nombreJugador1);
						status.append(loginResponse.getMensaje());
					}
					
				
					socketEntrada.close();}
				
				
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		
	}
	
	private class PanelJugador1 extends JPanel  {
		private static final long serialVersionUID = 1;
		
		private boolean jugador1Preparado=false;
		Thread login;
		
		private JButton btnHit;
		private JButton btnStay;
		private JButton btnConectarServidor;
		private JButton btnLogin;
		private JButton btnRegistrarse;
		int sum=0;
		private String ipDestino;
		private String ipServidor;
		public PanelJugador1() {
			
			//nombreJugador1 = JOptionPane.showInputDialog("Introduce tu nombre de usuario");
			String setIpServidor = JOptionPane.showInputDialog("Introduce Ip Servidor");
			ipServidor = setIpServidor;
			
			
			btnStay = new JButton("Stay");
			btnHit = new JButton("Jugar");
			btnRegistrarse = new JButton("Registrarse");
			btnLogin = new JButton("Login");
			add(btnHit);
			add(btnStay);
			add(btnRegistrarse);
			add(btnLogin);
			
			btnStay.setVisible(false);
			btnHit.setVisible(false);
			btnLogin.setVisible(true);
			btnRegistrarse.setVisible(true);
			
			PressHit pressHit = new PressHit();
			btnHit.addActionListener(pressHit);
			PressStay pressStay = new PressStay();
			btnStay.addActionListener(pressStay);
			PressLogin pressLogin = new PressLogin();
			btnLogin.addActionListener(pressLogin);
			
			/*String ptSalida = JOptionPane.showInputDialog("Introduce puerto de salida");
			puertoSalida = Integer.parseInt(ptSalida);
			String ptEscucha = JOptionPane.showInputDialog("Introduce puerto de escucha");
			puertoEscucha = Integer.parseInt(ptEscucha);*/

			lblJ1=new JLabel(nombreJugador1+": ");
			add(lblJ1);
					;
			suma1=new JLabel(""+sum);
			add(suma1);
			//login = new Thread(this);
			
			
		}
		private class PressRegister implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String nuevoUsuario = JOptionPane.showInputDialog("Introduce un nombre de Usuario");
				String contrasenya1 = JOptionPane.showInputDialog("Introduce una contraseña");
				String contrasenya2 = JOptionPane.showInputDialog("Introduce la contraseña otra vez");
				Socket misocket;
				if (contrasenya1.equals(contrasenya2)) {
					try {
						misocket = new Socket(ipServidor, 9911);
						ObjectOutputStream outputLine = new ObjectOutputStream(misocket.getOutputStream());
						PaqueteUsuario registerPetition = new PaqueteUsuario(nuevoUsuario, contrasenya1, "register");
						outputLine.writeObject(registerPetition);
						outputLine.close();
						misocket.close();
						
						
					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else status.append("\n La contraseña no es igual");
				
				
				
				
			}
			
		}
		
		private class PressLogin implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String nuevoUsuario = JOptionPane.showInputDialog("Introduce un nombre de Usuario");
				String contrasenya = JOptionPane.showInputDialog("Introduce la contraseña");
				boolean waitingResponse=true;
				boolean logged=false;
				
				try {
					Socket misocket = new Socket(ipServidor, 9911);
					
					ObjectOutputStream outputLine = new ObjectOutputStream(misocket.getOutputStream());
					PaqueteUsuario loginPetition = new PaqueteUsuario(nuevoUsuario, contrasenya, "login");
					outputLine.writeObject(loginPetition);
					outputLine.close();
					misocket.close();
					
					btnRegistrarse.setVisible(false);
					btnHit.setVisible(true);
					btnLogin.setVisible(false);
					
					
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
				if (logged) {
					btnRegistrarse.setVisible(false);
					btnHit.setVisible(true);
					btnLogin.setVisible(false);
				}
				
				
			}
			
		}
		private class PressHit implements ActionListener {
			String mensaje;			

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (!jugador1Preparado) {
					String setIpDestino = JOptionPane.showInputDialog("Introduce una IP del 2do jugador");
					ipDestino = setIpDestino;
					mensaje = nombreJugador1+" se unió a la partida";
					//add(btnStay);
					btnStay.setVisible(true);
					jugador1Preparado=true;
					btnHit.setText("Hit");
				}
				else {
					mensaje = nombreJugador1+" presiono Hit";
				}
				
				status.append("\n"+mensaje);
				Carta newCard = baraja.draw();
				sum+=newCard.getValor();
				suma1.setText(""+sum);
				JLabel lbl = new JLabel(newCard.getNombreCarta());
				//
				//ImageIcon temp = new ImageIcon("10C.png");
				//lbl.setIcon(temp);
				add(lbl);
				suma1.setVisible(false);
				suma1.setVisible(true);
				
				try {
					Socket socketSalida = new Socket(ipServidor,9911);
					ObjectOutputStream outputLine = new ObjectOutputStream(socketSalida.getOutputStream());
					
					PaqueteDatos envio = new PaqueteDatos(newCard, mensaje, ipDestino, nombreJugador1, jugador1Stay);
					
					outputLine.writeObject(envio);
					outputLine.close();
					socketSalida.close();

					
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			
		}
		
		private class PressStay implements ActionListener {
			

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String mensaje=nombreJugador1+" ha apreatado Stay";
				jugador1Stay=true;
				total1=sum;
				try {
					Socket socketSalida = new Socket(ipServidor,9911);
					ObjectOutputStream outputLine = new ObjectOutputStream(socketSalida.getOutputStream());
					
					PaqueteDatos envio = new PaqueteDatos(mensaje, ipDestino, nombreJugador1, jugador1Stay);
					
					outputLine.writeObject(envio);
					outputLine.close();
					socketSalida.close();
					
					status.append("\n"+mensaje);
					btnHit.setVisible(false);
					if (jugador1Stay&&jugador2Stay) {
						String winner = CheckWinner.checkWin(total1, total2, nombreJugador1, nombreJugador2);
						status.append(winner);
					}
					
					
					
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			}
			
		}

		/*@Override
		public void run() {
			// TODO Auto-generated method stub
			String nuevoUsuario = JOptionPane.showInputDialog("Introduce un nombre de Usuario");
			String contrasenya = JOptionPane.showInputDialog("Introduce la contraseña");
			boolean waitingResponse=true;
			boolean logged=false;
			
			try {
				Socket misocket = new Socket(ipServidor, 9911);
				
				ObjectOutputStream outputLine = new ObjectOutputStream(misocket.getOutputStream());
				PaqueteUsuario loginPetition = new PaqueteUsuario(nuevoUsuario, contrasenya, "login");
				outputLine.writeObject(loginPetition);
				outputLine.close();
				misocket.close();
				
				ServerSocket socketServidorCliente = new ServerSocket(9912);
				while (waitingResponse) {
					Socket socketEntrada = socketServidorCliente.accept();
					ObjectInputStream inputLine = new ObjectInputStream(socketEntrada.getInputStream());
					PaqueteLoginResponse loginResponse = (PaqueteLoginResponse) inputLine.readObject();
					if (loginResponse.isAccepted()) {
						nombreJugador1=loginResponse.getNombreUsuario();
						status.append(loginResponse.getMensaje());
						waitingResponse=false;
						socketEntrada.close();
						inputLine.close();
					}
					else {
						status.append(loginResponse.getMensaje());
						waitingResponse=false;
						socketEntrada.close();
						inputLine.close();
					}
				}
				socketServidorCliente.close();
				
				
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (logged) {
				btnRegistrarse.setVisible(false);
				btnHit.setVisible(true);
				btnLogin.setVisible(false);
			}
			login.stop();
			
		}*/
		
		
	}


	
}



