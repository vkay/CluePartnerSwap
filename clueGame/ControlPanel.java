package clueGame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ControlPanel extends JFrame {


	public ControlPanel(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Blank");
		setSize(600,200);
		WhoseTurn whoTurn = new WhoseTurn();
		add(whoTurn, BorderLayout.WEST);
		JButton nextPlayer = new JButton("Next Player");
		JButton makeAccusation = new JButton("Make an accusation");
		add(nextPlayer, BorderLayout.CENTER);
		add(makeAccusation, BorderLayout.EAST);
		Display dis = new Display();
		add(dis, BorderLayout.SOUTH);
	}


	public static void main(String[] args) {
		ControlPanel panel = new ControlPanel();
		panel.setVisible(true);
	}

}