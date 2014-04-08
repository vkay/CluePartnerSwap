package clueGame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class ClueGame extends JFrame {
	private static ArrayList<Player> people;
	private ArrayList<Card> deck;
	private ArrayList<Card> seen;
	private String cardLeg = "cardLeg";
	private String playerLeg = "playerLegend";
	private Board board;
	private int countWeap = 0;
	private int countPlay = 0;
	private int countRoom = 0;
	private Solution solu;
	private int whoseTurn;
	private boolean nextPlayer = true;
	private ControlPanel controlPanel;
	private int numPlayers;
	private int humanPlayerIndex;
	
	public ClueGame() throws FileNotFoundException {
		deck = new ArrayList<Card>();
		seen = new ArrayList<Card>();
		people = new ArrayList<Player>();
		board = new Board("clueLayout1.csv", "cluelegend.txt");
		board.loadConfigFiles();
		loadConfigFiles();
		whoseTurn = humanPlayerIndex-1;
		board.setPlayers(people);
		deal();
		/*for (Player p: people) {
			p.humanTurn();
		}*/
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(createFileMenu());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("ClueGame");
		add(board, BorderLayout.CENTER);
		setSize(24 * 35, 24 * 35);
		controlPanel = new ControlPanel(people.get(whoseTurn), this);
		add(new CardDisplay(people.get(4)), BorderLayout.EAST);
		add(controlPanel, BorderLayout.SOUTH);
	}

	private JMenu createFileMenu() {
		JMenu menu = new JMenu("File");
		menu.add(createFileExitItem());
		menu.add(createFileDetectItem());
		return menu;
	}

	private JMenuItem createFileDetectItem() {
		JMenuItem item = new JMenuItem("Detective Notes");
		class MenuItemListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				DetectiveNotesDialog dialog = new DetectiveNotesDialog(
						getDeck());
				dialog.setVisible(true);
			}
		}
		item.addActionListener(new MenuItemListener());
		return item;
	}

	private JMenuItem createFileExitItem() {
		JMenuItem item = new JMenuItem("Exit");
		class MenuItemListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		}
		item.addActionListener(new MenuItemListener());
		return item;
	}

	public Board getBoard() {
		return board;
	}

	public void deal() {
		long seed = System.nanoTime();
		Random rn = new Random();
		int person = Math.abs(rn.nextInt()) % 6 + 9;
		int weap = Math.abs(rn.nextInt()) % 6 + 15;
		int room = Math.abs(rn.nextInt()) % 9;
		Card pers2 = deck.get(person);
		Card weap2 = deck.get(weap);
		Card room2 = deck.get(room);
		solu = new Solution(room2.getName(), pers2.getName(), weap2.getName());
		deck.remove(weap2);
		deck.remove(pers2);
		deck.remove(room2);
		Collections.shuffle(deck, new Random(seed));
		Collections.shuffle(people, new Random(seed));
		int track = 0;
		for (Card c : deck) {
			people.get(track % 6).addToHand(c);
			track++;
		}
		deck.add(weap2);
		deck.add(pers2);
		deck.add(room2);
	}

	public void loadConfigFiles() throws FileNotFoundException {
		FileReader reader = new FileReader(cardLeg);
		Scanner in = new Scanner(reader);
		while (in.hasNextLine()) {
			String cardName = in.nextLine();
			String cardType = in.nextLine();
			if (cardType.equals("WEAPON")) {
				countWeap++;
			} else if (cardType.equals("PLAYER")) {
				countPlay++;
			} else if (cardType.equals("ROOM")) {
				countRoom++;
			}
			deck.add(new Card(cardName, CardType.valueOf((cardType))));
		}
		in.close();
		FileReader reader2 = new FileReader(playerLeg);
		Scanner in2 = new Scanner(reader2);
		Random rand = new Random();
		numPlayers = in2.nextInt();
		in2.nextLine();
		humanPlayerIndex = rand.nextInt(numPlayers);
		for (int count = 1; in2.hasNextLine(); count++) {
			String playerName = in2.nextLine();
			String playerColor = in2.nextLine();
			int x = in2.nextInt();
			int y = in2.nextInt();
			if (count != 6) {
				String extra = in2.nextLine();
			}
			if (count == humanPlayerIndex) {
				people.add(new HumanPlayer(playerName, playerColor, board
						.getCell(x, y)));
			} else {
				people.add(new ComputerPlayer(playerName, playerColor, board
						.getCell(x, y)));
			}
		}
		in2.close();

	}

	public void nextPlayer() {
		if (nextPlayer) {
			if(whoseTurn == people.size() - 1) {
				whoseTurn = 0;
			} else {
				whoseTurn++;
			}
		}
		controlPanel.setWhoseTurn(people.get(whoseTurn).getName());
	}
	
	public String turn() {
		return people.get(whoseTurn).getName();
	}

	public Card handleSuggestion(String person, String room, String weapon,
			Player accusingPerson) {
		int index = 0;
		Card c = null;

		for (Player p : people) {
			if (p.getName() == accusingPerson.getName()) {
				break;
			}
			index++;
		}
		for (int i = index + 1; i < index + people.size(); i++) {
			if (c == null) {
				c = people.get(i % people.size()).disproveSuggestion(person,
						room, weapon);
			} else {
				break;
			}
		}
		return c;

	}

	public boolean checkAccusation(Solution accusation) {
		return accusation.equals(solu);
	}

	public void setSolu(Solution solu) {
		this.solu = solu;
	}

	public ArrayList<Player> playerList() {
		return people;
	}

	public ArrayList<Card> getDeck() {
		return deck;
	}

	public ArrayList<Card> getSeen() {
		return seen;
	}

	public int countWeap() {
		return countWeap;
	}

	public int countPlay() {
		return countPlay;
	}

	public int countRoom() {
		return countRoom;
	}

	public static void main(String[] args) throws FileNotFoundException {
		ClueGame game = new ClueGame();
		game.setVisible(true);
		JOptionPane.showMessageDialog(new JFrame(),
				"You are " + people.get(game.humanPlayerIndex) +", press Next Player to begin play.",
				"Welcome to Clue", JOptionPane.INFORMATION_MESSAGE);

	}

}