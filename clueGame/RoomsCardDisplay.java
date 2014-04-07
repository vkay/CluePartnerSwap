package clueGame;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class RoomsCardDisplay extends JPanel {
	private JTextField rooms;
	
	public RoomsCardDisplay(Player player) {
		rooms = new JTextField(player.getRooms() + "\n", 8);
		add(rooms);
		setBorder(new TitledBorder (new EtchedBorder(), "Rooms"));
	}
}
