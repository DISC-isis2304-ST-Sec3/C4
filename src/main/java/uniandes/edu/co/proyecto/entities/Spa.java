package uniandes.edu.co.proyecto.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "spas")
public class Spa {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private int costo;
  private String descripcion;

  @ManyToOne
  @JoinColumn(name = "id_hotel", referencedColumnName = "idhotel")
  private Hotel hotel;

  public Spa() {
  }

  public Spa(int costo, String descripcion, Hotel hotel) {
    this.costo = costo;
    this.descripcion = descripcion;
    this.hotel = hotel;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int getCosto() {
    return costo;
  }

  public void setCosto(int costo) {
    this.costo = costo;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public Hotel getHotel() {
    return hotel;
  }

  public void setHotel(Hotel hotel) {
    this.hotel = hotel;
  }
}
