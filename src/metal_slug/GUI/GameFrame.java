package metal_slug.GUI;

import java.awt.Dimension;
import javax.swing.JFrame;
import metal_slug.GUI.panel.*;

public class GameFrame extends JFrame
  {
    //DIMENSIONI DEL FRAME
    public static final int HEIGHT_WINDOW = 720;
    public static final int WIDTH_WINDOW = 1280;
    public static final Dimension DIMENSION = new Dimension(WIDTH_WINDOW, HEIGHT_WINDOW);

    //SCELTE MENU
    public static final int WELCOME_PANEL = 1;
    public static final int HOW_TO_PLAY_PANEL = 2;
    public static final int GAME_PANEL = 3;

    //COSTANTE PER LA MODIFICA DELLA FINESTRA
    public static final boolean RESIZABLE = false;

    //NOME GIOCO
    public static final String GAME_NAME = "Metal Slug";

    //PUNTATORI AI PANNELLI
    private static WelcomePanel welcomePanel;
    public static HowToPlayPanel howToPlayPanel;
//    public static GamePanel gamePanel;

    public GameFrame()
      {
        this.setSize(DIMENSION);                                //IMPOSTIAMO LA DIMENSIONE DELLA FINESTRA

        this.setResizable(RESIZABLE);                           //GLI DICIAMO SE E' MODIFICABILE OPPURE NO

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //IMPOSTIAMO LA "X" COME PULSANTE PER LA CHIUSURA DEL PROGRAMMA

        this.setTitle(GAME_NAME);                               //IMPOSTIAMO IL NOME DEL GIOCO

        this.setLocationRelativeTo(null);                       //LO POSIZIONIAMO AL CENTRO

        this.setLayout(null);                                   //PER POTER MUOVERE A PICIMENTO I PANNELLI

        GameFrame.welcomePanel = new WelcomePanel();            //WELCOMEPANEL
        this.getContentPane().add(welcomePanel);                //ATTACHIAMO WELCOMEPANEL AL FRAME

        GameFrame.howToPlayPanel = new HowToPlayPanel();        //HOWTOPLAYPANEL
        this.getContentPane().add(howToPlayPanel);              //ATTACHIAMO HOWTOPLAYPANEL AL FRAME

//        GameFrame.gamePanel = new GamePanel();                  //GAMEPANEL
//        this.getContentPane().add(gamePanel);                   //ATTACHIAMO GAMEPANEL AL FRAME

        GameFrame.welcomePanel.setFocusable(true);

        GameFrame.howToPlayPanel.setFocusable(true);

//        GameFrame.gamePanel.setFocusable(true);

        GameFrame.showPanel(GameFrame.HOW_TO_PLAY_PANEL);       //PANNELLO DA VISUALIZZARE -> DA IMPOSTARE WELCOME PANEL COME PRINCIPALE
      }

    /**
     * CAMBIA LA VISIBILITA' DEI PANNELLI
     *
     * @param pGameStatus Variabile per indicare quale pannello visualizzare
     */
    public static void showPanel(int pGameStatus)
      {
        switch (pGameStatus)
          {
            case WELCOME_PANEL:

                GameFrame.welcomePanel.setVisible(true);
                GameFrame.howToPlayPanel.setVisible(false);
//                GameFrame.gamePanel.setVisible(false);

                GameFrame.welcomePanel.requestFocusInWindow();

            break;

            case HOW_TO_PLAY_PANEL:

                GameFrame.welcomePanel.setVisible(false);
                GameFrame.howToPlayPanel.setVisible(true);
//                GameFrame.gamePanel.setVisible(false);

                GameFrame.howToPlayPanel.requestFocusInWindow();

            break;

            case GAME_PANEL:

                GameFrame.welcomePanel.setVisible(false);
                GameFrame.howToPlayPanel.setVisible(false);
//                GameFrame.gamePanel.setVisible(true);

//                GameFrame.gamePanel.requestFocusInWindow();
                
            break;
          }
      }
  }
