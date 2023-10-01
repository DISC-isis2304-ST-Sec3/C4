package uniandes.edu.co.proyecto.controllers;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.proyecto.entities.Piscina;
import uniandes.edu.co.proyecto.entities.Servicio;
import uniandes.edu.co.proyecto.repositories.PiscinasRepositorio;
import uniandes.edu.co.proyecto.repositories.ServiciosRepositorio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/piscinas")
public class ControladorPiscina {
  @Autowired
  private PiscinasRepositorio piscinasRepositorio;
  @Autowired
  private ServiciosRepositorio serviciosRepositorio;

  private final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

  @GetMapping
  public String obtenerPiscinas(Model model) {
    model.addAttribute("piscinas", piscinasRepositorio.obtenerPiscinas());
    model.addAttribute("servicios", serviciosRepositorio.obtenerServiciosDisponibles());

    return "piscinas";
  }

  @PostMapping("/new/save")
  public String createPiscina(
      @ModelAttribute(name = "profundidad") int profundidad,
      @ModelAttribute(name = "horarioAbre") String horarioAbre,
      @ModelAttribute(name = "horarioCierra") String horarioCierra,
      @ModelAttribute(name = "idServicio") long idServicio
  ) throws ParseException {
    Servicio servicio = serviciosRepositorio.obtenerServicio(idServicio);

    piscinasRepositorio.crearPiscina(profundidad, timeFormat.parse(horarioAbre), timeFormat.parse(horarioCierra), servicio.getId());
    return "redirect:/piscinas";
  }

  @GetMapping("/{id}/edit")
  public String editPiscinaForm(@PathVariable(name = "id") Long id, Model model) {
    List<Servicio> servicios = new ArrayList<>(serviciosRepositorio.obtenerServiciosDisponibles().stream().toList());
    Piscina piscina = piscinasRepositorio.obtenerPiscina(id);
    servicios.add(piscina.getServicio());
    model.addAttribute("servicios", servicios);
    model.addAttribute("piscina", piscina);

    return "editarPiscina";
  }

  @PostMapping("/{id}/edit/save")
  public String actualizarPiscina(
      @PathVariable(name = "id") Long id,
      @ModelAttribute(name = "profundidad") int profundidad,
      @ModelAttribute(name = "horarioAbre") String horarioAbre,
      @ModelAttribute(name = "horarioCierra") String horarioCierra,
      @ModelAttribute(name = "idServicio") long idServicio
  ) throws ParseException {
    Servicio servicio = serviciosRepositorio.obtenerServicio(idServicio);

    piscinasRepositorio.actualizarPiscina(
        id, profundidad, timeFormat.parse(horarioAbre), timeFormat.parse(horarioCierra), servicio.getId());
    return "redirect:/piscinas";
  }

  @GetMapping("/{id}/delete")
  public String eliminarPiscina(@PathVariable(name = "id") Long id) {
    piscinasRepositorio.eliminarPiscina(id);
    return "redirect:/piscinas";
  }
}
