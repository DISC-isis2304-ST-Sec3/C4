package uniandes.edu.co.proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import uniandes.edu.co.proyecto.entities.especiales.DineroHabitacion;
import uniandes.edu.co.proyecto.repositories.EspecialesRepositorio;

import java.util.List;

@Controller
public class ControladorRequerimietos {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  private EspecialesRepositorio especialesRepositorio;

  @GetMapping("/dinero_habitaciones")
  public String obtenerDineroServicioPorHabitacion(Model model, @RequestParam(name = "page", defaultValue = "1") int page) {
    if (page < 1) page = 1;
    List<DineroHabitacion> r = especialesRepositorio.ejecutarRequerimiento1(page);
    model.addAttribute("resultados", r);
    model.addAttribute("pagina", page);

    return "dinero_habitaciones";
  }
}
