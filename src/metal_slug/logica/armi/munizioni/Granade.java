package metal_slug.logica.armi.munizioni;

import java.awt.Point;

import metal_slug.logica.armi.Weapon;

public class Granade extends Weapon {
	public static final int POTENZA_COLPO = 15;

	public boolean isAttiva;

	public Granade(Point pPosition) {
		super(Granade.POTENZA_COLPO, pPosition);

		this.isAttiva = false;
	}

	public boolean isIsAttiva() {
		return this.isAttiva;
	}

	public void setIsAttiva(boolean pIsAttiva) {
		this.isAttiva = pIsAttiva;
	}
}
