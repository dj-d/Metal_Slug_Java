package metal_slug.utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import metal_slug.logica.exception.SoundException;

/**
 * LA CLASSE MODELLA, RIPRODUCE E GESTISCE UN SUONO IN FORMATO WAVE
 */
public class Sound
  {
    private static final Line.Info INFO = new Line.Info(Clip.class);
    private URL soundUrl;
    private Clip readyClip;

    /**
     * CREA UN NUOVO SUONO A PARTIRE DAL PATH ASSOLUTO DI UN FILE WAVE
     *
     * @param path Il path assoluto di un file WAVE
     * @throws SoundException Se il path non risulta puntare ad un corretto file
     * WAVE
     * @throws MalformedURLException
     */
    public Sound(String path) throws SoundException, MalformedURLException, IOException
      {
        this(new URL(path));
      }

    /**
     * CREA e RESTITUISCE UN NUOV Clip DI UN FILE WAVE, PRONTO PER ESSERE
     * RIPRODOTTO
     *
     * @return Un oggetto di topo Clip che permette di gestire il suono
     * @throws SoundException Se non è possibile ottenere un Clip dal suono
     */
    public final Clip getNewClip() throws SoundException
      {
        try
          {
            if (this.readyClip == null)
              {
                this.readyClip = Sound.getNewClip(this.soundUrl);
              }

            Clip c = this.readyClip;
            this.readyClip = Sound.getNewClip(this.soundUrl);

            return c;
          }
        catch (SoundException ex)
          {
            this.readyClip = null;
            throw ex;
          }
      }

    /**
     * CREA e RESTITUISCE UN NUOVO Clip DI UN FILE WAVE, PRONTO PER ESSERE
     * RIPORDOTTO
     *
     * @param clipURL Il file WAVE dal quale ottenere un Clip
     * @return Un oggetto di tipo Clip che permette di gestire il suono
     * @throws SoundException Se non è possibile ottenere un Clip dal file
     */
    public static Clip getNewClip(URL clipURL) throws SoundException, RuntimeException
      {
        Clip clip = null;

        try
          {
            clip = (Clip) AudioSystem.getLine(INFO);
            clip.open(AudioSystem.getAudioInputStream(clipURL));
          }
        catch (IOException | UnsupportedAudioFileException ex)
          {
            throw new SoundException(clipURL.getFile(), ex);
          }
        catch (LineUnavailableException ex)
          {
            throw new RuntimeException(ex);
          }

        return clip;
      }

    /**
     * CREA UN NUOVO SUONO A PARTIRE DA UN FILE WAVE
     *
     * @param url Il file WAVE
     * @throws SoundException Se il file non risulta essere un corretto file
     * WAVE
     * @throws IOException
     */
    public Sound(URL url) throws SoundException, IOException
      {
        if (url == null)
          {
            throw new SoundException("Cannot read " + url.getPath());
          }

        this.soundUrl = url;
        this.readyClip = this.getNewClip();
      }

    /**
     * RIPRODUCE n VOLTE IL SUONO
     *
     * @param times Il numero di volte da riprodurre il suono (un numero
     * negativo provoca un loop)
     * @return Un oggetto di tipo Clip che permette di gestire il suono
     */
    public Clip play(int times)
      {
        Clip clip = null;

        try
          {
            clip = getNewClip();
          }
        catch (SoundException ex)
          {
            throw new RuntimeException(ex);
          }

        if ((clip != null) && (times != 0))
          {
            clip.loop(times - 1);
          }

        return clip;
      }

    /**
     * RIPRODUCE UNA VOLTA IL SUONO
     *
     * @return Un oggetto di tipo Clip che permette di gestire il suono
     */
    public Clip play()
      {
        return play(1);
      }

    /**
     * RIPRODUCE ALL'INFINITO IL SUONO
     *
     * @return Un oggetto di tipo Clip che permette di gestire il suono
     */
    public Clip loop()
      {
        return play(-1);
      }
  }
