package lat.hackademy.micro.exceptions;

import java.time.LocalDateTime;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	// Añadir un formato para el manejo de excepciones de forma global.
	@ExceptionHandler(ConversionFailedException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public final ResponseEntity<ExceptionDetails> handleInvalidConversionException(RuntimeException ex) {
		return new ResponseEntity<>(new ExceptionDetails(HttpStatus.BAD_REQUEST, LocalDateTime.now(), ex.getMessage()),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(SameUsernameException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public final ResponseEntity<ExceptionDetails> handleSameUsernameException(RuntimeException ex) {
		return new ResponseEntity<>(new ExceptionDetails(HttpStatus.BAD_REQUEST, LocalDateTime.now(), ex.getMessage()),
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(SameEmailException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public final ResponseEntity<ExceptionDetails> handleSameEmailException(RuntimeException ex) {
		return new ResponseEntity<>(new ExceptionDetails(HttpStatus.BAD_REQUEST, LocalDateTime.now(), ex.getMessage()),
				HttpStatus.BAD_REQUEST);
	}
	
	// Manejo de una excepción creada.
	@ExceptionHandler(ModelExampleNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public final ResponseEntity<ExceptionDetails> handleModelExampleNotFoundException(RuntimeException ex) {
		return new ResponseEntity<>(new ExceptionDetails(HttpStatus.NOT_FOUND, LocalDateTime.now(), ex.getMessage()),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = TokenRefreshException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public final ResponseEntity<ExceptionDetails> handleTokenRefreshException(TokenRefreshException ex, WebRequest request) {
		return new ResponseEntity<>(new ExceptionDetails(HttpStatus.FORBIDDEN, LocalDateTime.now(), ex.getMessage()),
				HttpStatus.FORBIDDEN);
	}
}