package logic.characters.logic;

import logic.characters.Character;
import logic.weapons.drawable.Grenade;

import java.awt.*;

public class Player extends Character {
	public static final int MAX_HEALTH = 100;
	public static final int MAX_NUM_GRENADE = 10;
	public static final int MAX_LIVES = 3;

	public int live;
	public Grenade[] grenades;

	public Player(Point pPosition) {
		super(Player.MAX_HEALTH, pPosition);

		this.grenades = new Grenade[Player.MAX_NUM_GRENADE];
	}

	public void loadGrenades() {
		for (int i = 0; i < Player.MAX_NUM_GRENADE; i++) {
			this.grenades[i].isEnable = true;
		}
	}
}
