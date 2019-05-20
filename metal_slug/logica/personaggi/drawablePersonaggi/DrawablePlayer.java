package metal_slug.logica.personaggi.drawablePersonaggi;

import java.awt.Image;
import java.awt.Point;
import metal_slug.logica.personaggi.Player;
import metal_slug.utils.Resource;

public class DrawablePlayer extends Player
  {
    //DIREZIONE
    public static final int RIGHT = 3;
    public static final int LEFT = -3;
    public static final int UP = -30;
    public static final int DOWN = 30;

    //DIMENSIONI ARRAY IMMAGINI
    private static final int NUM_IMAGE_JUMP = 7;
    private static final int NUM_IMAGE_WALK = 16;
    private static final int NUM_IMAGE_FIRE = 9;
    private static final int NUM_IMAGE_GRANADE = 7;

    //MODIFICA DEL PUNTO DI ANCORAGGIO DELL'IMMAGINE
    private static final int CROUCHING_HEIGHT_INCREASE = 42;

    //ARRAY IMMAGINI
    public Image[] imageMovementRightWalk;      //DIREZIONE DESTRA
    public Image[] imageMovementLeftWalk;       //DIREZIONE SINISTRA
    public Image[] imageMovementJump;           //SALTO
    public Image[] imageMovementFireGun;        //PISTOLA
    public Image[] imageMovementLaunchGranade;  //LANCIO GRANATA
    public Image imageCrouching;                //ACCOVACCIATO
    public Image imageDefault;                  //DEFAULT

    public DrawablePlayer(Point pPosition)
      {
        super(pPosition);

        //DIREZIONE DESTRA
        this.imageMovementRightWalk = new Image[DrawablePlayer.NUM_IMAGE_WALK];
        this.loadImageMovementRightWalk();

        //DIREZIONE SINISTRA
        this.imageMovementLeftWalk = new Image[DrawablePlayer.NUM_IMAGE_WALK];
        this.loadImageMovementLeftWalk();

        //SALTO
        this.imageMovementJump = new Image[DrawablePlayer.NUM_IMAGE_JUMP];
        this.loadImageMovementJump();

        //PISTOLA
        this.imageMovementFireGun = new Image[DrawablePlayer.NUM_IMAGE_FIRE];
        this.loadImageMovementFireGun();

        //LANCIO GRANATA
        this.imageMovementLaunchGranade = new Image[DrawablePlayer.NUM_IMAGE_GRANADE];
        this.loadImageMovementLaunchGranade();

        //ACCOVACCIATO
        this.imageCrouching = Resource.getImage("/metal_slug/media/images/Giocatore/Accovacciato.png");

        //DEFAULT
        this.imageDefault = Resource.getImage("/metal_slug/media/images/Giocatore/Direzione/0.png");
      }

    /**
     * CARICO LE IMMAGINI DELLA CAMMINATA VERSO DESTRA NELL'ARRAY
     */
    private void loadImageMovementRightWalk()
      {
        this.imageMovementRightWalk[0] = Resource.getImage("/metal_slug/media/images/Giocatore/Camminata/Destra/0.png");
        this.imageMovementRightWalk[1] = Resource.getImage("/metal_slug/media/images/Giocatore/Camminata/Destra/1.png");
        this.imageMovementRightWalk[2] = Resource.getImage("/metal_slug/media/images/Giocatore/Camminata/Destra/2.png");
        this.imageMovementRightWalk[3] = Resource.getImage("/metal_slug/media/images/Giocatore/Camminata/Destra/3.png");
        this.imageMovementRightWalk[4] = Resource.getImage("/metal_slug/media/images/Giocatore/Camminata/Destra/4.png");
        this.imageMovementRightWalk[5] = Resource.getImage("/metal_slug/media/images/Giocatore/Camminata/Destra/5.png");
        this.imageMovementRightWalk[6] = Resource.getImage("/metal_slug/media/images/Giocatore/Camminata/Destra/6.png");
        this.imageMovementRightWalk[7] = Resource.getImage("/metal_slug/media/images/Giocatore/Camminata/Destra/7.png");
        this.imageMovementRightWalk[8] = Resource.getImage("/metal_slug/media/images/Giocatore/Camminata/Destra/8.png");
        this.imageMovementRightWalk[9] = Resource.getImage("/metal_slug/media/images/Giocatore/Camminata/Destra/9.png");
        this.imageMovementRightWalk[10] = Resource.getImage("/metal_slug/media/images/Giocatore/Camminata/Destra/10.png");
        this.imageMovementRightWalk[11] = Resource.getImage("/metal_slug/media/images/Giocatore/Camminata/Destra/11.png");
        this.imageMovementRightWalk[12] = Resource.getImage("/metal_slug/media/images/Giocatore/Camminata/Destra/12.png");
        this.imageMovementRightWalk[13] = Resource.getImage("/metal_slug/media/images/Giocatore/Camminata/Destra/13.png");
        this.imageMovementRightWalk[14] = Resource.getImage("/metal_slug/media/images/Giocatore/Camminata/Destra/14.png");
        this.imageMovementRightWalk[15] = Resource.getImage("/metal_slug/media/images/Giocatore/Camminata/Destra/15.png");
      }

    /**
     * CARICO LE IMMAGINI DELLA CAMMINATA VERSO SINISTRA NELL'ARRAY
     */
    private void loadImageMovementLeftWalk()
      {
        this.imageMovementLeftWalk[0] = Resource.getImage("/metal_slug/media/images/Giocatore/Camminata/Sinistra/0.png");
        this.imageMovementLeftWalk[1] = Resource.getImage("/metal_slug/media/images/Giocatore/Camminata/Sinistra/1.png");
        this.imageMovementLeftWalk[2] = Resource.getImage("/metal_slug/media/images/Giocatore/Camminata/Sinistra/2.png");
        this.imageMovementLeftWalk[3] = Resource.getImage("/metal_slug/media/images/Giocatore/Camminata/Sinistra/3.png");
        this.imageMovementLeftWalk[4] = Resource.getImage("/metal_slug/media/images/Giocatore/Camminata/Sinistra/4.png");
        this.imageMovementLeftWalk[5] = Resource.getImage("/metal_slug/media/images/Giocatore/Camminata/Sinistra/5.png");
        this.imageMovementLeftWalk[6] = Resource.getImage("/metal_slug/media/images/Giocatore/Camminata/Sinistra/6.png");
        this.imageMovementLeftWalk[7] = Resource.getImage("/metal_slug/media/images/Giocatore/Camminata/Sinistra/7.png");
        this.imageMovementLeftWalk[8] = Resource.getImage("/metal_slug/media/images/Giocatore/Camminata/Sinistra/8.png");
        this.imageMovementLeftWalk[9] = Resource.getImage("/metal_slug/media/images/Giocatore/Camminata/Sinistra/9.png");
        this.imageMovementLeftWalk[10] = Resource.getImage("/metal_slug/media/images/Giocatore/Camminata/Sinistra/10.png");
        this.imageMovementLeftWalk[11] = Resource.getImage("/metal_slug/media/images/Giocatore/Camminata/Sinistra/11.png");
        this.imageMovementLeftWalk[12] = Resource.getImage("/metal_slug/media/images/Giocatore/Camminata/Sinistra/12.png");
        this.imageMovementLeftWalk[13] = Resource.getImage("/metal_slug/media/images/Giocatore/Camminata/Sinistra/13.png");
        this.imageMovementLeftWalk[14] = Resource.getImage("/metal_slug/media/images/Giocatore/Camminata/Sinistra/14.png");
        this.imageMovementLeftWalk[15] = Resource.getImage("/metal_slug/media/images/Giocatore/Camminata/Sinistra/15.png");
      }

    public int getNumImageMovementWalk()
      {
        return imageMovementRightWalk.length;
      }

    /**
     * CARICO LE IMMAGINI DEL SALTO NELL'ARRAY
     */
    private void loadImageMovementJump()
      {
        this.imageMovementJump[0] = Resource.getImage("/metal_slug/media/images/Giocatore/Salto/0.png");
        this.imageMovementJump[1] = Resource.getImage("/metal_slug/media/images/Giocatore/Salto/1.png");
        this.imageMovementJump[2] = Resource.getImage("/metal_slug/media/images/Giocatore/Salto/2.png");
        this.imageMovementJump[3] = Resource.getImage("/metal_slug/media/images/Giocatore/Salto/3.png");
        this.imageMovementJump[4] = Resource.getImage("/metal_slug/media/images/Giocatore/Salto/4.png");
        this.imageMovementJump[5] = Resource.getImage("/metal_slug/media/images/Giocatore/Salto/5.png");
        this.imageMovementJump[6] = Resource.getImage("/metal_slug/media/images/Giocatore/Salto/6.png");
      }

    public int getNumImageMovementJump()
      {
        return imageMovementJump.length;
      }

    /**
     * CARICO LE IMMAGINI DELLO SPARO NELL'ARRAY
     */
    private void loadImageMovementFireGun()
      {
        this.imageMovementFireGun[0] = Resource.getImage("/metal_slug/media/images/Giocatore/Armi/Pistola/0.png");
        this.imageMovementFireGun[1] = Resource.getImage("/metal_slug/media/images/Giocatore/Armi/Pistola/1.png");
        this.imageMovementFireGun[2] = Resource.getImage("/metal_slug/media/images/Giocatore/Armi/Pistola/2.png");
        this.imageMovementFireGun[3] = Resource.getImage("/metal_slug/media/images/Giocatore/Armi/Pistola/3.png");
        this.imageMovementFireGun[4] = Resource.getImage("/metal_slug/media/images/Giocatore/Armi/Pistola/4.png");
        this.imageMovementFireGun[5] = Resource.getImage("/metal_slug/media/images/Giocatore/Armi/Pistola/5.png");
        this.imageMovementFireGun[6] = Resource.getImage("/metal_slug/media/images/Giocatore/Armi/Pistola/6.png");
        this.imageMovementFireGun[7] = Resource.getImage("/metal_slug/media/images/Giocatore/Armi/Pistola/7.png");
        this.imageMovementFireGun[8] = Resource.getImage("/metal_slug/media/images/Giocatore/Salto/6.png");
      }

    public int getNumImageMovementFireGun()
      {
        return imageMovementFireGun.length;
      }

    /**
     * CARICO LE IMMAGINI DEL LANCIO DELLA GRANATA NELL'ARRAY
     */
    private void loadImageMovementLaunchGranade()
      {
        this.imageMovementLaunchGranade[0] = Resource.getImage("/metal_slug/media/images/Giocatore/Armi/Lancio_granata/0.png");
        this.imageMovementLaunchGranade[1] = Resource.getImage("/metal_slug/media/images/Giocatore/Armi/Lancio_granata/1.png");
        this.imageMovementLaunchGranade[2] = Resource.getImage("/metal_slug/media/images/Giocatore/Armi/Lancio_granata/2.png");
        this.imageMovementLaunchGranade[3] = Resource.getImage("/metal_slug/media/images/Giocatore/Armi/Lancio_granata/3.png");
        this.imageMovementLaunchGranade[4] = Resource.getImage("/metal_slug/media/images/Giocatore/Armi/Lancio_granata/4.png");
        this.imageMovementLaunchGranade[5] = Resource.getImage("/metal_slug/media/images/Giocatore/Armi/Lancio_granata/5.png");
        this.imageMovementLaunchGranade[6] = Resource.getImage("/metal_slug/media/images/Giocatore/Armi/Lancio_granata/6.png");
      }

    public int getNumImageMovementLaunchGranade()
      {
        return imageMovementLaunchGranade.length;
      }

    /**
     * MOVIMENTO SULL'ASSE Y DEL PERSONAGGIO PER IL SALTO
     *
     * @param index L'indice del'array delle immagini del salto
     */
    public void jumpMovement(int index)
      {
        if (index < (this.getNumImageMovementJump() / 2))
          {
            this.position.y += DrawablePlayer.UP;
          }

        if (index > (this.getNumImageMovementJump() / 2))
          {
            this.position.y += DrawablePlayer.DOWN;
          }
      }

    /**
     * VALORE DEL NUOVO PUNTO DI ANCORAGGIO
     *
     * @return Valore per il nuovo punto di ancoraggio
     */
    public int getCrouchingHeightIncrease()
      {
        return CROUCHING_HEIGHT_INCREASE;
      }
  }
