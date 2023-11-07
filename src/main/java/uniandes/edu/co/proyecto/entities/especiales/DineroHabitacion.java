package uniandes.edu.co.proyecto.entities.especiales;

public class DineroHabitacion {
  private Long idHabitacion;
  private int costeTotal;

  public DineroHabitacion() {
  }

  public DineroHabitacion(Long idHabitacion, int costeTotal) {
    this.idHabitacion = idHabitacion;
    this.costeTotal = costeTotal;
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
}
