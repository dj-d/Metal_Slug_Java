package logic.characters.logic;

import logic.characters.Character;

import java.awt.*;

public class Enemy extends Character {
	private static final boolean ALIVE = true;
	private static final boolean DEAD = false;

	private boolean status;

	public Enemy(int pHealth, Point pPosition) {
		super(pHealth, pPosition);

		this.setStatus(Enemy.isAlive());
	}

	public static boolean isAlive() {
		return ALIVE;
	}

	public static boolean isDead() {
		return DEAD;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
