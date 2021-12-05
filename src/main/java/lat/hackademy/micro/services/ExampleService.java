package lat.hackademy.micro.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import lat.hackademy.micro.exceptions.ModelExampleNotFoundException;
import lat.hackademy.micro.models.ExampleModel;

@Transactional
public interface ExampleService {

	static final Logger LOGGER = LoggerFactory.getLogger(ExampleService.class);
	
	String example();

	@Transactional(readOnly = true)
	List<ExampleModel> findAll();

	ExampleModel save(ExampleModel newModelExample);

	@Transactional(readOnly = true)
	ExampleModel findById(Long id) throws ModelExampleNotFoundException;

	ExampleModel updateOrSave(ExampleModel newModelExample, Long id);

	void deleteById(Long id);
}
