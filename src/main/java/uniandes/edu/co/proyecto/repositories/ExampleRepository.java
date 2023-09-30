package uniandes.edu.co.proyecto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uniandes.edu.co.proyecto.entities.ExampleEntity;

import java.util.Collection;

public interface ExampleRepository extends JpaRepository<ExampleEntity, Long> {

  @Query(value = "select * from examples", nativeQuery = true)
  public Collection<ExampleEntity> getExamples();
}
