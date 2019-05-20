package metal_slug.logica.armi.drawableWeapon;

import java.awt.Image;
import java.awt.Point;
import metal_slug.logica.armi.munizioni.RazzoElicottero;
import metal_slug.utils.Resource;

public class DrawableRazzoElicottero extends RazzoElicottero
  {
    public static final int LEFT = 30;
    
    public Image imgRazzo;
    
    public DrawableRazzoElicottero(Point pPosition)
      {
        super(pPosition);
        
        this.imgRazzo = Resource.getImage("/metal_slug/medi/images/Nemici/Elicottero/ColpoElicottero.png");
      }
  }
