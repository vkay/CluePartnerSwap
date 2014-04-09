package clueGame;

public class HumanPlayer extends Player {

	public HumanPlayer(String name, String color, BoardCell startingPosition) {
		super(name, color, startingPosition);
	}
	
	public void makeMove(ClueGame clueGame) {
		clueGame.getBoard().highlightTargets();
	}

}