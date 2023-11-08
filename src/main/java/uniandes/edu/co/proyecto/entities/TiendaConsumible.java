package uniandes.edu.co.proyecto.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tiendasConsumible")
public class TiendaConsumible {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String estilo;

  @OneToOne
  @JoinColumn(name = "id_tienda", referencedColumnName = "id", nullable = false)
  private Tienda tienda;

  public TiendaConsumible() {
  }

  public TiendaConsumible(String estilo) {
  this.estilo = estilo;
  }

  public Long getId() {
  return id;
  }

  public void setId(Long id) {
  this.id = id;
  }

  public String getEstilo() {
  return estilo;
  }

  public void setEstilo(String estilo) {
  this.estilo = estilo;
  }

}
