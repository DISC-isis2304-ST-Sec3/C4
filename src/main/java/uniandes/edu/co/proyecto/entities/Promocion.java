package uniandes.edu.co.proyecto.entities;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "promocion")
public class Promocion {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long idPromocion;

  @Temporal(TemporalType.TIME)
  private Date inicio;

  @Temporal(TemporalType.TIME)
  private Date fin;

  private String descripcion;
  private int rebaja;

  @ManyToOne
  @JoinColumn(name = "id_plan", referencedColumnName = "idPlan")
  private Plan plan;

  public Long getIdPromocion() {
    return idPromocion;
  }
  
  public void setIdPromocion(Long idPromocion) {
    this.idPromocion = idPromocion;
  }

  public Date getInicio() {
    return inicio;
  }

  public void setInicio(Date inicio) {
    this.inicio = inicio;
  }

  public Date getFin() {
    return fin;
  }

  public void setFin(Date fin) {
    this.fin = fin;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public int getRebaja() {
    return rebaja;
  }

  public void setRebaja(int rebaja) {
    this.rebaja = rebaja;
  }

  public Plan getPlan() {
    return plan;
  }
  
  public void setPlan(Plan plan) {
    this.plan = plan;
  }

}
