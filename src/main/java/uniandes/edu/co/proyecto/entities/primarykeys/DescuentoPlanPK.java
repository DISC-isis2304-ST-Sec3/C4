package uniandes.edu.co.proyecto.entities.primarykeys;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import uniandes.edu.co.proyecto.entities.Descuento;
import uniandes.edu.co.proyecto.entities.Plan;

public class DescuentoPlanPK {
  @ManyToOne
  @JoinColumn(name = "id_plan", referencedColumnName = "idPlan")
  private Plan plan;

  @ManyToOne
  @JoinColumn(name = "id_descuento", referencedColumnName = "idDescuento")
  private Descuento descuento;
  
  public DescuentoPlanPK() {
  }

  public DescuentoPlanPK(Descuento descuento, Plan plan) {
    super();
    this.plan = plan;
    this.descuento = descuento;
  }

  public Plan getPlan() {
    return plan;
  }
  
  public void setIdPlan(Plan plan) {
    this.plan = plan;
  }

  public Descuento getDescuento() {
    return descuento;
  }
  
  public void setDescuento(Descuento descuento) {
    this.descuento = descuento;
  }
}
