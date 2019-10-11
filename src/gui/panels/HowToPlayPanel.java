package gui.panels;

import gui.GameFrame;
import logic.characters.drawable.Player;
import logic.weapons.drawable.Grenade;
import logic.weapons.drawable.Shot;
import utils.Resource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HowToPlayPanel extends JPanel {
	private static final Dimension DIMENSION = new Dimension(GameFrame.WIDTH_WINDOW, GameFrame.HEIGHT_WINDOW);

	// TUTORIAL
	private static final int FIRST_INDICATION = 1;
	private static final int DISPLACEMENT_INDICATIONS = 2;
	private static final int JUMP = 3;
	private static final int CROUCHING = 4;
	private static final int FIRE = 5;
	private static final int GRENADE_LAUNCH = 6;
	private static final int CHANGE_PANEL = 7;

	private static final int PLAYER_X_POSITION = 600;
	private static final int PLAYER_Y_POSITION = 250;

	private static final int PLAYER_RIGHT_LIMIT = 755;
	private static final int PLAYER_LEFT_LIMIT = 460;

	private static final int RIGHT_SHOT_LIMIT = 1030;

	private static final int SLEEP_JUMP_THREAD = 150;
	private static final int SLEEP_FIRE_THREAD = 150;
	private static final int SLEEP_LAUNCH_GRENADE_THREAD = 200;
	private static final int SLEEP_SHOT_THREAD = 100;
	private static final int SLEEP_GRENADE_THREAD = 100;
	private static final int SLEEP_EXPLOSION_THREAD = 125;

	private String backgroundPath = "/media/images/Panels/HowToPlayPanel/1.png";
	private Image background;

	private Player player;
	private Shot shot;
	private Grenade grenade;

	private int progressTutorial;

	private boolean directionTutorial;
	private boolean jumpTutorial;
	private boolean crouchingTutorial;
	private boolean fireTutorial;
	private boolean grenadeTutorial;

	private boolean isRightWalk;
	private boolean isLeftWalk;
	private boolean isCrouching;
	private boolean isJump;
	private boolean isFire;
	private boolean isLaunchGrenade;
	private boolean isGrenade;
	private boolean isExplosion;
	private boolean isShot;

	private Resource resource;

	public HowToPlayPanel() {
		this.setSize(DIMENSION);
		this.setLayout(null);

		this.background = Resource.getImage(backgroundPath);
		this.player = new Player(new Point(HowToPlayPanel.PLAYER_X_POSITION, HowToPlayPanel.PLAYER_Y_POSITION));
		this.progressTutorial = 1;
		initTutorial();
		initMovements();
		this.resource = new Resource();

		this.addKeyListener(new KeyboardListener());
	}

	private void initTutorial() {
		this.directionTutorial = false;
		this.jumpTutorial = false;
		this.crouchingTutorial = false;
		this.fireTutorial = false;
		this.grenadeTutorial = false;
	}

	private void initMovements() {
		this.isRightWalk = false;
		this.isLeftWalk = false;
		this.isCrouching = false;
		this.isJump = false;
		this.isFire = false;
		this.isLaunchGrenade = false;
		this.isGrenade = false;
		this.isExplosion = false;
		this.isShot = false;
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(this.background, 0, 0, this);

		if (!this.isRightWalk && !this.isLeftWalk && !this.isCrouching && !this.isJump && !this.isFire && !this.isLaunchGrenade || (isRightWalk && isLeftWalk)) {
			this.resource.drawImage(g, this.player.getStanding(), this.player.getPosition(), 0);
		}

		if (this.isRightWalk && !this.isLeftWalk && !this.isCrouching && !this.isJump && !this.isFire && !this.isLaunchGrenade) {
			g.drawImage(this.player.getWalkRight(), this.player.getPosition().x, this.player.getPosition().y, this);
		}

		if (this.isLeftWalk && !this.isRightWalk && !this.isCrouching && !this.isJump && !this.isFire && !this.isLaunchGrenade) {
			g.drawImage(this.player.getWalkLeft(), this.player.getPosition().x, this.player.getPosition().y, this);
		}

		if (this.isJump && !this.isRightWalk && !this.isLeftWalk && !this.isCrouching && !this.isFire && !this.isLaunchGrenade) {
			g.drawImage(this.player.getJump(), this.player.getPosition().x, this.player.getPosition().y, this);
		}

		if (this.isCrouching && !this.isRightWalk && !this.isLeftWalk && !this.isJump && !this.isFire && !this.isLaunchGrenade) {
			this.resource.drawImage(g, this.player.getCrouched(), this.player.getPosition(), Player.CROUCHING_HEIGHT_INCREASE);
		}

		if (this.isFire && !this.isRightWalk && !this.isLeftWalk && !this.isCrouching && !this.isJump && !this.isLaunchGrenade) {
			g.drawImage(this.player.getFire(), this.player.getPosition().x, this.player.getPosition().y, this);
		}

		if (this.isShot) {
			this.resource.drawImage(g, this.shot.getImg(), this.shot.getPosition(), 0);
		}

		if (this.isLaunchGrenade && !this.isRightWalk && !this.isLeftWalk && !this.isCrouching && !this.isJump && !this.isFire) {
			g.drawImage(this.player.getGrenade(), this.player.getPosition().x, this.player.getPosition().y, this);
		}

		if (this.isGrenade) {
			g.drawImage(this.grenade.getGrenade(), this.grenade.getPosition().x, this.grenade.getPosition().y, this);
		}

		if (this.isExplosion) {
			g.drawImage(this.grenade.getExplosion(), this.grenade.getPosition().x, this.grenade.getPosition().y, this);
		}
	}

	private class KeyboardListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				GameFrame.showPanel(GameFrame.GAME_PANEL);
			}

			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				progressTutorial++;
				changeTutorial(progressTutorial);
				repaint();
			}

			if (directionTutorial) {
				if (player.getPosition().x <= HowToPlayPanel.PLAYER_RIGHT_LIMIT) {
					if ((e.getKeyCode() == KeyEvent.VK_D) && !isLeftWalk && !isCrouching && !isJump && !isFire && !isLaunchGrenade) {
						isRightWalk = true;
						player.move(Player.RIGHT);
						repaint();
					}
				}

				if (player.getPosition().x >= HowToPlayPanel.PLAYER_LEFT_LIMIT) {
					if ((e.getKeyCode() == KeyEvent.VK_A) && !isRightWalk && !isCrouching && !isJump && !isFire && !isLaunchGrenade) {
						isLeftWalk = true;
						player.move(Player.LEFT);
						repaint();
					}
				}
			}

			if (jumpTutorial && !isJump) {
				if ((e.getKeyCode() == KeyEvent.VK_SPACE) && !isRightWalk && !isLeftWalk && !isCrouching && !isFire && !isLaunchGrenade) {
					isJump = true;
					startJump();
				}
			}

			if (crouchingTutorial) {
				if ((e.getKeyCode() == KeyEvent.VK_S) && !isRightWalk && !isLeftWalk && !isJump && !isFire && !isLaunchGrenade) {
					isCrouching = true;
					repaint();
				}
			}

			if (fireTutorial && !isFire) {
				if ((e.getKeyCode() == KeyEvent.VK_COMMA) && !isRightWalk && !isLeftWalk && !isCrouching && !isJump && !isLaunchGrenade) {
					isFire = true;
					isShot = true;
					startFire();
				}
			}

			if (grenadeTutorial) {
				if ((e.getKeyCode() == KeyEvent.VK_PERIOD) && !isRightWalk && !isLeftWalk && !isCrouching && !isJump && !isFire) {
					isLaunchGrenade = true;
					isGrenade = true;
					startLaunchGrenade();
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_D) {
				isRightWalk = false;
				repaint();
			}

			if (e.getKeyCode() == KeyEvent.VK_A) {
				isLeftWalk = false;
				repaint();
			}

			//S - ACCOVACCIAMENTO
			if (e.getKeyCode() == KeyEvent.VK_S) {
				isCrouching = false;
				repaint();
			}
		}
	}

	private void changeTutorial(int pProgressTutorial) {
		switch (pProgressTutorial) {
			case FIRST_INDICATION:
				this.background = Resource.getImage("/media/images/Panels/HowToPlayPanel/1.png");
				break;

			case DISPLACEMENT_INDICATIONS:
				this.background = Resource.getImage("/media/images/Panels/HowToPlayPanel/2.png");
				this.directionTutorial = true;
				break;

			case JUMP:
				this.background = Resource.getImage("/media/images/Panels/HowToPlayPanel/2_spazio.png");
				this.jumpTutorial = true;
				break;

			case CROUCHING:
				this.background = Resource.getImage("/media/images/Panels/HowToPlayPanel/2_s.png");
				this.crouchingTutorial = true;
				break;

			case FIRE:
				this.background = Resource.getImage("/media/images/Panels/HowToPlayPanel/2_virgola.png");
				this.fireTutorial = true;
				break;

			case GRENADE_LAUNCH:
				this.background = Resource.getImage("/media/images/Panels/HowToPlayPanel/2_punto.png");
				this.grenadeTutorial = true;
				break;

			case CHANGE_PANEL:
				GameFrame.showPanel(GameFrame.GAME_PANEL);
				break;
		}
	}

	// TODO: Migliorare
	private class ThreadJump implements Runnable {
		@Override
		public void run() {
			for (int i = 0; i < Player.NUM_IMAGE_JUMP; i++) {
				player.jumpMovement(i);

				repaint();

				try {
					Thread.sleep(HowToPlayPanel.SLEEP_JUMP_THREAD);
				} catch (InterruptedException ex) {
					Logger.getLogger(HowToPlayPanel.class.getName()).log(Level.SEVERE, null, ex);
				}
			}

			isJump = false;
		}
	}

	private void startJump() {
		Thread threadJump = new Thread(new ThreadJump());
		threadJump.start();
	}

	// TODO: Migliorare
	private class ThreadFire implements Runnable {
		@Override
		public void run() {
			for (int i = 0; i < Player.NUM_IMAGE_FIRE; i++) {
				repaint();

				try {
					Thread.sleep(HowToPlayPanel.SLEEP_FIRE_THREAD);
				} catch (InterruptedException ex) {
					Logger.getLogger(HowToPlayPanel.class.getName()).log(Level.SEVERE, null, ex);
				}
			}

			isFire = false;
		}
	}

	private void startFire() {
		Thread thread = new Thread(new ThreadFire());
		thread.start();

		this.startShot();
	}

	// TODO: Migliorare
	private void creationShot() {
		this.shot = new Shot(new Point(this.player.getPosition().x + 20, this.player.getPosition().y + 20));
	}

	private class ThreadShot implements Runnable {
		@Override
		public void run() {
			while (shot.getPosition().x <= HowToPlayPanel.RIGHT_SHOT_LIMIT) {
				shot.move(Shot.RIGHT);
				repaint();

				try {
					Thread.sleep(HowToPlayPanel.SLEEP_SHOT_THREAD);
				} catch (InterruptedException ex) {
					Logger.getLogger(HowToPlayPanel.class.getName()).log(Level.SEVERE, null, ex);
				}
			}

			shot.setImg(null);
			repaint();

			isShot = false;
		}
	}

	private void startShot() {
		this.creationShot();
		Thread threadShot = new Thread(new ThreadShot());
		threadShot.start();
	}

	// TODO: Migliorare
	private class ThreadLaunchGrenade implements Runnable {
		@Override
		public void run() {
			for (int i = 0; i < Player.NUM_IMAGE_GRENADE; i++) {
				repaint();

				try {
					Thread.sleep(HowToPlayPanel.SLEEP_LAUNCH_GRENADE_THREAD);
				} catch (InterruptedException ex) {
					Logger.getLogger(HowToPlayPanel.class.getName()).log(Level.SEVERE, null, ex);
				}
			}

			isLaunchGrenade = false;
		}
	}

	private void startLaunchGrenade() {
		Thread threadLaunchGrenade = new Thread(new ThreadLaunchGrenade());
		threadLaunchGrenade.start();
		this.startGrenadeAndExplosion();
	}

	// TODO: Migliorare
	private void creationGrenade() {
		this.grenade = new Grenade(new Point(this.player.getPosition().x, this.player.getPosition().y));
	}

	private class ThreadGrenadeAndExplosion implements Runnable {
		@Override
		public void run() {
			for (int i = 0; i < Grenade.NUM_IMAGE_GRENADE; i++) {
				grenade.move(i);
				repaint();

				try {
					Thread.sleep(HowToPlayPanel.SLEEP_GRENADE_THREAD);
				} catch (InterruptedException ex) {
					Logger.getLogger(HowToPlayPanel.class.getName()).log(Level.SEVERE, null, ex);
				}
			}

			isGrenade = false;
			grenade.setPosition(Grenade.EXPLOSION_HEIGHT_INCREASE);
			isExplosion = true;

			for (int j = 0; j < Grenade.NUM_IMAGE_EXPLOSION; j++) {
				repaint();

				try {
					Thread.sleep(HowToPlayPanel.SLEEP_EXPLOSION_THREAD);
				} catch (InterruptedException ex) {
					Logger.getLogger(HowToPlayPanel.class.getName()).log(Level.SEVERE, null, ex);
				}

				if (j == Grenade.NUM_IMAGE_EXPLOSION - 1) {
					break;
				}
			}

			isExplosion = false;
		}
	}

	private void startGrenadeAndExplosion() {
		this.creationGrenade();
		Thread threadGrenadeAndExplosion = new Thread(new ThreadGrenadeAndExplosion());
		threadGrenadeAndExplosion.start();
	}
}
