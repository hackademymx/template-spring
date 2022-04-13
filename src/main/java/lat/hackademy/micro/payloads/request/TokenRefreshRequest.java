package lat.hackademy.micro.payloads.request;

import javax.validation.constraints.NotBlank;

public class TokenRefreshRequest {

	@NotBlank
	private String refreshToken;

	public TokenRefreshRequest() {
	}

	public TokenRefreshRequest(@NotBlank String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	
	
}
