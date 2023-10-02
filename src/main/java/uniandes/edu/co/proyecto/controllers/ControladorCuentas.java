package uniandes.edu.co.proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.proyecto.entities.Cuenta;
import uniandes.edu.co.proyecto.repositories.CuentasRepositorio;

@Controller
@RequestMapping("/cuentas")
public class ControladorCuentas {
  @Autowired
  private CuentasRepositorio cuentasRepositorio;

  @GetMapping
  public String obtenerCuentas(Model model) {
    model.addAttribute("cuentas", cuentasRepositorio.obtenerCuentas());

    return "cuentas";
  }

  @PostMapping("/new/save")
  public String crearCuenta(@ModelAttribute Cuenta cuenta) {
    cuentasRepositorio.crearCuenta(cuenta.getPagado());

    return "redirect:/cuentas";
  }

  @GetMapping("/{id}/edit")
  public String actualizarCuentasForm(@PathVariable(name = "id") long idpago, Model model) {

    model.addAttribute("cuentas", cuentasRepositorio.obtenerCuenta(idpago));

    return "editarCuenta";
  }

  @PostMapping("/{id}/edit/save")
  public String actualizarCuentas(@PathVariable(name = "id") long idpago, @ModelAttribute Cuenta cuenta) {
    cuentasRepositorio.actualizarCuenta(idpago, cuenta.getPagado());

    return "redirect:/cuentas";
  }

  @GetMapping("/{id}/delete")
  public String eliminarCuenta(@PathVariable(name = "id") long idpago) {
    cuentasRepositorio.eliminarCuenta(idpago);

    return "redirect:/cuentas";
  }
}
