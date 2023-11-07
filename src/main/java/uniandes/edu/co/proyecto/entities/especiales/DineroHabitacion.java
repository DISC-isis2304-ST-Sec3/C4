package uniandes.edu.co.proyecto.entities.especiales;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class DineroHabitacion {
  @Id
  @Column(name="idhabitacion")
  private Long idHabitacion;

  private int costeTotal;

  private int r;

  public DineroHabitacion() {
  }

  public DineroHabitacion(Long idHabitacion, int costeTotal, int r) {
    this.idHabitacion = idHabitacion;
    this.costeTotal = costeTotal;
    this.r = r;
  }

  public Long getIdHabitacion() {
    return idHabitacion;
  }

  public void setIdHabitacion(Long idHabitacion) {
    this.idHabitacion = idHabitacion;
  }

  public int getCosteTotal() {
    return costeTotal;
  }

  public void setCosteTotal(int costeTotal) {
    this.costeTotal = costeTotal;
  }

  public int getR() {
    return r;
  }

  public void setR(int r) {
    this.r = r;
  }
}
