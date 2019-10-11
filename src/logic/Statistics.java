package logic;

import utils.Resource;

import java.awt.*;

public class Statistics {
	public static final int NUM_IMG_COUNTDOWN = 61;
	private Image[] countdown;
	private static final int NUM_IMG_GRENADE = 11;
	private Image[] grenades;
	private Image box;
	private static final int NUM_MAX_LIVES = 3;
	private int numLives;
	private Image[] lives;

	private boolean isStartCountdown;

	public Statistics() {
		this.box = Resource.getImage("/media/images/Panels/GamePanel/Munitions_&_health.png");

		this.countdown = new Image[Statistics.NUM_IMG_COUNTDOWN];
		this.loadCountdownImage();

		this.grenades = new Image[Statistics.NUM_IMG_GRENADE];
		this.loadGrenadeImage();

		this.lives = new Image[Statistics.NUM_MAX_LIVES];
		this.loadLivesImage();
		this.numLives = Statistics.NUM_MAX_LIVES;

		this.isStartCountdown = false;
	}

	// TODO: Da unire i load

	private void loadCountdownImage() {
		for (int i = 0; i < Statistics.NUM_IMG_COUNTDOWN; i++) {
			int imgNum = Statistics.NUM_IMG_COUNTDOWN - i - 1;
			this.countdown[i] = Resource.getImage("/media/images/Panels/GamePanel/Timer/" + imgNum + ".png");
		}
	}

	private void loadGrenadeImage() {
		for (int i = 0; i < Statistics.NUM_IMG_GRENADE; i++) {
			int imgNum = Statistics.NUM_IMG_GRENADE - i - 1;
			this.grenades[i] = Resource.getImage("/media/images/Panels/GamePanel/Grenate/" + imgNum + ".png");
		}
	}

	private void loadLivesImage() {
		for (int i = 0; i < Statistics.NUM_MAX_LIVES; i++) {
			int imgNum = Statistics.NUM_MAX_LIVES - i - 1;
			this.lives[i] = Resource.getImage("/media/images/Panels/GamePanel/Health.png");
		}
	}

	public Image[] getCountdown() {
		return countdown;
	}

	public Image[] getGrenades() {
		return grenades;
	}

	public Image getBox() {
		return box;
	}

	public Image[] getLives() {
		return lives;
	}

	public boolean isStartCountdown() {
		return isStartCountdown;
	}

	public void setStartCountdown(boolean countdownStatus) {
		isStartCountdown = countdownStatus;
	}

	public int getNumLives() {
		return numLives;
	}

	public void decreaseLives() {
		this.numLives--;
	}
}
