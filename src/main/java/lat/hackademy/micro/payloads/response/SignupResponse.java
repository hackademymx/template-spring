package lat.hackademy.micro.payloads.response;


public class SignupResponse {

	private Long id;
	private String username;
	private String email;

	public SignupResponse() {
	}

	public SignupResponse(Long id, String username, String email ) {
		this.username = username;
		this.email = email;
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return id;
	}
}
