package utils;

import logic.exceptions.SoundException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Resource {
	static Class source = Resource.class;

	public Resource() {

	}

	/**
	 * IMPOSTA LA CLASSE DI RIFERIMENTO DALLA QUALE LEGGERE LE RISORSE
	 *
	 * @param source la classe di riferimento dalla quale leggere le risorse
	 */
	public static void setSourceClass(Class source) {
		Resource.source = source;
	}

	/**
	 * CHIAMAIL METODO getResource() SULLA CLASSE DI RIFERIMENTO
	 *
	 * @param resource Indirizzo parziale dell'immagine
	 * @return Indirizzo parziale dell'immagine
	 */
	public static URL getResource(String resource) {
		return Resource.source.getResource(resource);
	}

	/**
	 * RESTITUISCE UN IMMAGINE PRESENTE NELLE RISORSE DELL'APPLICAZIONE
	 *
	 * @param path Il path realativo dell'immagine
	 * @return L'immagine corrispondente al path
	 */
	public static BufferedImage getImage(String path) {
		BufferedImage image = null;

		try {
			image = ImageIO.read(getResource(path));
		} catch (IOException ex) {
			ex.printStackTrace(System.err);
			System.exit(-1);
		}

		return image;
	}

	/**
	 * DISEGNA UNA SINGOLA IMMAGINE
	 *
	 * @param g              Variabile Graphics
	 * @param pImage         L'immagine da disegnare
	 * @param pPosition      La posizione dell'immagine
	 * @param heightDecrease Decremento del punto di ancoraggio per immagini più
	 *                       basse
	 */
	public void drawImage(Graphics g, Image pImage, Point pPosition, int heightDecrease) {
		g.drawImage(pImage, pPosition.x, pPosition.y + heightDecrease, null);
	}

	/**
	 * DISEGNA UNA ARRAY DI IMMAGINI
	 *
	 * @param g              Variabile Graphics
	 * @param pImage         L'array da disegnare
	 * @param index          L'indice dell'array
	 * @param pPosition      La posizione dell'immagine
	 * @param heightIncrease Incremento del punto di ancoraggio per immagini più
	 *                       alte
	 */
	public void drawImageArray(Graphics g, Image[] pImage, int index, Point pPosition, int heightIncrease) {
		g.drawImage(pImage[index], pPosition.x, pPosition.y - heightIncrease, null);
	}

	/**
	 * RESTITUISCE UN SUONO WAVE PRESENTE NELLE RISORSE DELL'APPLICAZIONE
	 *
	 * @param path Il path relativo del suono
	 * @return Il suono corrispondente al path
	 * @throws IOException
	 */
	public static Sound getSound(String path) throws IOException {
		Sound sound = null;

		try {
			sound = new Sound(getResource(path));
		} catch (NullPointerException | SoundException | IllegalArgumentException ex) {
			ex.printStackTrace(System.err);
			System.exit(-1);
		}

		return sound;
	}
}