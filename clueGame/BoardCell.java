package clueGame;

import java.awt.Graphics;

public abstract class BoardCell {
	private int row;
	private int col;
	private String init;
	private char type;
	public static int LENGTH = 30;

	public BoardCell(){}


	public BoardCell(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}


	public void setRow(int row) {
		this.row = row;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public void setInit(String init) {
		this.init = init;
		this.type = init.charAt(0);
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public String getInit() {
		return init;
	}

	public char getType() {
		return type;
	}

	public boolean isWalkway(){
		return false;
	}

	public boolean isRoom(){
		return false;
	}

	public boolean isDoorway(){
		return false;
	}

	abstract public void draw(Graphics g, Board board);


}