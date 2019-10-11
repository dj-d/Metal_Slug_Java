package metal_slug.logica.personaggi.drawablePersonaggi;

import java.awt.Image;
import java.awt.Point;

import metal_slug.logica.personaggi.Nemico;
import metal_slug.utils.Resource;

public class Elicottero extends Nemico {
	public static final int SALUTE = 30;

	public static final int RIGHT = 15;
	public static final int LEFT = -15;

	public Image imgVivo;
	public Image imgMorto;

	public Elicottero(Point pPosition) {
		super(Elicottero.SALUTE, pPosition);

		this.imgVivo = Resource.getImage("/metal_slug/media/images/Nemici/Elicottero/ElicotteroVivo.png");
		this.imgMorto = Resource.getImage("/metal_slug/media/images/Nemici/Elicottero/ElicotteroMorto.png");
	}

	@Override
	public void colpito() {
		this.setIsActive(Elicottero.MORTO);
	}
}
