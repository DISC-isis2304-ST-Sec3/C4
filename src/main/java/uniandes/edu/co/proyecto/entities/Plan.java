package uniandes.edu.co.proyecto.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "planes")
public class Plan {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long idPlan;
  private String tipo;
  private int costo;
  private int duracion;

  public Plan() {
}

public Plan(String tipo, int costo, int duracion){
  this.tipo = tipo;
  this.costo = costo;
  this.duracion = duracion;
}

public Long getIdPlan() {
  return idPlan;
}

public void setIdPlan(Long idPlan) {
  this.idPlan = idPlan;
}

public String getTipo() {
  return tipo;
}

public void setTipo(String tipo) {
  this.tipo = tipo;
}

public int getCosto() {
  return costo;
}

public void setCosto(int costo) {
  this.costo = costo;
}

public int getDuracion() {
  return duracion;
}

public void setDuracion(int duracion) {
  this.duracion = duracion;
}
}
