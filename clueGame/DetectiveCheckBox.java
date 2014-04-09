package clueGame;

import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class DetectiveCheckBox extends JPanel{

	public DetectiveCheckBox(ArrayList<String> items, String title) {
		for(String name: items){
			add( new JCheckBox(name));
		}
		setBorder(new TitledBorder (new EtchedBorder(), title));

	}
}