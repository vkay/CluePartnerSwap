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
	private ClueGame game;
	private JButton nextPlayer, makeAccusation;

	public ControlPanel(Player player, final ClueGame game){
		this.game = game;
		setLayout(new BorderLayout());
		whoTurn = new WhoseTurn(game);
		add(whoTurn, BorderLayout.WEST);
		nextPlayer = new JButton("Next Player");
		nextPlayer.addActionListener(new ButtonListener());
		makeAccusation = new JButton("Make an accusation");
		makeAccusation.addActionListener(new ButtonListener());

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

	class ButtonListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getSource() == nextPlayer) {
				game.nextPlayer();
			} else if (arg0.getSource() == makeAccusation)
				game.humanAccusation();
		}
		 
	}
}