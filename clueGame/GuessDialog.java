package clueGame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class GuessDialog extends JDialog {

	public enum GuessType {
		SUGGESTION, ACCUSATION
	};

	private JButton makeGuess, cancel;
	private ClueGame game;
	private GuessType type;
	JComboBox<String> playerBox, weaponBox, roomBox;
	Card result;

	public GuessDialog(ArrayList<Card> deck, BoardCell currentPosition,
			ClueGame game, Player player, GuessType type) {
		this.game = game;
		this.type = type;
		result = new Card();
		setTitle("Make a guess!");
		setSize(300, 300);
		setVisible(true);
		setLayout(new GridLayout(4, 2));
		ArrayList<String> players = new ArrayList<String>();
		ArrayList<String> weapons = new ArrayList<String>();
		ArrayList<String> rooms = new ArrayList<String>();
		for (int i = 0; i < deck.size(); i++) {
			Card card = deck.get(i);
			if (card.getType() == CardType.PLAYER)
				players.add(card.getName());
			else if (card.getType() == CardType.WEAPON)
				weapons.add(card.getName());
			else
				rooms.add(card.getName());
		}
		add(new JLabel("Room"));
		if (this.type == GuessType.SUGGESTION)
			add(new JLabel(game.getBoard().getRooms()
					.get(currentPosition.getType())));
		else {
			roomBox = makeComboBox(rooms);
			add(roomBox);
		}

		playerBox = makeComboBox(players);
		weaponBox = makeComboBox(weapons);

		add(new JLabel("Player"));
		add(playerBox);

		add(new JLabel("Weapon"));
		add(weaponBox);

		makeGuess = new JButton("Make Guess");
		makeGuess.addActionListener(new ButtonListener(playerBox, roomBox, game
				.getBoard().getRooms().get(currentPosition.getType()),
				weaponBox, player, type));
		add(makeGuess);

		cancel = new JButton("Cancel");
		cancel.addActionListener(new ButtonListener());
		add(cancel);

	}

	public JComboBox<String> makeComboBox(ArrayList<String> cards) {
		JComboBox<String> combobox = new JComboBox<String>();
		for (String s : cards)
			combobox.addItem(s);
		return combobox;
	}

	public Card getResult() {
		return result;
	}

	class ButtonListener implements ActionListener {
		JComboBox<String> player, weapon, room;
		String roomName;
		Player accuser;

		public ButtonListener() {

		}

		public ButtonListener(JComboBox<String> player, JComboBox<String> room,
				String roomName, JComboBox<String> weapon, Player accuser,
				GuessType type) {
			this.player = player;
			this.roomName = roomName;
			this.room = room;
			this.weapon = weapon;
			this.accuser = accuser;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getSource() == makeGuess) {
				if (type == GuessType.SUGGESTION) {
					result = game.handleSuggestion(
							(String) player.getSelectedItem(), roomName,
							(String) weapon.getSelectedItem(), accuser);
					if (result == null)
						JOptionPane.showMessageDialog(game,
								"No player has a card to show.", "Result",
								JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(game,
								"Your suggestion was wrong.", "Result",
								JOptionPane.INFORMATION_MESSAGE);
				} else {
					if (game.checkAccusation(new Solution((String) player
							.getSelectedItem(), (String) weapon
							.getSelectedItem(), (String) room.getSelectedItem())))
						JOptionPane.showMessageDialog(game, "You win!",
								"Winner!", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(game,
								"Your accusation was wrong!", "Incorrect",
								JOptionPane.INFORMATION_MESSAGE);

					game.getBoard().setHighlighted(false);
					game.setHumanTurnFinished(true);

				}

			}
			setVisible(false);
			dispose();

		}

	}
}
