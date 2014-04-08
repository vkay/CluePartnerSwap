package clueGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class Display extends JPanel {
	private int roll;
	private DieDisplay dieDis;

	public Display(Player player) {
		setLayout(new GridLayout(1, 3));
		dieDis = new DieDisplay(player);
		add(dieDis, BorderLayout.WEST);
		GuessDis guessDis = new GuessDis();
		add(guessDis, BorderLayout.CENTER);
		ResultDis resDis = new ResultDis();
		add(resDis, BorderLayout.EAST);
	}
	
	public void setRoll(int roll) {
		dieDis.setRoll(roll);
	}
}
