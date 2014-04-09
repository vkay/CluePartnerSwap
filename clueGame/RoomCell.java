package clueGame;

import java.awt.Color;
import java.awt.Graphics;

public class RoomCell extends BoardCell {
	private Boolean nameHere;

	public enum DoorDirection {
	    UP, DOWN, LEFT, RIGHT, NONE 
	}

	private DoorDirection doorDirection;
	private boolean isDoor = false;

	public RoomCell(char dir){
		super();
		switch (dir){
			case 'U':	doorDirection = DoorDirection.UP;
			break;
			case 'L':	doorDirection = DoorDirection.LEFT;
			break;
			case 'R':	doorDirection = DoorDirection.RIGHT;
			break;
			case 'D':	doorDirection = DoorDirection.DOWN;
			break;
			default:	doorDirection = DoorDirection.NONE;
			break;
		}
		if (doorDirection != DoorDirection.NONE){
			isDoor = true;
		}
		nameHere=false;
	}

	public RoomCell() {
		super();
		nameHere = false;
	}

	public DoorDirection getDoorDirection() {
		return doorDirection;
	}



	public Boolean getNameHere() {
		return nameHere;
	}

	public void setNameHere(Boolean nameHere) {
		this.nameHere = nameHere;
	}

	@Override
	public boolean isRoom(){
		return true;
	}

	@Override
	public boolean isDoorway(){
		if (getInit().length() == 2)
			return true;
		return isDoor;
	}

	@Override
	public void draw(Graphics g, Board board) {
		setHighlighted(false);
		g.setColor(Color.gray);
		g.fillRect(getCol()*LENGTH, getRow()*LENGTH, LENGTH, LENGTH);
		if (isDoorway()){
			g.setColor(Color.cyan);
			if(getDoorDirection() == DoorDirection.UP){
				for(int i = 0; i < 4; i++)
					g.drawLine(getCol()*LENGTH, getRow()*LENGTH+i, getCol()*LENGTH + LENGTH, getRow()*LENGTH+i);
			}else if(getDoorDirection() == DoorDirection.DOWN){
				for(int i = 0; i < 4; i++)
					g.drawLine((getCol())*LENGTH, (getRow()+1)*LENGTH-i, (getCol()+1)*LENGTH, (getRow()+1)*LENGTH-i);
			}else if(getDoorDirection() == DoorDirection.RIGHT){
				for(int i = 0; i < 4; i++){
					g.drawLine((getCol()+1)*LENGTH-i, (getRow())*LENGTH, (getCol()+1)*LENGTH-i, (getRow()+1)*LENGTH);
				}
			}else if(getDoorDirection() == DoorDirection.LEFT){
				for(int i = 0; i < 4; i++){
					g.drawLine((getCol())*LENGTH+i, (getRow())*LENGTH, (getCol())*LENGTH+i, (getRow()+1)*LENGTH);
				}
			}
		}
		if(getNameHere()){
			g.setColor(Color.black);
			g.drawString(board.getRooms().get(getType()), getCol()*LENGTH, getRow()*LENGTH-10);
		}
	}
}