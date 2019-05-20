package metal_slug.GUI.panel;

import metal_slug.logica.Statistics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import metal_slug.GUI.GameFrame;
import metal_slug.logica.Map;
import metal_slug.logica.armi.drawableWeapon.DrawableGranade;
import metal_slug.logica.armi.drawableWeapon.DrawableShot;
import metal_slug.logica.personaggi.drawablePersonaggi.DrawablePlayer;
import metal_slug.logica.personaggi.drawablePersonaggi.Scienziato;
import metal_slug.utils.Resource;

public class GamePanel extends JPanel
  {
    public static final Dimension DIMENSION = new Dimension(GameFrame.WIDTH_WINDOW, GameFrame.HEIGHT_WINDOW);

    //SLEEP THREAD
    private static final int SLEEP_WALK_THREAD = 100;            //CAMMINATA
    private static final int SLEEP_JUMP_THREAD = 150;            //SALTO
    private static final int SLEEP_FIRE_THREAD = 150;            //MOVIMNENTO FUOCO
    private static final int SLEEP_LAUNCH_GRANADE_THREAD = 200;  //MOVIMENTO LANCIO GRANATA
    private static final int SLEEP_MOVE_SHOT_THREAD = 100;       //MOVIEMNTO IMMAGINE COLPO PISTOLA
    private static final int SLEEP_MOVE_GRANADE_THREAD = 100;    //MOVIEMNTO IMMAGINE GRANATA
    private static final int SLEEP_SEQ_EXPLOSION_THREAD = 125;   //ESPLOSIONE

    //POSIZIONE GIOCATORE
    private static final int PLAYER_X_AXIS = 150;                //ASSE X
    private static final int PLAYER_Y_AXIS = 500;                //ASSE Y

    //LIMITI PER IL MOVIMENTO
    private static final int PLAYER_RIGHT_LIMIT = 616;           //LIMITE DESTRO DEL PERSONAGGIO
    private static final int PLAYER_LEFT_LIMIT = 149;            //LIMITE SINISTRO DEL PERSONAGGIO

    private static final int MAP_LIMIT = -8220;

    private static final int RIGHT_SHOT_LIMIT = 1200;

    //CAMMINATA
    private Thread threadWalk;
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
    private boolean isMovementLauchGranade;                     //LANCIO GRANATA
    private Thread threadMovementImageGranadeAndExplosion;
    private boolean isGranadeOrExplosion;                       //ESPLOSIONE
    private Image[] arrayImages;                                //VARIABILE PER IL CAMBIO DI ARRAY D'IMMAIGNI
    private int changeHeightImgGranadeAndImgExplosion;          //VARIABILE PER LO SPOSTAMENTO DEL PUNTO DI ANCORAGGIO DELLE IMMAGINI DELL'ARRAY

    private boolean isMovementCrouching;                        //ACCOVACCIATO

    //VARIABILI PER IL CAMBIO DEI FRAME DELLE IMMAGINI
    private int numImageMovement;                               //MOVIMENTO PERSONAGGIO   
    private int numImageSequence;                               //SEQUENZA IMMAGINI GRANATA
    private int numImageContdown;

    private Map mappa;

    //GIOCATORE
    private DrawablePlayer player;
    private int playerAxisX;                                    //POSIZIONE ASSE X
    private int playerAxisY;                                    //POSIZIONE ASSE Y

    private DrawableGranade imageGranade;                       //GRANATA & ESPLOSIONE

    private DrawableShot imageShot;                             //COLPO PISTOLA

    //SCIENZIATO
    private Scienziato[] scienziato;
    private Image[] cambioImgScienziato;

    private Resource resource;

    private Image startButton;
    private boolean isGameStart;

    private Statistics info;
    private Thread threadCountdown;
    private static final int SLEEP_COUNTDOWN_THREAD = 1000;

    public GamePanel()
      {
        this.setSize(GamePanel.DIMENSION);

        this.setLayout(null);

        this.mappa = new Map(new Point(0, 0));

        //GIOCATORE
        this.playerAxisX = GamePanel.PLAYER_X_AXIS;
        this.playerAxisY = GamePanel.PLAYER_Y_AXIS;
        this.player = new DrawablePlayer(new Point(this.playerAxisX, this.playerAxisY));

        //IMPOSTO LE ANIMAZIONI SU FALSE
        this.isRightWalk = false;                               //CAMMINATA DESTRA
        this.isLeftWalk = false;                                //CAMMINATA SINISTRA
        this.isMovementCrouching = false;                       //ACCOVACCIATO
        this.isJump = false;                                    //SALTO
        this.isMovementFireGun = false;                         //SPARO
        this.isMovementLauchGranade = false;                    //LANCIO GRANATA
        this.isGranadeOrExplosion = false;                      //ESPLOSIONE
        this.isMovementImgShot = false;                         //COLPO PISTOLA

        this.scienziato = new Scienziato[2];

        Point point1 = new Point(618, 500);
        this.scienziato[0] = new Scienziato(point1);
        Point point2 = new Point(900, 500);
        this.scienziato[1] = new Scienziato(point2);

        this.cambioImgScienziato = new Image[2];
        this.cambioImgScienziato[0] = this.scienziato[0].imgVivo;
        this.cambioImgScienziato[1] = this.scienziato[1].imgVivo;

        this.resource = new Resource();
        this.info = new Statistics();
        this.info.setCountdownStart(true);

        this.startButton = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/push.png");
        this.isGameStart = false;

        this.addKeyListener(new KeyboardListener());
      }

    @Override
    protected void paintComponent(Graphics g)
      {
        //MAPPA
        this.mappa.drawMappa(g);

        //SCRITTA PER INDICARE DI PREMERE ENTER - MODIFICARE
        if (!this.isGameStart)
          {
            this.resource.drawImage(g, this.startButton, new Point(900, 40), 0);
          }

        //BARRA BLU - VITA GIOCATORE - MODIFICARE
        g.setColor(Color.BLUE);
        g.fillRect(70, 65, 185, 23);

        //RIQUATRO - VITA e MUNIZIONI - MODIFICARE
        this.resource.drawImage(g, this.info.riquadro, new Point(60, 28), 0);

        //NUMERI COUNTDOWN - MODIFICARE
        this.resource.drawImageArray(g, this.info.imgCountdown, this.numImageContdown, new Point(590, 40), 0);

        //SCIENZIATI - MODIFICARE
        this.resource.drawImage(g, this.cambioImgScienziato[0], this.scienziato[0].getPosition(), 0);
        this.resource.drawImage(g, this.cambioImgScienziato[1], this.scienziato[1].getPosition(), 0);

        //VITE GIOCATORE - MODIFICARE
        if (info.getNumVite() >= 1)
          {
            this.resource.drawImage(g, info.imgVite[0], new Point(70, 640), 0);

            if (info.getNumVite() >= 2)
              {
                this.resource.drawImage(g, info.imgVite[1], new Point(110, 640), 0);

                if (info.getNumVite() >= 3)
                  {
                    this.resource.drawImage(g, info.imgVite[2], new Point(150, 640), 0);
                  }
              }
          }

        //FERMO
        if (!this.isRightWalk && !this.isLeftWalk && !this.isMovementCrouching && !this.isJump && !this.isMovementFireGun && !this.isMovementLauchGranade || (isRightWalk && isLeftWalk))
          {
            this.resource.drawImage(g, this.player.imageDefault, this.player.getPosition(), 0);
          }

        //DESTRA
        if (this.isRightWalk && !this.isLeftWalk && !this.isMovementCrouching && !this.isJump && !this.isMovementFireGun && !this.isMovementLauchGranade)
          {
            this.resource.drawImageArray(g, this.player.imageMovementRightWalk, this.numImageMovement, this.player.getPosition(), 0);
          }

        //SINISTRA
        if (this.isLeftWalk && !this.isRightWalk && !this.isMovementCrouching && !this.isJump && !this.isMovementFireGun && !this.isMovementLauchGranade)
          {
            this.resource.drawImageArray(g, this.player.imageMovementLeftWalk, this.numImageMovement, this.player.getPosition(), 0);
          }

        //ACCOVACCIATO
        if (this.isMovementCrouching && !this.isRightWalk && !this.isLeftWalk && !this.isJump && !this.isMovementFireGun && !this.isMovementLauchGranade)
          {
            this.resource.drawImage(g, this.player.imageCrouching, this.player.getPosition(), this.player.getCrouchingHeightIncrease());
          }

        //SALTO
        if (this.isJump && !this.isRightWalk && !this.isLeftWalk && !this.isMovementCrouching && !this.isMovementFireGun && !this.isMovementLauchGranade)
          {
            this.resource.drawImageArray(g, this.player.imageMovementJump, this.numImageMovement, this.player.getPosition(), 0);
          }

        //COLPO PISTOLA
        if (this.isMovementImgShot)
          {
            this.resource.drawImage(g, this.imageShot.imageColpo, this.imageShot.getPosition(), 0);

          }

        //PISTOLA
        if (this.isMovementFireGun && !this.isRightWalk && !this.isLeftWalk && !this.isMovementCrouching && !this.isJump && !this.isMovementLauchGranade)
          {
            this.resource.drawImageArray(g, this.player.imageMovementFireGun, this.numImageMovement, this.player.getPosition(), 0);
          }

        //GRANATA
        if (this.isMovementLauchGranade && !this.isRightWalk && !this.isLeftWalk && !this.isMovementCrouching && !this.isJump && !this.isMovementFireGun)
          {
            this.resource.drawImageArray(g, this.player.imageMovementLaunchGranade, this.numImageMovement, this.player.getPosition(), 0);
          }

        //GRANATA e ESPLOSIONE
        if (this.isGranadeOrExplosion)
          {
            this.resource.drawImageArray(g, this.arrayImages, this.numImageSequence, this.imageGranade.getPosition(), this.changeHeightImgGranadeAndImgExplosion);
          }
      }

    private class KeyboardListener extends KeyAdapter
      {
        @Override
        public void keyPressed(KeyEvent e)
          {
            //ESC - 
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
              {

              }

            //ENTER - 
            if (!isGameStart && (e.getKeyCode() == KeyEvent.VK_ENTER))
              {
                isGameStart = true;             //PARTENZA GIOCO
                info.setCountdownStart(true);   //DICE SE IL COUNTDOWN E' PARTITO
                startCountdown();               //PARTENZA COUNTDOWN
              }

            //D - DESTRA
            if (isGameStart && (e.getKeyCode() == KeyEvent.VK_D) && !isLeftWalk && !isMovementCrouching && !isJump && !isMovementFireGun && !isMovementLauchGranade)
              {
                isRightWalk = true;

                if (player.getPosition().x <= PLAYER_RIGHT_LIMIT)
                  {
                    player.move(DrawablePlayer.RIGHT);
                    startWalk();
                  }

                else
                  {
                    //SPOSTA LA MAPPA AL POSTO DEL PERSONAGGIO
                    if (mappa.getPosition().x >= GamePanel.MAP_LIMIT)
                      {
                        mappa.move(Map.RIGHT);
                        startWalk();

                        //SPOSTA GLI SCIENZIATI QUANDO SONO MORTI
                        for (int i = 0; i < 2; i++)
                          {
                            if (!scienziato[i].isIsActive())
                              {
                                scienziato[i].move(Scienziato.LEFT);
                              }
                          }
                      }
                  }

                repaint();
              }

            //A - SINISTRA
            if (isGameStart && (e.getKeyCode() == KeyEvent.VK_A) && !isRightWalk && !isMovementCrouching && !isJump && !isMovementFireGun && !isMovementLauchGranade)
              {
                isLeftWalk = true;

                if (player.getPosition().x >= GamePanel.PLAYER_LEFT_LIMIT)
                  {
                    player.move(DrawablePlayer.LEFT);
                    startWalk();
                  }

                repaint();
              }

            //SPAZIO - SALTO
            if (isGameStart && (e.getKeyCode() == KeyEvent.VK_SPACE) && !isRightWalk && !isLeftWalk && !isMovementCrouching && !isMovementFireGun && !isMovementLauchGranade && !isJump)
              {
                isJump = true;

                startJump();
              }

            //S - ACCOVACCIAMENTO
            if (isGameStart && (e.getKeyCode() == KeyEvent.VK_S) && !isRightWalk && !isLeftWalk && !isJump && !isMovementFireGun && !isMovementLauchGranade)
              {
                isMovementCrouching = true;

                repaint();
              }

            //VIRGOLA - PISTOLA
            if (!isMovementImgShot)
              {
                if (isGameStart && (e.getKeyCode() == KeyEvent.VK_COMMA) && !isRightWalk && !isLeftWalk && !isMovementCrouching && !isJump && !isMovementLauchGranade)
                  {
                    isMovementFireGun = true;
                    isMovementImgShot = true;

                    startMovementFireGun();
                  }
              }

            //PUNTO - LANCIO GRANATA
            if (!isGranadeOrExplosion)   //PER FAR LANCIARE UNA GRANATA ALLA VOLTA
              {
                if (isGameStart && (e.getKeyCode() == KeyEvent.VK_PERIOD) && !isRightWalk && !isLeftWalk && !isMovementCrouching && !isJump && !isMovementFireGun)
                  {
                    isMovementLauchGranade = true;
                    isGranadeOrExplosion = true;

                    startMovementLaunchGranade();
                  }
              }
          }

        @Override
        public void keyReleased(KeyEvent e)
          {
            //D - DESTRA
            if (e.getKeyCode() == KeyEvent.VK_D)
              {
                isRightWalk = false;
                repaint();
              }

            //A - SINISTRA
            if (e.getKeyCode() == KeyEvent.VK_A)
              {
                isLeftWalk = false;
                repaint();
              }

            //S - ACCOVACCIAMENTO
            if (e.getKeyCode() == KeyEvent.VK_S)
              {
                isMovementCrouching = false;
                repaint();
              }
          }
      }

    /**
     * DEFINISCO LA CLASSE THREADWALK
     */
    private class ThreadWalk implements Runnable
      {
        @Override
        public void run()
          {
            //numImageMovement = 0; //INIZIALIZZAZIONE

            while (isRightWalk || isLeftWalk)
              {
                System.out.println("dentro while");
                for (numImageMovement = 0; numImageMovement < player.getNumImageMovementWalk(); numImageMovement++)
                  {
                    System.out.println("dentro for");
                    System.out.println(numImageMovement);

                    try
                      {
                        Thread.sleep(GamePanel.SLEEP_WALK_THREAD);
                      }
                    catch (InterruptedException ex)
                      {
                        Logger.getLogger(HowToPlayPanel.class.getName()).log(Level.SEVERE, null, ex);
                      }

                    repaint();

                    /*if(!isRightWalk && !isLeftWalk)
                        break;*/
                  }
                try
                  {
                    Thread.sleep(100);
                  }
                catch (InterruptedException ex)
                  {
                    Logger.getLogger(HowToPlayPanel.class.getName()).log(Level.SEVERE, null, ex);
                  }
                System.out.println("fuori for");

                //numImageMovement++; //INCREMENTO
                //NEL CASO IN CUI LE IMMAGINI SONO FINITE RIPARTE DA CAPO
                //if (numImageMovement == player.getNumImageMovementWalk())
                //{
                //numImageMovement = 0;
                //}
              }
            System.out.println("fuori while");
          }
      }

    /**
     * CREO E AVVIO IL THREAD PER CAMMINARE
     */
    public void startWalk()
      {
        this.threadWalk = new Thread(new ThreadWalk());

        this.threadWalk.start();
      }

    /**
     * DEFINISCO LA CLASSE THREADJUMP
     */
    private class ThreadJump implements Runnable
      {
        @Override
        public void run()
          {
            for (numImageMovement = 0; numImageMovement < player.getNumImageMovementJump(); numImageMovement++)
              {
                player.jumpMovement(numImageMovement);     //MOVIMENTO SULL'ASSE Y

                repaint();

                try
                  {
                    Thread.sleep(GamePanel.SLEEP_JUMP_THREAD);
                  }
                catch (InterruptedException ex)
                  {
                    Logger.getLogger(HowToPlayPanel.class.getName()).log(Level.SEVERE, null, ex);
                  }
              }

            isJump = false;
          }
      }

    /**
     * CREO E AVVIO IL THREAD PER SALTARE
     */
    public void startJump()
      {
        this.threadJump = new Thread(new ThreadJump());

        this.threadJump.start();
      }

    /**
     * DEFINISCO LA CLASSE THREADMOVEMENTFIREGUN
     */
    private class ThreadMovementFireGun implements Runnable
      {
        @Override
        public void run()
          {
            for (numImageMovement = 0; numImageMovement < player.getNumImageMovementFireGun(); numImageMovement++)
              {
                repaint();

                try
                  {
                    Thread.sleep(GamePanel.SLEEP_FIRE_THREAD);
                  }
                catch (InterruptedException ex)
                  {
                    Logger.getLogger(HowToPlayPanel.class.getName()).log(Level.SEVERE, null, ex);
                  }
              }

            isMovementFireGun = false;
          }
      }

    /**
     * CREO E AVVIO IL THREAD PER FARE FUOCO
     */
    public void startMovementFireGun()
      {
        this.threadMovementFireGun = new Thread(new ThreadMovementFireGun());

        this.threadMovementFireGun.start();

        this.startMovementImageShot();
      }

    /**
     * DEFINISCO LA CLASSE THREADIMAGESHOT
     */
    private class ThreadMovementImageShot implements Runnable
      {
        @Override
        public void run()
          {
            while (isMovementImgShot && (imageShot.getPosition().x <= GamePanel.RIGHT_SHOT_LIMIT))
              {
                imageShot.move(DrawableShot.RIGHT);
                repaint();

                for (int i = 0; i < 2; i++)
                  {
                    if ((imageShot.getPosition().x >= scienziato[i].getPosition().x) && (imageShot.getPosition().x <= (scienziato[i].getPosition().x + scienziato[i].imgVivo.getWidth(null))) && scienziato[i].isIsActive())
                      {
                        cambioImgScienziato[i] = scienziato[i].imgMorto;
                        scienziato[i].colpito();
                        isMovementImgShot = false;
                      }
                  }

                try
                  {
                    Thread.sleep(GamePanel.SLEEP_MOVE_SHOT_THREAD);
                  }
                catch (InterruptedException ex)
                  {
                    Logger.getLogger(HowToPlayPanel.class.getName()).log(Level.SEVERE, null, ex);
                  }
              }

            imageShot.imageColpo = null;
            repaint();
          }
      }

    /**
     * CREO E AVVIO IL THREAD PER MUOVERE L'IMMAGINE DEL COLPO
     */
    public void startMovementImageShot()
      {
        this.creationImageShot();

        this.threadMovementImageShot = new Thread(new ThreadMovementImageShot());

        this.threadMovementImageShot.start();
      }

    public void creationImageShot()
      {
        this.imageShot = new DrawableShot(new Point(this.player.getPosition().x + 20, this.player.getPosition().y + 20));
      }

    /**
     * DEFINISCO LA CLASSE THREADMOVEMENTLAUNCHGRANADE
     */
    private class ThreadMovementLaunchGranade implements Runnable
      {
        @Override
        public void run()
          {
            for (numImageMovement = 0; numImageMovement < player.getNumImageMovementLaunchGranade(); numImageMovement++)
              {
                repaint();

                try
                  {
                    Thread.sleep(GamePanel.SLEEP_LAUNCH_GRANADE_THREAD);
                  }
                catch (InterruptedException ex)
                  {
                    Logger.getLogger(HowToPlayPanel.class.getName()).log(Level.SEVERE, null, ex);
                  }
              }

            isMovementLauchGranade = false;
          }
      }

    /**
     * CREO E AVVIO IL THREAD PER LANCIARE LA GRANATA
     */
    public void startMovementLaunchGranade()
      {
        this.threadMovementLaunchGranade = new Thread(new ThreadMovementLaunchGranade());

        this.threadMovementLaunchGranade.start();

        this.startMovementImageGranadeAndExplosion();    //L'IMMAGINE DELLA GRANATA SI MUOVE
      }

    /**
     * DEFINISCO LA CLASSE THREADIMAGEGRANADE
     */
    private class ThreadMovementImageGranadeAndExplosion implements Runnable
      {
        @Override
        public void run()
          {
            arrayImages = imageGranade.imageGranade;        //ARRAY IMMAGINI GRANATA
            changeHeightImgGranadeAndImgExplosion = 0;      //DI QUANTO SPOSTARE VERSO L'ALTO L'IMMAGINE

            //SCORRO L'ARRAY DELLE IMMAGINI DELLA GRANATA
            for (numImageSequence = 0; numImageSequence < imageGranade.getNumberImageGranade(); numImageSequence++)
              {
                imageGranade.move(numImageSequence);        //SPOSTAMENTO IMMAGINE GRANATA

                repaint();

                try
                  {
                    Thread.sleep(GamePanel.SLEEP_MOVE_GRANADE_THREAD);
                  }
                catch (InterruptedException ex)
                  {
                    Logger.getLogger(HowToPlayPanel.class.getName()).log(Level.SEVERE, null, ex);
                  }
              }

            //CAMBIO DEI PARAMETRI
            arrayImages = imageGranade.imageExplosion;      //ARRAY IMMAGINI ESPLOSIONE
            changeHeightImgGranadeAndImgExplosion = imageGranade.getExplosionHeightIncrease();    //SPOSTO IL PUNTO DI ANCORAGGIO PIU' IN ALTO

            //SCORRO L'ARRAY DELLE IMMAGINI DELL'ESPLOSIONE
            for (numImageSequence = 0; numImageSequence < imageGranade.getNumberImageExplosion(); numImageSequence++)
              {
                repaint();

                try
                  {
                    Thread.sleep(GamePanel.SLEEP_SEQ_EXPLOSION_THREAD);
                  }
                catch (InterruptedException ex)
                  {
                    Logger.getLogger(HowToPlayPanel.class.getName()).log(Level.SEVERE, null, ex);
                  }

                if (numImageSequence == imageGranade.getNumberImageExplosion() - 1)
                  {
                    break;
                  }
              }

            isGranadeOrExplosion = false;
          }
      }

    /**
     * CREO E AVVIO IL THREAD PER MUOVERE L'IMMAGINE DELLA GRANATA
     */
    public void startMovementImageGranadeAndExplosion()
      {
        this.creationImageGranade();

        this.threadMovementImageGranadeAndExplosion = new Thread(new ThreadMovementImageGranadeAndExplosion());

        this.threadMovementImageGranadeAndExplosion.start();
      }

    public void creationImageGranade()
      {
        this.imageGranade = new DrawableGranade(new Point(this.player.getPosition().x, this.player.getPosition().y));
      }

    private class ThreadCountdown implements Runnable
      {
        @Override
        public void run()
          {
            numImageContdown = 0;

            while (info.isCountdownStart() && (numImageContdown < info.getNumImageConuntdown()))
              {
                repaint();

                try
                  {
                    Thread.sleep(GamePanel.SLEEP_COUNTDOWN_THREAD);
                  }
                catch (InterruptedException ex)
                  {
                    Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
                  }

                numImageContdown++;
              }

            info.setCountdownStart(false);
          }
      }

    public void startCountdown()
      {
        this.threadCountdown = new Thread(new ThreadCountdown());

        this.threadCountdown.start();
      }
  }
