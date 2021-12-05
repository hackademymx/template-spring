package lat.hackademy.micro.swagger;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.web.server.LocalManagementPort;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = { "springdoc.api-docs.path=/openapi" })
class SpringDocsIntegrationTest {

	protected static final Logger LOGGER = LoggerFactory.getLogger(SpringDocsIntegrationTest.class);

	@Autowired
	protected MockMvc mockMvc;

	@LocalManagementPort
	private int managementPort;

	protected RestTemplate actuatorRestTemplate;

	@Test
	void testApp() throws Exception {

		mockMvc.perform(get("/openapi")).andExpect(status().isOk());
		assertNotNull(mockMvc.perform(get("/openapi")).andExpect(status().isOk())
				.andExpect(jsonPath("$.openapi", is("3.0.1"))).andReturn());
	}

	@Test
	void shouldDisplaySwaggerUiPage() throws Exception {

		mockMvc.perform(get("/swagger-ui/index.html")).andExpect(status().isOk())
				.andExpect(content().string(containsString("Swagger UI")));

	}

	@SpringBootApplication
	static class SpringDocTestApp {
	}

}
