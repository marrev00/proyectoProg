package BlackJackGame;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.ImageIcon;
//import java.util.Comparator;

public class Carta implements Serializable{
	private static final long serialVersionUID = 1;
	
	String nombreCarta;
	int valor;
	String tipo;
	String imageUrl;
	//BufferedImage imagen;
	//ImageIcon icon;
	
	public Carta(String nombreCarta, int valor, String tipo, String url) {
		this.nombreCarta=nombreCarta;
		this.valor=valor;
		this.tipo=tipo;
		imageUrl=url;
		//Image temp = Toolkit.getDefaultToolkit().getImage(url);
		//icon= new ImageIcon(url);
		//imagen = (BufferedImage)icon;
		
	}

	public String getNombreCarta() {
		return nombreCarta;
	}

	public int getValor() {
		return valor;
	}

	public String getTipo() {
		return tipo;
	}
	
	public String getUrl() {
		return imageUrl;
	}

	public void setNombreCarta(String nombreCarta) {
		this.nombreCarta = nombreCarta;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public boolean compareCard (Carta a) {
		boolean equal = false;
		if (a.getNombreCarta().equalsIgnoreCase(nombreCarta)) {
			equal=true;
		}
		return equal;
	}
	public boolean checkIfUsed (ArrayList <Carta> usedCards) {
		boolean used = false;
		for (int i=0;i<usedCards.size(); i++) {
			boolean tempeq = this.compareCard(usedCards.get(i));
			if (tempeq) {
				used = true;
				break;
			}
		}
		return used;
	}
	
	
	
	/*public int compare (Carta card1, Carta card2) {
		
		return card1.getValor().compareTo(card2.getValor());
	}*/

	@Override
	public String toString() {
		return nombreCarta;
	}
	
}
