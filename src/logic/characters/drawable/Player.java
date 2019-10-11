package logic.characters.drawable;

import utils.Resource;

import javax.swing.*;
import java.awt.*;

public class Player extends logic.characters.logic.Player {
	public static final int RIGHT = 3;
	public static final int LEFT = -3;
	public static final int UP = -30;
	public static final int DOWN = 30;

	public static final int NUM_IMAGE_JUMP = 7;
	public static final int NUM_IMAGE_FIRE = 5;
	public static final int NUM_IMAGE_GRENADE = 4;

	public static final int CROUCHING_HEIGHT_INCREASE = 42;

	private String standingPath = "/media/images/Player/Standing.png";
	private Image standing;

	private String walkRightPath = "/home/djd/Documents/metalSlug/src/media/images/Player/WalkRight.gif";
	private Image walkRight;

	private String walkLeftPath = "/home/djd/Documents/metalSlug/src/media/images/Player/WalkLeft.gif";
	private Image walkLeft;

	private String turnRightPath = "/home/djd/Documents/metalSlug/src/media/images/Player/TurnRight.gif";
	private Image turnRight;

	private String turnLeftPath = "/home/djd/Documents/metalSlug/src/media/images/Player/TurnLeft.gif";
	private Image turnLeft;

	private String jumpPath = "/home/djd/Documents/metalSlug/src/media/images/Player/Jump.gif";
	private Image jump;

	private String firePath = "/home/djd/Documents/metalSlug/src/media/images/Player/Fire.gif";
	private Image fire;

	private String grenadePath = "/home/djd/Documents/metalSlug/src/media/images/Player/LaunchGranade.gif";
	private Image grenade;

	private String deathPath = "/home/djd/Documents/metalSlug/src/media/images/Player/PlayerDeath.gif";
	private Image death;

	private String crouchedPath = "/media/images/Player/Crouched.png";
	private Image crouched;

	public Player(Point pPosition) {
		super(pPosition);

		loadImages();
	}

	private void loadImages() {
		this.standing = Resource.getImage(this.standingPath);
		this.crouched = Resource.getImage(this.crouchedPath);
		this.walkRight = new ImageIcon(this.walkRightPath).getImage();
		this.walkLeft = new ImageIcon(this.walkLeftPath).getImage();
		this.turnRight = new ImageIcon(this.turnRightPath).getImage();
		this.turnLeft = new ImageIcon(this.turnLeftPath).getImage();
		this.jump = new ImageIcon(this.jumpPath).getImage();
		this.fire = new ImageIcon(this.firePath).getImage();
		this.grenade = new ImageIcon(this.grenadePath).getImage();
		this.death = new ImageIcon(this.deathPath).getImage();
	}

	public Image getStanding() {
		return standing;
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

	public Image getGrenade() {
		return grenade;
	}

	public Image getDeath() {
		return death;
	}

	public Image getCrouched() {
		return crouched;
	}

	public void jumpMovement(int index) {
		if (index < (Player.NUM_IMAGE_JUMP / 2)) {
			this.position.y += Player.UP;
		}

		if (index > (Player.NUM_IMAGE_JUMP / 2)) {
			this.position.y += Player.DOWN;
		}
	}
}
