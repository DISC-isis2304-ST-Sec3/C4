package uniandes.edu.co.proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.proyecto.entities.Gimnasio;
import uniandes.edu.co.proyecto.entities.Servicio;
import uniandes.edu.co.proyecto.repositories.EquiposRepositorio;
import uniandes.edu.co.proyecto.repositories.GimnasiosRepositorio;
import uniandes.edu.co.proyecto.repositories.ServiciosRepositorio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/gimnasios")
public class ControladorGimnasios {
  private final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
  @Autowired
  private EquiposRepositorio equiposRepositorio;
  @Autowired
  private GimnasiosRepositorio gimnasiosRepositorio;
  @Autowired
  private ServiciosRepositorio serviciosRepositorio;

  @GetMapping
  public String obtenerGimnasios(Model model) {
    model.addAttribute("gimnasios", gimnasiosRepositorio.obtenerGimnasios());
    model.addAttribute("servicios", serviciosRepositorio.obtenerServiciosDisponibles());

    return "gimnasios";
  }

  @GetMapping("/{id}/equipos")
  public String obtenerEquiposGimnasio(@PathVariable(name = "id") long id, Model model) {
    model.addAttribute("gimnasio", gimnasiosRepositorio.obtenerGimnasio(id));
    model.addAttribute("equipos_gimnasio", equiposRepositorio.obtenerEquiposGimnasio(id));
    model.addAttribute("equipos_disponibles", equiposRepositorio.obtenerEquiposParaGimnasio(id));

    return "equiposGimnasio";
  }

  @PostMapping("/new/save")
  public String createGimnasio(
      @ModelAttribute(name = "horarioAbre") String horarioAbre,
      @ModelAttribute(name = "horarioCierra") String horarioCierra,
      @ModelAttribute(name = "idServicio") long idServicio
  ) throws ParseException {
    Servicio servicio = serviciosRepositorio.obtenerServicio(idServicio);

    gimnasiosRepositorio.crearGimnasio(timeFormat.parse(horarioAbre), timeFormat.parse(horarioCierra), servicio.getId());
    return "redirect:/gimnasios";
  }

  @GetMapping("/{id}/edit")
  public String editGimnasioForm(@PathVariable(name = "id") Long id, Model model) {
    List<Servicio> servicios = new ArrayList<>(serviciosRepositorio.obtenerServiciosDisponibles().stream().toList());
    Gimnasio gimnasio = gimnasiosRepositorio.obtenerGimnasio(id);
    servicios.add(gimnasio.getServicio());
    model.addAttribute("servicios", servicios);
    model.addAttribute("gimnasio", gimnasio);

    return "editarGimnasio";
  }

  @PostMapping("/{id}/edit/save")
  public String actualizarGimnasio(
      @PathVariable(name = "id") Long id,
      @ModelAttribute(name = "horarioAbre") String horarioAbre,
      @ModelAttribute(name = "horarioCierra") String horarioCierra,
      @ModelAttribute(name = "idServicio") long idServicio
  ) throws ParseException {
    Servicio servicio = serviciosRepositorio.obtenerServicio(idServicio);

    gimnasiosRepositorio.actualizarGimnasio(
        id, timeFormat.parse(horarioAbre), timeFormat.parse(horarioCierra), servicio.getId());
    return "redirect:/gimnasios";
  }

  @GetMapping("/{id}/delete")
  public String eliminarGimnasio(@PathVariable(name = "id") Long id) {
    gimnasiosRepositorio.eliminarGimnasio(id);
    return "redirect:/gimnasios";
  }
}
