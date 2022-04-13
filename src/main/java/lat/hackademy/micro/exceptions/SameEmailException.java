package lat.hackademy.micro.exceptions;

public class SameEmailException extends Exception {

	private static final long serialVersionUID = 1L;

	public SameEmailException() {
			super("Error: Email is already in use!");
	}
}
	