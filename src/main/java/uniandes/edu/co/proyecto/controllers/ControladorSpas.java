package uniandes.edu.co.proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.proyecto.entities.Hotel;
import uniandes.edu.co.proyecto.entities.Spa;
import uniandes.edu.co.proyecto.repositories.HotelesRepositorio;
import uniandes.edu.co.proyecto.repositories.SpasRepositorio;

@Controller
@RequestMapping("/spas")
public class ControladorSpas {
  @Autowired
  private SpasRepositorio spasRepositorio;
  @Autowired
  private HotelesRepositorio hotelesRepositorio;

  @GetMapping
  public String obtenerSpas(Model model) {
    model.addAttribute("spas", spasRepositorio.obtenerSpas());
    model.addAttribute("hoteles", hotelesRepositorio.obtenerHoteles());
    model.addAttribute("hotelUnico", false);
    return "spas";
  }

  @GetMapping("/hotel/{idHotel}")
  public String obtenerSpasHotel(@PathVariable("idHotel") long idHotel, Model model) {
    model.addAttribute("spas", spasRepositorio.obtenerSpasHotel(idHotel));
    model.addAttribute("hoteles", hotelesRepositorio.obtenerHoteles());
    model.addAttribute("hotelUnico", true);
    return "spas";
  }

  @PostMapping("/new/save")
  public String crearSpa(
      @ModelAttribute(name = "costo") int costo,
      @ModelAttribute(name = "descripcion") String descripcion,
      @ModelAttribute(name = "idHotel") long idHotel
  ) {
    Hotel hotel = hotelesRepositorio.obtenerHoteles(idHotel);
    spasRepositorio.crearSpa(costo, descripcion, hotel.getId());
    return "redirect:/spas";
  }

  @GetMapping("/{id}/edit")
  public String editarSpaForm(@PathVariable(name = "id") Long id, Model model) {
    Spa spa = spasRepositorio.obtenerSpa(id);
    model.addAttribute("spa", spa);
    model.addAttribute("hoteles", hotelesRepositorio.obtenerHoteles());
    return "editarSpa";
  }

  @PostMapping("/{id}/edit/save")
  public String actualizarSpa(
      @PathVariable(name = "id") Long id,
      @ModelAttribute(name = "costo") int costo,
      @ModelAttribute(name = "descripcion") String descripcion,
      @ModelAttribute(name = "idHotel") long idHotel
  ) {
    Hotel hotel = hotelesRepositorio.obtenerHoteles(idHotel);
    spasRepositorio.actualizarSpa(id, costo, descripcion, hotel.getId());
    return "redirect:/spas";
  }

  @GetMapping("/{id}/delete")
  public String eliminarSpa(@PathVariable(name = "id") Long id) {
    spasRepositorio.eliminarSpa(id);
    return "redirect:/spas";
  }
}
