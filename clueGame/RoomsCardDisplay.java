package clueGame;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class RoomsCardDisplay extends JPanel {
	private JTextArea rooms;

	public RoomsCardDisplay(Player player) {
		rooms = new JTextArea(player.getRooms());
		add(rooms);
		setBorder(new TitledBorder (new EtchedBorder(), "Rooms"));
	}
}