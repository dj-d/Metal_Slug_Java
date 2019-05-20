package metal_slug.logica.armi.munizioni;

import java.awt.Point;
import metal_slug.logica.armi.Weapon;

public class ColpoZombie extends Weapon
  {
    public static final int POTENZA_COLPO = 10;
    
    public ColpoZombie(Point pPosition)
      {
        super(ColpoZombie.POTENZA_COLPO, pPosition);
      }
  }
