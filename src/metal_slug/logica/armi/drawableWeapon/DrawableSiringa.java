package metal_slug.logica.armi.drawableWeapon;

import java.awt.Image;
import java.awt.Point;
import metal_slug.logica.armi.munizioni.Siringa;
import metal_slug.utils.Resource;

public class DrawableSiringa extends Siringa
  {
    public static final int LEFT = 30;
    
    public Image imgSiringa;
    
    public DrawableSiringa(Point pPosition)
      {
        super(pPosition);
        
        this.imgSiringa = Resource.getImage("/metal_slug/media/images/Nemici/Scienziato/ColpoScienziato.png");
      }
  }
