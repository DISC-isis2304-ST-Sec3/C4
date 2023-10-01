package uniandes.edu.co.proyecto.entities.primarykeys;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import uniandes.edu.co.proyecto.entities.Equipo;
import uniandes.edu.co.proyecto.entities.Salon;

import java.io.Serializable;

@Embeddable
public class EquiposSalonesPK implements Serializable {
  @ManyToOne
  @JoinColumn(name = "id_equipo", referencedColumnName = "id")
  private Equipo equipo;

  @ManyToOne
  @JoinColumn(name = "id_salon", referencedColumnName = "id")
  private Salon salon;

  public EquiposSalonesPK() {
  }

  public EquiposSalonesPK(Equipo equipo, Salon salon) {
    super();
    this.equipo = equipo;
    this.salon = salon;
  }

  public Equipo getEquipo() {
    return equipo;
  }

  public void setEquipo(Equipo equipo) {
    this.equipo = equipo;
  }

  public Salon getSalon() {
    return salon;
  }

  public void setSalon(Salon salon) {
    this.salon = salon;
  }
}
