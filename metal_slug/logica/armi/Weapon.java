package metal_slug.logica.armi;

import java.awt.Point;

public abstract class Weapon
  {
    protected int potenza;
    
    protected Point position;

    public Weapon(int pPotenza, Point pPosition)
      {
        this.potenza = pPotenza;
        this.position = pPosition;
      }

    /**
     * POTENZA COLPO
     *
     * @return La potenza del colpo
     */
    public int getPotenza()
      {
        return potenza;
      }

    /**
     * POSIZIONE COLPO
     *
     * @return La posizione del colpo
     */
    public Point getPosition()
      {
        return position;
      }

    public void move(int direciton)
      {
        this.position.x += direciton;
      }
  }
