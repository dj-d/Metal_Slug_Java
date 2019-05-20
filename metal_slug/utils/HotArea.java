package metal_slug.utils;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

public class HotArea extends Rectangle
  {
    protected Rectangle area;
    
    private Dimension dimensione;
    
    private Point posizione;

    public HotArea(int larghezza, int altezza, Point pPoint)
      {
        this.area = new Rectangle();
        this.dimensione = new Dimension();
        this.dimensione.setSize(larghezza, altezza);
        this.posizione = new Point();
        this.posizione.setLocation(pPoint);
      }

    @Override
    public void setSize(int lar, int alt)
      {
        this.dimensione.height = alt;
        this.dimensione.width = lar;
        //this.area.width = lar;
        //this.area.height = alt;
      }

    @Override
    public Dimension getSize()
      {
        return dimensione;
      }

    @Override
    public void setLocation(Point point)
      {
        this.posizione = point;
      }

    @Override
    public Point getLocation()
      {
        return posizione;
      }
  }
