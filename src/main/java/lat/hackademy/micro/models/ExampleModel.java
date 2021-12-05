package lat.hackademy.micro.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

//Modelo de una entidad con validadores.
@Entity
public class ExampleModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
    @Size(min = 0, max = 20)
	private String exampleField1;
	
	@NotBlank
    @Size(min = 0, max = 40)
	private String exampleField2;

	protected ExampleModel() {
	}

	public ExampleModel(String exampleField1, String exampleField2) {
		this.exampleField1 = exampleField1;
		this.exampleField2 = exampleField2;
	}

	public Long getId() {
		return id;
	}

	public String getExampleField1() {
		return exampleField1;
	}
	
	public void setExampleField1(String exampleField1) {
		this.exampleField1 = exampleField1;
	}
	
	public String getExampleField2() {
		return exampleField2;
	}

	public String getExampleField3() {
		return exampleField1+exampleField2;
	}
	
	@Override
	public String toString() {
		return String.format("ExampleModel[id=%d, exampleField1='%s', exampleField2='%s']", id, exampleField1,
				exampleField2);
	}

}
