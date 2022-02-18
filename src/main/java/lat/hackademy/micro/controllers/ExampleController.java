package lat.hackademy.micro.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lat.hackademy.micro.exceptions.ModelExampleNotFoundException;
import lat.hackademy.micro.models.ExampleModel;
import lat.hackademy.micro.dto.ExampleModelDTO;
import lat.hackademy.micro.services.ExampleService;
import lat.hackademy.micro.utils.Mapper;

@RestController
@RequestMapping("/example") //Path base
public class ExampleController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExampleController.class);

	@Autowired
	private ExampleService exampleService;
	private Mapper mapper;

	// Lo mismo se consigue con:
	// @RequestMapping(path = "/", method = RequestMethod.GET)
	@GetMapping("/")
	public List<ExampleModelDTO> all() {
		LOGGER.info("GET /");
		return exampleService.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
	}

	// Lo mismo se consigue con:
	// @RequestMapping(path = "/", method = RequestMethod.POST)
	@PostMapping("/")
	public ExampleModelDTO newModelExample(@RequestBody ExampleModelDTO newModelExample) {
		LOGGER.info("POST /");
		
		ExampleModel exampleModel = mapper.toModel(newModelExample);
		
		return mapper.toDto(exampleService.save(exampleModel));
	}

	//Ejemplo que añade más información al swagger
	@Operation(summary = "Get a exampleModel by its id")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Found the exampleModel", 
	    content = { @Content(mediaType = "application/json", 
	      schema = @Schema(implementation = ExampleModel.class)) }),
	  @ApiResponse(responseCode = "404", description = "Not found"),
	  })
	@GetMapping("/{id}")
	public ExampleModelDTO one(@PathVariable Long id) throws ModelExampleNotFoundException {
		LOGGER.info("GET /{}", id);
		try{
			return mapper.toDto(exampleService.findById(id));
		}catch(ModelExampleNotFoundException ex){
			throw new ModelExampleNotFoundException(id);
		}
	}

	@PutMapping("/{id}")
	public ExampleModelDTO replaceModelExample(@RequestBody ExampleModelDTO newModelExample, @PathVariable Long id) {
		LOGGER.info("PUT /{}", id);
		
		ExampleModel exampleModel = mapper.toModel(newModelExample);
		
		return mapper.toDto(exampleService.updateOrSave(exampleModel, id));
	}

	@DeleteMapping("/{id}")
	public void deleteModelExample(@PathVariable Long id) {
		LOGGER.info("DELETE /{}", id);
		exampleService.deleteById(id);
	}
}
