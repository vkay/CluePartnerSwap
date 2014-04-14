package clueGame;

public class Solution {
	public String person;
	public String weapon;
	public String room;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || !(obj instanceof Solution))
			return false;
		Solution other = (Solution) obj;
		if (person.equals(other.person) && room.equals(other.room) && weapon.equals(other.weapon))
			return true;
		else return false;
	}

	public String getPerson() {
		return person;
	}

	public String getWeapon() {
		return weapon;
	}

	public String getRoom() {
		return room;
	}

	public Solution(String room, String person, String weapon) {
		this.person = person;
		this.weapon = weapon;
		this.room = room;
	}

}