package uniandes.edu.co.proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.proyecto.entities.Habitacion;
import uniandes.edu.co.proyecto.entities.Cuenta;
import uniandes.edu.co.proyecto.entities.Reserva;
import uniandes.edu.co.proyecto.repositories.HabitacionesRepositorio;
import uniandes.edu.co.proyecto.repositories.CuentasRepositorio;
import uniandes.edu.co.proyecto.repositories.ReservasRepositorio;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
@RequestMapping("/reservas")
public class ControladorReservas {
  private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
  @Autowired
  private ReservasRepositorio reservasRepositorio;
  @Autowired
  private CuentasRepositorio cuentasRepositorio;
  @Autowired
  private HabitacionesRepositorio habitacionesRepositorio;

  @GetMapping
  public String obtenerReservas(Model model) {
    model.addAttribute("reservas", reservasRepositorio.obtenerReservas());
    model.addAttribute("cuentas", cuentasRepositorio.obtenerCuentas());
    model.addAttribute("habitaciones", habitacionesRepositorio.obtenerHabitaciones());

    return "reservas";
  }

  @PostMapping("/new/save")
  public String createReserva(
      @ModelAttribute(name = "inicio") String inicio,
      @ModelAttribute(name = "fin") String fin,
      @ModelAttribute(name = "cantidadpersonas") int cantidadpersonas,
      @ModelAttribute(name = "idhabitacion") long idhabitacion,
      @ModelAttribute(name = "idpago") long idpago

  ) throws ParseException{
    Habitacion habitacion = habitacionesRepositorio.obtenerHabitacion(idhabitacion);
    Cuenta cuenta = cuentasRepositorio.obtenerCuenta(idpago);

    reservasRepositorio.crearReserva(dateFormat.parse(inicio), new Date(), dateFormat.parse(fin), cantidadpersonas, habitacion.getId(), cuenta.getId());
    return "redirect:/reservas";
  }
     
    @GetMapping("/{id}/edit")
  public String editReservaForm(@PathVariable(name = "id") Long idreservar, Model model) {
    Reserva reserva = reservasRepositorio.obtenerReserva(idreservar);
    model.addAttribute("habitacion", habitacionesRepositorio.obtenerHabitaciones());
    model.addAttribute("cuentas", cuentasRepositorio.obtenerCuentas());
    model.addAttribute("reserva", reserva);

    return "editarReserva";
  }
 
  @PostMapping("/{id}/edit/save")
  public String actualizarReserva(
      @PathVariable(name = "id") Long idreserva,
      @ModelAttribute(name = "inicio") String inicio,
      @ModelAttribute(name = "fin") String fin,
      @ModelAttribute(name = "cantidadpersonas") Integer cantidadpersonas,
      @ModelAttribute(name = "idhabitacion") long idhabitacion,
      @ModelAttribute(name = "idpago") long idpago
  ) throws ParseException {
    Habitacion habitacion = habitacionesRepositorio.obtenerHabitacion(idhabitacion);
    Cuenta cuenta = cuentasRepositorio.obtenerCuenta(idpago);

    reservasRepositorio.actualizarReserva(
        idreserva , dateFormat.parse(inicio), dateFormat.parse(fin), cantidadpersonas, habitacion.getId(), cuenta.getId());
    return "redirect:/reservas";
    
  }

  @GetMapping("/{id}/delete")
  public String eliminarReserva(@PathVariable(name = "id") Long idreserva) {
    reservasRepositorio.eliminarReserva(idreserva);
    return "redirect:/reservas";
  }



}
