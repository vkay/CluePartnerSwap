package clueGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Display extends JPanel {
	private DieDisplay dieDis;
	private GuessDis guessDis;
	private ResultDis resDis;

	public Display(Player player) {
		setLayout(new GridLayout(1, 3));
		dieDis = new DieDisplay(player);
		add(dieDis, BorderLayout.WEST);
		guessDis = new GuessDis(player);
		add(guessDis, BorderLayout.CENTER);
		resDis = new ResultDis();
		add(resDis, BorderLayout.EAST);
	}
	
	public void setRoll(int roll) {
		dieDis.setRoll(roll);
	}
	
	public void setGuess(String guess) {
		guessDis.setGuess(guess);
	}
	
	public void setResponse(String response) {
		if (response == null) {
			resDis.setResponse("No new clue");
		} else {
			resDis.setResponse(response);
		}
	}
}
