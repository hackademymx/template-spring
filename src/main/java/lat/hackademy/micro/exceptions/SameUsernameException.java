package lat.hackademy.micro.exceptions;

public class SameUsernameException extends Exception {

	private static final long serialVersionUID = 1L;

	public SameUsernameException() {
			super("Error: Username is already taken!");
	}
}
	