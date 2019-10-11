package logic.characters.drawable;

import logic.characters.logic.Enemy;
import utils.Resource;

import java.awt.*;

public class Scientist extends Enemy {
	public static final int HEALTH = 10;
	public static final int LEFT = -15;

	private Image alive;
	private Image dead;

	public Scientist(Point pPosition) {
		super(Scientist.HEALTH, pPosition);

		loadImage();
	}

	private void loadImage() {
		this.alive = Resource.getImage("/media/images/Enemy/Scientist/Alive.png");
		this.dead = Resource.getImage("/media/images/Enemy/Scientist/Dead.png");
	}

	public Image getAlive() {
		return alive;
	}

	public Image getDead() {
		return dead;
	}

	@Override
	public void hitIt() {
		this.setStatus(Scientist.isDead());
	}
}
