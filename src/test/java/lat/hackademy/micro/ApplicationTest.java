package lat.hackademy.micro;


import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
 class ApplicationTest {

	boolean test=true;
	
	@Test
	void contextLoads() {
		assertTrue(test);
	}
	
}
