package logic.characters;

import logic.weapons.drawable.Shot;

import java.awt.*;

public abstract class Character {
	protected int health;
	protected Point position;

	public Shot gun;

	public Character(int pHealth, Point pPosition) {
		this.health = pHealth;
		this.position = pPosition;
		this.gun = new Shot(pPosition);
	}

	public int getHealth() {
		return health;
	}

	public Point getPosition() {
		return position;
	}

	public void hitIt() {
		// TODO...
	}

	public void move(int direction) {
		this.position.x += direction;
	}
}
