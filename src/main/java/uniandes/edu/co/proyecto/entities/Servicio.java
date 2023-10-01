package uniandes.edu.co.proyecto.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "servicios")
public class Servicio {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String nombre;
  private int capacidad;
  private String tipoCobro;
  private int cobro;

  public Servicio() {
  }

  public Servicio(String nombre, int capacidad, String tipoCobro, int cobro) {
    this.nombre = nombre;
    this.capacidad = capacidad;
    this.tipoCobro = tipoCobro;
    this.cobro = cobro;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public int getCapacidad() {
    return capacidad;
  }

  public void setCapacidad(int capacidad) {
    this.capacidad = capacidad;
  }

  public String getTipoCobro() {
    return tipoCobro;
  }

  public void setTipoCobro(String tipoCobro) {
    this.tipoCobro = tipoCobro;
  }

  public int getCobro() {
    return cobro;
  }

  public void setCobro(int cobro) {
    this.cobro = cobro;
  }
}
