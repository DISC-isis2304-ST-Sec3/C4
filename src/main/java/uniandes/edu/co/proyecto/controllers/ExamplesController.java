package uniandes.edu.co.proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.proyecto.entities.ExampleEntity;
import uniandes.edu.co.proyecto.repositories.ExampleRepository;

import java.util.Collection;

@Controller
@RequestMapping("/examples")
public class ExamplesController {
  @Autowired
  private ExampleRepository exampleRepository;

  @GetMapping
  public String getExamples(Model model) {
    Collection<ExampleEntity> examples = exampleRepository.getExamples();

    model.addAttribute("examples", examples);

    return "examples";
  }

  @PostMapping("/new/save")
  public String createExample(@ModelAttribute ExampleEntity example) {
    exampleRepository.createExample(example.getSomeValue());
    return "redirect:examples";
  }

  @PutMapping("/{id}/edit/save")
  public String updateExample(@PathVariable(name = "id") Long id, @ModelAttribute ExampleEntity example) {
    exampleRepository.updateExample(id, example.getSomeValue());
    return "redirect:index";
  }

  @DeleteMapping("/{id}/delete")
  public String deleteExample(@PathVariable(name = "id") Long id) {
    exampleRepository.deleteExample(id);
    return "redirect:index";
  }
}
