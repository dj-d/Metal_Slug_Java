package metal_slug.logica.armi.munizioni;

import java.awt.Point;
import metal_slug.logica.armi.Weapon;

public class RazzoElicottero extends Weapon
  {
    public static final int POTENZA_COLPO = 25;
    
    public RazzoElicottero(Point pPosition)
      {
        super(RazzoElicottero.POTENZA_COLPO, pPosition);
      }
  }
