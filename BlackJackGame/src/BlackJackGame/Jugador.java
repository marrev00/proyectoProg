package BlackJackGame;

public class Jugador {
	String name;
	int points=200;
	
	public Jugador (String name) {
		this.name=name;
	}

	public String getName() {
		return name;
	}

	public int getPoints() {
		return points;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	

}
