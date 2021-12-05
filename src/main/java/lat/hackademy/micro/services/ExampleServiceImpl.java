package lat.hackademy.micro.services;

import java.util.List;

import org.springframework.stereotype.Service;

import lat.hackademy.micro.exceptions.ModelExampleNotFoundException;
import lat.hackademy.micro.models.ExampleModel;
import lat.hackademy.micro.repositories.ExampleCrudRepository;

@Service
public class ExampleServiceImpl implements ExampleService {

	private ExampleCrudRepository repository;

	@Override
	public String example() {
		return "Hello, World";
	}

	@Override
	public List<ExampleModel> findAll() {
		return repository.findAll();
	}

	@Override
	public ExampleModel save(ExampleModel newModelExample) {
		return repository.save(newModelExample);
	}

	@Override
	public ExampleModel findById(Long id) throws ModelExampleNotFoundException {
		return repository.findById(id).orElseThrow(() -> new ModelExampleNotFoundException(id));
	}

	@Override
	public ExampleModel updateOrSave(ExampleModel newExampleModel, Long id) {
		return repository.findById(id).map(exampleModel -> {
			exampleModel = new ExampleModel(newExampleModel.getExampleField1(), newExampleModel.getExampleField2());
			return repository.save(exampleModel);
		}).orElseGet(() -> repository.save(newExampleModel));
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
}
