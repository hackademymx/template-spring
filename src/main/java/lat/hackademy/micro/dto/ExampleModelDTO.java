package lat.hackademy.micro.dto;

public class ExampleModelDTO {

	private Long id;
	private String exampleField1;
	private String exampleField2;

	protected ExampleModelDTO() {
	}

	public ExampleModelDTO(Long id, String exampleField1, String exampleField2) {
		this.id = id;
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
		return String.format("ExampleModelDTO[id=%d, exampleField1='%s', exampleField2='%s']", id, exampleField1,
				exampleField2);
	}
	
}
