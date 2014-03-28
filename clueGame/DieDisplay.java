package clueGame;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class DieDisplay extends JPanel {

private JTextField die;


	public DieDisplay() {
		JLabel roll = new JLabel("Roll");
		add(roll);
		die = new JTextField(3);
		die.setEditable(false);
		add(die);
		setBorder(new TitledBorder (new EtchedBorder(), "Die"));

	}


}