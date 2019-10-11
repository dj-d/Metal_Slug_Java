package gui.panels;

import gui.GameFrame;
import logic.Map;
import logic.Statistics;
import logic.characters.drawable.Player;
import logic.characters.drawable.Scientist;
import logic.weapons.drawable.Grenade;
import logic.weapons.drawable.Shot;
import utils.Resource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GamePanel extends JPanel {
	private static final Dimension DIMENSION = new Dimension(GameFrame.WIDTH_WINDOW, GameFrame.HEIGHT_WINDOW);

	private static final int PLAYER_X_POSITION = 150;
	private static final int PLAYER_Y_POSITION = 500;

	private static final int PLAYER_RIGHT_LIMIT = 616;
	private static final int PLAYER_LEFT_LIMIT = 149;

	private static final int RIGHT_SHOT_LIMIT = 1200;

	private static final int MAP_LIMIT = -8220;

	private static final int SLEEP_JUMP_THREAD = 150;
	private static final int SLEEP_FIRE_THREAD = 150;
	private static final int SLEEP_LAUNCH_GRENADE_THREAD = 200;
	private static final int SLEEP_SHOT_THREAD = 100;
	private static final int SLEEP_GRENADE_THREAD = 100;
	private static final int SLEEP_EXPLOSION_THREAD = 125;

	private Map map;

	private Player player;
	private Shot shot;
	private Grenade grenade;

	private Scientist[] scientists;
	private Image[] changeScientists;

	private Statistics statistics;
	private boolean isGameStart;
	private Image startButton;

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

	public GamePanel() {
		this.setSize(GamePanel.DIMENSION);
		this.setLayout(null);

		this.map = new Map(new Point(0, 0));
		this.player = new Player(new Point(GamePanel.PLAYER_X_POSITION, GamePanel.PLAYER_Y_POSITION));
		initMovements();

		this.scientists = new Scientist[2];

		Point point1 = new Point(618, 500);
		this.scientists[0] = new Scientist(point1);
		Point point2 = new Point(900, 500);
		this.scientists[1] = new Scientist(point2);

		this.changeScientists = new Image[2];
		this.changeScientists[0] = this.scientists[0].getAlive();
		this.changeScientists[1] = this.scientists[1].getAlive();

		this.statistics = new Statistics();
		this.statistics.setStartCountdown(true);
		this.isGameStart = false;
		this.startButton = Resource.getImage("/media/images/Panels/GamePanel/Push_start.png");

		this.resource = new Resource();

		this.addKeyListener(new KeyboardListener());
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
		this.resource.drawImage(g, this.map.getMap(), new Point(0, 0), 0);

		g.setColor(Color.BLUE);
		g.fillRect(70, 65, 185, 23);

		if (!this.isGameStart) {
			this.resource.drawImage(g, this.startButton, new Point(900, 40), 0);
		}

		this.resource.drawImage(g, this.statistics.getBox(), new Point(69, 28), 0);
//		this.resource.drawImageArray(g, this.statistics.getCountdown(), Statistics.NUM_IMG_COUNTDOWN, new Point(590, 40), 0);

		this.resource.drawImage(g, this.changeScientists[0], this.scientists[0].getPosition(), 0);
		this.resource.drawImage(g, this.changeScientists[1], this.scientists[1].getPosition(), 0);


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

			if (!isGameStart && (e.getKeyCode() == KeyEvent.VK_ENTER)) {
				isGameStart = true;
			}

			if (isGameStart && (e.getKeyCode() == KeyEvent.VK_D) && !isLeftWalk && !isCrouching && !isJump && !isFire && !isLaunchGrenade) {
				isRightWalk = true;

				if (player.getPosition().x <= PLAYER_RIGHT_LIMIT) {
					player.move(Player.RIGHT);
				} else {
					if (map.getPosition().x >= GamePanel.MAP_LIMIT) {
						map.move(Map.RIGHT);
					}
				}
				repaint();
			}

			if (isGameStart && (e.getKeyCode() == KeyEvent.VK_A) && !isRightWalk && !isCrouching && !isJump && !isFire && !isLaunchGrenade) {
				isLeftWalk = true;

				if (player.getPosition().x >= GamePanel.PLAYER_LEFT_LIMIT) {
					player.move(Player.LEFT);
				}

				repaint();
			}

			if ((e.getKeyCode() == KeyEvent.VK_SPACE) && !isRightWalk && !isLeftWalk && !isCrouching && !isFire && !isLaunchGrenade) {
				isJump = true;
				startJump();
			}


			if ((e.getKeyCode() == KeyEvent.VK_S) && !isRightWalk && !isLeftWalk && !isJump && !isFire && !isLaunchGrenade) {
				isCrouching = true;
				repaint();
			}


			if ((e.getKeyCode() == KeyEvent.VK_COMMA) && !isRightWalk && !isLeftWalk && !isCrouching && !isJump && !isLaunchGrenade) {
				isFire = true;
				isShot = true;
				startFire();
			}


			if ((e.getKeyCode() == KeyEvent.VK_PERIOD) && !isRightWalk && !isLeftWalk && !isCrouching && !isJump && !isFire) {
				isLaunchGrenade = true;
				isGrenade = true;
				startLaunchGrenade();
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

	// TODO: Migliorare
	private class ThreadJump implements Runnable {
		@Override
		public void run() {
			for (int i = 0; i < Player.NUM_IMAGE_JUMP; i++) {
				player.jumpMovement(i);

				repaint();

				try {
					Thread.sleep(GamePanel.SLEEP_JUMP_THREAD);
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
					Thread.sleep(GamePanel.SLEEP_FIRE_THREAD);
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
			while (shot.getPosition().x <= GamePanel.RIGHT_SHOT_LIMIT) {
				shot.move(Shot.RIGHT);
				repaint();

				for (int i = 0; i < 2; i++) {
					if ((shot.getPosition().x >= scientists[i].getPosition().x) && (shot.getPosition().x <= (scientists[i].getPosition().x + scientists[i].getAlive().getWidth(null))) && scientists[i].isStatus() && isShot) {
						changeScientists[i] = scientists[i].getDead();
						scientists[i].hitIt();
						isShot = false;
					}
				}

				try {
					Thread.sleep(GamePanel.SLEEP_SHOT_THREAD);
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
					Thread.sleep(GamePanel.SLEEP_LAUNCH_GRENADE_THREAD);
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
					Thread.sleep(GamePanel.SLEEP_GRENADE_THREAD);
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
					Thread.sleep(GamePanel.SLEEP_EXPLOSION_THREAD);
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