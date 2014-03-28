package clueGame;

public enum CardType {
	ROOM("Room"), WEAPON("Weapon"), PLAYER("Player");
	String value;

	CardType(String value) {
		this.value = value;
	}
	public String toString(){
		return value;
	}



}