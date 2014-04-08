package clueGame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class ControlPanel extends JPanel {
	private String whoseTurn;
	private WhoseTurn whoTurn;
	private Display dis;

	public ControlPanel(Player player, final ClueGame clueGame){
		setLayout(new BorderLayout());
		whoTurn = new WhoseTurn(clueGame);
		add(whoTurn, BorderLayout.WEST);
		final JButton nextPlayer = new JButton("Next Player");
		nextPlayer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == nextPlayer) {
					clueGame.nextPlayer();
				}
			}
		});
		JButton makeAccusation = new JButton("Make an accusation");
		add(nextPlayer, BorderLayout.CENTER);
		add(makeAccusation, BorderLayout.EAST);
		dis = new Display(player);
		add(dis, BorderLayout.SOUTH);
	}
	
	public void setWhoseTurn(String whoseTurn) {
		whoTurn.setWhoseTurn(whoseTurn);
	}
	
	public Display getDisplay() {
		return dis;
	}


}