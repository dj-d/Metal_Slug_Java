package metal_slug.logica.armi.munizioni;

import java.awt.Point;
import metal_slug.logica.armi.Weapon;

public class MissileRobot extends Weapon
  {
    public static final int POTENZA_COLPO = 20;
    
    public MissileRobot(Point pPosition)
      {
        super(MissileRobot.POTENZA_COLPO, pPosition);
      }
  }
