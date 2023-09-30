package uniandes.edu.co.proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

  @PostMapping
  public String createExample(@ModelAttribute ExampleEntity example) {
    exampleRepository.createExample(example.getSomeValue());
    return "redirect:index";
  }

  @PutMapping("/{id}")
  public String updateExample(@PathVariable(name = "id") Long id, @ModelAttribute ExampleEntity example) {
    exampleRepository.updateExample(id, example.getSomeValue());
    return "redirect:index";
  }

  @DeleteMapping("/{id}")
  public String deleteExample(@PathVariable(name = "id") Long id) {
    exampleRepository.deleteExample(id);
    return "redirect:index";
  }
}
