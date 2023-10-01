package uniandes.edu.co.proyecto.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "hoteles")
public class Hotel {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long idhotel;
  private String nombre;
  private int estrellas;
  private String pais;

  public Hotel() {
  }

  public Hotel(String nombre, int estrellas, String pais) {
    this.nombre = nombre;
    this.estrellas = estrellas;
    this.pais = pais;
  }

  public Long getId() {
    return idhotel;
  }

  public void setId(Long idhotel) {
    this.idhotel = idhotel;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public int getEstrellas() {
    return estrellas;
  }

  public void setEstrellas(int estrellas) {
    this.estrellas = estrellas;
  }

  public String getPais() {
    return pais;
  }

  public void setPais(String pais) {
    this.pais = pais;
  }


}
