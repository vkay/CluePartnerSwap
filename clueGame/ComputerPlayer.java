package clueGame;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class ComputerPlayer extends Player {
	private char lastRoomVisited = ' ';

	public BoardCell pickLocation(Set<BoardCell> targets){
		BoardCell b = null;
		ArrayList<BoardCell> rooms = new ArrayList<BoardCell>();
		for(BoardCell c: targets){
			if(c.isDoorway() == true){
				if(lastRoomVisited != c.getType()){
					rooms.add(c);
				}
			}
		}
		Random rn = new Random();
		if(rooms.size() > 0){
			return rooms.get(Math.abs(rn.nextInt())%rooms.size());
		}
		int count = 0;
		int pick = Math.abs(rn.nextInt())%targets.size();
		for(BoardCell c : targets){
			if(count == pick){
				return c;
			}
			count++;
		}
		return b;
	}
	public char getLastRoomVisited() {
		return lastRoomVisited;
	}
	public void setLastRoomVisited(char lastRoomVisited) {
		this.lastRoomVisited = lastRoomVisited;
	}
	public Solution createdSuggestion(ArrayList<Card> deck, ArrayList<Card> seen, Map<Character,String> key){
		String accusedName="";
		String accusedWeap="";
		String accusedRoom=key.get(startingPosition.getType());
		ArrayList<Card> playerChoices = new ArrayList<Card>();
		ArrayList<Card> weapChoices = new ArrayList<Card>();
		for(Card c: deck){
			if(!seen.contains(c)&&!hand.contains(c)){
				if(c.getType() == CardType.PLAYER){
					playerChoices.add(c);
				}else if(c.getType() == CardType.WEAPON){
					weapChoices.add(c);
				}
			}
		}
		Random rn = new Random();
		accusedName = playerChoices.get( Math.abs( rn.nextInt() ) % playerChoices.size() ).getName();
		accusedWeap = weapChoices.get( Math.abs( rn.nextInt() ) % weapChoices.size() ).getName();
		return new Solution( accusedRoom,accusedName ,accusedWeap);

	}
	public void updateSeen(Card seen){

	}
	public ComputerPlayer(String name, String color, BoardCell startingPosition) {
		super(name, color, startingPosition);
	}

}