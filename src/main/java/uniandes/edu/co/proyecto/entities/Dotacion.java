package uniandes.edu.co.proyecto.entities;
import jakarta.persistence.*;

@Entity
@Table(name = "dotaciones")
public class Dotacion {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long iddotacion;
  private String nombre;
  private int cantidad;
  private int costoAdicional;

  public Dotacion() {
}

public Dotacion(String nombre, int cantidad, int costoAdicional) {
  this.nombre = nombre;
  this.cantidad = cantidad;
  this.costoAdicional = costoAdicional;
}

public Long getId() {
  return iddotacion;
}

public void setId(Long iddotacion) {
  this.iddotacion = iddotacion;
}

public String getNombre() {
  return nombre;
}

public void setNombre(String nombre) {
  this.nombre = nombre;
}

public int getCantidad() {
  return cantidad;
}

public void setCantidad(int cantidad) {
  this.cantidad = cantidad;
}

public int getCostoAdicional() {
  return costoAdicional;
}

public void setCostoAdicional(int costoAdicional) {
  this.costoAdicional = costoAdicional;
}

    
}
