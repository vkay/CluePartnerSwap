package clueGame;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class CardDisplay extends JPanel{

	public CardDisplay(Player player) {
		setLayout(new GridLayout(4, 1));
		JLabel myCards = new JLabel("My Cards");
		add(myCards);
		PeopleCardDisplay people = new PeopleCardDisplay(player);
		add(people);
		RoomsCardDisplay rooms = new RoomsCardDisplay(player);
		add(rooms);
		WeaponsCardDisplay weapons = new WeaponsCardDisplay(player);
		add(weapons);
	}

}