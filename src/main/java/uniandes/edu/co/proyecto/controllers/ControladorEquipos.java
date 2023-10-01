package uniandes.edu.co.proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.proyecto.entities.Equipo;
import uniandes.edu.co.proyecto.repositories.EquiposRepositorio;

import java.util.Collection;

@Controller
@RequestMapping("/equipos")
public class ControladorEquipos {
  @Autowired
  private EquiposRepositorio equiposRepositorio;

  @GetMapping
  public String obtenerEquipos(Model model) {
    Collection<Equipo> equipos = equiposRepositorio.obtenerEquipos();

    model.addAttribute("equipos", equipos);

    return "equipos";
  }

  @PostMapping("/new/save")
  public String crearEquipo(@ModelAttribute Equipo equipo) {
    equiposRepositorio.crearEquipo(equipo.getTipoEquipo(), equipo.getCostoAdicional());
    return "redirect:/equipos";
  }

  @GetMapping("/{id}/edit")
  public String formularioEditarEquipo(@PathVariable(name = "id") Long id, Model model) {
    Equipo equipo = equiposRepositorio.obtenerEquipo(id);

    model.addAttribute("equipo", equipo);

    return "editarEquipo";
  }

  @PostMapping("/{id}/edit/save")
  public String actualizarEquipo(@PathVariable(name = "id") Long id, @ModelAttribute Equipo equipo) {
    equiposRepositorio.actualizarEquipo(id, equipo.getTipoEquipo(), equipo.getCostoAdicional());
    return "redirect:/equipos";
  }

  @GetMapping("/{id}/delete")
  public String eliminarEquipo(@PathVariable(name = "id") Long id) {
    equiposRepositorio.eliminarEquipo(id);
    return "redirect:/equipos";
  }
}
