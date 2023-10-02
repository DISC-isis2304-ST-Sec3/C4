package uniandes.edu.co.proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.proyecto.entities.Habitacion;
import uniandes.edu.co.proyecto.entities.Hotel;
import uniandes.edu.co.proyecto.repositories.HabitacionesRepositorio;
import uniandes.edu.co.proyecto.repositories.HotelesRepositorio;

import java.text.ParseException;


@Controller
@RequestMapping("/habitaciones")
public class ControladorHabitaciones {
  @Autowired
  private HabitacionesRepositorio habitacionesRepositorio;
  @Autowired
  private HotelesRepositorio hotelesRepositorio;

  @GetMapping
  public String obtenerHabitaciones(Model model) {
    model.addAttribute("habitaciones", habitacionesRepositorio.obtenerHabitaciones());

    model.addAttribute("hoteles", hotelesRepositorio.obtenerHoteles());

    return "habitaciones";
  }

  @PostMapping("/new/save")
  public String createHabitacion(
      @ModelAttribute(name = "tipo") String tipo,
      @ModelAttribute(name = "capacidad") Integer capacidad,
      @ModelAttribute(name = "costo") Integer costo,
      @ModelAttribute(name = "idhotel") long idhotel
  ) {
    Hotel hotel = hotelesRepositorio.obtenerHoteles(idhotel);

    habitacionesRepositorio.crearHabitacion(tipo, capacidad, costo, hotel.getId());
    return "redirect:/habitaciones";
  }

  @GetMapping("/{id}/edit")
  public String editHabitacionForm(@PathVariable(name = "id") Long idhabitacion, Model model) {
    Habitacion habitacion = habitacionesRepositorio.obtenerHabitacion(idhabitacion);
    model.addAttribute("hoteles", hotelesRepositorio.obtenerHoteles());
    model.addAttribute("habitacion", habitacion);

    return "editarHabitacion";
  }

  @PostMapping("/{id}/edit/save")
  public String actualizarHabitacion(
      @PathVariable(name = "id") Long idhabitacion,
      @ModelAttribute(name = "tipo") String tipo,
      @ModelAttribute(name = "capacidad") Integer capacidad,
      @ModelAttribute(name = "costo") Integer costo,
      @ModelAttribute(name = "idhotel") long idhotel
  ) throws ParseException {
    Hotel hotel = hotelesRepositorio.obtenerHoteles(idhotel);

    habitacionesRepositorio.actualizarHabitacion(
        idhabitacion, tipo, capacidad, costo, hotel.getId());
    return "redirect:/habitaciones";
  }

  @GetMapping("/{id}/delete")
  public String eliminarHabitacion(@PathVariable(name = "id") Long idhabitacion) {
    habitacionesRepositorio.eliminarHabitacion(idhabitacion);
    return "redirect:/habitaciones";
  }

  @GetMapping("/{tipos}/tipos")
  public String obtenerTiposHabitaciones(@PathVariable(name = "tipos") String tipo) {
    habitacionesRepositorio.obtenerTiposHabitaciones();
    return "redirect:/habitaciones";
  }
}
