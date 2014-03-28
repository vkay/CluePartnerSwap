package clueGame;

public class Card {
	private String myName;
	private CardType type;

	public Card(String name, CardType type) {
		super();
		this.myName = name;
		this.type = type;
	}
	public Card() {}
	public String getName() {
		return myName;
	}
	public CardType getType() {
		return type;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (myName == null) {
			if (other.myName != null)
				return false;
		} else if (!myName.equals(other.myName))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

}