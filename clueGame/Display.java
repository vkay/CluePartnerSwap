package clueGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class Display extends JPanel {

	public Display(Player player) {
		setLayout(new GridLayout(1, 3));
		DieDisplay dieDis = new DieDisplay(player);
		add(dieDis, BorderLayout.WEST);
		GuessDis guessDis = new GuessDis();
		add(guessDis, BorderLayout.CENTER);
		ResultDis resDis = new ResultDis();
		add(resDis, BorderLayout.EAST);
	}
}
