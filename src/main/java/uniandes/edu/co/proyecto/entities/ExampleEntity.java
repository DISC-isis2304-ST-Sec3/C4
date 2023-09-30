package uniandes.edu.co.proyecto.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "examples")
public class ExampleEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String someValue;

  public ExampleEntity() {
  }

  public ExampleEntity(String someValue) {
    this.someValue = someValue;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getSomeValue() {
    return someValue;
  }

  public void setSomeValue(String some_value) {
    this.someValue = some_value;
  }
}
