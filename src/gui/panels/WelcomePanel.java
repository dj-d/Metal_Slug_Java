package gui.panels;

import gui.GameFrame;
import utils.Resource;
import utils.Sound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WelcomePanel extends JPanel {
	//WINDOW DIMENSION
	private static final Dimension DIMENSION = new Dimension(GameFrame.WIDTH_WINDOW, GameFrame.HEIGHT_WINDOW);

	//HOTAREA PARAMETERS
	private static final int POS_HOTAREA_X = 590;
	private static final int POS_HOTAREA_Y = 560;
	private static final int HEIGHT_HOTAREA = 100;
	private static final int WIDTH_HOTAREA = 575;

	private Image background;

	// START AREA
	private Rectangle hotArea;

	private Sound song;

	public WelcomePanel() {
		this.setSize(DIMENSION);
		this.setLayout(null);

		this.hotArea = new Rectangle();
		this.setHotArea();

		this.background = Resource.getImage("/media/images/Panels/WelcomePanel/WelcomePanel.png");

		try {
			this.song = Resource.getSound("/media/sounds/Main_menu.wav");
		} catch (IOException ex) {
			Logger.getLogger(WelcomePanel.class.getName()).log(Level.SEVERE, null, ex);
		}

		this.addMouseListener(new MyMouseListener());
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(this.background, 0, 0, getParent());
		this.song.loop();
	}

	private class MyMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (hotArea.contains(e.getPoint())) {
				GameFrame.showPanel(GameFrame.HOW_TO_PLAY_PANEL);
			}
		}
	}

	/**
	 * INIT HOTAREA
	 */
	private void setHotArea() {
		this.hotArea.x = WelcomePanel.POS_HOTAREA_X;
		this.hotArea.y = WelcomePanel.POS_HOTAREA_Y;
		this.hotArea.height = WelcomePanel.HEIGHT_HOTAREA;
		this.hotArea.width = WelcomePanel.WIDTH_HOTAREA;
	}
}