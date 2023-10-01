package uniandes.edu.co.proyecto.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import uniandes.edu.co.proyecto.entities.primarykeys.EquiposGimnasioPK;

@Entity
@Table(name = "equipos_gimnasios")
public class EquiposGimnasios {
  @EmbeddedId
  private EquiposGimnasioPK equiposGimnasioPK;

  public EquiposGimnasios() {
  }

  public EquiposGimnasios(Equipo equipo, Gimnasio gimnasio) {
    this.equiposGimnasioPK = new EquiposGimnasioPK(equipo, gimnasio);
  }

  public EquiposGimnasioPK getEquiposGimnasioPK() {
    return equiposGimnasioPK;
  }

  public void setEquiposGimnasioPK(EquiposGimnasioPK equiposGimnasioPK) {
    this.equiposGimnasioPK = equiposGimnasioPK;
  }
}
