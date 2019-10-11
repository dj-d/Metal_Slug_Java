package logic.weapons.logic;

import logic.weapons.Weapon;

import java.awt.*;

public class Shot extends Weapon {
	public static final int POWER = 10;

	public Shot(Point pPosition) {
		super(Shot.POWER, pPosition);
	}
}
