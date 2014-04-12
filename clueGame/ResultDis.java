package clueGame;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class ResultDis extends JPanel {

private JTextField res;

	public ResultDis() {
		JLabel result = new JLabel("Response");
		add(result);
		res = new JTextField(8);
		res.setEditable(false);
		add(res);
		setBorder(new TitledBorder (new EtchedBorder(), "Guess Result"));
	}
	
	public void setResponse(String response) {
		res.setText(response);
	}
}