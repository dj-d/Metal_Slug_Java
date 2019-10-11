package metal_slug.logica.personaggi.drawablePersonaggi;

import java.awt.Image;
import java.awt.Point;

import metal_slug.logica.personaggi.Player;
import metal_slug.utils.Resource;

import javax.swing.*;

public class DrawablePlayer extends Player {
	private static final int NUM_IMAGE_JUMP = 7;
	private static final int NUM_IMAGE_FIRE = 9;
	private static final int NUM_IMAGE_GRANADE = 7;

	//DIREZIONE
	public static final int RIGHT = 3;
	public static final int LEFT = -3;
	public static final int UP = -30;
	public static final int DOWN = 30;

	//MODIFICA DEL PUNTO DI ANCORAGGIO DELL'IMMAGINE
	private static final int CROUCHING_HEIGHT_INCREASE = 42;

	private String imageDefaultPath = "/metal_slug/media/images/Giocatore/Direzione/0.png";
	private Image imageDefault;
	private String imageCrouchingPath = "/metal_slug/media/images/Giocatore/Accovacciato.png";
	private Image imageCrouching;
	private String walkRightPath = "/home/djd/Documents/Git/Personale/metal-slug/src/metal_slug/media/images/MegalSlugGIF/walkRight.gif";
	private Image walkRight;
	private String walkLeftPath = "/home/djd/Documents/Git/Personale/metal-slug/src/metal_slug/media/images/MegalSlugGIF/walkLeft.gif";
	private Image walkLeft;
	private String turnRightPath = "/home/djd/Documents/Git/Personale/metal-slug/src/metal_slug/media/images/MegalSlugGIF/turnRight.gif";
	private Image turnRight;
	private String turnLeftPath = "/home/djd/Documents/Git/Personale/metal-slug/src/metal_slug/media/images/MegalSlugGIF/turnLeft.gif";
	private Image turnLeft;
	private String jumpPath = "/home/djd/Documents/Git/Personale/metal-slug/src/metal_slug/media/images/MegalSlugGIF/jump.gif";
	private Image jump;
	private String firePath = "/home/djd/Documents/Git/Personale/metal-slug/src/metal_slug/media/images/MegalSlugGIF/fire.gif";
	private Image fire;
	private String granadePath = "/home/djd/Documents/Git/Personale/metal-slug/src/metal_slug/media/images/MegalSlugGIF/launchGranade.gif";
	private Image granade;
	private String deathPath = "/home/djd/Documents/Git/Personale/metal-slug/src/metal_slug/media/images/MegalSlugGIF/PersonageDeath.gif";
	private Image death;

	public DrawablePlayer(Point pPosition) {
		super(pPosition);

		this.imageDefault = Resource.getImage(this.imageDefaultPath);
		this.imageCrouching = Resource.getImage(this.imageCrouchingPath);
		this.walkRight = new ImageIcon(this.walkRightPath).getImage();
		this.walkLeft = new ImageIcon(this.walkLeftPath).getImage();
		this.turnRight = new ImageIcon(this.turnRightPath).getImage();
		this.turnLeft = new ImageIcon(this.turnLeftPath).getImage();
		this.jump = new ImageIcon(this.jumpPath).getImage();
		this.fire = new ImageIcon(this.firePath).getImage();
		this.granade = new ImageIcon(this.granadePath).getImage();
		this.death = new ImageIcon(this.deathPath).getImage();
	}

	public int getCrouchingHeightIncrease() {
		return CROUCHING_HEIGHT_INCREASE;
	}

	public Image getImageDefault() {
		return imageDefault;
	}

	public Image getImageCrouching() {
		return imageCrouching;
	}

	public Image getWalkRight() {
		return walkRight;
	}

	public Image getWalkLeft() {
		return walkLeft;
	}

	public Image getTurnRight() {
		return turnRight;
	}

	public Image getTurnLeft() {
		return turnLeft;
	}

	public Image getJump() {
		return jump;
	}

	public Image getFire() {
		return fire;
	}

	public Image getGranade() {
		return granade;
	}

	public Image getDeath() {
		return death;
	}

	public int getNumImageMovementJump() {
		return NUM_IMAGE_JUMP;
	}

	public int getNumImageMovementFireGun() {
		return NUM_IMAGE_FIRE / 2;
	}

	public int getNumImageMovementLaunchGranade() {
		return NUM_IMAGE_GRANADE/2;
	}

	public void jumpMovement(int index) {
		if (index < (this.getNumImageMovementJump() / 2)) {
			this.position.y += DrawablePlayer.UP;
		}

		if (index > (this.getNumImageMovementJump() / 2)) {
			this.position.y += DrawablePlayer.DOWN;
		}
	}
}
