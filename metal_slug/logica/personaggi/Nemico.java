package metal_slug.logica.personaggi;

import java.awt.Point;

public class Nemico extends Personage
  {
    protected static final boolean VIVO = true;
    protected static final boolean MORTO = false;
    
    protected boolean isActive;

    public Nemico(int pVita, Point pPosition)
      {
        super(pVita, pPosition);

        this.setIsActive(Nemico.VIVO);
      }

    public boolean isIsActive()
      {
        return isActive;
      }

    public void setIsActive(boolean pValue)
      {
        this.isActive = pValue;
      }
  }
