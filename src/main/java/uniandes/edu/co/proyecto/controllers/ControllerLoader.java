package uniandes.edu.co.proyecto.controllers;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uniandes.edu.co.proyecto.repositories.HotelesRepositorio;

@Controller
@RequestMapping("/loader")
public class ControllerLoader {
  @Autowired
  private HotelesRepositorio hotelesRepositorio;

  private final Faker faker = new Faker();

  @PostMapping("/load")
  public String loadData() {
    for (int i = 0; i < 1000; i++) {
      hotelesRepositorio.crearHotel(faker.name().name(), faker.number().numberBetween(1, 5), faker.address().country());
    }

    return "redirect:/";
  }
}
