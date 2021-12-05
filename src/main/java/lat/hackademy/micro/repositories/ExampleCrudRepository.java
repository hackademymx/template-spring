package lat.hackademy.micro.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lat.hackademy.micro.models.ExampleModel;

@Repository
public interface ExampleCrudRepository extends JpaRepository<ExampleModel, Long> {
		List<ExampleModel> findByExampleField1(String string);
}
