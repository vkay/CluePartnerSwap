package clueGame;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class Display extends JPanel {
	private DisplayPanel dieDis;
	private DisplayPanel guessDis;
	private DisplayPanel resDis;
	private GridBagConstraints gbc;

	public Display(Player player) {
		gbc = new GridBagConstraints();
		setLayout(new GridBagLayout());
		dieDis = new DisplayPanel("Roll",25, 1);
		addComp(dieDis,0,0,1,1,GridBagConstraints.HORIZONTAL, .10, 0.0);
		guessDis = new DisplayPanel("Guess",150,30);
		addComp(guessDis,1,0,1,1,GridBagConstraints.HORIZONTAL, .6, 0.0);
		resDis = new DisplayPanel("Result",25,7);
		addComp(resDis,2,0,1,1,GridBagConstraints.HORIZONTAL, .3, 0.0);
	}

	private void addComp(JPanel panel, int gridx, int gridy,
			int gridwidth, int gridheight, int fill, double weightx,
			double weighty) {
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		gbc.gridwidth = gridwidth;
		gbc.gridheight = gridheight;
		gbc.fill = fill;
		gbc.weightx = weightx;
		gbc.weighty = weighty;

		add(panel, gbc);
	}

	public void setRoll(int roll) {
		dieDis.setDisplay(Integer.toString(roll));
	}

	public void setGuess(String guess) {
		guessDis.setDisplay(guess);
	}

	public void setResponse(String response) {
		if (response == null) {
			resDis.setDisplay("No new clue");
		} else {
			resDis.setDisplay(response);
		}
	}
	
	class DisplayPanel extends JPanel {

		private JTextField display;

		public DisplayPanel(String title, int size, int length) {;
			display = new JTextField(length);
			display.setEditable(false);
			add(display);
			setBorder(new TitledBorder(new EtchedBorder(), title));
		}

		public void setDisplay(String display) {
			this.display.setText(display);
		}
	}
}
