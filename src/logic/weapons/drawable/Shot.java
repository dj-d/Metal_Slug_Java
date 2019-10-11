package logic.weapons.drawable;

import utils.Resource;

import java.awt.*;

public class Shot extends logic.weapons.logic.Shot{
	public static final int RIGHT = 40;
	public static final int LEFT = -3;

	private Image img;

	public Shot(Point pPosition) {
		super(pPosition);

		this.img = Resource.getImage("/media/images/Weapons/Shot.png");
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}
}
