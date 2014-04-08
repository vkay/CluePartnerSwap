package clueGame;


import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WhoseTurn extends JPanel {
	private JTextField name;

	public WhoseTurn(ClueGame clueGame) {
		setLayout(new GridLayout(0,1));
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5) ); 
		JLabel turn = new JLabel("Whose turn?");
		add(turn);
		name = new JTextField(10);
		add(name);
	}
	
	public void setWhoseTurn(String who) {
		name.setText(who);
	}
	
}