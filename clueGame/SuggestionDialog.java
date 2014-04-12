package clueGame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class SuggestionDialog extends JDialog {
	
	private JButton makeSuggestion, cancel;
	private ClueGame game;
	JComboBox<String> playerBox;
	JComboBox<String> weaponBox;

	public SuggestionDialog(ArrayList<Card> deck, BoardCell currentPosition, ClueGame game, Player player) {
		this.game = game;
		setTitle("Make a suggestion!");
		setSize(300,300);
		setVisible(true);
		setLayout(new GridLayout(4,2));
		ArrayList<String> players = new ArrayList<String>();
		ArrayList<String> weapons = new ArrayList<String>();
		for (int i = 0; i < deck.size(); i++) {
			Card card = deck.get(i);
			if (card.getType() == CardType.PLAYER) players.add(card.getName());
			else if (card.getType() == CardType.WEAPON) weapons.add(card.getName());
		}
		add(new JLabel("Room"));
		add(new JLabel(game.getBoard().getRooms().get(currentPosition.getType())));
		
		playerBox = makeComboBox(players);
		weaponBox = makeComboBox(weapons);
		
		add(new JLabel("Player"));
		add(playerBox);

		add(new JLabel("Weapon"));
		add(weaponBox);

		makeSuggestion = new JButton("Make Suggestion");
		makeSuggestion.addActionListener(new ButtonListener(playerBox, game.getBoard().getRooms().get(currentPosition.getType()), weaponBox, player));
		add(makeSuggestion);
		
		cancel = new JButton("Cancel");
		cancel.addActionListener(new ButtonListener());
		add(cancel);
		
	}
	
	public JComboBox<String> makeComboBox(ArrayList<String> cards) {
		JComboBox<String> combobox = new JComboBox<String>();
		for (String s : cards) combobox.addItem(s);
		return combobox;
	}
	
	
	class ButtonListener implements ActionListener {
		JComboBox<String> player, weapon;
		String room;
		Player accuser;
		public ButtonListener() {
			
		}
		public ButtonListener(JComboBox<String> player, String room, JComboBox<String> weapon, Player accuser) {
			this.player= player;
			this.room = room;
			this.weapon = weapon;
			this.accuser= accuser;
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getSource() == makeSuggestion)
			game.handleSuggestion((String)player.getSelectedItem(), room, (String)weapon.getSelectedItem(), accuser);
			else {
				setVisible(false); 
				dispose();
			}
			
		}
		
	}
}
