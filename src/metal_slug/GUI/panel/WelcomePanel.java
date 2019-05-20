package metal_slug.GUI.panel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import metal_slug.GUI.GameFrame;
import metal_slug.utils.*;

public class WelcomePanel extends JPanel
  {
    //DIMENSIONE DELLA FINESTRA
    public static final Dimension DIMENSIONE = new Dimension(GameFrame.WIDTH_WINDOW, GameFrame.HEIGHT_WINDOW);

    //PARAMETRI HOTAREA
    public static final int POS_HOTAREA_X = 590;
    public static final int POS_HOTAREA_Y = 560;
    public static final int HEIGHT_HOTAREA = 100;
    public static final int WIDTH_HOTAREA = 575;

    //IMMAGINI
    private Image background;
    
    private Rectangle hotArea;            //HOTAREA DELLA SCRITTA PARTENZA

    //SUONO
    private Sound song;

    public WelcomePanel()
      {
        this.setSize(DIMENSIONE);       //IMPOSTIAMO LA DIMENSIONE DELLA FINESTRA

        this.setLayout(null);           //PER POTER MUOVERE A PICIMENTO I PANNELLI

        this.hotArea = new Rectangle();   //CREIAMO L'HOTAREA
        this.setHotArea();              //IMPOSTIAMO L'HOTAREA

        this.background = Resource.getImage("/metal_slug/media/images/Panels/WelcomePanel/WelcomePanel.png"); //SFONDO

        try
          {
            this.song = Resource.getSound("/metal_slug/media/sounds/Main_menu.wav");    //MAIN SONG
          }
        catch (IOException ex)
          {
            Logger.getLogger(WelcomePanel.class.getName()).log(Level.SEVERE, null, ex);
          }

        this.addMouseListener(new MyMouseListener());                           //AGGIUNGIAMO IL MOUSELISTENER AL PANNELLO
      }

    @Override
    protected void paintComponent(Graphics g)
      {
        g.drawImage(this.background, 0, 0, getParent());    //DISEGNO LO SFONDO

        this.song.loop();                                   //METTO IN LOOP LA MAIN SONG
      }

    /**
     * MOUSE LISTENER PER HOTAREA
     */
    private class MyMouseListener extends MouseAdapter
      {
        @Override
        public void mouseClicked(MouseEvent e)
          {
            if (hotArea.contains(e.getPoint()))
              {
                GameFrame.showPanel(GameFrame.HOW_TO_PLAY_PANEL); //CAMBIO PANNELLO
              }
          }
      }

    /**
     * IMPOSTA I VALORI DELL'HOTAREA PER LA PARTENZA
     */
    private void setHotArea()
      {
        this.hotArea.x = WelcomePanel.POS_HOTAREA_X;
        this.hotArea.y = WelcomePanel.POS_HOTAREA_Y;
        this.hotArea.height = WelcomePanel.HEIGHT_HOTAREA;
        this.hotArea.width = WelcomePanel.WIDTH_HOTAREA;
      }
  }
