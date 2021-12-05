package lat.hackademy.micro.exceptions;

public class ModelExampleNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public ModelExampleNotFoundException(Long id) {
			super("Example with id " + id + " was not found.");
	}
}
	