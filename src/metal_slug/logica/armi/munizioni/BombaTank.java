package metal_slug.logica.armi.munizioni;

import java.awt.Point;

import metal_slug.logica.armi.Weapon;

public class BombaTank extends Weapon {
	public static final int POTENZA_COLPO = 15;

	public BombaTank(Point pPosition) {
		super(BombaTank.POTENZA_COLPO, pPosition);
	}
}
