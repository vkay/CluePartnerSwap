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


	public ControlPanel(Player player){
		setLayout(new BorderLayout());
		WhoseTurn whoTurn = new WhoseTurn();
		add(whoTurn, BorderLayout.WEST);
		JButton nextPlayer = new JButton("Next Player");
		JButton makeAccusation = new JButton("Make an accusation");
		add(nextPlayer, BorderLayout.CENTER);
		add(makeAccusation, BorderLayout.EAST);
		Display dis = new Display(player);
		add(dis, BorderLayout.SOUTH);
	}

}