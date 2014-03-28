package clueGame;

public class Card {
	private String name;
	private CardType type;

	public Card(String name, CardType type) {
		super();
		this.name = name;
		this.type = type;
	}
	public Card() {}
	public String getName() {
		return name;
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
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

}