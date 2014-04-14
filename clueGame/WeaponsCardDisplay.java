package clueGame;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class WeaponsCardDisplay extends JPanel {
	private JTextArea weapons;

	public WeaponsCardDisplay(Player player) {
		weapons = new JTextArea(player.getWeapons());
		add(weapons);
		setBorder(new TitledBorder (new EtchedBorder(), "Weapons"));

	}
}