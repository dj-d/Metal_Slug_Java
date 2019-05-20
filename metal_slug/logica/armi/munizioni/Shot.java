package metal_slug.logica.armi.munizioni;

import java.awt.Point;
import metal_slug.logica.armi.Weapon;

public class Shot extends Weapon
  {
    public static final int POTENZA_COLPO = 10;

    public Shot(Point pPosition)
      {
        super(Shot.POTENZA_COLPO, pPosition);
      }
  }
