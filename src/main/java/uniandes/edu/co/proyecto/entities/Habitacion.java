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
  private Hoteles hoteles;

  private String tipo;
  private int capacidad;
  private int costo;

  public Habitacion() {
  }

  public Habitacion(String tipo, int capacidad, int costo, Hoteles hoteles) {
    this.tipo = tipo;
    this.capacidad = capacidad;
    this.costo = costo;
    this.hoteles = hoteles;
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

  public Hoteles getHoteles() {
    return hoteles;
  }

  public void setHoteles(Hoteles hoteles) {
    this.hoteles = hoteles;
  }
}