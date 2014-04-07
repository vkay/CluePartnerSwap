package clueGame;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class PeopleCardDisplay extends JPanel{
	private JTextField people;
	
	public PeopleCardDisplay(Player player) {
		people = new JTextField(player.getPeople() + "\n", 8);
		add(people);
		setBorder(new TitledBorder (new EtchedBorder(), "People"));
	}
}
