package lat.hackademy.micro.services;

import javax.validation.Valid;

import lat.hackademy.micro.exceptions.SameEmailException;
import lat.hackademy.micro.exceptions.SameUsernameException;
import lat.hackademy.micro.payloads.request.LoginRequest;
import lat.hackademy.micro.payloads.request.SignupRequest;
import lat.hackademy.micro.payloads.request.TokenRefreshRequest;
import lat.hackademy.micro.payloads.response.JwtResponse;
import lat.hackademy.micro.payloads.response.SignupResponse;
import lat.hackademy.micro.payloads.response.TokenRefreshResponse;

public interface AuthService {

	public JwtResponse authenticateUser(LoginRequest loginRequest);

	public TokenRefreshResponse refreshtoken(@Valid TokenRefreshRequest request);

	public SignupResponse registerUser(@Valid SignupRequest signUpRequest)
			throws SameUsernameException, SameEmailException;

}
