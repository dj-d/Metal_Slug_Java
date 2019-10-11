package logic.weapons.logic;

import logic.weapons.Weapon;

import java.awt.*;

public class Grenade extends Weapon {
	public static final int POWER = 15;

	public boolean isEnable;

	public Grenade(Point pPosition) {
		super(Grenade.POWER, pPosition);

		this.isEnable = false;
	}

	public boolean isEnable() {
		return isEnable;
	}

	public void setEnable(boolean pValue) {
		isEnable = pValue;
	}
}
