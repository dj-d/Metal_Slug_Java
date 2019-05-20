package metal_slug.logica.armi.drawableWeapon;

import java.awt.Image;
import java.awt.Point;
import metal_slug.logica.armi.munizioni.Shot;
import metal_slug.utils.Resource;

public class DrawableShot extends Shot
  {
    public static final int RIGHT = 40;
    public static final int LEFT = -3;
    
    public Image imageColpo;

    public DrawableShot(Point pPosition)
      {
        super(pPosition);
        
        this.imageColpo = Resource.getImage("/metal_slug/media/images/Giocatore/Munizioni/Colpo_pistola.png");
      }
  }
