package uniandes.edu.co.proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uniandes.edu.co.proyecto.entities.Equipo;
import uniandes.edu.co.proyecto.entities.EquiposGimnasios;
import uniandes.edu.co.proyecto.entities.EquiposSalones;
import uniandes.edu.co.proyecto.entities.Gimnasio;
import uniandes.edu.co.proyecto.entities.primarykeys.EquiposGimnasioPK;
import uniandes.edu.co.proyecto.repositories.EquiposGimnasiosRepositorio;
import uniandes.edu.co.proyecto.repositories.EquiposRepositorio;
import uniandes.edu.co.proyecto.repositories.GimnasiosRepositorio;

@Controller
@RequestMapping("/equiposgimnasios")
public class ControladorEquiposGimnasios {
  @Autowired
  private EquiposRepositorio equiposRepositorio;
  @Autowired
  private GimnasiosRepositorio gimnasiosRepositorio;
  @Autowired
  private EquiposGimnasiosRepositorio equiposGimnasiosRepositorio;

  @GetMapping("/new")
  public String equiposGimnasiosForm(Model model) {
    model.addAttribute("equipos", equiposRepositorio.obtenerEquipos());
    model.addAttribute("gimnasios", gimnasiosRepositorio.obtenerGimnasios());
    return "equiposGimnasiosNuevo";
  }

  @PostMapping("/new/save")
  public String relacionarEquiposGimnasios(@ModelAttribute(name = "idGimnasio") long idGimnasio, @ModelAttribute(name = "idEquipo") long idEquipo) {
    Gimnasio gimnasio = gimnasiosRepositorio.obtenerGimnasio(idGimnasio);
    Equipo equipo = equiposRepositorio.obtenerEquipo(idEquipo);

    EquiposGimnasioPK equiposGimnasioPK = new EquiposGimnasioPK(equipo, gimnasio);
    EquiposGimnasios equiposGimnasio = new EquiposGimnasios();
    equiposGimnasio.setEquiposGimnasioPK(equiposGimnasioPK);
    equiposGimnasiosRepositorio.relacionarEquipoConGimnasio(
        equiposGimnasio.getEquiposGimnasioPK().getEquipo().getId(), equiposGimnasio.getEquiposGimnasioPK().getGimnasio().getId()
    );

    return "redirect:/equipos";
  }
}
