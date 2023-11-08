package uniandes.edu.co.proyecto.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.proyecto.entities.Registro;
import uniandes.edu.co.proyecto.entities.Reserva;
import uniandes.edu.co.proyecto.repositories.RegistrosRepositorio;
import uniandes.edu.co.proyecto.repositories.ReservasRepositorio;

import java.text.ParseException;


@Controller
@RequestMapping("/registros")
public class ControladorRegistros {
    @Autowired
  private RegistrosRepositorio registrosRepositorio;
  @Autowired
  private ReservasRepositorio reservasRepositorio;

  @GetMapping
  public String obtenerRegistros(Model model) {
    model.addAttribute("registros", registrosRepositorio.obtenerRegistros());
    model.addAttribute("reservas", reservasRepositorio.obtenerReservas());

    return "registros";
  }

  @PostMapping("/new/save")
  public String createRegistro(
      @ModelAttribute(name = "docpersona") Integer docpersona,
      @ModelAttribute(name = "capacidad") Integer capacidad,
      @ModelAttribute(name = "idreserva") long idreserva
  ) {
    Reserva reserva = reservasRepositorio.obtenerReserva(idreserva);

    registrosRepositorio.crearRegistro(docpersona, capacidad, reserva.getId());
    return "redirect:/registros";
  }

  @GetMapping("/{id}/edit")
  public String editRegistroForm(@PathVariable(name = "id") Integer docpersona, Model model) {
    Registro registro = registrosRepositorio.obtenerRegistro(docpersona);
    model.addAttribute("reservas", reservasRepositorio.obtenerReservas());
    model.addAttribute("registro", registro);

    return "editarRegistro";
  }

  @PostMapping("/{id}/edit/save")
  public String actualizarRegistro(
      @PathVariable(name = "id") Integer docpersona,
      @ModelAttribute(name = "capacidad") Integer capacidad,
      @ModelAttribute(name = "idreserva") long idreserva
  ) throws ParseException {
    Reserva reserva = reservasRepositorio.obtenerReserva(idreserva);

    registrosRepositorio.actualizarRegistro(
        docpersona, capacidad, reserva.getId());
    return "redirect:/registros";
  }

  @GetMapping("/{id}/delete")
  public String eliminarRegistro(@PathVariable(name = "id") Integer docpersona) {
    registrosRepositorio.eliminarRegistro(docpersona);
    return "redirect:/registros";
  }
}
