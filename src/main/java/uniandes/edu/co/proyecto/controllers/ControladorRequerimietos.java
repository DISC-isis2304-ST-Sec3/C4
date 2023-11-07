package uniandes.edu.co.proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import uniandes.edu.co.proyecto.repositories.EspecialesRepositorio;

@Controller
public class ControladorRequerimietos {
  @Autowired
  private EspecialesRepositorio especialesRepositorio;

  @GetMapping("/dinero_habitaciones")
  public String obtenerDineroServicioPorHabitacion(Model model, @RequestParam(name = "page", defaultValue = "1") int page) {
    model.addAttribute("resultados", especialesRepositorio.ejecutarRequerimiento1());
    model.addAttribute("pagina", String.format("Página %d", page));

    return "dinero_habitaciones";
  }
}
