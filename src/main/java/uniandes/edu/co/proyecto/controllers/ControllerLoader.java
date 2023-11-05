package uniandes.edu.co.proyecto.controllers;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uniandes.edu.co.proyecto.entities.Hotel;
import uniandes.edu.co.proyecto.repositories.HabitacionesRepositorio;
import uniandes.edu.co.proyecto.repositories.HotelesRepositorio;

@Controller
@RequestMapping("/loader")
public class ControllerLoader {
  @Autowired
  private HotelesRepositorio hotelesRepositorio;
  @Autowired
  private HabitacionesRepositorio habitacionesRepositorio;

  private final Faker faker = new Faker();

  @PostMapping("/load")
  public String loadData() {
    for (int i = 0; i < 100; i++) {
      hotelesRepositorio.crearHotel(
          faker.company().name(),
          faker.number().numberBetween(1, 5),
          faker.address().country()
      );

      long hotel = hotelesRepositorio.darUltimoId();

      for (int j = 0; j < faker.number().numberBetween(10, 50); j++) {
        String[] tipos = {"Suite", "Multiple", "Económica", "Doble"};
        habitacionesRepositorio.crearHabitacion(
            tipos[faker.random().nextInt(tipos.length)],
            faker.number().numberBetween(1, 10),
            faker.number().numberBetween(10000, 100000),
            hotel
        );
      }
    }

    return "redirect:/";
  }
}
