package uniandes.edu.co.proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.proyecto.entities.Dotacion;
import uniandes.edu.co.proyecto.repositories.DotacionesRepositorio;


@Controller
@RequestMapping("/dotaciones")
public class ControladorDotaciones {
  @Autowired
  private DotacionesRepositorio dotacionesRepositorio;

  @GetMapping
  public String obtenerDotaciones(Model model) {
    model.addAttribute("dotaciones", dotacionesRepositorio.obtenerDotaciones());

    return "dotaciones";
  }

  @PostMapping("/new/save")
  public String crearDotacion(@ModelAttribute Dotacion dotacion) {
    dotacionesRepositorio.crearDotacion(dotacion.getNombre(), dotacion.getCantidad(), dotacion.getCostoadicional());

    return "redirect:/dotaciones";
  }

  @GetMapping("/{id}/edit")
  public String actualizarDotacionesForm(@PathVariable(name = "id") long iddotacion, Model model) {

    model.addAttribute("dotaciones", dotacionesRepositorio.obtenerDotacion(iddotacion));

    return "editarDotacion";
  }

  @PostMapping("/{id}/edit/save")
  public String actualizarDotaciones(@PathVariable(name = "id") long iddotacion, @ModelAttribute Dotacion dotacion) {
    dotacionesRepositorio.actualizarDotacion(iddotacion, dotacion.getNombre(), dotacion.getCantidad(), dotacion.getCostoadicional());

    return "redirect:/dotaciones";
  }

  @GetMapping("/{id}/delete")
  public String eliminarDotacion(@PathVariable(name = "id") long iddotacion) {
    dotacionesRepositorio.eliminarDotacion(iddotacion);

    return "redirect:/dotaciones";
  }
}
