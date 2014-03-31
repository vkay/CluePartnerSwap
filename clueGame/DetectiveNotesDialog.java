package clueGame;

import java.awt.GridLayout;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JTextField;

public class DetectiveNotesDialog extends JDialog {
	private JTextField name;

	public DetectiveNotesDialog(ArrayList<Card> deck){
		ArrayList<String> people = new ArrayList<String>();
		ArrayList<String> rooms = new ArrayList<String>();
		ArrayList<String> weapons = new ArrayList<String>();
		for(Card c: deck){
			if(c.getType() == CardType.PLAYER){
				people.add(c.getName());
			}else if(c.getType() == CardType.ROOM){
				rooms.add(c.getName());
			}else if(c.getType() == CardType.WEAPON){
				weapons.add(c.getName());
			}
		}
		setTitle("Detective Notes");
		setSize(400,600);
		setLayout( new GridLayout(3,2));
		DetectiveCheckBox peopleCheck = new DetectiveCheckBox(people, "People");
		DetectiveNotesGuess peopleGuess = new DetectiveNotesGuess(people, "Person Guess");
		DetectiveCheckBox roomsCheck = new DetectiveCheckBox(rooms, "Rooms");
		DetectiveNotesGuess roomsGuess = new DetectiveNotesGuess(rooms, "Room Guess");
		DetectiveCheckBox weaponsCheck = new DetectiveCheckBox(weapons, "Weapons");
		DetectiveNotesGuess weaponsGuess = new DetectiveNotesGuess(weapons, "Weapon Guess");
		add(peopleCheck);
		add(peopleGuess);
		add(roomsCheck);
		add(roomsGuess);
		add(weaponsCheck);
		add(weaponsGuess);
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		ClueGame game = new ClueGame();
		game.deal();
		DetectiveNotesDialog panel = new DetectiveNotesDialog(game.getDeck());
		panel.setVisible(true);
	}


}