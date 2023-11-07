package uniandes.edu.co.proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uniandes.edu.co.proyecto.entities.Salon;
import uniandes.edu.co.proyecto.entities.Spa;
import uniandes.edu.co.proyecto.repositories.ReservasServicioRepositorio;
import uniandes.edu.co.proyecto.repositories.SalonesRepositorio;
import uniandes.edu.co.proyecto.repositories.SpasRepositorio;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/reservas_servicios")
public class ControladorReservasServicio {
  private static final DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

  @Autowired
  private ReservasServicioRepositorio reservasServicioRepositorio;

  @Autowired
  private SpasRepositorio spasRepositorio;

  @Autowired
  private SalonesRepositorio salonesRepositorio;

  @GetMapping
  public String obtenerReservasServicio(Model model) {
    model.addAttribute("salones", salonesRepositorio.obtenerSalones());
    model.addAttribute("spas", spasRepositorio.obtenerSpas());

    model.addAttribute("reservas", reservasServicioRepositorio.obtenerReservasServicio());

    return "reservasServicio";
  }

  @PostMapping("/new/save")
  public String crearReserva(
      @ModelAttribute(name = "horario") String horario,
      @ModelAttribute(name = "duracion") int duracion,
      @ModelAttribute(name = "idSalon") String idSalon,
      @ModelAttribute(name = "idSpa") String idSpa
  ) throws ParseException {
    Date horarioReserva = dateTimeFormat.parse(horario.replace("T", " "));
    Salon salon;
    Spa spa;
    if (!idSalon.isBlank()) {
      salon = salonesRepositorio.obtenerSalon(Long.parseLong(idSalon));
      reservasServicioRepositorio.crearReservaSalon(horarioReserva, duracion, salon.getId());
    } else if (!idSpa.isBlank()) {
      spa = spasRepositorio.obtenerSpa(Long.parseLong(idSpa));
      reservasServicioRepositorio.crearReservaSpa(horarioReserva, duracion, spa.getId());
    }

    return "redirect:/reservas_servicios";
  }
}
