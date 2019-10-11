package logic.weapons;

import java.awt.*;

public abstract class Weapon {
	private int power;
	protected Point position;

	public Weapon(int pPower, Point pPosition) {
		this.power = pPower;
		this.position = pPosition;
	}

	public int getPower() {
		return power;
	}

	public Point getPosition() {
		return position;
	}

	public void move(int direction) {
		this.position.x += direction;
	}
}
