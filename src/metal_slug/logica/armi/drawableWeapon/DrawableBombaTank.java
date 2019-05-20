package metal_slug.logica.armi.drawableWeapon;

import java.awt.Image;
import java.awt.Point;
import metal_slug.logica.armi.munizioni.BombaTank;
import metal_slug.utils.Resource;

public class DrawableBombaTank extends BombaTank
  {
    public static final int LEFT = 30;
    
    public Image imgBomba;
    
    public DrawableBombaTank(Point pPosition)
      {
        super(pPosition);
        
        this.imgBomba = Resource.getImage("/metal_slug/media/images/Nemici/Tank/ColpoTank.png");
      }
  }
