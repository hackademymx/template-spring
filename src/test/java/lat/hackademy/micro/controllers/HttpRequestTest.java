package lat.hackademy.micro.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class HttpRequestTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void ShouldReturnDefaultMessageWithURL() throws Exception {
		String response = this.restTemplate.getForObject("http://localhost:" + port + "/hello", String.class);
		assertThat(response).isNotNull().isNotEmpty().contains("Hello, World!!! up and ready");
	}

	@Test
	void ShouldReturnDefaultMessageAbstract() throws Exception {
		ResponseEntity<String> response = restTemplate.getForEntity("/hello", String.class);
		assertThat(response.getBody()).isEqualTo("Hello, World!!! up and ready");
	}

}
