package metal_slug.logica.personaggi;

import java.awt.Point;

import metal_slug.logica.armi.drawableWeapon.DrawableGranade;

public class Player extends Personage {
	public static final int MAX_SALUTE = 100;
	public static final int MAX_NUM_GRANATE = 10;
	public static final int MAX_VITE = 3;

	public int vite;

	public DrawableGranade[] granate;

	public Player(Point pPosition) {
		super(Player.MAX_SALUTE, pPosition);

		this.granate = new DrawableGranade[Player.MAX_NUM_GRANATE];
	}

	public void caricoGranate() {
		for (int i = 0; i < Player.MAX_NUM_GRANATE; i++) {
			this.granate[i].isAttiva = true;
		}
	}

	public void lancioGranata() {
		//TO DO...
	}

	public void guarisci() {
		//TO DO...
	}
}
