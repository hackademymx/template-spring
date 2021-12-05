package lat.hackademy.micro.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import lat.hackademy.micro.exceptions.ModelExampleNotFoundException;
import lat.hackademy.micro.models.ExampleModel;
import lat.hackademy.micro.repositories.ExampleCrudRepository;
import lat.hackademy.micro.utils.Utils;

@SpringBootTest
class ExampleServiceImplTest {

	@Mock
	private ExampleCrudRepository repository;

	@InjectMocks
	private ExampleService exampleService = new ExampleServiceImpl();

	private ExampleModel exampleModel1;
	private ExampleModel exampleModel2;
	private List<ExampleModel> listReturn;

	@BeforeEach
	public void setUp() {
		exampleModel1 = new ExampleModel(Utils.GeneratingRandomString(10), Utils.GeneratingRandomString(10));
		exampleModel2 = new ExampleModel(Utils.GeneratingRandomString(10), Utils.GeneratingRandomString(10));
		listReturn = new ArrayList<ExampleModel>();
		listReturn.add(exampleModel1);
		listReturn.add(exampleModel2);

		List<ExampleModel> listReturnOne = new ArrayList<>();
		
		Mockito.when(repository.findByExampleField1(exampleModel1.getExampleField1())).thenReturn(listReturnOne);
		Mockito.when(repository.findAll()).thenReturn(listReturn);
		Mockito.when(repository.findById(1L)).thenReturn(Optional.ofNullable(exampleModel1));
		Mockito.when(repository.save(exampleModel1)).thenReturn(exampleModel1);
	}

	@AfterEach
	public void tearDown() {
		exampleModel1 = exampleModel2 = null;
		listReturn = null;
	}

	@Test
	@Tag("NoCRUD")
	@DisplayName("Test example method")
	void testExample() {
		String value = exampleService.example();
		assertNotNull(value);
		assertFalse(value.isEmpty());
		assertEquals("Hello, World", value);
	}

	@Test
	@Tag("CRUD")
	@DisplayName("Test findAll method")
	void testFindAll() {
		List<ExampleModel> listaExample = exampleService.findAll();
		assertNotNull(listaExample);
		assertEquals(2, listaExample.size());
	}

	@Test
	@Tag("CRUD")
	@DisplayName("Test save method")
	void testSave(){
		ExampleModel exampleModel = exampleService.save(exampleModel1);
		assertNotNull(exampleModel);
		
		assertEquals(exampleModel1, exampleModel);
		
	}

	@Test
	@Tag("CRUD")
	@DisplayName("Test findById method no failing")
	void testFindById() throws ModelExampleNotFoundException{
		ExampleModel exampleModel = exampleService.findById(1L);
		assertNotNull(exampleModel);
		assertEquals(exampleModel1, exampleModel);
	}

	@Test
	@Tag("CRUD")
	@DisplayName("Test findById method failing")
	void testFailFindById(){
		assertThrows(ModelExampleNotFoundException.class,() -> { exampleService.findById(3L);});
	}
	
	@Test
	@Tag("CRUD")
	@DisplayName("Test updateOrSave method")
	void testUpdateOrSave(){
		ExampleModel exampleModel = exampleService.updateOrSave(exampleModel1, 1L);
		assertNotNull(exampleModel);
		
		assertEquals(exampleModel1, exampleModel);
	}

	@Test
	@Tag("CRUD")
	@DisplayName("Test deleteById method")
	void testDeleteById() {
		exampleService.deleteById(1L);
		assertTrue(true);
	}

}
