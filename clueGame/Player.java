package clueGame;

import java.awt.Color;
import java.awt.Graphics;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;

public class Player {
	private String name;
	private String color;
	protected BoardCell startingPosition;
	private int numCards=0;
	protected ArrayList<Card> hand;
	public static int LENGTH = 30;

	public ArrayList<Card> getHand() {
		return hand;
	}
	public void addToHand(Card c){
		hand.add(c);
		numCards++;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Color getColor() {
		Color newColor;
		try{
			// We can use reflection to convert the string to a color
			Field field = Class.forName("java.awt.Color").getField(color.trim());     
			newColor = (Color)field.get(null); } 
		catch (Exception e){
			newColor = null; //Not defined
		}

		return newColor;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public BoardCell getStartingPosition() {
		return startingPosition;
	}

	public void setStartingPosition(BoardCell startingPosition) {
		this.startingPosition = startingPosition;
	}

	public Player(String name, String color, BoardCell startingPosition) {
		super();
		this.name = name;
		this.color = color;
		this.startingPosition = startingPosition;
		hand = new ArrayList<Card>();
	}

	public Card disproveSuggestion(String person, String room, String weapon){
		Random rn = new Random();
		ArrayList<Card> choices = new ArrayList<Card>();
		for(Card c: hand){
			if(c.getName().equals(person)){
				choices.add(c);
			}
			if(c.getName().equals(room)){
				choices.add(c);
			}
			if(c.getName().equals(weapon)){
				choices.add(c);
			}
		}
		if(choices.size() != 0){
			int pick = Math.abs(rn.nextInt())%choices.size();
			return choices.get(pick);
		}
		return null;

	}

	public int getNumCards() {
		return numCards;
	}

	public void draw(Graphics g, Board board){
		g.setColor(getColor());
		int x = startingPosition.getCol()*LENGTH;
		int y = startingPosition.getRow()*LENGTH;
		g.fillOval(x, y,LENGTH ,LENGTH );
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (startingPosition == null) {
			if (other.startingPosition != null)
				return false;
		} else if (!startingPosition.equals(other.startingPosition))
			return false;
		return true;
	}


}