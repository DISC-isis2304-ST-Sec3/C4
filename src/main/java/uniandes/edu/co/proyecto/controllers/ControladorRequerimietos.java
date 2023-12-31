package uniandes.edu.co.proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.proyecto.entities.especiales.DineroHabitacion;
import uniandes.edu.co.proyecto.entities.especiales.FechasImportantes;
import uniandes.edu.co.proyecto.entities.especiales.ServicioPocaDemanda;
import uniandes.edu.co.proyecto.entities.especiales.ServiciosEspeciales;
import uniandes.edu.co.proyecto.repositories.iteracion2.RepositorioReq1;
import uniandes.edu.co.proyecto.repositories.iteracion2.RepositorioReq11;
import uniandes.edu.co.proyecto.repositories.iteracion2.RepositorioReq2;
import uniandes.edu.co.proyecto.repositories.iteracion2.RepositorioReq3;
import uniandes.edu.co.proyecto.repositories.iteracion2.RepositorioReq4;
import uniandes.edu.co.proyecto.repositories.iteracion2.RepositorioReq6;
import uniandes.edu.co.proyecto.repositories.iteracion2.RepositorioReq8;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class ControladorRequerimietos {
  @Autowired
  private RepositorioReq1 repositorioReq1;
  @Autowired
  private RepositorioReq2 repositorioReq2;
  @Autowired
  private RepositorioReq3 repositorioReq3;
  @Autowired
  private RepositorioReq4 repositorioReq4;
  @Autowired
  private RepositorioReq6 repositorioReq6;
  @Autowired
  private RepositorioReq8 repositorioReq8;
  @Autowired
  private RepositorioReq11 repositorioReq11;

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
    @PathVariable(name = "date1") String date1,
    @PathVariable(name = "date2") String date2
  ) throws ParseException {
    Date fechaInicial = dateFormat.parse(date1);
    Date fechaFinal = dateFormat.parse(date2);

    model.addAttribute("resultados", repositorioReq2.ejecutarRequerimiento2(fechaInicial, fechaFinal));
    model.addAttribute("fechaInicial", date1);
    model.addAttribute("fechaFinal", date2);

    return "servicios_populares";
  }

  @GetMapping("/ocupacion_habitaciones")
  public String obtenerOcupacionHabitacionesHotelUltimoAnio(
      Model model,
      @RequestParam(name = "hotel", defaultValue = "-1") long idHotel
  ) {
    if (idHotel == -1) {
      model.addAttribute("resultados", new ArrayList<>());
    } else {
      model.addAttribute("resultados", repositorioReq3.ejecutarRequerimiento3(idHotel));
    }

    return "ocupacion_habitaciones";
  }

  @GetMapping("/fechasImportantes")
  public String obtenerFechasImportantes(Model model,
   @RequestParam(name = "page", defaultValue = "1") int page) {
    if (page < 1) page = 1;
    List<FechasImportantes> r = repositorioReq6.ejecutarRequerimiento6(page);
    model.addAttribute("resultados", repositorioReq6.ejecutarRequerimiento6(page));
    model.addAttribute("page", page);

    return "fechasImportantes";
  }


  @GetMapping("/servicioPocaDemanda")
  public String obtenerServiciosPocaDemanda(Model model,
   @RequestParam(name = "page", defaultValue = "1") int page) {
    if (page < 1) page = 1;
    List<ServicioPocaDemanda> r = repositorioReq8.ejecutarRequerimiento8(page);
    model.addAttribute("resultados", repositorioReq8.ejecutarRequerimiento8(page));
    model.addAttribute("page", page);

    return "servicioPocaDemanda";
  }

  @GetMapping("/serviciosEspeciales")
  public String obtenerServiciosEspeciales(Model model,
   @RequestParam(name = "page", defaultValue = "1") int page) {
    if (page < 1) page = 1;
    List<ServiciosEspeciales> r = repositorioReq4.ejecutarRequerimiento4(page);
    model.addAttribute("resultados", repositorioReq4.ejecutarRequerimiento4(page));
    model.addAttribute("page", page);

    return "serviciosEspeciales";
   }
   
  @GetMapping("/funcionamiento")
  public String obtenerFuncionamiento(Model model) {
    model.addAttribute("funcionamiento", repositorioReq11.ejecutarRequerimiento11());

    return "funcionamiento";
  }

}
