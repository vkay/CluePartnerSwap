package clueGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Board extends JPanel {
	private ArrayList<BoardCell> cells;
	private Map<Character,String> rooms;
	private int numRows;
	private int numColumns;
	private String csv = "clueLayout1.csv";
	private String legend = "cluelegend.txt";
	private Map<Integer, ArrayList<Integer>> adjMap;
	private Set<BoardCell> targetSet;
	boolean[] check = null;
	private ArrayList<Player> players;

	public Board(){
		cells = new ArrayList<BoardCell>();
		rooms = new HashMap<Character,String>();
		adjMap = new HashMap<Integer, ArrayList<Integer>>();
		targetSet = new HashSet<BoardCell>();
		players = new ArrayList<Player>();
	}
	public Board(ArrayList<Player> players){
		cells = new ArrayList<BoardCell>();
		rooms = new HashMap<Character,String>();
		adjMap = new HashMap<Integer, ArrayList<Integer>>();
		targetSet = new HashSet<BoardCell>();
		players = new ArrayList<Player>();
	}
	public Board(String c, String l){
		csv = c;
		legend = l;
		cells = new ArrayList<BoardCell>();
		rooms = new HashMap<Character,String>();
		adjMap = new HashMap<Integer, ArrayList<Integer>>();
		targetSet = new HashSet<BoardCell>();
		players = new ArrayList<Player>();
	}

	public void loadLegend() throws BadConfigFormatException {
		FileReader file = null;
		Scanner scan = null;
		String s = "";
		String [] str = null;
		Character key = null;
		String value = null;
		try {
			file = new FileReader(legend);
			scan = new Scanner(file);
			while(scan.hasNext()){
				s = scan.nextLine();
				str = s.split(", ");
				if (str.length != 2) {
					BadConfigFormatException e = new BadConfigFormatException("Initial or room name missing from legend");
					System.out.println(e);
					throw e;
				}
				key = str[0].charAt(0);
				value = str[1];
				rooms.put(key, value);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find file: " + legend);
		} catch (IOException e) {
			System.out.println("Cannot input from file: " + legend);
		}	
	}

	public void loadLayout() throws BadConfigFormatException {
		FileReader file = null;
		Scanner scan = null;
		String s = "";
		String [] str = null;
		BoardCell b;
		WalkwayCell walk;
		int row = 0;
		try {
			file = new FileReader(csv);
			scan = new Scanner(file);
			if (scan.hasNext()){
				s = scan.nextLine();
				str = s.split(",");				
				numColumns = str.length;
				if (numColumns == 0) {
					BadConfigFormatException e = new BadConfigFormatException("Empty layout");
					System.out.println(e);
					throw e;
				}
				for (int i = 0; i < numColumns; i++){
					if (!(rooms.containsKey(str[i].charAt(0)))){
						BadConfigFormatException e = new BadConfigFormatException("Incompatible cell initial in layout");
						System.out.println(e);
						throw e;
					}
					if (str[i].charAt(0) == 'W'){
						walk = new WalkwayCell();
						b = walk;
					}
					else {
						if(str[i].length() == 2){
							b = new RoomCell(str[i].charAt(1));
						}else if (str[i].length() == 3){
							b = new RoomCell();
							((RoomCell) b).setNameHere(true);
						}else{
							b = new RoomCell();
						}
					}
					b.setInit(str[i]);
					b.setRow(row);
					b.setCol(i);
					cells.add(b);
				}
			}
			else{
				BadConfigFormatException e = new BadConfigFormatException("Empty layout");
				System.out.println(e);
				throw e;
			}
			while (scan.hasNext()) {
				row ++;
				s = scan.nextLine();
				str = s.split(",");				
				if (numColumns != str.length){
					BadConfigFormatException e = new BadConfigFormatException("Row length mismatch in layout");
					System.out.println(e);
					throw e;
				}
				for (int i = 0; i < numColumns; i++){
					if (!(rooms.containsKey(str[i].charAt(0)))){
						BadConfigFormatException e = new BadConfigFormatException("Incompatible room initial in layout");
						System.out.println(e);
						throw e;
					}
					if (str[i].charAt(0) == 'W'){
						walk = new WalkwayCell();
						b = walk;
					}
					else {
						if(str[i].length() == 2){
							b = new RoomCell(str[i].charAt(1));
						}else if (str[i].length() == 3){
							b = new RoomCell();
							((RoomCell) b).setNameHere(true);
						}else{
							b = new RoomCell();
						}
					}
					b.setInit(str[i]);
					b.setRow(row);
					b.setCol(i);
					cells.add(b);
				}
			}
			numRows = row + 1;	
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find file: " + csv);
		} catch (IOException e) {
			System.out.println("Cannot input from file: " + csv);
		}
	}

	public void setAdjMap() {
		int size = numRows * numColumns;
		for (int i = 0; i < size; i++) {
			int r = i / numColumns;
			int c = i % numColumns;
			calcAdjacencies(r, c);
		}
	}

	public void loadConfigFiles() throws BadConfigFormatException {
		this.loadLegend();
		this.loadLayout();
		this.setAdjMap();
		this.setBool(numRows * numColumns);
	}

	public int calcIndex(int row, int col){
		int index = numColumns*row + col;
		return index;
	}

	public BoardCell getCell(int row, int col) {
		int index = calcIndex(row, col);
		return cells.get(index);
	}

	public BoardCell getCell(int index) {
		return cells.get(index);
	}

	public RoomCell getRoomCell(int index){
		BoardCell b = cells.get(index);
		char dir = 'z';
		if (b.getInit().length() == 2){
			dir =  b.getInit().charAt(1);
		}
		RoomCell r = new RoomCell(dir);
		r.setInit(b.getInit());		
		return r;
	}

	public Map<Character, String> getRooms() {
		return rooms;
	}

	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}

	public void calcAdjacencies(int row, int col) {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		int index = calcIndex(row, col);

		if(getCell(row, col).getInit().equals("W")){
			if (row + 1  < numRows){
				if((getCell(row+1, col).getInit().equals("W")) || (getRoomCell(calcIndex(row+1, col))).getDoorDirection() == RoomCell.DoorDirection.UP){
					arr.add(calcIndex(row + 1, col));
				}
			}
			if (row - 1 >= 0){
				if((getCell(row-1, col).getInit().equals("W")) || (getRoomCell(calcIndex(row-1, col))).getDoorDirection() == RoomCell.DoorDirection.DOWN){
					arr.add(calcIndex(row - 1, col));
				}
			}
			if (col + 1 < numColumns){
				if((getCell(row, col+1).getInit().equals("W")) || (getRoomCell(calcIndex(row, col+1))).getDoorDirection() == RoomCell.DoorDirection.LEFT){
					arr.add(calcIndex(row, col + 1));
				}
			}
			if (col - 1 >= 0){
				if((getCell(row, col-1).getInit().equals("W")) || (getRoomCell(calcIndex(row, col-1))).getDoorDirection() == RoomCell.DoorDirection.RIGHT){
					arr.add(calcIndex(row, col - 1));
				}
			}			
		}
		if(getRoomCell(calcIndex(row, col)).isDoorway()){

			 if(getRoomCell(calcIndex(row, col)).getDoorDirection() == RoomCell.DoorDirection.DOWN){
					arr.add(calcIndex(row+1, col));
			 }
			 if(getRoomCell(calcIndex(row, col)).getDoorDirection() == RoomCell.DoorDirection.UP){
					arr.add(calcIndex(row-1, col));
			 }
			 if(getRoomCell(calcIndex(row, col)).getDoorDirection() == RoomCell.DoorDirection.RIGHT){
					arr.add(calcIndex(row, col+1));
			 }
			 if(getRoomCell(calcIndex(row, col)).getDoorDirection() == RoomCell.DoorDirection.LEFT){
					arr.add(calcIndex(row, col-1));
			 }
		}
		adjMap.put(index, arr);
	}

	public void calcTargets(int row, int col, int steps) {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		int index = calcIndex(row, col);
		check[index] = true;

		for (Integer i : getAdjMap().get(index)) {
			if (check[i] == false){
				arr.add(i);
			}
		}

		for (Integer s : arr) {
			check[s] = true;
			if (steps <= 1 || getRoomCell(s).isDoorway()) {
				targetSet.add(getCell(s));
			}
			else {
				row = s / numColumns;
				col = s % numColumns;
				calcTargets(row, col, steps - 1);
			}
			check[s] = false;
		}
		check[index] = false;
	}

	public Set<BoardCell> getTargets() {
		Set<BoardCell> set = targetSet;
		targetSet = new HashSet<BoardCell>();	
		return set;
	}
	
	public void highlightTargets() {
		
		repaint();
	}

	public Map<Integer, ArrayList<Integer>> getAdjMap() {
		return adjMap;
	}
	public ArrayList<Integer> getAdjList(int index) {
		return getAdjMap().get(index);
	}

	// the check array keeps track of where the player has gone, true he's been there, false he hasn't
	public void setBool(int size) {
		check = new boolean[size];
		for (int i = 0; i < size; i++) {
			check[i] = false;
		}
	}

	public void setPlayers(ArrayList<Player> players){
		this.players = players;
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for (BoardCell b: cells ){
			b.draw(g,  this);
		}
		for(Player p : players){
			p.draw(g, this);
		}
	}

}