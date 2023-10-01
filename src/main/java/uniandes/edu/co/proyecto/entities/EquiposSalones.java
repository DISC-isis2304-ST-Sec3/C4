package uniandes.edu.co.proyecto.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import uniandes.edu.co.proyecto.entities.primarykeys.EquiposSalonesPK;

@Entity
@Table(name = "equipos_salones")
public class EquiposSalones {
  @EmbeddedId
  private EquiposSalonesPK equiposSalonesPK;

  public EquiposSalones() {
  }

  public EquiposSalones(Equipo equipo, Salon salon) {
    this.equiposSalonesPK = new EquiposSalonesPK(equipo, salon);
  }

  public EquiposSalonesPK getEquiposSalonesPK() {
    return equiposSalonesPK;
  }

  public void setEquiposSalonesPK(EquiposSalonesPK equiposSalonesPK) {
    this.equiposSalonesPK = equiposSalonesPK;
  }
}
