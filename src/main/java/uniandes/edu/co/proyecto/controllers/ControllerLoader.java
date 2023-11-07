package uniandes.edu.co.proyecto.controllers;

import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uniandes.edu.co.proyecto.entities.Hotel;
import uniandes.edu.co.proyecto.repositories.CuentasRepositorio;
import uniandes.edu.co.proyecto.repositories.HabitacionesRepositorio;
import uniandes.edu.co.proyecto.repositories.HotelesRepositorio;
import uniandes.edu.co.proyecto.repositories.ReservasRepositorio;
import uniandes.edu.co.proyecto.services.CargaDatos;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Controller
@RequestMapping("/loader")
public class ControllerLoader {
  @Autowired
  private CargaDatos servicioCargaDatos;

  @PostMapping("/load")
  public String loadData() {
    System.out.println(servicioCargaDatos.cargar());

    return "redirect:/";
  }
}
