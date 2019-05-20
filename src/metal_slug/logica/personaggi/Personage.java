package metal_slug.logica.personaggi;

import java.awt.Point;
import metal_slug.logica.armi.drawableWeapon.DrawableShot;

public abstract class Personage
  {
    protected int salute;
    protected Point position;

    public DrawableShot pistola;

    public Personage(int pVita, Point pPosition)
      {
        this.salute = pVita;
        this.position = pPosition;
        this.pistola = new DrawableShot(pPosition);
      }

    /**
     * VITA PERSONAGGIO
     *
     * @return Restituisce la vita del personaggio
     */
    public int getVita()
      {
        return salute;
      }

    /**
     * POSIZIONE DEL PERSONAGGIO
     *
     * @return Le coordinate del personaggio
     */
    public Point getPosition()
      {
        return position;
      }

    public void fuoco()
      {
        //TO DO...
      }

    public void colpito()
      {
        //TO DO...
      }

    /**
     * MOVIMENTO SULL'ASSE X DEL PERSONAGGIO
     *
     * @param direction La direzione da prendere
     */
    public void move(int direction)
      {
        this.position.x += direction;
      }
  }
