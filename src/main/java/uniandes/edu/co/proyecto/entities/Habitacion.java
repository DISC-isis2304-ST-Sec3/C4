package uniandes.edu.co.proyecto.entities;

import jakarta.persistence.*;


@Entity
@Table(name = "habitaciones")
public class Habitacion {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long idhabitacion;

  @OneToOne
  @JoinColumn(name = "idhotel", referencedColumnName = "idhotel", nullable = false)
  private Hotel hotel;

  private String tipo;
  private int capacidad;
  private int costo;

  public Habitacion() {
  }

  public Habitacion(String tipo, int capacidad, int costo, Hotel hotel) {
    this.tipo = tipo;
    this.capacidad = capacidad;
    this.costo = costo;
    this.hotel = hotel;
  }

  public Long getId() {
    return idhabitacion;
  }

  public void setId(Long idhabitacion) {
    this.idhabitacion = idhabitacion;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public Integer getCapacidad() {
    return capacidad;
  }

  public void setCapacidad(Integer capacidad) {
    this.capacidad = capacidad;
  }

  public Integer getCosto() {
    return costo;
  }

  public void setCosto(Integer costo) {
    this.costo = costo;
  }

  public Hotel getHoteles() {
    return hotel;
  }

  public void setHoteles(Hotel hotel) {
    this.hotel = hotel;
  }
}