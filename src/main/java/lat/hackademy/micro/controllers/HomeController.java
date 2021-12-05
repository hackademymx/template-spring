package lat.hackademy.micro.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lat.hackademy.micro.services.ExampleService;

@RestController
@RequestMapping("/")
public class HomeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

	//La implementación del servicio puede ser por autowire
	//(Ver ejemplo de ExampleController)
	private final ExampleService service;

	public HomeController(ExampleService service) {
		this.service = service;
		LOGGER.info("HomeController ejecutado");
	}

	//Endpoint con respuesta sin servicio
	@GetMapping("hello")
	public String greeting() {
		LOGGER.info("greeting ejecutado");
		return "Hello, World!!! up and ready";
	}

	//Endpoint con respuesta con servicio
	@GetMapping("service")
	public String service() {
		LOGGER.info("service ejecutado");
		return service.example();
	}

}
