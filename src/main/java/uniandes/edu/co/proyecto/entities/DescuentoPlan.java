package uniandes.edu.co.proyecto.entities;

import jakarta.persistence.*;
import uniandes.edu.co.proyecto.entities.primarykeys.DescuentoPlanPK;

@Entity
@Table(name = "descuentos_Planes")
public class DescuentoPlan {

  @EmbeddedId
  private DescuentoPlanPK descuentoPlanPK;

  public DescuentoPlan() {
  }

  public DescuentoPlan(Descuento descuento, Plan plan) {
    this.descuentoPlanPK = new DescuentoPlanPK(descuento, plan);
  }

  public DescuentoPlanPK getDescuentoPlanPK() {
    return descuentoPlanPK;
  }

  public void setDescuentoPlanPK(DescuentoPlanPK descuentoPlanPK) {
    this.descuentoPlanPK = descuentoPlanPK;
  }

    
}
