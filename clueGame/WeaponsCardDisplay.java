package clueGame;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class WeaponsCardDisplay extends JPanel {
	private JTextField weapons;
	
	public WeaponsCardDisplay(Player player) {
		weapons = new JTextField(player.getWeapons() + "\n", 8);
		add(weapons);
		setBorder(new TitledBorder (new EtchedBorder(), "Weapons"));
		
	}
}
