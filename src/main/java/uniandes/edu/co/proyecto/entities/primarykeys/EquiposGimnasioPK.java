package uniandes.edu.co.proyecto.entities.primarykeys;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import uniandes.edu.co.proyecto.entities.Equipo;
import uniandes.edu.co.proyecto.entities.Gimnasio;

import java.io.Serializable;

@Embeddable
public class EquiposGimnasioPK implements Serializable {
  @ManyToOne
  @JoinColumn(name = "id_equipo", referencedColumnName = "id")
  private Equipo equipo;

  @ManyToOne
  @JoinColumn(name = "id_gimnasio", referencedColumnName = "id")
  private Gimnasio gimnasio;

  public EquiposGimnasioPK() {
  }

  public EquiposGimnasioPK(Equipo equipo, Gimnasio gimnasio) {
    super();
    this.equipo = equipo;
    this.gimnasio = gimnasio;
  }

  public Equipo getEquipo() {
    return equipo;
  }

  public void setEquipo(Equipo equipo) {
    this.equipo = equipo;
  }

  public Gimnasio getGimnasio() {
    return gimnasio;
  }

  public void setGimnasio(Gimnasio gimnasio) {
    this.gimnasio = gimnasio;
  }
}
