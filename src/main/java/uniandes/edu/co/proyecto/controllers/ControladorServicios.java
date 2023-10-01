package uniandes.edu.co.proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.proyecto.entities.Servicio;
import uniandes.edu.co.proyecto.repositories.ServiciosRepositorio;

import java.util.Collection;

@Controller
@RequestMapping("/servicios")
public class ControladorServicios {
  @Autowired
  private ServiciosRepositorio serviciosRepositorio;

  @GetMapping
  public String getServicios(Model model) {
    Collection<Servicio> servicios = serviciosRepositorio.obtenerServicios();

    model.addAttribute("servicios", servicios);

    return "servicios";
  }

  @PostMapping("/new/save")
  public String createServicio(@ModelAttribute Servicio servicio) {
    serviciosRepositorio.crearServicio(servicio.getNombre(), servicio.getCapacidad(), servicio.getTipoCobro(), servicio.getCobro());
    return "redirect:/servicios";
  }

  @GetMapping("/{id}/edit")
  public String editServicioForm(@PathVariable(name = "id") Long id, Model model) {
    Servicio servicio = serviciosRepositorio.obtenerServicio(id);

    model.addAttribute("servicio", servicio);

    return "editarServicio";
  }

  @PostMapping("/{id}/edit/save")
  public String updateServicio(@PathVariable(name = "id") Long id, @ModelAttribute Servicio servicio) {
    serviciosRepositorio.actualizarServicio(id, servicio.getNombre(), servicio.getCapacidad(), servicio.getTipoCobro(), servicio.getCobro());
    return "redirect:/servicios";
  }

  @GetMapping("/{id}/delete")
  public String deleteServicio(@PathVariable(name = "id") Long id) {
    serviciosRepositorio.eliminarServicio(id);
    return "redirect:/servicios";
  }
}
