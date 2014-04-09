package clueGame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ControlPanel extends JPanel {
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