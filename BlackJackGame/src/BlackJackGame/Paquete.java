package BlackJackGame;

import java.io.Serializable;

public abstract class Paquete implements Serializable{
	private static final long serialVersionUID = 1;
	String tipo;
	public String getTipo() {
		return tipo;
	}
	
	
}
class PaqueteDatos extends Paquete implements Serializable {
	private static final long serialVersionUID = 1;
	Carta card;
	String mensaje;
	String ip;
	String nombreJugador;
	
	boolean pushedStay;
	
	public PaqueteDatos() {
		super.tipo="PaqueteDatos";
	}
	public PaqueteDatos( String mensaje, String ip, String nombreJugador, boolean pushedStay) {
		this.mensaje=mensaje;
		this.ip=ip;
		this.nombreJugador=nombreJugador;
		this.pushedStay=pushedStay;
		super.tipo="PaqueteDatos";
	}
	public PaqueteDatos(Carta card, String mensaje, String ip, String nombreJugador, boolean pushedStay) {
		this.card=card;
		this.mensaje=mensaje;
		this.ip=ip;
		this.nombreJugador=nombreJugador;
		this.pushedStay=pushedStay;
		super.tipo="PaqueteDatos";
	}
	public Carta getCard() {
		return card;
	}
	public String getMensaje() {
		return mensaje;
	}
	public String getIp() {
		return ip;
	}
	public String getNombreJugador() {
		return nombreJugador;
	}
	public boolean getPushedStay() {
		return pushedStay;
	}
	public void setCard(Carta card) {
		this.card = card;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public void setIp(String ip) {
		this.ip=ip;
	}
	public void setNombreJugador(String nombreJugador) {
		this.nombreJugador=nombreJugador;
	}
	public void setPushedStay(boolean pushedStay) {
		this.pushedStay=pushedStay;
	}
	
}
class PaqueteUsuario extends Paquete implements Serializable{
	private static final long serialVersionUID = 1;
	String nombreUsuario;
	String contrasenya;
	
	public PaqueteUsuario(String nombreUsuario, String contrasenya, String tipo) {
		this.nombreUsuario=nombreUsuario;
		this.contrasenya=contrasenya;
		super.tipo=tipo;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public String getContrasenya() {
		return contrasenya;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario=nombreUsuario;
	}
	public void setContrasenya(String contrasenya) {
		this.contrasenya=contrasenya;
	}
}
class PaqueteLoginResponse extends Paquete implements Serializable {
	private static final long serialVersionUID = 1;
	private String nombreUsuario;
	private String mensaje;
	private boolean accepted=false; 
	public PaqueteLoginResponse(String nombreUsuario, String mensaje, boolean accepted) {
		this.nombreUsuario=nombreUsuario;
		this.mensaje=mensaje;
		this.accepted=accepted;
		super.tipo="PaqueteLoginAccept";
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public String getMensaje() {
		return mensaje;
	}
	public boolean isAccepted() {
		return accepted;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario=nombreUsuario;
	}
	public void setMensaje(String mensaje) {
		this.mensaje=mensaje;
	}
	public void setAccepted(boolean accepted) {
		this.accepted=accepted;
	}
}
