package metal_slug.logica;

import java.awt.Image;

import metal_slug.utils.Resource;

public class Statistics {
	private static final int NUM_IMAGE_COUNTDOWN = 71;
	public Image[] imgCountdown;

	private static final int NUM_GRANATE = 11;
	public Image[] imgNumGrenade;

	public Image riquadro;

	public static final int NUM_MAX_VITE = 3;
	public int numVite;
	public Image[] imgVite;

	private boolean countdownStart;

	public Statistics() {
		this.riquadro = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Armi + vita2.png");

		this.imgCountdown = new Image[Statistics.NUM_IMAGE_COUNTDOWN];
		this.loadImageCountdown();

		this.imgNumGrenade = new Image[Statistics.NUM_GRANATE];
		this.loadImageNumGranate();

		this.imgVite = new Image[Statistics.NUM_MAX_VITE];
		this.loadImageVite();
		this.numVite = Statistics.NUM_MAX_VITE;

		this.countdownStart = false;
	}

	private void loadImageCountdown() {
		this.imgCountdown[0] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/60.png");
		this.imgCountdown[1] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/59.png");
		this.imgCountdown[2] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/58.png");
		this.imgCountdown[3] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/57.png");
		this.imgCountdown[4] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/56.png");
		this.imgCountdown[5] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/55.png");
		this.imgCountdown[6] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/54.png");
		this.imgCountdown[7] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/53.png");
		this.imgCountdown[8] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/52.png");
		this.imgCountdown[9] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/51.png");
		this.imgCountdown[10] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/50.png");
		this.imgCountdown[11] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/49.png");
		this.imgCountdown[12] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/48.png");
		this.imgCountdown[13] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/47.png");
		this.imgCountdown[14] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/46.png");
		this.imgCountdown[15] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/45.png");
		this.imgCountdown[16] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/44.png");
		this.imgCountdown[17] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/43.png");
		this.imgCountdown[18] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/42.png");
		this.imgCountdown[19] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/41.png");
		this.imgCountdown[20] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/40.png");
		this.imgCountdown[21] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/39.png");
		this.imgCountdown[22] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/38.png");
		this.imgCountdown[23] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/37.png");
		this.imgCountdown[24] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/36.png");
		this.imgCountdown[25] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/35.png");
		this.imgCountdown[26] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/34.png");
		this.imgCountdown[27] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/33.png");
		this.imgCountdown[28] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/32.png");
		this.imgCountdown[29] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/31.png");
		this.imgCountdown[30] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/30.png");
		this.imgCountdown[41] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/29.png");
		this.imgCountdown[42] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/28.png");
		this.imgCountdown[43] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/27.png");
		this.imgCountdown[44] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/26.png");
		this.imgCountdown[45] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/25.png");
		this.imgCountdown[46] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/24.png");
		this.imgCountdown[47] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/23.png");
		this.imgCountdown[48] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/22.png");
		this.imgCountdown[49] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/21.png");
		this.imgCountdown[50] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/20.png");
		this.imgCountdown[51] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/19.png");
		this.imgCountdown[52] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/18.png");
		this.imgCountdown[53] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/17.png");
		this.imgCountdown[54] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/16.png");
		this.imgCountdown[55] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/15.png");
		this.imgCountdown[56] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/14.png");
		this.imgCountdown[57] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/13.png");
		this.imgCountdown[58] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/12.png");
		this.imgCountdown[59] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/11.png");
		this.imgCountdown[60] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/10.png");
		this.imgCountdown[61] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/9.png");
		this.imgCountdown[62] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/8.png");
		this.imgCountdown[63] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/7.png");
		this.imgCountdown[64] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/6.png");
		this.imgCountdown[65] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/5.png");
		this.imgCountdown[66] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/4.png");
		this.imgCountdown[67] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/3.png");
		this.imgCountdown[68] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/2.png");
		this.imgCountdown[69] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/1.png");
		this.imgCountdown[70] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Timer/0.png");
	}

	public int getNumImageConuntdown() {
		return imgCountdown.length;
	}

	private void loadImageNumGranate() {
		this.imgNumGrenade[0] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Numero_granate/0.png");
		this.imgNumGrenade[1] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Numero_granate/1.png");
		this.imgNumGrenade[2] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Numero_granate/2.png");
		this.imgNumGrenade[3] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Numero_granate/3.png");
		this.imgNumGrenade[4] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Numero_granate/4.png");
		this.imgNumGrenade[5] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Numero_granate/5.png");
		this.imgNumGrenade[6] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Numero_granate/6.png");
		this.imgNumGrenade[7] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Numero_granate/7.png");
		this.imgNumGrenade[8] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Numero_granate/8.png");
		this.imgNumGrenade[9] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Numero_granate/9.png");
		this.imgNumGrenade[10] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Numero_granate/10.png");
	}

	private void loadImageVite() {
		for (int i = 0; i < this.getNumImgVite(); i++) {
			imgVite[i] = Resource.getImage("/metal_slug/media/images/Panels/GamePanel/Vita.png");
		}
	}

	public int getNumImgVite() {
		return imgVite.length;
	}

	public int getNumVite() {
		return numVite;
	}

	public void decrementoNumVite() {
		this.numVite--;
	}

	public boolean isCountdownStart() {
		return countdownStart;
	}

	public void setCountdownStart(boolean pGameStatus) {
		this.countdownStart = pGameStatus;
	}
}
