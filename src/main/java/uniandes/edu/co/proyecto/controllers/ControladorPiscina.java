package uniandes.edu.co.proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.proyecto.entities.Servicio;
import uniandes.edu.co.proyecto.repositories.PiscinasRepositorio;
import uniandes.edu.co.proyecto.repositories.ServiciosRepositorio;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
}
