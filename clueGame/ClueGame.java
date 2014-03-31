package clueGame;

import java.awt.BorderLayout;
import java.awt.Graphics;
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

public class ClueGame extends JFrame {
	private ArrayList<Player> people;
	private ArrayList<Card> deck;
	private ArrayList<Card> seen;
	private String cardLeg = "cardLeg";
	private String playerLeg = "playerLegend";
	private Board board;
	private int countWeap=0;
	private int countPlay=0;
	private int countRoom=0;
	private Solution solu;
	private Random rn;
	private Card pers;
	private Card weap;
	private Card room;

	public ClueGame() throws FileNotFoundException{
		deck = new ArrayList<Card>();
		seen = new ArrayList<Card>();
		people = new ArrayList<Player>();
		board = new Board("clueLayout1.csv", "cluelegend.txt");
		board.loadConfigFiles();
		loadConfigFiles();
		board.setPlayers(people);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(createFileMenu());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("ClueGame");
		add(board, BorderLayout.CENTER);
		setSize(24*30,24*30);
	}
	private JMenu createFileMenu()
	{
	  JMenu menu = new JMenu("File"); 
	  menu.add(createFileExitItem());
	  menu.add(createFileDetectItem());
	  return menu;
	}
	private JMenuItem createFileDetectItem() {
		JMenuItem item = new JMenuItem("Detective Notes");
		class MenuItemListener implements ActionListener {
		    public void actionPerformed(ActionEvent e)
		    {
		       DetectiveNotesDialog dialog = new DetectiveNotesDialog(getDeck());
		       dialog.setVisible(true);
		    }
		  }
		item.addActionListener(new MenuItemListener());
		return item;
	}
	private JMenuItem createFileExitItem()
	{
	  JMenuItem item = new JMenuItem("Exit");
	  class MenuItemListener implements ActionListener {
	    public void actionPerformed(ActionEvent e)
	    {
	       System.exit(0);
	    }
	  }
	  item.addActionListener(new MenuItemListener());
	  return item;
	}



	public Board getBoard() {
		return board;
	}
	public void deal(){
		long seed = System.nanoTime();
		Random rn = new Random();
		solu = new Solution( room.getName(),pers.getName() ,weap.getName());
		deck.remove(weap);
		deck.remove(pers);
		deck.remove(room);
		Collections.shuffle(deck, new Random(seed));
		Collections.shuffle(people, new Random(seed));
		int track = 0;
		for(Card c: deck){
			people.get(track%6).addToHand(c);
			track++;
		}
		addToDeck();
	}
	
	public void addToDeck() {
		long seed = System.nanoTime();
		Random rn = new Random();
		Card pers = deck.get(Math.abs(rn.nextInt())%6+9);
		Card weap = deck.get(Math.abs(rn.nextInt())%6 +15);
		Card room = deck.get(Math.abs(rn.nextInt())%9);
		deck.add(weap);
		deck.add(pers);
		deck.add(room);
	}
	
	public void loadConfigFiles() throws FileNotFoundException{
		FileReader reader = new FileReader(cardLeg);
		Scanner in = new Scanner(reader);
		while (in.hasNextLine()) {
			String cardName = in.nextLine();
			String cardType = in.nextLine();
			if(cardType.equals("WEAPON")){
				countWeap++;
			}else if(cardType.equals("PLAYER")){
				countPlay++;
			}else if(cardType.equals("ROOM")){
				countRoom++;
			}
			deck.add(new Card(cardName, CardType.valueOf((cardType))));
		}
		in.close();
		FileReader reader2 = new FileReader(playerLeg);
		Scanner in2 = new Scanner(reader2);
		int count = 1;
		while (in2.hasNextLine()) {
			String playerName = in2.nextLine();
			String playerColor = in2.nextLine();
			int x = in2.nextInt();
			int y = in2.nextInt();
			if(count !=6){
				String extra = in2.nextLine();
			}
			if(count == 5){
				people.add(new HumanPlayer(playerName, playerColor, board.getCell(x,y)));
			}else{
				people.add(new ComputerPlayer(playerName, playerColor, board.getCell(x,y)));
			}
			count++;
		}
		in2.close();

	}
	public void selectAnswer(){

	}
	public Card handleSuggestion(String person, String room, String weapon, Player accusingPerson){
		int index=0;
		Card c =null;

		for(Player p: people){
			if(p.getName() == accusingPerson.getName()){
				break;
			}
			index++;	
		}
		for(int i =index+1; i < index+people.size(); i++){
			if(c == null){
				c = people.get(i%people.size()).disproveSuggestion(person, room, weapon);
			}else{
				break;
			}
		}
		return c;

	}
	public boolean checkAccusation(Solution accusation){
		return accusation.equals(solu);
	}

	public void setSolu(Solution solu) {
		this.solu = solu;
	}


	public ArrayList<Player> playerList(){
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

	}

}