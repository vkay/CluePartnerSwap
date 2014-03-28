package clueGame;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class GuessDis extends JPanel{

	private JTextField gue;

	public GuessDis() {
		JLabel guess = new JLabel("Guess");
		add(guess);
		gue = new JTextField(15);
		gue.setEditable(false);
		add(gue);
		setBorder(new TitledBorder (new EtchedBorder(), "Guess"));
	}
}