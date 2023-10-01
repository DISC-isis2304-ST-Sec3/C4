package uniandes.edu.co.proyecto.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "equipos")
public class Equipo {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String tipoEquipo;
  private int costoAdicional;

  public Equipo() {
  }

  public Equipo(String tipoEquipo, int costoAdicional) {
    this.tipoEquipo = tipoEquipo;
    this.costoAdicional = costoAdicional;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTipoEquipo() {
    return tipoEquipo;
  }

  public void setTipoEquipo(String tipoEquipo) {
    this.tipoEquipo = tipoEquipo;
  }

  public int getCostoAdicional() {
    return costoAdicional;
  }

  public void setCostoAdicional(int costoAdicional) {
    this.costoAdicional = costoAdicional;
  }
}
