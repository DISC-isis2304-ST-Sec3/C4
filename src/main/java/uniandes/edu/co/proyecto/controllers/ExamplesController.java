package uniandes.edu.co.proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uniandes.edu.co.proyecto.entities.ExampleEntity;
import uniandes.edu.co.proyecto.repositories.ExampleRepository;

import java.util.Collection;

@RestController
@RequestMapping("/examples")
public class ExamplesController {
  @Autowired
  private ExampleRepository exampleRepository;

  @GetMapping
  public Collection<ExampleEntity> getExamples() {
    return exampleRepository.getExamples();
  }
}
