package metal_slug.logica.armi.drawableWeapon;

import java.awt.Image;
import java.awt.Point;
import metal_slug.logica.armi.munizioni.MissileRobot;
import metal_slug.utils.Resource;

public class DrawableMissileRobot extends MissileRobot
  {
    public static final int LEFT = 30;
    
    public Image imgMissile;
    
    public DrawableMissileRobot(Point pPosition)
      {
        super(pPosition);
        
        this.imgMissile = Resource.getImage("/metal_slug/media/images/Nemici/Robot/ColpoRobot.png");
      }
  }
