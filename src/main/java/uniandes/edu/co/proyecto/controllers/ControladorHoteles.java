package uniandes.edu.co.proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.proyecto.entities.Hoteles;
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
  public String crearHotel(@ModelAttribute Hoteles hoteles) {
    hotelesRepositorio.crearHotel(hoteles.getNombre(), hoteles.getEstrellas(), hoteles.getPais());

    return "redirect:/hoteles";
  }

  @GetMapping("/{id}/edit")
  public String actualizarHotelesForm(@PathVariable(name = "idhotel") long idhotel, Model model) {

    model.addAttribute("hoteles", hotelesRepositorio.obtenerHoteles(idhotel));

    return "editarHotel";
  }

  @PostMapping("/{id}/edit/save")
  public String actualizarHoteles(@PathVariable(name = "idhotel") long idhotel, @ModelAttribute Hoteles hoteles) {
    hotelesRepositorio.actualizarHotel(idhotel, hoteles.getNombre(), hoteles.getEstrellas(), hoteles.getPais());

    return "redirect:/hoteles";
  }

  @GetMapping("/{id}/delete")
  public String eliminarHotel(@PathVariable(name = "id") long idhotel) {
    hotelesRepositorio.eliminarHotel(idhotel);

    return "redirect:/hoteles";
  }
}
