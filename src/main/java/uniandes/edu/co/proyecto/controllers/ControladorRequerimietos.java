package uniandes.edu.co.proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.proyecto.entities.especiales.DineroHabitacion;
import uniandes.edu.co.proyecto.repositories.iteracion2.RepositorioReq1;
import uniandes.edu.co.proyecto.repositories.iteracion2.RepositorioReq2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class ControladorRequerimietos {
  @Autowired
  private RepositorioReq1 repositorioReq1;
  @Autowired
  private RepositorioReq2 repositorioReq2;

  private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

  @GetMapping("/dinero_habitaciones")
  public String obtenerDineroServicioPorHabitacion(Model model, @RequestParam(name = "page", defaultValue = "1") int page) {
    if (page < 1) page = 1;
    List<DineroHabitacion> r = repositorioReq1.ejecutarRequerimiento1(page);
    model.addAttribute("resultados", r);
    model.addAttribute("pagina", page);

    return "dinero_habitaciones";
  }

  @GetMapping("/servicios_populares")
  public String obtenerServiciosMasPopularesFechas(Model model) {
    model.addAttribute("resultados", new ArrayList<>());
    model.addAttribute("fechaInicial", new Date());
    model.addAttribute("fechaFinal", new Date());

    return "servicios_populares";
  }

  @PostMapping("/servicios_populares")
  public String obtenerServiciosMasPopularesFechas(
      @ModelAttribute(name = "fechaInicial") String fechaInicial,
      @ModelAttribute(name = "fechaFinal") String fechaFinal
    ) {

    return "redirect:/servicios_populares/" + fechaInicial + "/" + fechaFinal;
  }

  @GetMapping("/servicios_populares/{date1}/{date2}")
  public String obtenerServiciosMasPopularesFechas(
    Model model,
    @PathVariable(name = "date1", required = true) String date1,
    @PathVariable(name = "date2", required = true) String date2
  ) throws ParseException {
    Date fechaInicial = dateFormat.parse(date1);
    Date fechaFinal = dateFormat.parse(date2);

//    List<Map<String, Object>> r = especialesRepositorio.ejecutarRequerimiento2(fechaInicial, fechaFinal);
//    for (Map<String, Object> m: r) {
//      for (Map.Entry<String, Object> entry : m.entrySet()) {
//        System.out.printf("%s, %s\n", entry.getKey(), entry.getValue());
//      }
//      System.out.println("=");
//    }
    model.addAttribute("resultados", repositorioReq2.ejecutarRequerimiento2(fechaInicial, fechaFinal));
    model.addAttribute("fechaInicial", date1);
    model.addAttribute("fechaFinal", date2);

    return "servicios_populares";
  }
}
