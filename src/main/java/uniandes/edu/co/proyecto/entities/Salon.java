package uniandes.edu.co.proyecto.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "salones")
public class Salon {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String tipo;
  private int costo;
  private int capacidad;

  public Salon() {
  }

  public Salon(String tipo, int costo, int capacidad) {
    this.tipo = tipo;
    this.costo = costo;
    this.capacidad = capacidad;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public int getCapacidad() {
    return capacidad;
  }

  public void setCapacidad(int capacidad) {
    this.capacidad = capacidad;
  }
}
