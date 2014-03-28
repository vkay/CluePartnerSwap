package clueGame;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class DetectiveNotesGuess extends JPanel{
	private JComboBox<String> combo;

	public DetectiveNotesGuess(ArrayList<String> items, String title) {
		combo = createCombo(items);
		setBorder(new TitledBorder (new EtchedBorder(), title));
		add(combo);

	}

	private JComboBox<String> createCombo(ArrayList<String> items) {
		JComboBox<String> combo = new JComboBox<String>();
		for(String s: items){
			combo.addItem(s);
		}
		combo.addItem("Unsure");
		return combo;
	}
}