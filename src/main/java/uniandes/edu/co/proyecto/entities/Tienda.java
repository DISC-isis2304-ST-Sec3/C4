package uniandes.edu.co.proyecto.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tiendas")
public class Tienda {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String tipo;
  private String nombre;

  @ManyToOne
  @JoinColumn(name = "id_hotel", referencedColumnName = "idhotel")
  private Hotel hotel;

  public Tienda() {
  }

  public Tienda(String tipo, String nombre, Hotel hotel) {
    this.tipo = tipo;
    this.nombre = nombre;
    this.hotel = hotel;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public String getNombre() {
    return nombre;
  }

  public void setCosto(String nombre) {
    this.nombre = nombre;
  }

}
