package lat.hackademy.micro.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import lat.hackademy.micro.utils.Utils;

class ExampleModelTest {

	ExampleModel exampleModel;

	@BeforeEach
	void setup() {
		exampleModel = new ExampleModel();
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

		exampleModel = new ExampleModel(exampleField1, exampleField2);
		assertNotNull(exampleModel);

		assertNull(exampleModel.getId());

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
		ExampleModel newExampleModel = new ExampleModel();
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
		assertEquals(exampleModel.getExampleField1(), randomString);
	}

	@Test
	@Tag("GetterAndSetter")
	@DisplayName("ExampleField2Methods")
	void checkSetExampleField2() {
		String randomString = Utils.GeneratingRandomString(15);
		assertNull(exampleModel.getExampleField2());

		exampleModel = new ExampleModel(Utils.GeneratingRandomString(15), randomString);
		assertNotNull(exampleModel.getExampleField2());
		assertEquals(exampleModel.getExampleField2(), randomString);
	}

	@Test
	@Tag("GetterAndSetter")
	@DisplayName("ExampleField3Methods")
	void checkSetExampleField3() {
		String randomString1 = Utils.GeneratingRandomString(15);
		String randomString2 = Utils.GeneratingRandomString(15);
		assertNotNull(exampleModel.getExampleField3());
		assertEquals("nullnull", exampleModel.getExampleField3());

		exampleModel = new ExampleModel(randomString1, randomString2);
		assertNotNull(exampleModel.getExampleField3());
		assertEquals(exampleModel.getExampleField3(), randomString1+randomString2);
	}
	
	@Test
	@Tag("GetterAndSetter")
	@DisplayName("IdMethods")
	void checkGetId() {
		assertNull(exampleModel.getId());
	}

}
