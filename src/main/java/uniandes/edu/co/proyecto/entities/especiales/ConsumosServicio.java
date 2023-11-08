package uniandes.edu.co.proyecto.entities.especiales;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ConsumosServicio {
  @Id
  @Column(name = "servicio_id")
  private Long id;

  @Column(name = "nombre")
  private String nombre;

  @Column(name = "cantidad_consumos")
  private long cantidadConsumos;

  public ConsumosServicio() {
  }

  public ConsumosServicio(Long id, String nombre, int cantidadConsumos) {
    this.id = id;
    this.nombre = nombre;
    this.cantidadConsumos = cantidadConsumos;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public long getCantidadConsumos() {
    return cantidadConsumos;
  }

  public void setCantidadConsumos(long cantidadConsumos) {
    this.cantidadConsumos = cantidadConsumos;
  }
}
