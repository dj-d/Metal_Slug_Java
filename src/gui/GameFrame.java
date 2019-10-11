package gui;

import gui.panels.GamePanel;
import gui.panels.HowToPlayPanel;
import gui.panels.WelcomePanel;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
	public static final int HEIGHT_WINDOW = 720;
	public static final int WIDTH_WINDOW = 1280;
	public static final Dimension DIMENSION = new Dimension(WIDTH_WINDOW, HEIGHT_WINDOW);

	public static final int WELCOME_PANEL = 1;
	public static final int HOW_TO_PLAY_PANEL = 2;
	public static final int GAME_PANEL = 3;

	private static final boolean RESIZABLE = false;

	private static final String GAME_NAME = "Metal Slug";

	private static WelcomePanel welcomePanel;
	public static HowToPlayPanel howToPlayPanel;
    public static GamePanel gamePanel;

	public GameFrame() {
		this.setSize(DIMENSION);
		this.setResizable(RESIZABLE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(GAME_NAME);
		this.setLocationRelativeTo(null);
		this.setLayout(null);

		GameFrame.welcomePanel = new WelcomePanel();
		this.getContentPane().add(welcomePanel);

		GameFrame.howToPlayPanel = new HowToPlayPanel();
		this.getContentPane().add(howToPlayPanel);

        GameFrame.gamePanel = new GamePanel();
        this.getContentPane().add(gamePanel);

		GameFrame.welcomePanel.setFocusable(true);
		GameFrame.howToPlayPanel.setFocusable(true);
        GameFrame.gamePanel.setFocusable(true);

		GameFrame.showPanel(GameFrame.WELCOME_PANEL);
	}

	/**
	 * CAMBIA LA VISIBILITA' DEI PANNELLI
	 *
	 * @param pGameStatus Variabile per indicare quale pannello visualizzare
	 */
	public static void showPanel(int pGameStatus) {
		switch (pGameStatus) {
			case WELCOME_PANEL:
				GameFrame.welcomePanel.setVisible(true);
				GameFrame.howToPlayPanel.setVisible(false);
                GameFrame.gamePanel.setVisible(false);

				GameFrame.welcomePanel.requestFocusInWindow();
				break;

			case HOW_TO_PLAY_PANEL:
				GameFrame.welcomePanel.setVisible(false);
				GameFrame.howToPlayPanel.setVisible(true);
                GameFrame.gamePanel.setVisible(false);

				GameFrame.howToPlayPanel.requestFocusInWindow();
				break;

			case GAME_PANEL:
				GameFrame.welcomePanel.setVisible(false);
				GameFrame.howToPlayPanel.setVisible(false);
                GameFrame.gamePanel.setVisible(true);

                GameFrame.gamePanel.requestFocusInWindow();
				break;
		}
	}
}