package lat.hackademy.micro.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ExceptionDetails {
	private HttpStatus httpStatus;
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private LocalDateTime timestamp;
	private String message;
	
	public ExceptionDetails(HttpStatus httpStatus, LocalDateTime timestamp, String message) {
		this.httpStatus = httpStatus;
		this.timestamp = timestamp;
		this.message = message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
