package clueGame;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class Display extends JPanel {

	public Display() {
		DieDisplay dieDis = new DieDisplay();
		add(dieDis, BorderLayout.WEST);
		GuessDis guessDis = new GuessDis();
		add(guessDis, BorderLayout.CENTER);
		ResultDis resDis = new ResultDis();
		add(resDis, BorderLayout.EAST);
	}
}