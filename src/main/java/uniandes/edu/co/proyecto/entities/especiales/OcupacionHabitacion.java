package uniandes.edu.co.proyecto.entities.especiales;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class OcupacionHabitacion {
  @Id
  @Column(name = "idhabitacion")
  private Long id;

  private float ocupacion;

  @Column(name = "cantidad_reservas")
  private long cantidadReservas;

  public OcupacionHabitacion() {}

  public OcupacionHabitacion(Long id, float ocupacion, long cantidadReservas) {
    this.id = id;
    this.ocupacion = ocupacion;
    this.cantidadReservas = cantidadReservas;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public float getOcupacion() {
    return ocupacion;
  }

  public void setOcupacion(float ocupacion) {
    this.ocupacion = ocupacion;
  }

  public long getCantidadReservas() {
    return cantidadReservas;
  }

  public void setCantidadReservas(long cantidadReservas) {
    this.cantidadReservas = cantidadReservas;
  }
}
