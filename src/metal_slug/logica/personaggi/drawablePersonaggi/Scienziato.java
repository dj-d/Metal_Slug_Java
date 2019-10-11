package metal_slug.logica.personaggi.drawablePersonaggi;

import java.awt.Image;
import java.awt.Point;

import metal_slug.logica.personaggi.Nemico;
import metal_slug.utils.Resource;

public class Scienziato extends Nemico {
	public static final int SALUTE = 10;

	public static final int LEFT = -15;

	public Image imgVivo;
	public Image imgMorto;

	public Scienziato(Point pPosition) {
		super(Scienziato.SALUTE, pPosition);

		this.imgVivo = Resource.getImage("/metal_slug/media/images/Nemici/Scienziato/ScienziatoVivo.png");
		this.imgMorto = Resource.getImage("/metal_slug/media/images/Nemici/Scienziato/ScienziatoMorto.png");
	}

	@Override
	public void colpito() {
		this.setIsActive(Scienziato.MORTO);
	}
}
