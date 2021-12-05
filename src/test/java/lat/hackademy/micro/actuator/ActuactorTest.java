package lat.hackademy.micro.actuator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;


@AutoConfigureMockMvc
@SpringBootTest
@TestPropertySource(properties = {"management.port=","management.server.port="})
class ActuactorTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void greetingShouldReturnActuatorHealth() throws Exception {

		this.mockMvc.perform(get("/actuator/health").accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(equalTo("{\"status\":\"UP\"}")));
	}
	
    @SpringBootApplication
    static class SpringActuactorTestApp {
    }
}
