package clueGame;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class DetectiveNotesDialog extends JDialog {

	public DetectiveNotesDialog(ArrayList<Card> deck) {
		ArrayList<String> people = new ArrayList<String>();
		ArrayList<String> rooms = new ArrayList<String>();
		ArrayList<String> weapons = new ArrayList<String>();
		for (Card c : deck) {
			if (c.getType() == CardType.PLAYER) {
				people.add(c.getName());
			} else if (c.getType() == CardType.ROOM) {
				rooms.add(c.getName());
			} else if (c.getType() == CardType.WEAPON) {
				weapons.add(c.getName());
			}
		}
		setTitle("Detective Notes");
		setSize(400, 600);
		setLayout(new GridLayout(3, 2));
		DetectiveCheckBox peopleCheck = new DetectiveCheckBox(people, "People");
		DetectiveNotesGuess peopleGuess = new DetectiveNotesGuess(people,
				"Person Guess");
		DetectiveCheckBox roomsCheck = new DetectiveCheckBox(rooms, "Rooms");
		DetectiveNotesGuess roomsGuess = new DetectiveNotesGuess(rooms,
				"Room Guess");
		DetectiveCheckBox weaponsCheck = new DetectiveCheckBox(weapons,
				"Weapons");
		DetectiveNotesGuess weaponsGuess = new DetectiveNotesGuess(weapons,
				"Weapon Guess");
		add(peopleCheck);
		add(peopleGuess);
		add(roomsCheck);
		add(roomsGuess);
		add(weaponsCheck);
		add(weaponsGuess);
	}
	
	class DetectiveNotesGuess extends JPanel{
		private JComboBox<String> combo;
		
		public DetectiveNotesGuess(ArrayList<String> items, String title) {
			 combo = new JComboBox<String>();
				for (String s : items) {
					combo.addItem(s);
				}
				combo.addItem("Unsure");
				setBorder(new TitledBorder(new EtchedBorder(), title));
				add(combo);
			}
	}
	
	class DetectiveCheckBox extends JPanel {
		public DetectiveCheckBox(ArrayList<String> items, String title) {
			for (String name : items) {
				add(new JCheckBox(name));
			}
			setBorder(new TitledBorder(new EtchedBorder(), title));
		}
	}
}