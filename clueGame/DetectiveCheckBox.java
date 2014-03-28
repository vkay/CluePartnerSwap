package clueGame;

import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class DetectiveCheckBox extends JPanel{

	public DetectiveCheckBox(ArrayList<String> items, String title) {
		for(String name: items){
			add( new JCheckBox(name));
		}
		setBorder(new TitledBorder (new EtchedBorder(), title));

	}
}