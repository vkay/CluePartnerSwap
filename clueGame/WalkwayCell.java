package clueGame;

import java.awt.Color;
import java.awt.Graphics;

public class WalkwayCell extends BoardCell {

	public WalkwayCell() {
		super();
	}

	@Override
	public boolean isWalkway() {
		return true;
	}

	@Override
	public void draw(Graphics g, Board board) {
		setHighlighted(false);
		g.setColor(Color.yellow);
		g.fillRect(getCol() * LENGTH, getRow() * LENGTH, LENGTH, LENGTH);
		g.setColor(Color.BLACK);
		g.drawRect(getCol() * LENGTH, getRow() * LENGTH, LENGTH, LENGTH);

	}
}