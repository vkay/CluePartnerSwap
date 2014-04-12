package clueGame;

public class HumanPlayer extends Player {
	/*private Solution suggestion;*/

	public HumanPlayer(String name, String color, BoardCell startingPosition) {
		super(name, color, startingPosition);
	}
	
/*	public void makeSuggestion(Solution sugg) {
		suggestion = sugg;
	}
	
	public Solution getSuggestion() {
		return suggestion;
	}*/
	
	@Override
	public void handleTurn(ClueGame clueGame) {
		clueGame.setHumanTurnFinished(false);
		
		roll();
		clueGame.getBoard().calcAdjacencies(currentPosition.getRow(), currentPosition.getCol());
		clueGame.getBoard().calcTargets(currentPosition.getRow(), currentPosition.getCol(), getRoll());
		clueGame.getBoard().setHighlighted(true);
	}
	
	public void makeMove(ClueGame game, Board board, int row, int col) {
		setCurrentPosition(board.getCell(row, col));
		board.repaint();
		if (currentPosition.isRoom()) game.humanSuggestion();
	}

}