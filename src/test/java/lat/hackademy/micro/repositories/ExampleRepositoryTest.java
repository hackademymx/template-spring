package lat.hackademy.micro.repositories;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.StreamSupport;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import lat.hackademy.micro.models.ExampleModel;

@DataJpaTest
class ExampleRepositoryTest {

	@Autowired
	private ExampleCrudRepository repository;

	@BeforeEach
	void SetUp() {
		repository.save(new ExampleModel("Jack", "Bauer"));
		repository.save(new ExampleModel("Chloe", "O'Brian"));
		repository.save(new ExampleModel("Kim", "Bauer"));
		repository.save(new ExampleModel("David", "Palmer"));
		repository.save(new ExampleModel("Michelle", "Dessler"));
	}

	@AfterEach
	void tearDown() {
		repository.deleteAll();
	}

	@Test
	void testAllData() {
		// fetch all exampleModel values
		System.out.println("Customers found with findAll():");
		Iterable<ExampleModel> testData = repository.findAll();

		assertNotNull(testData);
		assertEquals(5, StreamSupport.stream(testData.spliterator(), false).count());

	}

	@Test
	void testRetriveById() {
		// fetch an individual exampleModel by ID
		Iterable<ExampleModel> testData = repository.findAll();

		ExampleModel exampleTest = testData.iterator().next();

		Optional<ExampleModel> optionalExampleModel = repository.findById(exampleTest.getId());
		assertNotNull(optionalExampleModel);
		assertFalse(optionalExampleModel.isEmpty());

		ExampleModel exampleModel = optionalExampleModel.get();

		assertEquals(exampleTest.getExampleField1(), exampleModel.getExampleField1());
		assertEquals(exampleTest.getExampleField2(), exampleModel.getExampleField2());
		
	}

	@Test
	void testRetrieveByField() {
		// fetch customers by last name
		Predicate<String> isNull = s -> s == null;
		Predicate<String> isEmpty = s -> s.isEmpty();
		Predicate<String> predicate = s -> s.equals("Jack");
		Predicate<String> p = isNull.negate().and(isEmpty.negate()).and(predicate);
		repository.findByExampleField1("Jack").forEach(s -> assertTrue(p.test(s.getExampleField1())));
	}
}
