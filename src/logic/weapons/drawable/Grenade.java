package logic.weapons.drawable;

import javax.swing.*;
import java.awt.*;

public class Grenade extends logic.weapons.logic.Grenade {
	private static final int RIGHT = 80;
	private static final int UP = -30;
	private static final int DOWN = 60;

	public static final int NUM_IMAGE_GRENADE = 5;
	public static final int NUM_IMAGE_EXPLOSION = 15;
	public static final int EXPLOSION_HEIGHT_INCREASE = 249;

	private String grenadePath = "/home/djd/Documents/metalSlug/src/media/images/Weapons/Grenade/Grenade.gif";
	private Image grenade;

	private String explosionPath = "/home/djd/Documents/metalSlug/src/media/images/Weapons/Grenade/Explosion.gif";
	private Image explosion;

	public Grenade(Point pPosition) {
		super(pPosition);

		loadImages();
	}

	private void loadImages() {
		this.grenade = new ImageIcon(this.grenadePath).getImage();
		this.explosion = new ImageIcon(this.explosionPath).getImage();
	}

	public Image getGrenade() {
		return grenade;
	}

	public Image getExplosion() {
		return explosion;
	}

	public void setPosition(int increase) {
		this.position.y -= increase;
	}

	@Override
	public void move(int index) {
		if (index < (NUM_IMAGE_GRENADE / 2)) {
			this.position.y += Grenade.UP;
			this.position.x += Grenade.RIGHT;
		}

		if (index > (NUM_IMAGE_GRENADE / 2)) {
			this.position.y += Grenade.DOWN;
			this.position.x += Grenade.RIGHT;
		}
	}
}
