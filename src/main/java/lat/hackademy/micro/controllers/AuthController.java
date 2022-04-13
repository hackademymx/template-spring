package lat.hackademy.micro.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lat.hackademy.micro.exceptions.SameEmailException;
import lat.hackademy.micro.exceptions.SameUsernameException;
import lat.hackademy.micro.payloads.request.LoginRequest;
import lat.hackademy.micro.payloads.request.SignupRequest;
import lat.hackademy.micro.payloads.request.TokenRefreshRequest;
import lat.hackademy.micro.payloads.response.JwtResponse;
import lat.hackademy.micro.payloads.response.SignupResponse;
import lat.hackademy.micro.payloads.response.TokenRefreshResponse;
import lat.hackademy.micro.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	AuthService authService;

	@PostMapping("/signin")
	public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		return ResponseEntity.ok(authService.authenticateUser(loginRequest));
	}

	@PostMapping("/refreshtoken")
	public ResponseEntity<TokenRefreshResponse> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {

		return ResponseEntity.ok(authService.refreshtoken(request));
	}

	@PostMapping("/signup")
	public ResponseEntity<SignupResponse> registerUser(@Valid @RequestBody SignupRequest signUpRequest)
			throws SameUsernameException, SameEmailException {

		return ResponseEntity.ok(authService.registerUser(signUpRequest));
	}
}
