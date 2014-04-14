package clueGame;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class CardDisplay extends JPanel{
	
	private Player player;
	
	public CardDisplay(Player player) {
		this.player = player;
		setLayout(new GridLayout(4, 1));
		JLabel myCards = new JLabel("My Cards");
		add(myCards);
		CardPanel people = new CardPanel("People");
		add(people);
		CardPanel rooms = new CardPanel("Rooms");
		add(rooms);
		CardPanel weapons = new CardPanel("Weapons");
		add(weapons);
	}
	
	class CardPanel extends JPanel {
		private JTextArea text;

		public CardPanel(String title) {
			switch (title) {
				case "People":
					text = new JTextArea(player.getPeople());
					break;
				case "Rooms":
					text = new JTextArea(player.getRooms());
					break;
				case "Weapons":
					text = new JTextArea(player.getWeapons());
					break;
			}
			add(text);
			text.setEditable(false);
			setBorder(new TitledBorder(new EtchedBorder(), title));
		}
	}

}