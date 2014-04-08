package clueGame;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class PeopleCardDisplay extends JPanel{
	private JTextArea people;

	public PeopleCardDisplay(Player player) {
		people = new JTextArea(player.getPeople());
		add(people);
		setBorder(new TitledBorder (new EtchedBorder(), "People"));
	}
}
