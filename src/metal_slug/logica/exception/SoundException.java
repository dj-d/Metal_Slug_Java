package metal_slug.logica.exception;

public class SoundException extends Exception
  {
    public SoundException(String s)
      {
        super(s);
      }

    public SoundException(String fileName, Throwable cause)
      {
        super("Cannot read/ply " + fileName + ": " + cause.getMessage());
      }

    public SoundException(Throwable cause)
      {
        super(cause);
      }
  }
