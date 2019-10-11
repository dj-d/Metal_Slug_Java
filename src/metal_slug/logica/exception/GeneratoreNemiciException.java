package metal_slug.logica.exception;

public class GeneratoreNemiciException extends Exception {
	public GeneratoreNemiciException(String str) {
		super(str);
	}

	public GeneratoreNemiciException() {
		super("Errore nel tipo di nemico");
	}
}
