package BlackJackGame;

import java.util.ArrayList;
import java.util.Random;

public class Baraja {
	ArrayList<Carta> baraja = new ArrayList<Carta>();
	ArrayList<Carta> usadas = new ArrayList<Carta>();
	int lengthBaraja = baraja.size();
	int lengthUsadas;
	public static Random r = new Random();

	public Baraja() {

		baraja.add(new Carta("AS de Picas", 1, "Picas", "../img/AS.png"));
		baraja.add(new Carta("2 de Picas", 2, "Picas", "../img/2S.png"));
		baraja.add(new Carta("3 de Picas", 3, "Picas", "../img/3S.png"));
		baraja.add(new Carta("4 de Picas", 4, "Picas", "../img/4S.png"));
		baraja.add(new Carta("5 de Picas", 5, "Picas", "../img/5S.png"));
		baraja.add(new Carta("6 de Picas", 6, "Picas", "../img/6S.png"));
		baraja.add(new Carta("7 de Picas", 7, "Picas", "../img/7S.png"));
		baraja.add(new Carta("8 de Picas", 8, "Picas", "../img/8S.png"));
		baraja.add(new Carta("9 de Picas", 9, "Picas", "../img/9S.png"));
		baraja.add(new Carta("10 de Picas", 10, "Picas", "../img/10S.png"));
		baraja.add(new Carta("J de Picas", 11, "Picas", "../img/JS.png"));
		baraja.add(new Carta("Q de Picas", 12, "Picas", "../img/QS.png"));
		baraja.add(new Carta("K de Picas", 13, "Picas", "../img/KS.png"));

		baraja.add(new Carta("AS de Trebol", 1, "Trebol", "../img/AC.png"));
		baraja.add(new Carta("2 de Trebol", 2, "Trebol", "../img/2C.png"));
		baraja.add(new Carta("3 de Trebol", 3, "Trebol", "../img/3C.png"));
		baraja.add(new Carta("4 de Trebol", 4, "Trebol", "../img/4C.png"));
		baraja.add(new Carta("5 de Trebol", 5, "Trebol", "../img/5C.png"));
		baraja.add(new Carta("6 de Trebol", 6, "Trebol", "../img/6C.png"));
		baraja.add(new Carta("7 de Trebol", 7, "Trebol", "../img/7C.png"));
		baraja.add(new Carta("8 de Trebol", 8, "Trebol", "../img/8C.png"));
		baraja.add(new Carta("9 de Trebol", 9, "Trebol", "../img/9C.png"));
		baraja.add(new Carta("10 de Trebol", 10, "Trebol", "../img/10C.png"));
		baraja.add(new Carta("J de Trebol", 11, "Trebol", "../img/JC.png"));
		baraja.add(new Carta("Q de Trebol", 12, "Trebol", "../img/QC.png"));
		baraja.add(new Carta("K de Trebol", 13, "Trebol", "../img/KC.png"));

		baraja.add(new Carta("AS de Diamantes", 1, "Diamantes", "../img/AD.png"));
		baraja.add(new Carta("2 de Diamantes", 2, "Diamantes", "../img/2D.png"));
		baraja.add(new Carta("3 de Diamantes", 3, "Diamantes", "../img/3D.png"));
		baraja.add(new Carta("4 de Diamantes", 4, "Diamantes", "../img/4D.png"));
		baraja.add(new Carta("5 de Diamantes", 5, "Diamantes", "../img/5D.png"));
		baraja.add(new Carta("6 de Diamantes", 6, "Diamantes", "../img/6D.png"));
		baraja.add(new Carta("7 de Diamantes", 7, "Diamantes", "../img/7D.png"));
		baraja.add(new Carta("8 de Diamantes", 8, "Diamantes", "../img/8D.png"));
		baraja.add(new Carta("9 de Diamantes", 9, "Diamantes", "../img/9D.png"));
		baraja.add(new Carta("10 de Diamantes", 10, "Diamantes", "../img/10D.png"));
		baraja.add(new Carta("J de Diamantes", 11, "Diamantes", "../img/JD.png"));
		baraja.add(new Carta("Q de Diamantes", 12, "Diamantes", "../img/QD.png"));
		baraja.add(new Carta("K de Diamantes", 13, "Diamantes", "../img/KD.png"));

		baraja.add(new Carta("AS de Corazones", 1, "Corazones", "../img/AH.png"));
		baraja.add(new Carta("2 de Corazones", 2, "Corazones", "../img/2H.png"));
		baraja.add(new Carta("3 de Corazones", 3, "Corazones", "../img/3H.png"));
		baraja.add(new Carta("4 de Corazones", 4, "Corazones", "../img/4H.png"));
		baraja.add(new Carta("5 de Corazones", 5, "Corazones", "../img/5H.png"));
		baraja.add(new Carta("6 de Corazones", 6, "Corazones", "../img/6H.png"));
		baraja.add(new Carta("7 de Corazones", 7, "Corazones", "../img/7H.png"));
		baraja.add(new Carta("8 de Corazones", 8, "Corazones", "../img/8H.png"));
		baraja.add(new Carta("9 de Corazones", 9, "Corazones", "../img/9H.png"));
		baraja.add(new Carta("10 de Corazones", 10, "Corazones", "../img/10H.png"));
		baraja.add(new Carta("J de Corazones", 11, "Corazones", "../img/JH.png"));
		baraja.add(new Carta("Q de Corazones", 12, "Corazones", "../img/QH.png"));
		baraja.add(new Carta("K de Corazones", 13, "Corazones", "../img/KH.png"));

		lengthBaraja = baraja.size();
	}

	public Carta draw() {
		int rng = r.nextInt(lengthBaraja);
		Carta drawCard = baraja.get(rng);
		usadas.add(baraja.get(rng));
		baraja.remove(rng);
		lengthBaraja = baraja.size();

		return drawCard;
	}

	public void oponenDraw(Carta card) {
		for (int i = 0; i < lengthBaraja; i++) {
			if (baraja.get(i).getNombreCarta().equals(card.getNombreCarta())) {
				usadas.add(card);
				baraja.remove(i);
				lengthBaraja = baraja.size();
				break;
			}
		}
	}

}
