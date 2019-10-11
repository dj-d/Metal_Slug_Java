package metal_slug.GUI.panel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

import metal_slug.GUI.GameFrame;
import metal_slug.logica.armi.drawableWeapon.DrawableShot;
import metal_slug.logica.armi.drawableWeapon.DrawableGranade;
import metal_slug.logica.personaggi.drawablePersonaggi.DrawablePlayer;
import metal_slug.utils.Resource;

public class HowToPlayPanel extends JPanel {
	//DIMENSIONE PANNELLO
	public static final Dimension DIMENSION = new Dimension(GameFrame.WIDTH_WINDOW, GameFrame.HEIGHT_WINDOW);

	//MENU TUTORIAL
	private static final int FIRST_INDICATION = 1;              //INDICAZIONI INIZIALI
	private static final int DISPLACEMENT_INDICATIONS = 2;      //INDICAZIONI PER LO SPOSTAMENTO
	private static final int JUMP = 3;                          //INDICAZIONI PER IL SALTO
	private static final int CROUCHING = 4;                     //INDICAZIONI PER L'ACCOVACCIAMENTO
	private static final int FIRE = 5;                          //INDICAZIONI PER SPARARE
	private static final int GRENADE_LAUNCH = 6;                //INDICAZIONI PER LANCIARE LE GRANATE
	private static final int CHANGE_PANEL = 7;                  //CAMBIO PANNELLO

	//SLEEP THREAD
	private static final int SLEEP_WALK_THREAD = 100;            //CAMMINATA
	private static final int SLEEP_JUMP_THREAD = 150;            //SALTO
	private static final int SLEEP_FIRE_THREAD = 150;            //MOVIMNENTO FUOCO
	private static final int SLEEP_LAUNCH_GRENADE_THREAD = 200;  //MOVIMENTO LANCIO GRANATA
	private static final int SLEEP_MOVE_SHOT_THREAD = 100;       //MOVIEMNTO IMMAGINE COLPO PISTOLA
	private static final int SLEEP_MOVE_GRENADE_THREAD = 100;    //MOVIEMNTO IMMAGINE GRANATA
	private static final int SLEEP_SEQ_EXPLOSION_THREAD = 125;   //ESPLOSIONE

	//POSIZIONE GIOCATORE
	private static final int PLAYER_X_AXIS = 600;                //ASSE X
	private static final int PLAYER_Y_AXIS = 250;                //ASSE Y

	//LIMITE DESTRO DEL COLPO
	private static final int RIGHT_SHOT_LIMIT = 1030;

	//LIMITI PER IL MOVIMENTO
	private static final int RIGHT_LIMIT = 755;                 //LIMITE DESTRO DEL PONTE
	private static final int LEFT_LIMIT = 460;                  //LIMITE SINISTRO DEL PONTE

	//CAMMINATA
	private boolean isRightWalk;                                //DIREZIONE DESTRA
	private boolean isLeftWalk;                                 //DIREZIONE SINISTRA

	//SALTO
	private Thread threadJump;
	private boolean isJump;                                     //SALTO

	//PISTOLA
	private Thread threadMovementFireGun;
	private boolean isMovementFireGun;                          //FUOCO PISTOLA
	private Thread threadMovementImageShot;
	private boolean isMovementImgShot;                          //COLPO PISTOLA

	//GRANATA
	private Thread threadMovementLaunchGranade;
	private boolean isMovementLaunchGrenade;                     //LANCIO GRANATA
	private Thread threadMovementImageGrenadeAndExplosion;
	private boolean isGrenadeOrExplosion;                       //ESPLOSIONE
	private Image[] arrayImages;                                //VARIABILE PER IL CAMBIO DI ARRAY D'IMMAIGNI
	private int changeHeightImgGreadeAndImgExplosion;          //VARIABILE PER LO SPOSTAMENTO DEL PUNTO DI ANCORAGGIO DELLE IMMAGINI DELL'ARRAY

	private boolean isMovementCrouching;                        //ACCOVACCIATO

	//VARIABILI PER IL CAMBIO DEI FRAME DELLE IMMAGINI
	private int numImageMovement;                               //MOVIMENTO PERSONAGGIO
	private int numImageSequence;                               //MOVIMENTO COLPI/GRANATE/ESPLOSIONI

	private Image background;                                   //IMMAGINE DI SFONDO

	//GIOCATORE
	private DrawablePlayer player;
	private int playerAxisX;                                    //POSIZIONE ASSE X GIOCATORE
	private int playerAxisY;                                    //POSIZIONE ASSE Y GIOCATORE

	private DrawableGranade imageGrenade;                       //GRANATA & ESPLOSIONE

	private DrawableShot imageShot;                             //COLPO PISTOLA

	private int progressTutorial;                               //VARIABILE PER L'AVANZAMETO DEI TUTORIAL

	//TUTORIAL
	private boolean tutorialDirection;                          //A e D
	private boolean tutorialJump;                               //SPAZIO
	private boolean tutorialCrouching;                          //S
	private boolean tutorialFire;                               //, - VIRGOLA
	private boolean tutorialGrenade;                            //. - PUNTO

	private Resource resource;

	public HowToPlayPanel() {
		this.setSize(DIMENSION);                                //IMPOSTIAMO LA DIMENSIONE DELLA FINESTRA

		this.setLayout(null);                                   //PER POTER MUOVERE A PICIMENTO I PANNELLI

		this.background = Resource.getImage("/metal_slug/media/images/Panels/HowToPlayPanel/1.png");    //SFONDO

		//GIOCATORE
		this.playerAxisX = HowToPlayPanel.PLAYER_X_AXIS;
		this.playerAxisY = HowToPlayPanel.PLAYER_Y_AXIS;
		this.player = new DrawablePlayer(new Point(this.playerAxisX, this.playerAxisY));

		this.progressTutorial = 1;                              //VARIABILE AVANZAMENTO TUTORIAL

		//IMPOSTO I TUTORIAL SU FALSE
		this.tutorialDirection = true;                         //DIREZIONE
		this.tutorialJump = true;                              //SALTO
		this.tutorialCrouching = true;                         //ACCOVACCIAMENTO
		this.tutorialFire = true;                              //SPARO
		this.tutorialGrenade = true;                           //LANCIO GRANATA

		//IMPOSTO LE ANIMAZIONI SU FALSE
		this.isRightWalk = false;                               //CAMMINATA DESTRA
		this.isLeftWalk = false;                                //CAMMINATA SINISTRA
		this.isMovementCrouching = false;                       //ACCOVACCIATO
		this.isJump = false;                                    //SALTO
		this.isMovementFireGun = false;                         //SPARO
		this.isMovementLaunchGrenade = false;                    //LANCIO GRANATA
		this.isGrenadeOrExplosion = false;                      //ESPLOSIONE
		this.isMovementImgShot = false;                         //COLPO PISTOLA

		this.resource = new Resource();

		this.addKeyListener(new KeyboardListener());            //KEYBOARDLISTENER
	}

	@Override
	protected void paintComponent(Graphics g) {
		//SFONDO
		g.drawImage(this.background, 0, 0, null);

		if (!this.isRightWalk && !this.isLeftWalk && !this.isMovementCrouching && !this.isJump && !this.isMovementFireGun && !this.isMovementLaunchGrenade || (isRightWalk && isLeftWalk)) {
			this.resource.drawImage(g, this.player.getImageDefault(), this.player.getPosition(), 0);
		}

		if (this.isRightWalk && !this.isLeftWalk && !this.isMovementCrouching && !this.isJump && !this.isMovementFireGun && !this.isMovementLaunchGrenade) {
			g.drawImage(this.player.getWalkRight(), this.player.getPosition().x, this.player.getPosition().y, this);
		}

		if (this.isLeftWalk && !this.isRightWalk && !this.isMovementCrouching && !this.isJump && !this.isMovementFireGun && !this.isMovementLaunchGrenade) {
			g.drawImage(this.player.getWalkLeft(), this.player.getPosition().x, this.player.getPosition().y, this);
		}

		if (this.isMovementCrouching && !this.isRightWalk && !this.isLeftWalk && !this.isJump && !this.isMovementFireGun && !this.isMovementLaunchGrenade) {
			this.resource.drawImage(g, this.player.getImageCrouching(), this.player.getPosition(), this.player.getCrouchingHeightIncrease());
		}

		if (this.isJump && !this.isRightWalk && !this.isLeftWalk && !this.isMovementCrouching && !this.isMovementFireGun && !this.isMovementLaunchGrenade) {
			g.drawImage(this.player.getJump(), this.player.getPosition().x, this.player.getPosition().y, this);
		}

		if (this.isMovementImgShot) {
			this.resource.drawImage(g, this.imageShot.imageColpo, this.imageShot.getPosition(), 0);
		}

		if (this.isMovementFireGun && !this.isRightWalk && !this.isLeftWalk && !this.isMovementCrouching && !this.isJump && !this.isMovementLaunchGrenade) {
			g.drawImage(this.player.getFire(), this.player.getPosition().x, this.player.getPosition().y, this);
		}

		if (this.isMovementLaunchGrenade && !this.isRightWalk && !this.isLeftWalk && !this.isMovementCrouching && !this.isJump && !this.isMovementFireGun) {
			g.drawImage(this.player.getGranade(), this.player.getPosition().x, this.player.getPosition().y, this);
		}

		//GRANATA e ESPLOSIONE
		if (this.isGrenadeOrExplosion) {

		}
	}

	private class KeyboardListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			//ESC - USCITA TUTORIAL
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				GameFrame.showPanel(GameFrame.GAME_PANEL);
			}

			//ENTER - AVANZAMENTO
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				progressTutorial++;
				changeTutorial(progressTutorial);
				repaint();
			}

			//A & D - DIREZIONE
			if (tutorialDirection) {
				if (player.getPosition().x <= HowToPlayPanel.RIGHT_LIMIT) {
					//D - DESTRA
					if ((e.getKeyCode() == KeyEvent.VK_D) && !isLeftWalk && !isMovementCrouching && !isJump && !isMovementFireGun && !isMovementLaunchGrenade) {
						isRightWalk = true;
						player.move(DrawablePlayer.RIGHT);
						repaint();
					}
				}

				if (player.getPosition().x >= HowToPlayPanel.LEFT_LIMIT) {
					//A - SINISTRA
					if ((e.getKeyCode() == KeyEvent.VK_A) && !isRightWalk && !isMovementCrouching && !isJump && !isMovementFireGun && !isMovementLaunchGrenade) {
						isLeftWalk = true;
						player.move(DrawablePlayer.LEFT);
						repaint();
					}
				}
			}

			//SPAZIO - SALTO
			if (tutorialJump && !isJump) {
				if ((e.getKeyCode() == KeyEvent.VK_SPACE) && !isRightWalk && !isLeftWalk && !isMovementCrouching && !isMovementFireGun && !isMovementLaunchGrenade) {
					isJump = true;
					startJump();
				}
			}

			//S - ACCOVACCIAMENTO
			if (tutorialCrouching) {
				if ((e.getKeyCode() == KeyEvent.VK_S) && !isRightWalk && !isLeftWalk && !isJump && !isMovementFireGun && !isMovementLaunchGrenade) {
					isMovementCrouching = true;
					repaint();
				}
			}

			//VIRGOLA - PISTOLA
			if (tutorialFire && !isMovementImgShot) {
				if ((e.getKeyCode() == KeyEvent.VK_COMMA) && !isRightWalk && !isLeftWalk && !isMovementCrouching && !isJump && !isMovementLaunchGrenade) {
					isMovementFireGun = true;
					isMovementImgShot = true;
					startMovementFireGun();
				}
			}

			//PUNTO - LANCIO GRANATA
			if (tutorialGrenade) {
				if ((e.getKeyCode() == KeyEvent.VK_PERIOD) && !isRightWalk && !isLeftWalk && !isMovementCrouching && !isJump && !isMovementFireGun) {
					isMovementLaunchGrenade = true;
					isGrenadeOrExplosion = true;
					startMovementLaunchGranade();
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			//D - DESTRA
			if (e.getKeyCode() == KeyEvent.VK_D) {
				isRightWalk = false;
				repaint();
			}

			//A - SINISTRA
			if (e.getKeyCode() == KeyEvent.VK_A) {
				isLeftWalk = false;
				repaint();
			}

			//S - ACCOVACCIAMENTO
			if (e.getKeyCode() == KeyEvent.VK_S) {
				isMovementCrouching = false;
				repaint();
			}
		}
	}

	/**
	 * CAMBIA LE INDICAZIONI DEI TUTORIAL
	 *
	 * @param pProgressTutorial L'indice di avanzamneto dei tutorial
	 */
	private void changeTutorial(int pProgressTutorial) {
		switch (pProgressTutorial) {
			//INDICAZIONI INIZIALI
			case FIRST_INDICATION:

				this.background = Resource.getImage("/metal_slug/media/images/Panels/HowToPlayPanel/1.png");

				break;

			//INDICAZIONI PER LO SPOSTAMENTO
			case DISPLACEMENT_INDICATIONS:

				this.background = Resource.getImage("/metal_slug/media/images/Panels/HowToPlayPanel/2.png");
				this.tutorialDirection = true;

				break;

			//INDICAZIONI PER IL SALTO
			case JUMP:

				this.background = Resource.getImage("/metal_slug/media/images/Panels/HowToPlayPanel/2_spazio.png");
				this.tutorialJump = true;

				break;

			//INDICAZIONI PER L'ACCOVACCIAMENTO
			case CROUCHING:

				this.background = Resource.getImage("/metal_slug/media/images/Panels/HowToPlayPanel/2_s.png");
				this.tutorialCrouching = true;

				break;

			//INDICAZIONI PER SPARARE
			case FIRE:

				this.background = Resource.getImage("/metal_slug/media/images/Panels/HowToPlayPanel/2_virgola.png");
				this.tutorialFire = true;

				break;

			//INDICAZIONI PER LANCIARE LE GRANATE
			case GRENADE_LAUNCH:

				this.background = Resource.getImage("/metal_slug/media/images/Panels/HowToPlayPanel/2_punto.png");
				this.tutorialGrenade = true;

				break;

			//CAMBIO PANNELLO
			case CHANGE_PANEL:

				GameFrame.showPanel(GameFrame.GAME_PANEL);

				break;
		}
	}

	/**
	 * DEFINISCO LA CLASSE THREADJUMP
	 */
	private class ThreadJump implements Runnable {
		@Override
		public void run() {
			for (numImageMovement = 0; numImageMovement < player.getNumImageMovementJump(); numImageMovement++) {
				player.jumpMovement(numImageMovement);     //MOVIMENTO SULL'ASSE Y

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

	/**
	 * CREO E AVVIO IL THREAD PER SALTARE
	 */
	public void startJump() {
		this.threadJump = new Thread(new ThreadJump());

		this.threadJump.start();
	}

	/**
	 * DEFINISCO LA CLASSE THREADMOVEMENTFIREGUN
	 */
	private class ThreadMovementFireGun implements Runnable {
		@Override
		public void run() {
			for (numImageMovement = 0; numImageMovement < player.getNumImageMovementFireGun(); numImageMovement++) {
				repaint();

				try {
					Thread.sleep(HowToPlayPanel.SLEEP_FIRE_THREAD);
				} catch (InterruptedException ex) {
					Logger.getLogger(HowToPlayPanel.class.getName()).log(Level.SEVERE, null, ex);
				}
			}

			isMovementFireGun = false;
		}
	}

	/**
	 * CREO E AVVIO IL THREAD PER FARE FUOCO
	 */
	private void startMovementFireGun() {
		this.threadMovementFireGun = new Thread(new ThreadMovementFireGun());

		this.threadMovementFireGun.start();

		this.startMovementImageShot();
	}

	/**
	 * DEFINISCO LA CLASSE THREADIMAGESHOT
	 */
	private class ThreadMovementImageShot implements Runnable {
		@Override
		public void run() {
			while (imageShot.getPosition().x <= HowToPlayPanel.RIGHT_SHOT_LIMIT) {
				imageShot.move(DrawableShot.RIGHT);
				repaint();

				try {
					Thread.sleep(HowToPlayPanel.SLEEP_MOVE_SHOT_THREAD);
				} catch (InterruptedException ex) {
					Logger.getLogger(HowToPlayPanel.class.getName()).log(Level.SEVERE, null, ex);
				}
			}

			imageShot.imageColpo = null;
			repaint();

			isMovementImgShot = false;
		}
	}

	/**
	 * CREO E AVVIO IL THREAD PER MUOVERE L'IMMAGINE DEL COLPO
	 */
	public void startMovementImageShot() {
		this.creationImageShot();

		this.threadMovementImageShot = new Thread(new ThreadMovementImageShot());

		this.threadMovementImageShot.start();
	}

	public void creationImageShot() {
		this.imageShot = new DrawableShot(new Point(this.player.getPosition().x + 20, this.player.getPosition().y + 20));
	}

	/**
	 * DEFINISCO LA CLASSE THREADMOVEMENTLAUNCHGRANADE
	 */
	private class ThreadMovementLaunchGranade implements Runnable {
		@Override
		public void run() {
			for (numImageMovement = 0; numImageMovement < player.getNumImageMovementLaunchGranade(); numImageMovement++) {
				repaint();

				try {
					Thread.sleep(HowToPlayPanel.SLEEP_LAUNCH_GRENADE_THREAD);
				} catch (InterruptedException ex) {
					Logger.getLogger(HowToPlayPanel.class.getName()).log(Level.SEVERE, null, ex);
				}
			}

			isMovementLaunchGrenade = false;
		}
	}

	/**
	 * CREO E AVVIO IL THREAD PER LANCIARE LA GRANATA
	 */
	public void startMovementLaunchGranade() {
		this.threadMovementLaunchGranade = new Thread(new ThreadMovementLaunchGranade());

		this.threadMovementLaunchGranade.start();

		this.startMovementImageGranadeAndExplosion();    //L'IMMAGINE DELLA GRANATA SI MUOVE
	}

	/**
	 * DEFINISCO LA CLASSE THREADIMAGEGRANADE
	 */
	private class ThreadMovementImageGranadeAndExplosion implements Runnable {
		@Override
		public void run() {
			arrayImages = imageGrenade.imageGranade;        //ARRAY IMMAGINI GRANATA
			changeHeightImgGreadeAndImgExplosion = 0;      //DI QUANTO SPOSTARE VERSO L'ALTO L'IMMAGINE

			//SCORRO L'ARRAY DELLE IMMAGINI DELLA GRANATA
			for (numImageSequence = 0; numImageSequence < imageGrenade.getNumberImageGranade(); numImageSequence++) {
				imageGrenade.move(numImageSequence);        //SPOSTAMENTO IMMAGINE GRANATA

				repaint();

				try {
					Thread.sleep(HowToPlayPanel.SLEEP_MOVE_GRENADE_THREAD);
				} catch (InterruptedException ex) {
					Logger.getLogger(HowToPlayPanel.class.getName()).log(Level.SEVERE, null, ex);
				}
			}

			//CAMBIO DEI PARAMETRI
			arrayImages = imageGrenade.imageExplosion;      //ARRAY IMMAGINI ESPLOSIONE
			changeHeightImgGreadeAndImgExplosion = imageGrenade.getExplosionHeightIncrease();    //SPOSTO IL PUNTO DI ANCORAGGIO PIU' IN ALTO

			//SCORRO L'ARRAY DELLE IMMAGINI DELL'ESPLOSIONE
			for (numImageSequence = 0; numImageSequence < imageGrenade.getNumberImageExplosion(); numImageSequence++) {
				repaint();

				try {
					Thread.sleep(HowToPlayPanel.SLEEP_SEQ_EXPLOSION_THREAD);
				} catch (InterruptedException ex) {
					Logger.getLogger(HowToPlayPanel.class.getName()).log(Level.SEVERE, null, ex);
				}

				if (numImageSequence == imageGrenade.getNumberImageExplosion() - 1) {
					break;
				}
			}

			isGrenadeOrExplosion = false;
		}
	}

	/**
	 * CREO E AVVIO IL THREAD PER MUOVERE L'IMMAGINE DELLA GRANATA
	 */
	public void startMovementImageGranadeAndExplosion() {
		this.creationImageGranade();

		this.threadMovementImageGrenadeAndExplosion = new Thread(new ThreadMovementImageGranadeAndExplosion());

		this.threadMovementImageGrenadeAndExplosion.start();
	}

	public void creationImageGranade() {
		this.imageGrenade = new DrawableGranade(new Point(this.player.getPosition().x, this.player.getPosition().y));
	}
}
