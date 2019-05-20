package metal_slug.logica;

import java.awt.Point;
import java.security.GeneralSecurityException;
import metal_slug.logica.personaggi.drawablePersonaggi.DrawablePlayer;
import metal_slug.logica.personaggi.drawablePersonaggi.Elicottero;
import metal_slug.logica.personaggi.drawablePersonaggi.Robot;
import metal_slug.logica.personaggi.drawablePersonaggi.Scienziato;
import metal_slug.logica.personaggi.drawablePersonaggi.Tank;
import metal_slug.logica.personaggi.drawablePersonaggi.Zombie;

public class GameController
  {
    public DrawablePlayer player;
    
    public Scienziato scienziato;
    
    public Zombie zombie;
    
    public Tank tank;
    
    public Robot robot;

    public Elicottero elicottero;
    
    public GameController()
      {
        
      }
    
    public void newPlayer(Point pPosition)
      {
        this.player = new DrawablePlayer(pPosition);
      }
    
    public void generatoreNemici(String tipo, Point pPosition) throws GeneralSecurityException
      {
        switch(tipo)
          {
            case "Scienziato":
                
                this.scienziato = new Scienziato(pPosition);
                
                break;
                
            case "Zombie":
                
                this.zombie = new Zombie(pPosition);
                
                break;
                
            case "Tank":
                
                this.tank = new Tank(pPosition);
                
                break;
                
            case "Robot":
                
                this.robot = new Robot(pPosition);
                
                break;
                
            case "Elicottero":
                
                this.elicottero = new Elicottero(pPosition);
                
                break;
                
            default:
                
                throw new GeneralSecurityException();
          }
      }
  }
