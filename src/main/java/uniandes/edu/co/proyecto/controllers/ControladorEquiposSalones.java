package uniandes.edu.co.proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.proyecto.entities.Equipo;
import uniandes.edu.co.proyecto.entities.EquiposSalones;
import uniandes.edu.co.proyecto.entities.Salon;
import uniandes.edu.co.proyecto.entities.primarykeys.EquiposSalonesPK;
import uniandes.edu.co.proyecto.repositories.EquiposSalonesRepositorio;
import uniandes.edu.co.proyecto.repositories.EquiposRepositorio;
import uniandes.edu.co.proyecto.repositories.SalonesRepositorio;

@Controller
@RequestMapping("/equipossalones")
public class ControladorEquiposSalones {
  @Autowired
  private EquiposRepositorio equiposRepositorio;
  @Autowired
  private SalonesRepositorio salonesRepositorio;
  @Autowired
  private EquiposSalonesRepositorio equiposSalonesRepositorio;

  @PostMapping("/new/save")
  public String relacionarEquiposSalones(@ModelAttribute(name = "idSalon") long idSalon, @ModelAttribute(name = "idEquipo") long idEquipo) {
    Salon salon = salonesRepositorio.obtenerSalon(idSalon);
    Equipo equipo = equiposRepositorio.obtenerEquipo(idEquipo);

    EquiposSalonesPK equiposSalonPK = new EquiposSalonesPK(equipo, salon);
    EquiposSalones equiposSalon = new EquiposSalones();
    equiposSalon.setEquiposSalonesPK(equiposSalonPK);
    equiposSalonesRepositorio.relacionarEquipoConSalon(
        equiposSalon.getEquiposSalonesPK().getEquipo().getId(), equiposSalon.getEquiposSalonesPK().getSalon().getId()
    );

    return String.format("redirect:/salones/%s/equipos", idSalon);
  }

  @GetMapping("/{idGym}/{idEquipo}/delete")
  public String eliminarRelacionEquipoSalon(@PathVariable(name = "idGym") long idSalon, @PathVariable(name = "idEquipo") long idEquipo) {
    equiposSalonesRepositorio.eliminarRelacionEquipoConSalon(idEquipo, idSalon);

    return String.format("redirect:/salones/%s/equipos", idSalon);
  }
}
