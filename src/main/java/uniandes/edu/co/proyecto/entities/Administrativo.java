package uniandes.edu.co.proyecto.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "administrativos")
public class Administrativo {
    @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @OneToOne
  @JoinColumn(name = "id_usuario", referencedColumnName = "id", nullable = false)
  private Usuario usuario;

  @ManyToOne
  @JoinColumn(name = "id_hotel", referencedColumnName = "idhotel")
  private Hotel hotel;

  public Administrativo() {
  }

  public Administrativo(Usuario usuario, Hotel hotel) {
    this.usuario = usuario;
    this.hotel = hotel;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  public Hotel getHotel() {
    return hotel;
  }

  public void setHotel(Hotel hotel) {
    this.hotel = hotel;
  }
}
