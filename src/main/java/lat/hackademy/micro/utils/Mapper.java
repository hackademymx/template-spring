package lat.hackademy.micro.utils;

import org.springframework.stereotype.Component;

import lat.hackademy.micro.dto.ExampleModelDTO;
import lat.hackademy.micro.models.ExampleModel;

@Component
public class Mapper {

	public ExampleModelDTO toDto(ExampleModel exampleModel) {
		Long id = exampleModel.getId();
        String exampleField1 = exampleModel.getExampleField1();
        String exampleField2 = exampleModel.getExampleField2();

        return new ExampleModelDTO(id, exampleField1, exampleField2);
    }

    public ExampleModel toModel(ExampleModelDTO exampleModelDTO) {
        return new ExampleModel(exampleModelDTO.getExampleField1(), exampleModelDTO.getExampleField2());
    }
}
