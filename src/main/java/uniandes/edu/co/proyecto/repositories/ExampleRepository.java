package uniandes.edu.co.proyecto.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uniandes.edu.co.proyecto.entities.ExampleEntity;

import java.util.Collection;

public interface ExampleRepository extends JpaRepository<ExampleEntity, Long> {

  @Query(value = "select * from examples", nativeQuery = true)
  Collection<ExampleEntity> getExamples();

  @Modifying
  @Transactional
  @Query(value = "insert into examples (id, some_value) values (examples_sequence.nextval, :someValue)", nativeQuery = true)
  void createExample(@Param("someValue") String someValue);
}
