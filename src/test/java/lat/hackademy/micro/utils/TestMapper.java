package lat.hackademy.micro.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.jupiter.api.Test;

import lat.hackademy.micro.dto.ExampleModelDTO;
import lat.hackademy.micro.models.ExampleModel;

class TestMapper {

	private Mapper mapper = new Mapper();

	@Test
	void ExampleModelEntityToExampleModelDTO() {
		String randomString1 = Utils.GeneratingRandomString(15);
		String randomString2 = Utils.GeneratingRandomString(15);
		ExampleModel exampleModel = new ExampleModel(randomString1, randomString2);

		ExampleModelDTO exampleModelDTO = mapper.toDto(exampleModel);
		assertEquals(exampleModel.getId(), exampleModelDTO.getId());
		assertEquals(exampleModel.getExampleField1(), exampleModelDTO.getExampleField1());
		assertEquals(exampleModel.getExampleField2(), exampleModelDTO.getExampleField2());
	}

	@Test
	void ExampleModelDTOToExampleModelEntity() {
		String randomString1 = Utils.GeneratingRandomString(15);
		String randomString2 = Utils.GeneratingRandomString(15);
		Long randomLong = Utils.GeneratingRandomLong();
		ExampleModelDTO exampleModelDTO = new ExampleModelDTO(randomLong, randomString1, randomString2);

		ExampleModel exampleModel = mapper.toModel(exampleModelDTO);
		assertNotEquals(exampleModelDTO.getId(), exampleModel.getId());
		assertEquals(exampleModelDTO.getExampleField1(), exampleModel.getExampleField1());
		assertEquals(exampleModelDTO.getExampleField2(), exampleModel.getExampleField2());
	}

}
