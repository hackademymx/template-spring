package lat.hackademy.micro.services;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import lat.hackademy.micro.exceptions.SameEmailException;
import lat.hackademy.micro.exceptions.SameUsernameException;
import lat.hackademy.micro.exceptions.TokenRefreshException;
import lat.hackademy.micro.models.RefreshToken;
import lat.hackademy.micro.models.User;
import lat.hackademy.micro.payloads.request.LoginRequest;
import lat.hackademy.micro.payloads.request.SignupRequest;
import lat.hackademy.micro.payloads.request.TokenRefreshRequest;
import lat.hackademy.micro.payloads.response.JwtResponse;
import lat.hackademy.micro.payloads.response.SignupResponse;
import lat.hackademy.micro.payloads.response.TokenRefreshResponse;
import lat.hackademy.micro.repositories.UserRepository;
import lat.hackademy.micro.security.JwtUtils;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	RefreshTokenService refreshTokenService;

	@Override
	public JwtResponse authenticateUser(LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		String jwt = jwtUtils.generateJwtToken(userDetails);

		RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

		return new JwtResponse(jwt, refreshToken.getToken(), userDetails.getId(), userDetails.getEmail());
	}

	@Override
	public TokenRefreshResponse refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
		String requestRefreshToken = request.getRefreshToken();

		return refreshTokenService.findByToken(requestRefreshToken).map(refreshTokenService::verifyExpiration)
				.map(RefreshToken::getUser).map(user -> {
					String token = jwtUtils.generateTokenFromUsername(user.getUsername());
					return new TokenRefreshResponse(token, requestRefreshToken);
				})
				.orElseThrow(() -> new TokenRefreshException(requestRefreshToken, "Refresh token is not in database!"));
	}

	@Override
	public SignupResponse registerUser(@Valid @RequestBody SignupRequest signUpRequest)
			throws SameUsernameException, SameEmailException {
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			throw new SameEmailException();
		}
		// Create new user's account
		User user = new User(signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));

		userRepository.save(user);
		return new SignupResponse(user.getId(), user.getUsername(), user.getEmail());
	}

}
