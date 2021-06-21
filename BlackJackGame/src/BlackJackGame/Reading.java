package BlackJackGame;

import java.io.BufferedReader;
import java.io.IOException;

import javax.swing.JTextArea;

public class Reading extends Thread{
	private String name;
	private BufferedReader in=null;
	private JTextArea textArea=null;
	
	public Reading (String name, BufferedReader in, JTextArea area) {
		this.name=name;
		this.in=in;
		textArea=area;
	}
	public void run () {
		String line = null;
		while (true) {
			try {
				line=in.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			textArea.setText(textArea.getText()+"\n"+name+": "+line);
		}
	}
}
