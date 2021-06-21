package BlackJackGame;

public class CheckWinner {
	public static String checkWin (int n1, int n2, String j1, String j2){
		if (n1==21&&n2==21) {
			return "Empate";
		}
		else {
			if (n1==21) {
				return "Gano "+j1;
			}
			else {
				if (n2==21){
					return "Gano "+j2;
				}
				else {
					if (n1<n2) {
						return "Gano "+j1;
					}
					else {
						return "Gano "+j2;
						
					}
				}
			}
		}
	}
}
