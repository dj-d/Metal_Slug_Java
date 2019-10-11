package metal_slug.logica.armi.munizioni;

import java.awt.Point;

import metal_slug.logica.armi.Weapon;

public class Siringa extends Weapon {
	public static final int POTENZA_COLPOO = 5;

	public Siringa(Point pPosition) {
		super(Siringa.POTENZA_COLPOO, pPosition);
	}
}
