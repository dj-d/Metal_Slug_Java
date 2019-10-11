package metal_slug.logica.personaggi.drawablePersonaggi;

import java.awt.Image;
import java.awt.Point;

import metal_slug.logica.personaggi.Nemico;
import metal_slug.utils.Resource;

public class Zombie extends Nemico {
	public static final int SALUTE = 15;

	public static final int RIGHT = 15;
	public static final int LEFT = -15;

	public Image imgVivo;
	public Image imgMorto;

	public Zombie(Point pPosition) {
		super(Zombie.SALUTE, pPosition);

		this.imgVivo = Resource.getImage("/metal_slug/media/images/Nemici/Robot/RobotVivo.png");
		this.imgMorto = Resource.getImage("/metal_slug/media/images/Nemici/Robot/RobotMorto.png");
	}

	@Override
	public void colpito() {
		this.setIsActive(Zombie.MORTO);
	}
}
