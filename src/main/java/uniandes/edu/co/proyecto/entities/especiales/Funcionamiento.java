package uniandes.edu.co.proyecto.entities.especiales;

import jakarta.persistence.*;
import uniandes.edu.co.proyecto.entities.Servicio;

@Entity
public class Funcionamiento {
  @Id
  private Integer semana;

  @ManyToOne
  @JoinColumn(name = "servicio_menos_consumo", referencedColumnName = "id")
  private Servicio servicioMenosConsumo;

  @Column(name = "menor_cantidad")
  private Integer menorCantidad;

  @ManyToOne
  @JoinColumn(name = "servicio_mas_consumo", referencedColumnName = "id")
  private Servicio servicioMasConsumo;

  @Column(name = "mayor_cantidad")
  private Integer mayorCantidad;

  @Column(name = "habitacion_menos_pedida")
  private Long habitacionMenosPedida;

  @Column(name = "menor_cantidad_reservas")
  private Integer menorCantidadReservas;

  @Column(name = "habitacion_mas_pedida")
  private Long habitacionMasPedida;

  @Column(name = "mayor_cantidad_reservas")
  private Integer mayorCantidadReservas;

  public Funcionamiento() {}

  public Funcionamiento(Integer semana, Servicio servicioMenosConsumo, Integer menorCantidad, Servicio servicioMasConsumo, Integer mayorCantidad, Long habitacionMenosPedida, Integer menorCantidadReservas, Long habitacionMasPedida, Integer mayorCantidadReservas) {
    this.semana = semana;
    this.servicioMenosConsumo = servicioMenosConsumo;
    this.menorCantidad = menorCantidad;
    this.servicioMasConsumo = servicioMasConsumo;
    this.mayorCantidad = mayorCantidad;
    this.habitacionMenosPedida = habitacionMenosPedida;
    this.menorCantidadReservas = menorCantidadReservas;
    this.habitacionMasPedida = habitacionMasPedida;
    this.mayorCantidadReservas = mayorCantidadReservas;
  }

  public Integer getSemana() {
    return semana;
  }

  public void setSemana(Integer semana) {
    this.semana = semana;
  }

  public Servicio getServicioMenosConsumo() {
    return servicioMenosConsumo;
  }

  public void setServicioMenosConsumo(Servicio servicioMenosConsumo) {
    this.servicioMenosConsumo = servicioMenosConsumo;
  }

  public Integer getMenorCantidad() {
    return menorCantidad;
  }

  public void setMenorCantidad(Integer menorCantidad) {
    this.menorCantidad = menorCantidad;
  }

  public Servicio getServicioMasConsumo() {
    return servicioMasConsumo;
  }

  public void setServicioMasConsumo(Servicio servicioMasConsumo) {
    this.servicioMasConsumo = servicioMasConsumo;
  }

  public Integer getMayorCantidad() {
    return mayorCantidad;
  }

  public void setMayorCantidad(Integer mayorCantidad) {
    this.mayorCantidad = mayorCantidad;
  }

  public Long getHabitacionMenosPedida() {
    return habitacionMenosPedida;
  }

  public void setHabitacionMenosPedida(Long habitacionMenosPedida) {
    this.habitacionMenosPedida = habitacionMenosPedida;
  }

  public Integer getMenorCantidadReservas() {
    return menorCantidadReservas;
  }

  public void setMenorCantidadReservas(Integer menorCantidadReservas) {
    this.menorCantidadReservas = menorCantidadReservas;
  }

  public Long getHabitacionMasPedida() {
    return habitacionMasPedida;
  }

  public void setHabitacionMasPedida(Long habitacionMasPedida) {
    this.habitacionMasPedida = habitacionMasPedida;
  }

  public Integer getMayorCantidadReservas() {
    return mayorCantidadReservas;
  }

  public void setMayorCantidadReservas(Integer mayorCantidadReservas) {
    this.mayorCantidadReservas = mayorCantidadReservas;
  }
}
