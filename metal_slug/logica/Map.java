package metal_slug.logica;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import metal_slug.utils.Resource;

public class Map
  {
    public static final int RIGHT = -15;

    private Point position;
    public Image imgMappa;

    public Map(Point pPosition)
      {
        this.position = pPosition;

        this.imgMappa = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Mappa.png");
      }

    public void drawMappa(Graphics g)
      {
        g.drawImage(this.imgMappa, this.getPosition().x, this.getPosition().y, null);
      }

    public void move(int direction)
      {
        this.position.x += direction;
      }

    public Point getPosition()
      {
        return position;
      }
  }
