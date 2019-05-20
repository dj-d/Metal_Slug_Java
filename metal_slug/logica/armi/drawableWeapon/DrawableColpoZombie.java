package metal_slug.logica.armi.drawableWeapon;

import java.awt.Image;
import java.awt.Point;
import metal_slug.logica.armi.munizioni.ColpoZombie;
import metal_slug.utils.Resource;

public class DrawableColpoZombie extends ColpoZombie
  {
    public static final int LEFT = 20;
    
    public Image imgColpo;
    
    public DrawableColpoZombie(Point pPosition)
      {
        super(pPosition);
        
        this.imgColpo = Resource.getImage("/metal_slug/media/images/Nemici/Zombie/ColpoZombie.png");
      }
  }
