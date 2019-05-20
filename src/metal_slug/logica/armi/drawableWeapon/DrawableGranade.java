package metal_slug.logica.armi.drawableWeapon;

import java.awt.Image;
import java.awt.Point;
import metal_slug.logica.armi.munizioni.Granade;
import metal_slug.utils.Resource;

public class DrawableGranade extends Granade
  {
    //DIREZIONI
    public static final int RIGHT = 80;
    public static final int UP = -30;
    public static final int DOWN = 60;

    //DIUMENSIONI ARRAY UMMAGINI
    private static final int NUM_IMAGE_GRANADE = 5;
    private static final int NUM_IMAGE_EXPLOSION = 21;

    //MODIFICA DEL PUNTO DI ANCORAGGIO DELL'IMMAGINE
    private static final int EXPLOSION_HEIGHT_INCREASE = 249;

    //ARRAY IMMAGINI
    public Image[] imageGranade;
    public Image[] imageExplosion;

    public DrawableGranade(Point pPosition)
      {
        super(pPosition);

        //ARRAY IMMAGINI GRANATA
        this.imageGranade = new Image[DrawableGranade.NUM_IMAGE_GRANADE];
        this.loadImageGranade();

        //ARRAY IMMAGINI ESPLOSIONE
        this.imageExplosion = new Image[DrawableGranade.NUM_IMAGE_EXPLOSION];
        this.loadImageExplosion();
      }

    /**
     * CARICO LE IMMAGINI DEL MOVIMENTO DELLA GRANATA NELL'ARRAY
     */
    private void loadImageGranade()
      {
        this.imageGranade[0] = Resource.getImage("/metal_slug/media/images/Giocatore/Munizioni/Granata/0.png");
        this.imageGranade[1] = Resource.getImage("/metal_slug/media/images/Giocatore/Munizioni/Granata/1.png");
        this.imageGranade[2] = Resource.getImage("/metal_slug/media/images/Giocatore/Munizioni/Granata/2.png");
        this.imageGranade[3] = Resource.getImage("/metal_slug/media/images/Giocatore/Munizioni/Granata/3.png");
        this.imageGranade[4] = Resource.getImage("/metal_slug/media/images/Giocatore/Munizioni/Granata/4.png");
      }

    public int getNumberImageGranade()
      {
        return imageGranade.length;
      }

    /**
     * CARICO LE IMMAGINI DELLE ESPLOSIONI NELL'ARRAY
     */
    private void loadImageExplosion()
      {
        this.imageExplosion[0] = Resource.getImage("/metal_slug/media/images/Giocatore/Munizioni/Granata/Esplosione/0.png");
        this.imageExplosion[1] = Resource.getImage("/metal_slug/media/images/Giocatore/Munizioni/Granata/Esplosione/1.png");
        this.imageExplosion[2] = Resource.getImage("/metal_slug/media/images/Giocatore/Munizioni/Granata/Esplosione/2.png");
        this.imageExplosion[3] = Resource.getImage("/metal_slug/media/images/Giocatore/Munizioni/Granata/Esplosione/3.png");
        this.imageExplosion[4] = Resource.getImage("/metal_slug/media/images/Giocatore/Munizioni/Granata/Esplosione/4.png");
        this.imageExplosion[5] = Resource.getImage("/metal_slug/media/images/Giocatore/Munizioni/Granata/Esplosione/5.png");
        this.imageExplosion[6] = Resource.getImage("/metal_slug/media/images/Giocatore/Munizioni/Granata/Esplosione/6.png");
        this.imageExplosion[7] = Resource.getImage("/metal_slug/media/images/Giocatore/Munizioni/Granata/Esplosione/7.png");
        this.imageExplosion[8] = Resource.getImage("/metal_slug/media/images/Giocatore/Munizioni/Granata/Esplosione/8.png");
        this.imageExplosion[9] = Resource.getImage("/metal_slug/media/images/Giocatore/Munizioni/Granata/Esplosione/9.png");
        this.imageExplosion[10] = Resource.getImage("/metal_slug/media/images/Giocatore/Munizioni/Granata/Esplosione/10.png");
        this.imageExplosion[11] = Resource.getImage("/metal_slug/media/images/Giocatore/Munizioni/Granata/Esplosione/11.png");
        this.imageExplosion[12] = Resource.getImage("/metal_slug/media/images/Giocatore/Munizioni/Granata/Esplosione/12.png");
        this.imageExplosion[13] = Resource.getImage("/metal_slug/media/images/Giocatore/Munizioni/Granata/Esplosione/13.png");
        this.imageExplosion[14] = Resource.getImage("/metal_slug/media/images/Giocatore/Munizioni/Granata/Esplosione/14.png");
        this.imageExplosion[15] = Resource.getImage("/metal_slug/media/images/Giocatore/Munizioni/Granata/Esplosione/15.png");
        this.imageExplosion[16] = Resource.getImage("/metal_slug/media/images/Giocatore/Munizioni/Granata/Esplosione/16.png");
        this.imageExplosion[17] = Resource.getImage("/metal_slug/media/images/Giocatore/Munizioni/Granata/Esplosione/17.png");
        this.imageExplosion[18] = Resource.getImage("/metal_slug/media/images/Giocatore/Munizioni/Granata/Esplosione/18.png");
        this.imageExplosion[19] = Resource.getImage("/metal_slug/media/images/Giocatore/Munizioni/Granata/Esplosione/19.png");
        this.imageExplosion[20] = null;
      }

    public int getNumberImageExplosion()
      {
        return imageExplosion.length;
      }

    /**
     * MOVIMENTO DELLA GRANATA
     *
     * @param index L'indice dell'array
     */
    @Override
    public void move(int index)
      {
        if (index < (this.getNumberImageGranade() / 2))
          {
            this.position.y += DrawableGranade.UP;
            this.position.x += DrawableGranade.RIGHT;
          }

        if (index > (this.getNumberImageGranade() / 2))
          {
            this.position.y += DrawableGranade.DOWN;
            this.position.x += DrawableGranade.RIGHT;
          }
      }

    /**
     * VALORE DEL NUOVO PUNTO DI ANCORAGGIO
     *
     * @return Valore per il nuovo punto di ancoraggio
     */
    public int getExplosionHeightIncrease()
      {
        return EXPLOSION_HEIGHT_INCREASE;
      }
  }
