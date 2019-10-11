package logic;

import utils.Resource;

import java.awt.*;

public class Map {
	public static final int RIGHT = -15;

	private Point position;
	private String mapPath = "/media/images/Panels/GamePanel/Map.png";
	private Image map;

	public Map(Point pPosition) {
		this.position = pPosition;
		this.map = Resource.getImage(this.mapPath);
	}

	public Point getPosition() {
		return position;
	}

	public Image getMap() {
		return map;
	}

	public void move(int direction) {
		this.position.x += direction;
	}

}