package lat.hackademy.micro.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import lat.hackademy.micro.utils.Utils;

class ExampleModelDTOTest {

	ExampleModelDTO exampleModel;

	@BeforeEach
	void setup() {
		exampleModel = new ExampleModelDTO();
	}

	@Test
	@Tag("creation")
	@DisplayName("SourceModel()")
	void checkCreation() {
		assertNotNull(exampleModel);
		assertNull(exampleModel.getId());
		assertNull(exampleModel.getExampleField1());
		assertNull(exampleModel.getExampleField2());
	}

	@Test
	@Tag("creation")
	@DisplayName("ExampleModelTest(value, value)")
	void checkCreationWithValue() {
		String exampleField1 = Utils.GeneratingRandomString(15);
		String exampleField2 = Utils.GeneratingRandomString(15);

		exampleModel = new ExampleModelDTO(1L, exampleField1, exampleField2);
		assertNotNull(exampleModel);

		assertNotNull(exampleModel.getId());
		assertEquals(1L, exampleModel.getId());

		assertNotNull(exampleModel.getExampleField1());
		assertEquals(exampleModel.getExampleField1(), exampleField1);

		assertNotNull(exampleModel.getExampleField2());
		assertEquals(exampleModel.getExampleField2(), exampleField2);
	}

	@Test
	@Tag("creation")
	@DisplayName("NoSingleton")
	void checkNoSingleton() {
		assertNotNull(exampleModel);
		ExampleModelDTO newExampleModel = new ExampleModelDTO();
		assertNotNull(newExampleModel);
		assertNotSame(exampleModel, newExampleModel);
	}

	@Test
	@Tag("GetterAndSetter")
	@DisplayName("ExampleField1Methods")
	void checkSetExampleField1() {
		String randomString = Utils.GeneratingRandomString(15);
		assertNull(exampleModel.getExampleField1());

		exampleModel.setExampleField1(randomString);
		assertNotNull(exampleModel.getExampleField1());
		assertEquals(randomString, exampleModel.getExampleField1() );
	}

	@Test
	@Tag("GetterAndSetter")
	@DisplayName("ExampleField2Methods")
	void checkSetExampleField2() {
		String randomString = Utils.GeneratingRandomString(15);
		assertNull(exampleModel.getExampleField2());

		exampleModel = new ExampleModelDTO(Utils.GeneratingRandomLong(), Utils.GeneratingRandomString(15),
				randomString);
		assertNotNull(exampleModel.getExampleField2());
		assertEquals(randomString, exampleModel.getExampleField2() );
	}

	@Test
	@Tag("GetterAndSetter")
	@DisplayName("ExampleField3Methods")
	void checkSetExampleField3() {
		String randomString1 = Utils.GeneratingRandomString(15);
		String randomString2 = Utils.GeneratingRandomString(15);
		assertNotNull(exampleModel.getExampleField3());
		assertEquals("nullnull", exampleModel.getExampleField3() );

		exampleModel = new ExampleModelDTO(Utils.GeneratingRandomLong(), randomString1, randomString2);
		assertNotNull(exampleModel.getExampleField3());
		assertEquals(randomString1 + randomString2, exampleModel.getExampleField3());
	}

	@Test
	@Tag("GetterAndSetter")
	@DisplayName("IdMethods")
	void checkGetId() {
		Long randomLong = Utils.GeneratingRandomLong();
		assertNull(exampleModel.getId());

		exampleModel = new ExampleModelDTO(randomLong, Utils.GeneratingRandomString(15),
				Utils.GeneratingRandomString(15));
		assertNotNull(exampleModel.getId());
		assertEquals(randomLong, exampleModel.getId() );

	}
}
