package uniandes.edu.co.proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.proyecto.entities.Hotel;
import uniandes.edu.co.proyecto.repositories.HotelesRepositorio;

@Controller
@RequestMapping("/hoteles")
public class ControladorHoteles {
  @Autowired
  private HotelesRepositorio hotelesRepositorio;

  @GetMapping
  public String obtenerHoteles(Model model) {
    model.addAttribute("hoteles", hotelesRepositorio.obtenerHoteles());

    return "hoteles";
  }

  @PostMapping("/new/save")
  public String crearHotel(@ModelAttribute Hotel hotel) {
    hotelesRepositorio.crearHotel(hotel.getNombre(), hotel.getEstrellas(), hotel.getPais());

    return "redirect:/hoteles";
  }

  @GetMapping("/{id}/edit")
  public String actualizarHotelesForm(@PathVariable(name = "id") long idhotel, Model model) {

    model.addAttribute("hoteles", hotelesRepositorio.obtenerHoteles(idhotel));

    return "editarHotel";
  }

  @PostMapping("/{id}/edit/save")
  public String actualizarHoteles(@PathVariable(name = "id") long idhotel, @ModelAttribute Hotel hotel) {
    hotelesRepositorio.actualizarHotel(idhotel, hotel.getNombre(), hotel.getEstrellas(), hotel.getPais());

    return "redirect:/hoteles";
  }

  @GetMapping("/{id}/delete")
  public String eliminarHotel(@PathVariable(name = "id") long idhotel) {
    hotelesRepositorio.eliminarHotel(idhotel);

    return "redirect:/hoteles";
  }
}
