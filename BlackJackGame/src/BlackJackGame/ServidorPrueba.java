package BlackJackGame;

import javax.swing.*;

import java.awt.*;
import java.io.*;
import java.net.*;

public class ServidorPrueba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		VentanaServidor ventana = new VentanaServidor();
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}

class VentanaServidor extends JFrame implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1;
	int puertoEscucha;
	int puertoSalida;

	JTextArea texto;

	public VentanaServidor() {
		setBounds(300, 300, 200, 100);
		/*
		 * String ptEscucha =
		 * JOptionPane.showInputDialog("Introduce puerto de escucha"); puertoEscucha =
		 * Integer.parseInt(ptEscucha); String ptSalida =
		 * JOptionPane.showInputDialog("Introduce puerto de salida"); puertoSalida =
		 * Integer.parseInt(ptSalida);
		 */

		JPanel panel = new JPanel();
		add(panel);
		texto = new JTextArea("s");
		add(texto);
		setVisible(true);

		Thread hilo = new Thread(this);
		hilo.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			ServerSocket socketServidor = new ServerSocket(9911);
			String ipDestino;

			while (true) {
				Socket socketEntrada = socketServidor.accept();
				ObjectInputStream flujoEntrada = new ObjectInputStream(socketEntrada.getInputStream());
				Paquete paqueteRecibido = (Paquete) flujoEntrada.readObject();
				String tipo = paqueteRecibido.getTipo();
				System.out.println("He recibido algo");
				if (tipo.equals("PaqueteDatos")) {
					PaqueteDatos paqueteDatosRecibido = (PaqueteDatos) paqueteRecibido;
					String mensaje = paqueteDatosRecibido.getMensaje();
					ipDestino = paqueteDatosRecibido.getIp();
					texto.append("\n" + mensaje);
					// flujoEntrada.close();
					socketEntrada.close();
					System.out.println("he llegado a recibir algo");

					Socket socketSalida = new Socket(ipDestino, 9912);
					ObjectOutputStream flujoSalida = new ObjectOutputStream(socketSalida.getOutputStream());
					flujoSalida.writeObject(paqueteDatosRecibido);
					socketSalida.close();
					flujoSalida.close();
					System.out.println("He enviado datos");
				}
				if (tipo.equals("register")) {
					System.out.println("Aquí tendría que ir el registro en la base de datos");
					
				}
				if (tipo.equals("login")) {
					InetAddress localizacion = socketEntrada.getInetAddress();
					String IpRemota = localizacion.getHostAddress();
					System.out.println(IpRemota);
					PaqueteUsuario paqueteUsuarioRecibido = (PaqueteUsuario) paqueteRecibido;
					PaqueteLoginResponse loginResponse = new PaqueteLoginResponse(paqueteUsuarioRecibido.getNombreUsuario(), "Aceptado", true);
					Socket socketSalida = new Socket(IpRemota, 9912);
					ObjectOutputStream flujoSalida = new ObjectOutputStream(socketSalida.getOutputStream());
					flujoSalida.writeObject(loginResponse);
					socketSalida.close();
					flujoSalida.close();
					System.out.println("He enviado login accept");
				}
				/*
				 * PaqueteDatos paqueteRecibido = (PaqueteDatos) flujoEntrada.readObject();
				 * String mensaje = paqueteRecibido.getMensaje(); ipDestino =
				 * paqueteRecibido.getIp(); texto.append("\n"+mensaje); //flujoEntrada.close();
				 * socketEntrada.close(); System.out.println("he llegado a recibir algo");
				 * 
				 * Socket socketSalida = new Socket(ipDestino,9902); ObjectOutputStream
				 * flujoSalida = new ObjectOutputStream(socketSalida.getOutputStream());
				 * flujoSalida.writeObject(paqueteRecibido); socketSalida.close();
				 * flujoSalida.close(); System.out.println("He enviado algo");
				 */
			}

		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
