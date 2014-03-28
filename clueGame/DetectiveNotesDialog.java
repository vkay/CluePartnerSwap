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
		DetectiveCheckBox dis1 = new DetectiveCheckBox(people, "People");
		DetectiveNotesGuess dis2 = new DetectiveNotesGuess(people, "Person Guess");
		DetectiveCheckBox dis3 = new DetectiveCheckBox(rooms, "Rooms");
		DetectiveNotesGuess dis4 = new DetectiveNotesGuess(rooms, "Room Guess");
		DetectiveCheckBox dis5 = new DetectiveCheckBox(weapons, "Weapons");
		DetectiveNotesGuess dis6 = new DetectiveNotesGuess(weapons, "Weapon Guess");
		add(dis1);
		add(dis2);
		add(dis3);
		add(dis4);
		add(dis5);
		add(dis6);
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		ClueGame game = new ClueGame();
		game.deal();
		DetectiveNotesDialog panel = new DetectiveNotesDialog(game.getDeck());
		panel.setVisible(true);
	}


}