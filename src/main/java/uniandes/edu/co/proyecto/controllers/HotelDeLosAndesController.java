package uniandes.edu.co.proyecto.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HotelDeLosAndesController {
  @GetMapping
  public String index() {
    return "index";
  }
}
