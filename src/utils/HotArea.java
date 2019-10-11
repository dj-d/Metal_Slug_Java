package utils;

import java.awt.*;

public class HotArea extends Rectangle {
	private Dimension dimension;
	private Point position;

	public HotArea(int pWidth, int pHeight, Point pPoint) {
		this.dimension = new Dimension();
		this.dimension.setSize(pWidth, pHeight);
		this.position = new Point();
		this.position.setLocation(pPoint);
	}

	@Override
	public void setSize(int pWidth, int pHeight) {
		this.dimension.width = pWidth;
		this.dimension.height = pHeight;
	}

	@Override
	public Dimension getSize() {
		return dimension;
	}

	@Override
	public void setLocation(Point point) {
		this.position = point;
	}

	@Override
	public Point getLocation() {
		return position;
	}
}
