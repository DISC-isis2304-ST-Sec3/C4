package uniandes.edu.co.proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.proyecto.entities.Salon;
import uniandes.edu.co.proyecto.repositories.SalonesRepositorio;

@Controller
@RequestMapping("/salones")
public class ControladorSalones {
  @Autowired
  private SalonesRepositorio salonesRepositorio;

  @GetMapping
  public String obtenerSalones(Model model) {
    model.addAttribute("salones", salonesRepositorio.obtenerSalones());

    return "salones";
  }

  @PostMapping("/new/save")
  public String crearSalon(@ModelAttribute Salon salon) {
    salonesRepositorio.crearSalon(salon.getTipo(), salon.getCosto(), salon.getCapacidad());

    return "redirect:/salones";
  }

  @GetMapping("/{id}/edit")
  public String actualizarSalonForm(@PathVariable(name = "id") long id, Model model) {

    model.addAttribute("salon", salonesRepositorio.obtenerSalon(id));

    return "editarSalon";
  }

  @PostMapping("/{id}/edit/save")
  public String actualizarSalon(@PathVariable(name = "id") long id, @ModelAttribute Salon salon) {
    salonesRepositorio.actualizarSalon(id, salon.getTipo(), salon.getCosto(), salon.getCapacidad());

    return "redirect:/salones";
  }

  @GetMapping("/{id}/delete")
  public String eliminarSalon(@PathVariable(name = "id") long id) {
    salonesRepositorio.eliminarSalon(id);

    return "redirect:/salones";
  }
}
